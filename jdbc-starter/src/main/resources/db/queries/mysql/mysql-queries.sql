-- 1. Write a query that returns a list of reservations that end in July 2023, including the name of the guest, the room number(s), and the reservation dates.
select 
	concat(first_name, ' ', last_name) as "NAME", 
	room_id as "ROOM #",
	checkin_date as "CHECKIN DATE", 
	checkout_date as "CHECKOUT DATE"
from reservations
inner join guests on guests.id = guest_id
where checkout_date between str_to_date('20230701', '%Y%m%d') and str_to_date('20230731', '%Y%m%d')
order by checkout_date;
/*
--      NAME      | ROOM # |      CHECKIN DATE      |     CHECKOUT DATE      
-- ----------------+--------+------------------------+------------------------
-- Nardos Tessema |    205 | 2023-06-28 00:00:00-05 | 2023-07-02 00:00:00-05
-- Walter Holaway |    204 | 2023-07-13 00:00:00-05 | 2023-07-14 00:00:00-05
-- Wilfred Vise   |    401 | 2023-07-18 00:00:00-05 | 2023-07-21 00:00:00-05
-- Bettyann Seery |    303 | 2023-07-28 00:00:00-05 | 2023-07-29 00:00:00-05
-- (4 rows)
*/

-- 2. Write a query that returns a list of all reservations for rooms with a jacuzzi, displaying the guest's name, the room number, and the dates of the reservation.
select 
	rooms.id as "ROOM #",
	concat(guests.first_name, ' ',guests.last_name) as "NAME",
	reservations.checkin_date as "CHECKIN DATE",
	reservations.checkout_date as "CHECKOUT_DATE"
from rooms
inner join rooms_amenities on rooms.id = rooms_amenities.room_id
inner join amenities on rooms_amenities.amenity_id = amenities.id
inner join reservations on reservations.room_id = rooms.id
inner join guests on guests.id = reservations.guest_id
where amenity = 'Jacuzzi'
order by rooms.id;

/*
--  ROOM # |      NAME      |      CHECKIN DATE      |     CHECKOUT_DATE      
-- --------+----------------+------------------------+------------------------
--     201 | Karie Yang     | 2023-03-06 00:00:00-06 | 2023-03-07 00:00:00-06
--     203 | Bettyann Seery | 2023-02-05 00:00:00-06 | 2023-02-10 00:00:00-06
--     203 | Karie Yang     | 2023-09-13 00:00:00-05 | 2023-09-15 00:00:00-05
--     205 | Nardos Tessema | 2023-06-28 00:00:00-05 | 2023-07-02 00:00:00-05
--     207 | Wilfred Vise   | 2023-04-23 00:00:00-05 | 2023-04-24 00:00:00-05
--     301 | Walter Holaway | 2023-04-09 00:00:00-05 | 2023-04-13 00:00:00-05
--     301 | Mack Simmer    | 2023-11-22 00:00:00-06 | 2023-11-25 00:00:00-06
--     303 | Bettyann Seery | 2023-07-28 00:00:00-05 | 2023-07-29 00:00:00-05
--     305 | Duane Cullison | 2023-02-22 00:00:00-06 | 2023-02-24 00:00:00-06
--     305 | Bettyann Seery | 2023-08-30 00:00:00-05 | 2023-09-01 00:00:00-05
--     307 | Nardos Tessema | 2023-03-17 00:00:00-05 | 2023-03-20 00:00:00-05
-- (11 rows)
*/

-- 3. Write a query that returns all the rooms reserved for a specific guest, including the guest's name, the room(s) reserved, the starting date of the reservation, and how many people were included in the reservation. (Choose a guest's name from the existing data.)
select
	concat(first_name, ' ', last_name) as "NAME",
	room_id as "ROOM #",
	checkin_date as "CHECKIN DATE",
	(adults + minors) as "NUMBER OF PEOPLE"
from guests
inner join reservations on guests.id = guest_id
where concat(first_name, ' ', last_name) = 'Mack Simmer'
order by checkin_date desc;

