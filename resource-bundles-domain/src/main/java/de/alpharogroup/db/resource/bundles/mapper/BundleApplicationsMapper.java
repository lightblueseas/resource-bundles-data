package de.alpharogroup.db.resource.bundles.mapper;

import org.springframework.stereotype.Component;

import de.alpharogroup.db.entitymapper.AbstractEntityDOMapper;
import de.alpharogroup.db.resource.bundles.domain.BundleApplication;
import de.alpharogroup.db.resource.bundles.entities.BundleApplications;

/**
 * The class {@link BundleApplicationsMapper}.
 */
@Component
public class BundleApplicationsMapper extends AbstractEntityDOMapper<BundleApplications, BundleApplication> {
}