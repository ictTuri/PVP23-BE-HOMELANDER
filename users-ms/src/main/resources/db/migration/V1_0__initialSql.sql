--CREATE TENANTS SLOTS TABLE
CREATE SEQUENCE users_ms.tenants_slots_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 100
	CACHE 1
	NO CYCLE;

CREATE TABLE users_ms.tenants_slots (
	id bigserial NOT NULL,
	available integer NOT NULL,
	used integer NOT NULL,
	CONSTRAINT tenants_slots_pkey PRIMARY KEY (id)
);

--CREATE PROPERTIES SLOTS TABLE
CREATE SEQUENCE users_ms.properties_slots_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 100
	CACHE 1
	NO CYCLE;

CREATE TABLE users_ms.properties_slots (
	id bigserial NOT NULL,
	available integer NOT NULL,
	used integer NOT NULL,
	CONSTRAINT properties_slots_pkey PRIMARY KEY (id)
);

--CREATE ADDRESS TABLE
CREATE SEQUENCE users_ms.address_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 100
	CACHE 1
	NO CYCLE;

CREATE TABLE users_ms.address (
	id bigserial NOT NULL,
	country varchar(255) NULL,
	city varchar(255) NULL,
	address_one varchar(255) NULL,
	address_two varchar(255) NULL,
	zip_code varchar(255) NULL,
	street varchar(255) NULL,
	CONSTRAINT address_pkey PRIMARY KEY (id)
);

--CREATE USERS TABLE
CREATE SEQUENCE users_ms.users_id_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 100
	CACHE 1
	NO CYCLE;

CREATE TABLE users_ms.users (
	id bigserial NOT NULL,
	username varchar(255) NOT NULL,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	email varchar(255) NOT NULL,
	phone varchar(255) NULL,
	superuser boolean NOT NULL DEFAULT FALSE,
	password varchar(255) NULL,
	tenants_slots int8 NULL,
	properties_slots int8 NULL,
	address int8 NULL,
	CONSTRAINT users_pkey PRIMARY KEY (id),
	CONSTRAINT users_tenants_slots_fkey FOREIGN KEY (tenants_slots) REFERENCES tenants_slots(id),
	CONSTRAINT users_properties_slots_fkey FOREIGN KEY (properties_slots) REFERENCES properties_slots(id),
	CONSTRAINT users_address_fkey FOREIGN KEY (address) REFERENCES address(id)
);