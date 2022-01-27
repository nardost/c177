-- data standards from:
-- https://webarchive.nationalarchives.gov.uk/ukgwa/20100407173424/http://www.cabinetoffice.gov.uk/govtalk/schemasstandards/e-gif/datastandards.aspx

-- rooms table
drop table if exists rooms;
create table rooms (
    id integer primary key,
    room_type enum ('Single', 'Double', 'Suite') not null,
    ada_accessible boolean not null,
    standard_occupancy integer not null,
    max_occupancy integer not null,
    base_price decimal(6, 2) not null,
    extra_price_per_person decimal(6, 2) not null
);

-- guests table
drop table if exists guests;
create table guests (
    id integer primary key auto_increment,
    first_name varchar(35) not null,
    last_name varchar(35) not null,
    date_of_birth date,
    phone varchar(15) not null,
    street_address varchar(46) not null,
    city varchar(48) not null,
    state varchar(2) not null,
    zip_code integer
);

-- amenities table
drop table if exists amenities;
create table amenities (
    id integer primary key auto_increment,
    amenity varchar(32) not null,
    price decimal(6, 2) not null
);

-- rooms_amenities table
drop table if exists rooms_amenities;
create table rooms_amenities (
    room_id integer not null,
    amenity_id integer not null,
    primary key (room_id, amenity_id),
    foreign key (room_id) references rooms(id) on delete cascade,
    foreign key (amenity_id) references amenities(id) on delete cascade
);

-- reservations table
drop table if exists reservations;
create table reservations (
    id integer primary key auto_increment,
    guest_id integer not null,
    room_id integer not null,
    checkin_date datetime not null,
    checkout_date datetime,
    adults integer not null,
    minors integer not null,
    total_cost decimal(8, 2),
    foreign key (guest_id) references guests(id) on delete cascade,
    foreign key (room_id) references rooms(id) on delete cascade
);

-- bills view
drop view if exists bills;
create view bills
as
select
	G.id as guest_id,
	first_name,
	last_name,
	R.id as room_number,
	checkin_date,
	checkout_date,
    datediff(checkout_date, checkin_date) as days_stayed,
	case
		when (adults - R.standard_occupancy) < 0 then 0
		else (adults - R.standard_occupancy)
	end as extra_guests,
	(
		(
			select sum(price)
			from rooms
			inner join rooms_amenities on rooms.id = room_id
			inner join amenities on amenities.id = amenity_id
			where rooms.id = R.id
			group by rooms.id
		) + 
        R.base_price + 
        R.extra_price_per_person * (
			case
				when (adults - R.standard_occupancy) < 0 then 0
				else (adults - R.standard_occupancy)
			end
		)
	) * datediff(checkout_date, checkin_date) as total_cost
from rooms R
inner join reservations V on R.id = room_id
inner join guests G on G.id = V.guest_id
order by last_name, first_name, checkout_date desc, checkin_date desc;

