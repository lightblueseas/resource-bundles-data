/**
 * The MIT License
 *
 * Copyright (C) 2007 - 2015 Asterios Raptis
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *  *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *  *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package de.alpharogroup.db.resource.bundles.entities;

import de.alpharogroup.db.entity.enums.DatabasePrefix;
import de.alpharogroup.db.entity.name.NameEntity;
import de.alpharogroup.db.entity.name.versionable.VersionableNameUUIDEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

/**
 * The entity class {@link LanguageLocales} holds the data for the languages.
 */
@Entity
@Table(name = Languages.TABLE_NAME, uniqueConstraints = {
	@UniqueConstraint(name = DatabasePrefix.UNIQUE_CONSTRAINT_PREFIX + Languages.TABLE_NAME
		+ DatabasePrefix.UNDERSCORE
		+ NameEntity.COLUMN_NAME_NAME, columnNames = NameEntity.COLUMN_NAME_NAME),
	@UniqueConstraint(name = DatabasePrefix.UNIQUE_CONSTRAINT_PREFIX + Languages.TABLE_NAME
		+ DatabasePrefix.UNDERSCORE
		+ Languages.COLUMN_NAME_ISO_639_DASH1, columnNames = Languages.COLUMN_NAME_ISO_639_DASH1) }, indexes = {
	@Index(name = DatabasePrefix.INDEX_PREFIX + Languages.TABLE_NAME
		+ DatabasePrefix.UNDERSCORE
		+ NameEntity.COLUMN_NAME_NAME, columnList = NameEntity.COLUMN_NAME_NAME),
	@Index(name = DatabasePrefix.INDEX_PREFIX + Languages.TABLE_NAME
		+ DatabasePrefix.UNDERSCORE
		+ Languages.COLUMN_NAME_ISO_639_DASH1, columnList = Languages.COLUMN_NAME_ISO_639_DASH1) })
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder
public class Languages extends VersionableNameUUIDEntity implements Cloneable
{

	public static final String COLUMN_NAME_ISO_639_DASH1 = "iso639_1";

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;
	public static final String TABLE_NAME = "languages";

	/** The iso639_1 code with two characters. */
	@Column(name = "iso639_1", length = 2)
	String iso639Dash1;

}

