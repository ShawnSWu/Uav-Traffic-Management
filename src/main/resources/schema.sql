drop table if exists trajectory_point;
drop table if exists flight_plan;
drop table if exists uav, pilot;

create table pilot
(
    id              integer      not null auto_increment,
    account         varchar(128) not null unique,
    hashed_password varchar(64)  not null,
    phone_number    varchar(20)  not null,
    name            varchar(50)  not null,
    email           varchar(128) not null,
    institution     varchar(255) not null,
    primary key (id)
);

create table uav
(
    id             integer      not null auto_increment,
    weight         double       not null,
    mac_address    varchar(255) not null unique,
    axes_number    integer      not null,
    body_color     varchar(20)  not null,
    flight_control varchar(255) not null,
    owner          integer      not null,
    primary key (id),
    FOREIGN KEY (owner) REFERENCES pilot (id) On delete cascade
);


create table flight_plan
(
    id                    integer      not null auto_increment,
    execution_date        date         not null,
    start_time            time         not null,
    max_flying_altitude   int          not null,
    end_time              time         not null,
    flight_plan_waypoints json         not null,
    description           varchar(255) not null,
    uav                   integer      not null,
    primary key (id),
    FOREIGN KEY (uav) REFERENCES uav (id)
);


create table trajectory_point
(
    id              integer          not null auto_increment,
    date            date             not null,
    time            time             not null,
    hex_data_packet varchar(250)     not null,
    latitude        double precision not null,
    longitude       double precision not null,
    altitude        double          not null,
    gyro_x          double precision not null,
    gyro_y          double precision not null,
    gyro_z          double precision not null,
    acc_x           double precision not null,
    acc_y           double precision not null,
    acc_z           double precision not null,
    ned_north       double precision,
    ned_east        double precision,
    ned_down        double precision,
    pitch           double precision,
    yaw             double precision,
    roll            double precision,
    flight_plan     integer          not null,
    primary key (id),
    FOREIGN KEY (flight_plan) REFERENCES flight_plan (id) ON DELETE CASCADE ON UPDATE CASCADE

);