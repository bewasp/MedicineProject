/*
Created: 4/8/2020
Modified: 12/10/2020
Model: PostgreSQL 11
Database: PostgreSQL 11
*/

-- Create schemas section -------------------------------------------------

CREATE SCHEMA "medicine"
;

-- Create tables section -------------------------------------------------

-- Table medicine.client_personal

CREATE TABLE "medicine"."client_personal"
(
  "id_client" Bigint NOT NULL,
  "email" Character varying(100) NOT NULL,
  "name" Character varying(20) NOT NULL,
  "surname" Character varying(20) NOT NULL,
  "phone_number" Character varying(20) NOT NULL,
  "password" Character varying(20) NOT NULL,
  "activated_email" Boolean DEFAULT false NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "medicine"."client_personal" ADD CONSTRAINT "PK_client_personal" PRIMARY KEY ("id_client")
;

-- Table medicine.cure

CREATE TABLE "medicine"."cure"
(
  "id_cure" Bigint NOT NULL,
  "name" Character varying(40) NOT NULL,
  "daily_of_dose" Integer NOT NULL,
  "dose_timestamp" Integer NOT NULL,
  "number_of_dose" Integer NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "medicine"."cure" ADD CONSTRAINT "PK_cure" PRIMARY KEY ("id_cure")
;

-- Table medicine.dose_of_client

CREATE TABLE "medicine"."dose_of_client"
(
  "id_cure" Bigint NOT NULL,
  "id_client" Bigint NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "medicine"."dose_of_client" ADD CONSTRAINT "PK_dose_of_client" PRIMARY KEY ("id_cure","id_client")
;

-- Table medicine.accepted_dose

CREATE TABLE "medicine"."accepted_dose"
(
  "id_client" Bigint NOT NULL,
  "id_cure" Bigint NOT NULL,
  "date" Date NOT NULL,
  "accepted" Boolean NOT NULL,
  "delayed" Boolean
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "medicine"."accepted_dose" ADD CONSTRAINT "PK_accepted_dose" PRIMARY KEY ("id_client","id_cure")
;

-- Table medicine.tokens

CREATE TABLE "medicine"."tokens"
(
  "id_client" Bigint NOT NULL,
  "token" Text NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "medicine"."tokens" ADD CONSTRAINT "PK_tokens" PRIMARY KEY ("id_client")
;

-- Table medicine.links

CREATE TABLE "medicine"."links"
(
  "id_client" Bigint NOT NULL,
  "link" Text NOT NULL
)
WITH (
  autovacuum_enabled=true)
;

ALTER TABLE "medicine"."links" ADD CONSTRAINT "PK_links" PRIMARY KEY ("id_client")
;

-- Create foreign keys (relationships) section -------------------------------------------------

ALTER TABLE "medicine"."dose_of_client"
  ADD CONSTRAINT "Relationship3"
    FOREIGN KEY ("id_cure")
    REFERENCES "medicine"."cure" ("id_cure")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "medicine"."dose_of_client"
  ADD CONSTRAINT "Relationship5"
    FOREIGN KEY ("id_client")
    REFERENCES "medicine"."client_personal" ("id_client")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "medicine"."accepted_dose"
  ADD CONSTRAINT "Relationship8"
    FOREIGN KEY ("id_client")
    REFERENCES "medicine"."client_personal" ("id_client")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "medicine"."accepted_dose"
  ADD CONSTRAINT "Relationship9"
    FOREIGN KEY ("id_cure")
    REFERENCES "medicine"."cure" ("id_cure")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "medicine"."tokens"
  ADD CONSTRAINT "Relationship10"
    FOREIGN KEY ("id_client")
    REFERENCES "medicine"."client_personal" ("id_client")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

ALTER TABLE "medicine"."links"
  ADD CONSTRAINT "Relationship11"
    FOREIGN KEY ("id_client")
    REFERENCES "medicine"."client_personal" ("id_client")
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
;

