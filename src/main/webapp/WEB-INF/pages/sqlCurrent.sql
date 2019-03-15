create database flyway;

create table flyway_schema_history
(
  installed_rank integer                 not null
    constraint flyway_schema_history_pk
    primary key,
  version        varchar(50),
  description    varchar(200)            not null,
  type           varchar(20)             not null,
  script         varchar(1000)           not null,
  checksum       integer,
  installed_by   varchar(100)            not null,
  installed_on   timestamp default now() not null,
  execution_time integer                 not null,
  success        boolean                 not null
);

create index flyway_schema_history_s_idx
  on flyway_schema_history (success);

create table senders
(
  sender_id     serial       not null
    constraint pk_senders
    primary key,
  project_id    integer
    constraint senders_projects_project_id_fk
    references projects
    on delete cascade,
  email_from    varchar(255) not null,
  location_name varchar(255) not null,
  report_rate   bigint       not null,
  sample_rate   bigint       not null,
  launch_time   bigint       not null,
  serial_number varchar(255) not null,
  battery_level integer      not null,
  status        varchar(50)  not null
);

create table sensors
(
  sensor_id        serial       not null
    constraint pk_sensors
    primary key,
  sender_id        integer      not null,
  location_name    varchar(255) not null,
  memory_mode      varchar(255) not null,
  auto_record_rate integer      not null,
  id_auto_record   boolean      not null,
  log_type         varchar(255),
  sensor_type      varchar(255) not null,
  serial_number    varchar(255) not null,
  battery_level    integer      not null,
  total_memory     integer      not null,
  used_memory      integer      not null,
  status           varchar(50)  not null,
  launch_time      bigint       not null,
  baralogger_id    integer,
  zero_point       integer,
  zero_point_value numeric(10, 2),
  index_number     integer      not null
);

create table measurements
(
  sensor_id      integer        not null,
  measuring_time timestamp      not null,
  type           integer        not null,
  value          numeric(10, 2) not null,
  correleation   numeric(10, 2),
  constraint sensor_type_time
  unique (measuring_time, sensor_id, type)
);

create table emailservers
(
  email_server_id       serial       not null
    constraint pk_emailservers
    primary key,
  server_name           varchar(255) not null,
  email                 varchar(255) not null,
  password              varchar(255) not null,
  server_protocol       integer      not null,
  server_prot           integer,
  ssl_enable            boolean      not null,
  last_receive_date     bigint,
  last_error_time       bigint,
  outcoming_server_name varchar(255) not null,
  outcoming_email       varchar(255),
  outcoming_password    varchar(255),
  outcoming_server_prot integer,
  outcoming_ssl_enable  boolean      not null
);

create table latestmeasurements
(
  sensor_id    integer        not null,
  timestamp    bigint         not null,
  type         integer        not null,
  value        numeric(10, 2) not null,
  correleation numeric(10, 2),
  constraint sensor_measurement_type_unit
  unique (sensor_id, type)
);

create table sensormeasurementtypes
(
  sensor_id       integer not null,
  type            integer not null,
  unit            integer not null,
  last_value_time bigint  not null,
  constraint sensor_measurement_type
  unique (sensor_id, type)
);

create table changetablelogs
(
  log_id     serial       not null
    constraint pk_changetablelogs
    primary key,
  table_name varchar(255) not null,
  pk         integer,
  new_values text         not null,
  type_log   integer      not null,
  timestamp  bigint       not null
);

create table companies
(
  company_id   serial       not null
    constraint pk_companies
    primary key,
  company_name varchar(255) not null
);

create table alerts
(
  alert_id             serial         not null
    constraint pk_alerts
    primary key,
  type_device          integer        not null,
  type_measurement     integer        not null,
  min_value            numeric(10, 2) not null,
  max_value            numeric(10, 2) not null,
  device_id            integer        not null,
  is_application_alert boolean        not null,
  is_sound_alert       boolean        not null,
  emails_list          text           not null,
  last_update          bigint         not null
);

create table reports
(
  report_id   serial       not null
    constraint pk_reports
    primary key,
  report_name varchar(255) not null,
  sensors     varchar(255) not null,
  types       varchar(12)  not null,
  last_update bigint       not null
);

create table externalpressuremeasurement
(
  compensation_id integer        not null,
  value           numeric(10, 2) not null,
  measuring_time  timestamp      not null,
  constraint external_pressure_measurement
  unique (compensation_id, measuring_time)
);

