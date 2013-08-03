create table es_role (
  id number(20) primary key,
  name varchar2(100) constraint uq_role_name unique not null,
  description varchar2(4000)
);
create table es_user (
  id number(20) primary key,
  role_id constraint fk_role_id references es_role(id) not null,
  login varchar2(20) constraint uq_user_login unique not null,
  password varchar(20) not null,
  email varchar2(100) constraint uq_user_email unique not null,
  biography clob,
  description varchar2(4000),
  constraint ck_user_email_pattern check (regexp_like(email, '\w+@\w+(\.\w+)+')),
  constraint ck_user_login_pattern check (regexp_like(login, '\D+\w+'))
);
create index ix_user_01 on es_user(role_id);
create sequence hibernate_sequence increment by 1 nocache nocycle;
