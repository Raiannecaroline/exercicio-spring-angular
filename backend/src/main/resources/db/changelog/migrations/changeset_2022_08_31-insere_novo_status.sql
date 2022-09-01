-- liquibase formatted sql
-- changeset sombriks:changeset_2022_08_31-insere_novo_status.sql

insert into estados_documento (id, nome)
values (3, 'encerrado')