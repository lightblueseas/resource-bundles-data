package de.alpharogroup.db.resource.bundles.rest.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.alpharogroup.db.resource.bundles.domain.BundleName;
import de.alpharogroup.service.rs.RestfulResource;

/**
 * The interface {@link BundleNamesResource} provides rest methods
 */
@Path("/bundle/names/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface BundleNamesResource extends RestfulResource<java.lang.Integer, BundleName>
{
}