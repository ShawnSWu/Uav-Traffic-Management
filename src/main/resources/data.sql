insert into pilot (id, account, hashed_password, phone_number, name, email, institution)
values (1101, 'ShawnWu', '$2a$10$oaw/0Z2Xlj5776BnQT5Xx.8byWVE6fwTUD2mFA3kfo2tw9lnHinXm',
'0912345678', 'ShawnWu', 'swshawnwu@gmail.com', 'NUTN');


insert into uav (id, weight , mac_address, axes_number, body_color, flight_control, owner)
values (2201, 1450, '0000000018021025', 4, 'Black', 'pixhawks', 1101);


insert into flight_plan(id,  execution_date, start_time, max_flying_altitude, end_time, flight_plan_waypoints, description, uav)
values (3301, '2020-09-10', '14:00:00', 20, '23:00:00',
'{
    "coordinate": [
            [
              120.281781,
              22.617574
            ],
            [
              120.280219,
              22.618252
            ],
            [
              120.279195,
              22.619339
            ],
            [
              120.280108,
              22.620315
            ],
            [
              120.283327,
              22.618851
            ],
            [
              120.283855,
              22.617654
            ],
            [
              120.283155,
              22.617386
            ]
          ]
        }', 'ShawnWu~~~ first flight', 2201);

insert into flight_plan(id,  execution_date, start_time, max_flying_altitude, end_time, flight_plan_waypoints, description, uav)
values (3302, '2020-09-10', '21:00:00', 20, '23:10:00',
'{
    "coordinate": [
            [
              120.281781,
              22.617574
            ],
            [
              120.280219,
              22.618252
            ],
            [
              120.279195,
              22.619339
            ],
            [
              120.280108,
              22.620315
            ],
            [
              120.283327,
              22.618851
            ],
            [
              120.283855,
              22.617654
            ],
            [
              120.283155,
              22.617386
            ]
          ]
        }', 'ShawnWu first flight', 2201);

