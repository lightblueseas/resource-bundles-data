package de.alpharogroup.db.resource.bundles.mapper;

import org.springframework.stereotype.Component;

import de.alpharogroup.db.entitymapper.AbstractEntityDOMapper;
import de.alpharogroup.db.resource.bundles.domain.Language;
import de.alpharogroup.db.resource.bundles.entities.Languages;

/**
 * The class {@link LanguagesMapper}.
 */
@Component
public class LanguagesMapper extends AbstractEntityDOMapper<Languages, Language> {
}