package de.alpharogroup.db.resource.bundles.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import de.alpharogroup.db.entity.version.VersionableBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** 
 * Entity class for saving resource bundles in database.
 */
@Entity
@Table(name="resourcebundles")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
public class Resourcebundles 
extends VersionableBaseEntity<Integer>
implements Cloneable {

	/** Serial Version UID */
	private static final long serialVersionUID = 1L;

	/** The bundleName from this {@link BundleNames} object. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "bundlename_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_RESOURCEBUNDLES_BUNDLENAME_ID"))
	private BundleNames bundleName;
	
	/** The properties key from this {@link BundleNames} object. */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "properties_key_id", nullable = true, referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_RESOURCEBUNDLES_PROPERTIES_KEY_ID"))
	private PropertiesKeys key;
	
	/** The value for the properties key. */
	@Column( name="value", length = 2048  )
	private String value;	
}