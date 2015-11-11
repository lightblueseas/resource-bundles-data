create table resourcebundles (id int4 not null, base_name varchar(1024), properties_key varchar(1024), locale varchar(64), value varchar(2048), primary key (id));
