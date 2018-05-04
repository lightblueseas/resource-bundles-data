
MERGE INTO language_locales (id, version, locale) VALUES (1,1,'en');
MERGE INTO language_locales (id, version, locale) VALUES (2,1,'en_GB');
MERGE INTO language_locales (id, version, locale) VALUES (3,1,'en_US');
MERGE INTO language_locales (id, version, locale) VALUES (4,1,'de');
MERGE INTO language_locales (id, version, locale) VALUES (5,1,'de_AT');
MERGE INTO language_locales (id, version, locale) VALUES (6,1,'de_CH');
MERGE INTO language_locales (id, version, locale) VALUES (7,1,'de_DE');
MERGE INTO language_locales (id, version, locale) VALUES (8,1,'es');
MERGE INTO language_locales (id, version, locale) VALUES (9,1,'es_ES');
MERGE INTO language_locales (id, version, locale) VALUES (10,1,'fr');
MERGE INTO language_locales (id, version, locale) VALUES (11,1,'fr_BE');
MERGE INTO language_locales (id, version, locale) VALUES (12,1,'fr_CH');
MERGE INTO language_locales (id, version, locale) VALUES (13,1,'fr_FR');
MERGE INTO language_locales (id, version, locale) VALUES (14,1,'it');
MERGE INTO language_locales (id, version, locale) VALUES (15,1,'it_IT');
MERGE INTO language_locales (id, version, locale) VALUES (16,1,'nl');
MERGE INTO language_locales (id, version, locale) VALUES (17,1,'nl_BE');
MERGE INTO language_locales (id, version, locale) VALUES (18,1,'nl_NL');

MERGE INTO bundle_applications (id, version, name, default_locale_id) VALUES (1, 1, 'base-bundle-application', 1);

MERGE INTO basenames (id, version, name) VALUES (1,1,'base-resource-bundles');
MERGE INTO basenames (id, version, name) VALUES (2,1,'test');

MERGE INTO bundlenames (id, version, base_name_id, locale_id, owner_id) VALUES (1, 1, 1, 4, 1);
MERGE INTO bundlenames (id, version, base_name_id, locale_id, owner_id) VALUES (2, 1, 1, 2, 1);
MERGE INTO bundlenames (id, version, base_name_id, locale_id, owner_id) VALUES (3, 1, 2, 7, 1);
MERGE INTO bundlenames (id, version, base_name_id, locale_id, owner_id) VALUES (4, 1, 2, 3, 1);

MERGE INTO properties_keys (id, version, name) VALUES (1, 1, 'resource.bundles.test.label');
MERGE INTO properties_keys (id, version, name) VALUES (2, 1, 'com.example.gui.window.buttons.save');
MERGE INTO properties_keys (id, version, name) VALUES (3, 1, 'com.example.gui.window.title');
MERGE INTO properties_keys (id, version, name) VALUES (4, 1, 'com.example.gui.prop.with.params.label');
MERGE INTO properties_keys (id, version, name) VALUES (5, 1, 'com.example.gui.window.buttons.cancel');

MERGE INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (1, 1, 1, 'Erstes label', 1);
MERGE INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (2, 1, 1, 'First label', 2);
MERGE INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (3, 1, 2, 'Speichern', 3);
MERGE INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (4, 1, 3, 'Hallo, dort!', 3);
MERGE INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (5, 1, 4, 'Hallo ich bin {0} und komme aus {1}.', 3);
MERGE INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (6, 1, 5, 'Abbrechen', 3);
MERGE INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (7, 1, 2, 'Save', 4);
MERGE INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (8, 1, 3, 'Hello, there!', 4);
MERGE INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (9, 1, 4, 'Hello i am {0} and i come from {1}.', 4);
MERGE INTO resourcebundles (id, version, properties_key_id, value, bundlename_id) VALUES (10, 1, 5, 'Cancel', 4);
