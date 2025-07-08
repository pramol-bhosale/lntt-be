create table if not exists public.schedules(
id varchar(36) not null default gen_random_uuid(),
from_date date not null,
to_date date not null,
from_time time not null,
to_time time not null,
user_id varchar(36) not null,
activity_id varchar(36) not null,
description text,
created_on timestamp not null default now(),
updated_on timestamp,
constraint pk_schedules_id primary key(id),
constraint fk_schedules_users foreign key(user_id) references public.users(id),
constraint fk_schedules_activities foreign key(activity_id) references public.activities(id)
);