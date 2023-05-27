--CREATE ADDITIONAL ATTRIBUTE TABLE
create sequence properties_ms.additional_attributes_id_seq
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 100
	cache 1
	NO CYCLE;

create TABLE properties_ms.additional_attributes (
	id bigserial NOT NULL,
	furnished boolean default false,
	typology varchar(255),
	type varchar(255),
	appliances text[],
	images text[],
	CONSTRAINT additional_attributes_pkey PRIMARY KEY (id)
);

--CREATE PROPERTIES ADDRESS TABLE
create sequence properties_ms.property_address_id_seq
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 100
	cache 1
	NO CYCLE;

create TABLE properties_ms.property_address (
	id bigserial NOT NULL,
	country varchar(100),
	city varchar(100),
	"zone" varchar(100),
	address varchar(200),
	floor_number int4,
	door_number int4,
	street varchar(254),
	CONSTRAINT property_address_pkey PRIMARY KEY (id)
);

--CREATE OWNER TABLE
create sequence properties_ms.owner_id_seq
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 100
	cache 1
	NO CYCLE;

create TABLE properties_ms.owner (
    id bigserial NOT NULL,
    username varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    start_date TIMESTAMP NULL,
    CONSTRAINT owner_pkey PRIMARY KEY (id)
);

--CREATE TENANT TABLE
create sequence properties_ms.tenant_id_seq
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 100
	cache 1
	NO CYCLE;

create TABLE properties_ms.tenant (
    id bigserial NOT NULL,
    username varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    start_date TIMESTAMP NULL,
    CONSTRAINT tenant_pkey PRIMARY KEY (id)
);

--CREATE PROPERTY TABLE
create sequence properties_ms.properties_id_seq
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 100
	cache 1
	NO CYCLE;

create TABLE properties_ms.properties (
	id bigserial NOT NULL,
    description varchar(254),
    long_description text,
    "size" float4,
    unit varchar(25),
    for_sale boolean default false,
    for_rent boolean default false,
    price float4,
    year int4,
    additional_attributes int8 NULL,
    property_address int8 NULL,
    owner int8 NOT NULL,
    tenant int8 NULL,
    rented boolean default false,
    sold boolean default false,
    creation_date TIMESTAMP NULL,
    last_updated_date TIMESTAMP NULL,
	CONSTRAINT properties_pkey PRIMARY KEY (id),
	CONSTRAINT properties_additional_attributes_fkey FOREIGN KEY (additional_attributes) REFERENCES additional_attributes(id),
    CONSTRAINT properties_property_address_fkey FOREIGN KEY (property_address) REFERENCES property_address(id),
    CONSTRAINT properties_owner_fkey FOREIGN KEY (owner) REFERENCES owner(id),
    CONSTRAINT properties_tenant_fkey FOREIGN KEY (tenant) REFERENCES tenant(id)
);
