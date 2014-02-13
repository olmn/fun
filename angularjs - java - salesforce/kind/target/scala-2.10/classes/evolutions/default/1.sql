# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table question (
  id                        integer not null,
  question                  TEXT,
  constraint pk_question primary key (id))
;

create table respondent (
  id                        integer not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  city                      varchar(255),
  age                       varchar(255),
  gender                    varchar(255),
  constraint pk_respondent primary key (id))
;

create table response (
  id                        integer not null,
  question_id               integer,
  respondent_id             integer,
  question                  TEXT,
  response                  TEXT,
  constraint pk_response primary key (id))
;

create sequence question_seq;

create sequence respondent_seq;

create sequence response_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists question;

drop table if exists respondent;

drop table if exists response;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists question_seq;

drop sequence if exists respondent_seq;

drop sequence if exists response_seq;

