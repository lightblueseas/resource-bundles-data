package de.alpharogroup.db.resource.bundles.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.alpharogroup.db.entity.name.unique.ExtraSmallUNameBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 
 * Entity class for saving in database languages.
 */
@Entity
@Table(name="languages")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Languages 
extends ExtraSmallUNameBaseEntity<Integer>
implements Cloneable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;

	/** The iso639_1 code with two characters. */
	@Column(name = "iso639_1", length = 2)
	private String iso639Dash1;	

	/**
	 * Instantiates a new {@link Languages} entity object.
	 *
	 * @param name the name
	 * @param iso639Dash1 the iso 639 dash 1
	 */
	@Builder
	Languages(String name, String iso639Dash1) {
		this.iso639Dash1 = iso639Dash1;
	}
}
