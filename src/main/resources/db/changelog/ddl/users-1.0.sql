create table if not exists public.users(
id          varchar(36)     not null default gen_random_uuid(),
email       varchar(100)    not null,
username    varchar(100)    not null,
password    text    not null,
enabled     bool    not null default true,
created_on  timestamp  not null default now(),
updated_on  timestamp,
constraint  pk_usersid primary key(id),
constraint  uq_usersemail unique(email),
constraint  uq_usersusername unique(username)
);