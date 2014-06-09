# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table docente (
  n_docente_id              integer auto_increment not null,
  t_codigo                  integer,
  t_nombre                  varchar(255),
  t_apellido_paterno        varchar(255),
  t_apellido_materno        varchar(255),
  constraint pk_docente primary key (n_docente_id))
;

create table usuario (
  n_usuario_id              integer auto_increment not null,
  t_usuario                 varchar(255),
  t_password                varchar(255),
  rep_password              varchar(255),
  n_docente_id              integer,
  constraint pk_usuario primary key (n_usuario_id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table docente;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;

