# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table alumno (
  n_alumno_id               integer auto_increment not null,
  t_codigo                  varchar(255),
  t_nombre                  varchar(255),
  t_apellido_paterno        varchar(255),
  t_apellido_materno        varchar(255),
  t_curso                   varchar(255),
  t_grupo                   varchar(255),
  n_docente_id              integer,
  constraint pk_alumno primary key (n_alumno_id))
;

create table curso (
  n_curso_id                integer auto_increment not null,
  t_nombre                  varchar(255),
  n_grupo                   varchar(255),
  n_docente_id              integer,
  constraint pk_curso primary key (n_curso_id))
;

create table docente (
  n_docente_id              integer auto_increment not null,
  t_codigo                  integer,
  t_nombre                  varchar(255),
  t_apellido_paterno        varchar(255),
  t_apellido_materno        varchar(255),
  constraint pk_docente primary key (n_docente_id))
;

create table evaluacion (
  n_evaluacion_id           integer auto_increment not null,
  titulo                    varchar(255),
  tiempo                    varchar(255),
  curso                     varchar(255),
  grupo                     varchar(255),
  descripcion               varchar(255),
  n_docente_id              integer,
  constraint pk_evaluacion primary key (n_evaluacion_id))
;

create table usuario (
  n_usuario_id              integer auto_increment not null,
  t_usuario                 varchar(255),
  t_password                varchar(255),
  rep_password              varchar(255),
  n_docente_id              integer,
  constraint pk_usuario primary key (n_usuario_id))
;

alter table alumno add constraint fk_alumno_docente_1 foreign key (n_docente_id) references docente (n_docente_id) on delete restrict on update restrict;
create index ix_alumno_docente_1 on alumno (n_docente_id);
alter table curso add constraint fk_curso_docente_2 foreign key (n_docente_id) references docente (n_docente_id) on delete restrict on update restrict;
create index ix_curso_docente_2 on curso (n_docente_id);
alter table evaluacion add constraint fk_evaluacion_docente_3 foreign key (n_docente_id) references docente (n_docente_id) on delete restrict on update restrict;
create index ix_evaluacion_docente_3 on evaluacion (n_docente_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table alumno;

drop table curso;

drop table docente;

drop table evaluacion;

drop table usuario;

SET FOREIGN_KEY_CHECKS=1;

