insert into pilot (id, account, hashed_password, phone_number, name, email, institution)
values (1101, 'ShawnWu', '$2a$10$oaw/0Z2Xlj5776BnQT5Xx.8byWVE6fwTUD2mFA3kfo2tw9lnHinXm',
'0912345678', 'ShawnWu', 'swshawnwu@gmail.com', 'NUTN');

insert into uav (id, weight , mac_address, axes_number, body_color, flight_control, owner)
values (2201, 1450, '0000000018021025', 4, 'Black', 'pixhawks', 1101);

insert into uav (id, weight , mac_address, axes_number, body_color, flight_control, owner)
values (2203, 1450, '0000000018120225', 4, 'Black', 'pixhawks', 1101);


insert into flight_plan(id,  execution_date, start_time, max_flying_altitude, end_time, flight_plan_waypoints, description, uav)
values (3301, '2020-09-27', '00:00:00', 20, '00:10:00',
'{
    "coordinate": [
          [
            120.27145385742186,
            22.62557821612849
          ],
          [
            120.2838134765625,
            22.592299675703206
          ],
          [
            120.30303955078124,
            22.582314544433036
          ],
          [
            120.27162551879884,
            22.575815895599796
          ],
          [
            120.26870727539062,
            22.60751372151609
          ]
        ]
    }', 'ShawnWu~~~ first flight', 2201);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (4001, '2020-09-27', '01:12:00', '',  22.605453584593423, 120.26561737060545, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3301);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (4002, '2020-09-27', '01:12:05', '',  22.587386447606395, 120.27454376220703, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3301);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)

values (4003, '2020-09-27', '01:12:05', '',  22.574072303523796, 120.27179718017578, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3301);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (4004, '2020-09-27', '01:12:08', '',  22.5791445101221, 120.29067993164064, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3301);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (4005, '2020-09-27', '01:12:11', '',  22.58104653946133, 120.30269622802734, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3301);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (4006, '2020-09-27', '01:12:13', '',  22.587069459129385, 120.29891967773438, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3301);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (4007, '2020-09-27', '01:12:15', '',  22.589605346517473, 120.28587341308592, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3301);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (4008, '2020-09-27', '01:12:17', '',  22.59752969362172, 120.2838134765625, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3301);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (4009, '2020-09-27', '01:12:19', '',  22.619398524467492, 120.27111053466797, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3301);




insert into flight_plan(id,  execution_date, start_time, max_flying_altitude, end_time, flight_plan_waypoints, description, uav)
values (3302, '2190-11-27', '00:15:00', 20, '06:35:00',
'{
    "coordinate": [
          [
            120.32604217529295,
            22.632866726168892
          ],
          [
            120.34955978393555,
            22.643798766430393
          ],
          [
            120.37050247192383,
            22.62637046420206
          ],
          [
            120.34389495849608,
            22.616546265412556
          ],
          [
            120.36500930786134,
            22.60624594859848
          ],
          [
            120.3761672973633,
            22.611633902823524
          ]

          ]
        }', 'ShawnWu first flight', 2201);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5001, '2020-09-27', '04:02:00', '',  22.634530353625664, 120.32587051391602, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3302);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5002, '2020-09-27', '04:02:10', '',  22.638095201780473, 120.3427791595459, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3302);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5003, '2020-09-27', '04:02:15', '',  22.644353267028727, 120.34852981567381, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3302);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5004, '2020-09-27', '04:02:20', '',  22.63817441957783, 120.35891532897948, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3302);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5005, '2020-09-27', '04:02:25', '',  22.627004259373486, 120.36784172058105, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3302);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5006, '2020-09-27', '04:02:30', '',  22.61908160971607, 120.35307884216307, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3302);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5007, '2020-09-27', '04:02:35', '',  22.614644726512385, 120.34518241882323, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3302);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5008, '2020-09-27', '04:02:40', '',  22.60814760359453, 120.35865783691406, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3302);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5009, '2020-09-27', '04:02:45', '',  22.60672136481142, 120.36792755126953, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3302);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)

values (5010, '2020-09-27', '04:02:50', '',  22.611950834736213, 120.37436485290527, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3302);


insert into flight_plan(id,  execution_date, start_time, max_flying_altitude, end_time, flight_plan_waypoints, description, uav)
values (3303, '2020-09-27', '02:50:00', 20, '05:30:00',
'{
    "coordinate": [
          [
            120.40225982666014,
            22.600065388365888
          ],
          [
            120.358829498291,
            22.556001050606962
          ],
          [
            120.40088653564453,
            22.53285370752713
          ],
          [
            120.38166046142578,
            22.58532600946538
          ]
        ]
    }', 'ShawnWu first flight', 2201);



insert into flight_plan(id,  execution_date, start_time, max_flying_altitude, end_time, flight_plan_waypoints, description, uav)
values (3310, '2020-10-02', '01:00:00', 20, '05:39:00',

'{
    "coordinate": [
          [
            120.40221231232666014,
            22.6001231238365888
          ],
          [
            120.35812312398291,
            22.5561231231206962
          ],
          [
            120.400123123564453,
            22.5328123123752713
          ],
          [
            120.381123456442578,
            22.5853123123946538
          ]
        ]
    }', 'ShawnWu first flight', 2201);



insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)

values (5101, '2020-10-02', '01:31:00', '',  22.902995442724, 120.27282178401946, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3310);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5102, '2020-10-02', '01:31:03', '',  22.9028076650445, 120.27309536933899, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3310);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5103, '2020-10-02', '01:31:06', '',  22.902422225835192, 120.27315437793733, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3310);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5104, '2020-10-02', '01:31:09', '',  22.90212573339032, 120.27316510677338, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3310);

insert into flight_data(id, date, time, hex_data_packet, latitude, longitude, altitude, gyro_x, gyro_y, gyro_z, acc_x, acc_y, acc_z, ned_north, ned_east, ned_down, pitch, yaw, roll, flight_plan)
values (5105, '2020-10-02', '01:31:12', '',  22.901908305185454, 120.27303099632262, 20, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 3310);


insert into flight_plan(id,  execution_date, start_time, max_flying_altitude, end_time, flight_plan_waypoints, description, uav)
values (3311, '2020-10-01', '23:50:00', 20, '23:59:00',

'{
    "coordinate": [
          [
            120.40225982666014,
            22.600065388365888
          ],
          [
            120.358829498291,
            22.556001050606962
          ],
          [
            120.40088653564453,
            22.53285370752713
          ],
          [
            120.38166046142578,
            22.58532600946538
          ]
        ]
    }', 'ShawnWu first flight', 2201);
