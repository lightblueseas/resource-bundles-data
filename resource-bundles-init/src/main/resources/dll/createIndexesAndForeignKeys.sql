alter table bundle_application_language_locales add constraint FKC6BB891EFB766A66 foreign key (language_locales_id) references language_locales;
alter table bundle_application_language_locales add constraint FKC6BB891E64F45D92 foreign key (application_id) references bundle_applications;
alter table bundle_applications add constraint FK176B41C017DEE600 foreign key (default_locale_id) references language_locales;
alter table bundlenames add constraint FKF230A806D4CC327E foreign key (locale_id) references language_locales;
alter table bundlenames add constraint FKF230A80614CE514F foreign key (owner_id) references bundle_applications;
alter table bundlenames add constraint FKF230A80663C76715 foreign key (base_name_id) references basenames;
alter table resourcebundles add constraint FKD0A7106365054731 foreign key (properties_key_id) references properties_keys;
alter table resourcebundles add constraint FKD0A71063BE71D570 foreign key (bundlename_id) references bundlenames;
create sequence hibernate_sequence;
