INSERT INTO language_locales (id, version, locale) VALUES (1,1,'de_DE');
INSERT INTO language_locales (id, version, locale) VALUES (2,1,'UK');
INSERT INTO language_locales (id, version, locale) VALUES (3,1,'en_GB');

INSERT INTO bundlenames (id, version, base_name, locale_id) VALUES (1, 1,'base-resource-bundles', 1);
INSERT INTO bundlenames (id, version, base_name, locale_id) VALUES (2,1,'base-resource-bundles', 2);
INSERT INTO bundlenames (id, version, base_name, locale_id) VALUES (3,1,'test', 1);
INSERT INTO bundlenames (id, version, base_name, locale_id) VALUES (4,1,'test', 3);

INSERT INTO resourcebundles (id, version, properties_key, value, bundlename_id) VALUES (1, 1, 'resource.bundles.test.label', 'Erstes label', 1);
INSERT INTO resourcebundles (id, version, properties_key, value, bundlename_id) VALUES (2, 1, 'resource.bundles.test.label', 'First label', 2);
INSERT INTO resourcebundles (id, version, properties_key, value, bundlename_id) VALUES (3, 1, 'com.example.gui.window.buttons.save', 'Speichern', 3);
INSERT INTO resourcebundles (id, version, properties_key, value, bundlename_id) VALUES (4, 1, 'com.example.gui.window.title', 'Hallo, dort!', 3);
INSERT INTO resourcebundles (id, version, properties_key, value, bundlename_id) VALUES (5, 1, 'com.example.gui.prop.with.params.label', 'Hallo ich bin {0} und komme aus {1}.', 3);
INSERT INTO resourcebundles (id, version, properties_key, value, bundlename_id) VALUES (6, 1, 'com.example.gui.window.buttons.cancel', 'Abbrechen', 3);
INSERT INTO resourcebundles (id, version, properties_key, value, bundlename_id) VALUES (7, 1, 'com.example.gui.window.buttons.save', 'Save', 4);
INSERT INTO resourcebundles (id, version, properties_key, value, bundlename_id) VALUES (8, 1, 'com.example.gui.window.title', 'Hello, there!', 4);
INSERT INTO resourcebundles (id, version, properties_key, value, bundlename_id) VALUES (9, 1, 'com.example.gui.prop.with.params.label', 'Hello i am {0} and i come from {1}.', 4);
INSERT INTO resourcebundles (id, version, properties_key, value, bundlename_id) VALUES (10, 1, 'com.example.gui.window.buttons.cancel', 'Cancel', 4);