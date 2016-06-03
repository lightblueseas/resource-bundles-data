alter table bundlenames drop constraint FKF230A806D4CC327E;
alter table default_locale_basename_map drop constraint FKD0DB6E4017DEE600;
alter table default_locale_basename_map drop constraint FKD0DB6E40BE71D570;
alter table resourcebundles drop constraint FKD0A71063BE71D570;
drop table bundlenames;
drop table default_locale_basename_map;
drop table language_locales;
drop table resourcebundles;
drop sequence hibernate_sequence;
