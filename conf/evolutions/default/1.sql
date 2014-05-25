# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_category primary key (id))
;

create table task (
  id                        bigint not null,
  label                     varchar(255),
  done                      boolean,
  category_id               bigint,
  due_date                  timestamp,
  constraint pk_task primary key (id))
;

create sequence category_seq;

create sequence task_seq;

alter table task add constraint fk_task_category_1 foreign key (category_id) references category (id) on delete restrict on update restrict;
create index ix_task_category_1 on task (category_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists category;

drop table if exists task;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists category_seq;

drop sequence if exists task_seq;

