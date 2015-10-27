package de.alpharogroup.db.resource.bundles.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import de.alpharogroup.db.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/** 
 * Entity class for saving resource bundles in database.
 */
@Entity
@Table(name="resourcebundles")
@Getter
@Setter
@NoArgsConstructor
public class Resourcebundles 
extends BaseEntity<Integer>
implements Cloneable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;	
	/** The bundle name. */
	@Column( name = "base_name", length = 1024  )
	private String baseName;
	/** The locale of this entry. */
	@Column( length = 64  )
	private String locale;	
	/** The properties key. */
	@Column(name = "properties_key", length = 1024  )
	private String key;	
	/** The value for the properties key. */
	@Column( name="value", length = 2048  )
	private String value;	
}