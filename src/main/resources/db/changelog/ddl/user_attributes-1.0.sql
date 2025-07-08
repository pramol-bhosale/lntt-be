create table if not exists public.user_attributes(
id varchar(36) not null default gen_random_uuid(),
name varchar(100) not null,
value text not null,
user_id varchar(36) not null,
constraint pk_userattributesid primary key(id),
constraint uq_userattributesnameuserid unique(name, user_id),
constraint fk_userattributes_user foreign key(user_id) references public.users(id)
)