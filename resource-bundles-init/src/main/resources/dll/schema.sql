create table bundlenames (id int4 not null, version int4, base_name varchar(1024), locale_id int4, primary key (id));
create table default_locale_basename_map (id int4 not null, version int4, bundlename_id int4, default_locale_id int4, primary key (id));
create table language_locales (id int4 not null, version int4, locale varchar(64), primary key (id));
create table resourcebundles (id int4 not null, version int4, properties_key varchar(1024), value varchar(2048), bundlename_id int4, primary key (id));
