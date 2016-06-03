alter table bundlenames add constraint FKF230A806D4CC327E foreign key (locale_id) references language_locales;
alter table default_locale_basename_map add constraint FKD0DB6E4017DEE600 foreign key (default_locale_id) references language_locales;
alter table default_locale_basename_map add constraint FKD0DB6E40BE71D570 foreign key (bundlename_id) references bundlenames;
alter table resourcebundles add constraint FKD0A71063BE71D570 foreign key (bundlename_id) references bundlenames;
create sequence hibernate_sequence;
