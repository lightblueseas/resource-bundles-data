package de.alpharogroup.db.resource.bundles.mapper;

import org.springframework.stereotype.Component;

import de.alpharogroup.db.entitymapper.AbstractEntityDOMapper;
import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.db.resource.bundles.entities.BundleNames;

/**
 * The class {@link BundleNamesMapper}.
 */
@Component
public class BundleNamesMapper extends AbstractEntityDOMapper<BundleNames, BundleName> {
}