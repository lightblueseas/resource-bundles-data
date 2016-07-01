
    create table basenames (
        id int4 not null,
        version int4,
        name varchar(512) unique,
        primary key (id)
    );
create table bundlenames (
        id int4 not null,
        version int4,
        base_name_id int4,
        locale_id int4,
        primary key (id)
    );
create table default_locale_basenames (
        id int4 not null,
        version int4,
        bundlename_id int4,
        default_locale_id int4,
        primary key (id)
    );
create table language_locales (
        id int4 not null,
        version int4,
        locale varchar(64) unique,
        primary key (id)
    );
create table properties_keys (
        id int4 not null,
        version int4,
        name varchar(1024) unique,
        primary key (id)
    );
create table resourcebundles (
        id int4 not null,
        version int4,
        value varchar(2048),
        bundlename_id int4,
        properties_key_id int4,
        primary key (id)
    );


alter table bundlenames add constraint FKF230A806D4CC327E foreign key (locale_id) references language_locales;
alter table bundlenames add constraint FKF230A80663C76715 foreign key (base_name_id) references basenames;
alter table default_locale_basenames add constraint FKC87181B017DEE600 foreign key (default_locale_id) references language_locales;
alter table default_locale_basenames add constraint FKC87181B0BE71D570 foreign key (bundlename_id) references bundlenames;
alter table resourcebundles add constraint FKD0A7106365054731 foreign key (properties_key_id) references properties_keys;
alter table resourcebundles add constraint FKD0A71063BE71D570 foreign key (bundlename_id) references bundlenames;
create sequence hibernate_sequence;