/*
--     NAME     | ROOM # |      CHECKIN DATE      | NUMBER OF PEOPLE 
-- -------------+--------+------------------------+------------------
--  Mack Simmer |    206 | 2023-11-22 00:00:00-06 |                2
--  Mack Simmer |    301 | 2023-11-22 00:00:00-06 |                4
--  Mack Simmer |    208 | 2023-09-16 00:00:00-05 |                2
--  Mack Simmer |    308 | 2023-02-02 00:00:00-06 |                1
-- (4 rows)
*/
-- 4. Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. The results should include all rooms, whether or not there is a reservation associated with the room.
select
	rooms.id as "ROOM #",
	reservations.id as "RESERVATION ID",
	base_price as "BASE PRICE"
from rooms
left join reservations on rooms.id = reservations.room_id
order by rooms.id;
/*
--  ROOM # | RESERVATION ID | BASE PRICE 
-- --------+----------------+------------
--     201 |              4 |     199.99
--     202 |              7 |     174.99
--     203 |              2 |     199.99
--     203 |             21 |     199.99
--     204 |             16 |     174.99
--     205 |             15 |     174.99
--     206 |             23 |     149.99
--     206 |             12 |     149.99
--     207 |             10 |     174.99
--     208 |             13 |     149.99
--     208 |             20 |     149.99
--     301 |              9 |     199.99
--     301 |             24 |     199.99
--     302 |              6 |     174.99
--     302 |             25 |     174.99
--     303 |             18 |     199.99
--     304 |             14 |     174.99
--     305 |             19 |     174.99
--     305 |              3 |     174.99
--     306 |           null |     149.99
--     307 |              5 |     174.99
--     308 |              1 |     149.99
--     401 |             22 |     399.99
--     401 |             17 |     399.99
--     401 |             11 |     399.99
--     402 |           null |     399.99
-- (26 rows)
*/

-- 5. Write a query that returns all the rooms accommodating at least three guests and that are reserved on any date in April 2023.
-- Jeremiah Pendergrass was deleted previously...
select
	rooms.id as "ROOM #",
	(minors + adults) as "NUMBER OF GUESTS",
	checkin_date as "CHECKIN_DATE",
	checkout_date as "CHECKOUT_DATE"
from rooms
inner join reservations on rooms.id = room_id
where
	checkout_date between str_to_date('20230401', '%Y%m%d') and str_to_date('20230430', '%Y%m%d')
	and
	(minors + adults) > 2
order by rooms.id;

/*
--  ROOM # | NUMBER OF GUESTS | CHECKIN_DATE | CHECKOUT_DATE 
-- --------+------------------+--------------+---------------
-- (0 rows)
*/

-- 6. Write a query that returns a list of all guest names and the number of reservations per guest, sorted starting with the guest with the most reservations and then by the guest's last name.
select
	concat(first_name, ' ', last_name) as "NAME",
	count(reservations.id) as "NUMBER OF RESERVATIONS"
from guests
inner join reservations on guests.id = guest_id
group by guests.id
order by count(reservations.id) desc, last_name;

/*
--         NAME         | NUMBER OF RESERVATIONS 
-- ---------------------+------------------------
--  Mack Simmer         |                      4
--  Bettyann Seery      |                      3
--  Duane Cullison      |                      2
--  Walter Holaway      |                      2
--  Aurore Lipton       |                      2
--  Nardos Tessema      |                      2
--  Maritza Tilton      |                      2
--  Joleen Tison        |                      2
--  Wilfred Vise        |                      2
--  Karie Yang          |                      2
--  Zachery Luechtefeld |                      1
-- (11 rows)
*/

-- 7. Write a query that displays the name, address, and phone number of a guest based on their phone number. (Choose a phone number from the existing data.)
select
	concat(first_name, ' ', last_name) as "NAME",
	concat(street_address, ', ', city, ', ', state, ' ', zip_code) as "ADDRESS",
	phone as "PHONE NUMBER"
from guests
where phone='(231) 893-2755';

/*
--      NAME     |               ADDRESS               |  PHONE NUMBER  
-- --------------+-------------------------------------+----------------
--  Joleen Tison | 87 Queen St., Drexel Hill, PA 19026 | (231) 893-2755
-- (1 row)
*/
