-- rooms
insert into rooms 
    (id, room_type, ada_accessible, standard_occupancy, max_occupancy, base_price, extra_price_per_person)
values
    (201,'Double',false,2,4,199.99,10),
    (202,'Double',true,2,4,174.99,10),
    (203,'Double',false,2,4,199.99,10),
    (204,'Double',true,2,4,174.99,10),
    (205,'Single',false,2,2,174.99,0),
    (206,'Single',true,2,2,149.99,0),
    (207,'Single',false,2,2,174.99,0),
    (208,'Single',true,2,2,149.99,0),
    (301,'Double',false,2,4,199.99,10),
    (302,'Double',true,2,4,174.99,10),
    (303,'Double',false,2,4,199.99,10),
    (304,'Double',true,2,4,174.99,10),
    (305,'Single',false,2,2,174.99,0),
    (306,'Single',true,2,2,149.99,0),
    (307,'Single',false,2,2,174.99,0),
    (308,'Single',true,2,2,149.99,0),
    (401,'Suite',true,3,8,399.99,20),
    (402,'Suite',true,3,8,399.99,20);

-- amenities
insert into amenities
    (id, amenity, price) 
values
    (1, 'Microwave', 0),
    (2, 'Refrigerator', 0),
    (3, 'Oven', 0),
    (4, 'Jacuzzi', 25.00);

-- rooms_amenities
insert into rooms_amenities
    (room_id, amenity_id)
values
	(201,1),
	(201,4),
	(202,2),
	(203,1),
	(203,4),
	(204,2),
	(205,1),
	(205,2),
	(205,4),
	(206,1),
	(206,2),
	(207,1),
	(207,2),
	(207,4),
	(208,1),
	(208,2),
	(301,1),
	(301,4),
	(302,2),
	(303,1),
	(303,4),
	(304,2),
	(305,1),
	(305,2),
	(305,4),
	(306,1),
	(306,2),
	(307,1),
	(307,2),
	(307,4),
	(308,1),
	(308,2),
	(401,1),
	(401,2),
	(401,3),
	(402,1),
	(402,2),
	(402,3);

-- guests
insert into guests 
    (id, first_name, last_name, street_address, city, state, zip_code, phone)
values 
    (1, 'Nardos', 'Tessema', '1600 Pennsylvania Avenue NW', 'Washington', 'DC', 20500, '(202) 456-1111'),
    (2, 'Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs', 'IA', 51501, '(291) 553-0508'),
    (3, 'Bettyann', 'Seery', '750 Wintergreen Dr.', 'Wasilla', 'AK', 99654, '(478) 277-9632'),
    (4, 'Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', 78552, '(308) 494-0198'),
    (5, 'Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', 08096, '(214) 730-0298'),
    (6, 'Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', 48601, '(377) 507-0974'),
    (7, 'Zachery', 'Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', 80003, '(814) 485-2615'),
    (8, 'Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion', 'IL', 60099, '(279) 491-0960'),
    (9, 'Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', 02864, '(446) 396-6785'),
    (10, 'Wilfred', 'Vise', '77 West Surrey Street', 'Oswego', 'NY', 13126, '(834) 727-1001'),
    (11, 'Maritza', 'Tilton', '939 Linda Rd.', 'Burke', 'VA', 22015, '(446) 351-6860'),
    (12, 'Joleen', 'Tison', '87 Queen St.', 'Drexel Hill', 'PA', 19026, '(231) 893-2755');

-- reservations
insert into reservations 
    (room_id,guest_id,adults,minors,checkin_date,checkout_date,total_cost)
values
    (308,2,1,0,'2023-2-2','2023-2-4',299.98),
    (203,3,2,1,'2023-2-5','2023-2-10',999.95),
    (305,4,2,0,'2023-2-22','2023-2-24',349.98),
    (201,5,2,2,'2023-3-6','2023-3-7',199.99),
    (307,1,1,1,'2023-3-17','2023-3-20',524.97),
    (302,6,3,0,'2023-3-18','2023-3-23',924.95),
    (202,7,2,2,'2023-3-29','2023-3-31',349.98),
    (304,8,2,0,'2023-3-31','2023-4-5',874.95),
    (301,9,1,0,'2023-4-9','2023-4-13',799.96),
    (207,10,1,1,'2023-4-23','2023-4-24',174.99),
    (401,11,2,4,'2023-5-30','2023-6-2',1199.97),
    (206,12,2,0,'2023-6-10','2023-6-14',599.96),
    (208,12,1,0,'2023-6-10','2023-6-14',599.96),
    (304,6,3,0,'2023-6-17','2023-6-18',184.99),
    (205,1,2,0,'2023-6-28','2023-7-2',699.96),
    (204,9,3,1,'2023-7-13','2023-7-14',184.99),
    (401,10,4,2,'2023-7-18','2023-7-21',1259.97),
    (303,3,2,1,'2023-7-28','2023-7-29',199.99),
    (305,3,1,0,'2023-8-30','2023-9-1',349.98),
    (208,2,2,0,'2023-9-16','2023-9-17',149.99),
    (203,5,2,2,'2023-9-13','2023-9-15',399.98),
    (401,4,2,2,'2023-11-22','2023-11-25',1199.97),
    (206,2,2,0,'2023-11-22','2023-11-25',449.97),
    (301,2,2,2,'2023-11-22','2023-11-25',599.97),
    (302,11,2,0,'2023-12-24','2023-12-28',699.96);

-- deleting 'Jeremiah Penderegrass and all his reservations'

-- Since the ON DELETE CASCADE referential action is set on the `guest_id` foreign key upon
-- creation of `reservations`, deleting a guest from the parent table (guest) will automatically
-- delete the reservations corresponding to that guest from the child table (reservations).
delete from guests where id = 8;