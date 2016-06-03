
    create table bundlenames (
        id int4 not null,
        version int4,
        base_name varchar(1024),
        locale_id int4,
        primary key (id)
    );
create table default_locale_basename_map (
        id int4 not null,
        version int4,
        bundlename_id int4,
        default_locale_id int4,
        primary key (id)
    );
create table language_locales (
        id int4 not null,
        version int4,
        locale varchar(64),
        primary key (id)
    );
create table resourcebundles (
        id int4 not null,
        version int4,
        properties_key varchar(1024),
        value varchar(2048),
        bundlename_id int4,
        primary key (id)
    );


alter table bundlenames add constraint FKF230A806D4CC327E foreign key (locale_id) references language_locales;
alter table default_locale_basename_map add constraint FKD0DB6E4017DEE600 foreign key (default_locale_id) references language_locales;
alter table default_locale_basename_map add constraint FKD0DB6E40BE71D570 foreign key (bundlename_id) references bundlenames;
alter table resourcebundles add constraint FKD0A71063BE71D570 foreign key (bundlename_id) references bundlenames;
create sequence hibernate_sequence;