create table barometriccompensation
(
  compensation_id serial         not null
    constraint pk_barometriccompensation
    primary key,
  pressure        numeric(7, 2)  not null,
  longitude       numeric(12, 9) not null,
  latitude        numeric(12, 9) not null,
  last_update     bigint         not null,
  location        varchar(100)   not null,
  is_external     boolean        not null,
  internal_id     integer
);

create table emailstosend
(
  email_id     serial       not null
    constraint pk_emailstosend
    primary key,
  email        varchar(255) not null,
  title        varchar(255) not null,
  body         text         not null,
  content_type varchar(100) not null,
  is_sent      boolean      not null,
  time_created timestamp    not null,
  time_sent    timestamp
);

create table settingschange
(
  settings_change_id        serial  not null
    constraint pk_settingschange
    primary key,
  sender_id                 integer not null,
  need_sender_update        boolean not null,
  need_sensor_one_update    boolean not null,
  need_sensor_second_update boolean not null,
  last_update               bigint  not null,
  changed                   boolean not null
);

create table users
(
  user_id     serial       not null
    constraint pk_users
    primary key,
  user_name   varchar(255) not null,
  user_type   integer      not null,
  password    varchar(255) not null,
  date_create timestamp    not null,
  is_active   boolean      not null,
  email       varchar(255)
);

create table notificationalerts
(
  notification_alert_id serial         not null
    constraint pk_notificationalerts
    primary key,
  alert_id              integer        not null,
  device_id             integer        not null,
  current_value         numeric(10, 2) not null,
  time_start            timestamp      not null,
  time_notified         timestamp,
  time_end              timestamp,
  notified              boolean        not null
);

create table license
(
  license_id       serial       not null
    constraint pk_license
    primary key,
  license_type     varchar(255) not null,
  license_key_hash varchar(255) not null,
  date_until       timestamp    not null,
  sign             varchar(255) not null
);

create table mapservice
(
  service_id   serial       not null
    constraint pk_map_id
    primary key,
  service_name varchar(255) not null
);

create table weatherconfig
(
  id             serial not null
    constraint pk_weatherconf_id
    primary key,
  weathertoken   varchar(255),
  consumerkey    varchar(255),
  consumersecret varchar(255),
  cachetimeout   integer,
  ratecheck      integer
);

create table weatherservice
(
  service_id   serial       not null
    constraint pk_weather_id
    primary key,
  service_name varchar(255) not null,
  config_id    integer
    constraint fk_config_id
    references weatherconfig
);

create table projects
(
  project_id serial  not null
    constraint pk_projects
    primary key,
  company_id integer not null,
  longitude  numeric(12, 9),
  latitude   numeric(12, 9),
  weather_id integer
    constraint fk_weather_id
    references weatherservice,
  map_id     integer
    constraint projects_mapservice_service_id_fk
    references mapservice
);

create table weathercompanies
(
  companies_id integer not null
    constraint weather_companies_companies_companies_id_fk
    references companies,
  weather_id   integer not null
    constraint weather_companies_weatherservice_service_id_fk
    references weatherservice,
  id_config    integer
    constraint weathercompanies_weatherconfig_id_fk
    references weatherconfig,
  constraint weather_project_project_id_weather_id_pk
  primary key (companies_id, weather_id)
);

create unique index weathercompanies_id_config_uindex
  on weathercompanies (id_config);

create table mapcompanies
(
  companies_id integer not null
    constraint map_companie_companies_companie_id_fk
    references companies,
  map_id       integer not null
    constraint map_companies_mapservice_service_id_fk
    references mapservice,
  constraint weather_project_project_id_map_id_pk
  primary key (companies_id, map_id)
);

create table userproject
(
  id_user integer not null
    constraint user_project_users_user_id_fk
    references users,
  id_proj integer not null
    constraint user_project_projects_project_id_fk
    references projects,
  constraint user_project_id_user_id_proj_pk
  primary key (id_user, id_proj)
);

create table projectcompanies
(
  id_project   integer not null
    constraint projectcompanies_projects_project_id_fk
    references projects
    on delete cascade,
  id_companies integer not null
    constraint projectcompanies_companies_company_id_fk
    references companies
    on delete cascade,
  constraint projectcompanies_id_project_id_companies_pk
  primary key (id_project, id_companies)
);
