create table if not exists public.activities (
id varchar(36) not null default gen_random_uuid(),
name varchar(255) not null ,
description varchar(255),
created_on timestamp not null default now(),
updated_on timestamp,
constraint pk_activities primary key (id),
constraint uq_activitiesname unique (name)
);