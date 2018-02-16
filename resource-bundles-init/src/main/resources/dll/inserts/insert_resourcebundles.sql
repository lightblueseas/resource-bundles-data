INSERT INTO language_locales (id, version, locale) VALUES (1,1,'en');
INSERT INTO language_locales (id, version, locale) VALUES (2,1,'en_GB');
INSERT INTO language_locales (id, version, locale) VALUES (3,1,'en_US');
INSERT INTO language_locales (id, version, locale) VALUES (4,1,'de');
INSERT INTO language_locales (id, version, locale) VALUES (5,1,'de_AT');
INSERT INTO language_locales (id, version, locale) VALUES (6,1,'de_CH');
INSERT INTO language_locales (id, version, locale) VALUES (7,1,'de_DE');
INSERT INTO language_locales (id, version, locale) VALUES (8,1,'es');
INSERT INTO language_locales (id, version, locale) VALUES (9,1,'es_ES');
INSERT INTO language_locales (id, version, locale) VALUES (10,1,'fr');
INSERT INTO language_locales (id, version, locale) VALUES (11,1,'fr_BE');
INSERT INTO language_locales (id, version, locale) VALUES (12,1,'fr_CH');
INSERT INTO language_locales (id, version, locale) VALUES (13,1,'fr_FR');
INSERT INTO language_locales (id, version, locale) VALUES (14,1,'it');
INSERT INTO language_locales (id, version, locale) VALUES (15,1,'it_IT');
INSERT INTO language_locales (id, version, locale) VALUES (16,1,'nl');
INSERT INTO language_locales (id, version, locale) VALUES (17,1,'nl_BE');
INSERT INTO language_locales (id, version, locale) VALUES (18,1,'nl_NL');

INSERT INTO bundle_applications (id, version, name, default_locale_id) VALUES (1, 1, 'AwesomeApp', 1);

INSERT INTO basenames (id, version, name) VALUES (1,1,'base-resource-bundles');
INSERT INTO basenames (id, version, name) VALUES (2,1,'test');

INSERT INTO bundlenames (id, version, base_name_id, locale_id, owner_id) VALUES (1, 1, 1, 1, 1);
INSERT INTO bundlenames (id, version, base_name_id, locale_id, owner_id) VALUES (2, 1, 1, 2, 1);
INSERT INTO bundlenames (id, version, base_name_id, locale_id, owner_id) VALUES (3, 1, 2, 1, 1);
INSERT INTO bundlenames (id, version, base_name_id, locale_id, owner_id) VALUES (4, 1, 2, 3, 1);

INSERT INTO properties_keys (id, version, name) VALUES (1, 1, 'resource.bundles.test.label');
INSERT INTO properties_keys (id, version, name) VALUES (2, 1, 'com.example.gui.window.buttons.save');
INSERT INTO properties_keys (id, version, name) VALUES (3, 1, 'com.example.gui.window.title');
INSERT INTO properties_keys (id, version, name) VALUES (4, 1, 'com.example.gui.prop.with.params.label');
INSERT INTO properties_keys (id, version, name) VALUES (5, 1, 'com.example.gui.window.buttons.cancel');

INSERT INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (1, 1, 1, 'Erstes label', 1);
INSERT INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (2, 1, 1, 'First label', 2);
INSERT INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (3, 1, 2, 'Speichern', 3);
INSERT INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (4, 1, 3, 'Hallo, dort!', 3);
INSERT INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (5, 1, 4, 'Hallo ich bin {0} und komme aus {1}.', 3);
INSERT INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (6, 1, 5, 'Abbrechen', 3);
INSERT INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (7, 1, 2, 'Save', 4);
INSERT INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (8, 1, 3, 'Hello, there!', 4);
INSERT INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (9, 1, 4, 'Hello i am {0} and i come from {1}.', 4);
INSERT INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (10, 1, 5, 'Cancel', 4);
