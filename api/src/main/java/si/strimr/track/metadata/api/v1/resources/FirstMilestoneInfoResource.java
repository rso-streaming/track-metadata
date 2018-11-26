package si.strimr.track.metadata.api.v1.resources;


import si.strimr.track.metadata.services.properties.AppProperties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/demo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FirstMilestoneInfoResource {

    @Inject
    private AppProperties appProperties;

    @GET
    @Path("/info")
    public Response getInfo() {

        JsonObject json = Json.createObjectBuilder()
                .add("clani", Json.createArrayBuilder()
                        .add("zc6857")
                        .add("mm3058"))
                .add("opis_projekta", "Nas projekt implementira aplikacijo streamanje glasbe.")
                .add("mikrostoritve", Json.createArrayBuilder()
                        .add(appProperties.getEndpointZc())
                        .add(appProperties.getEndpointMm()))
                .add("github", Json.createArrayBuilder()
                        .add("https://github.com/strimr/track-data")
                        .add("https://github.com/strimr/track-metadata"))
                .add("travis", Json.createArrayBuilder()
                        .add("https://travis-ci.org/strimr/track-data")
                        .add("https://travis-ci.org/strimr/track-metadata"))
                .add("dockerhub", Json.createArrayBuilder()
                        .add("https://hub.docker.com/r/strimr/track-data")
                        .add("https://hub.docker.com/r/strimr/track-metadata"))
                .build();

        return Response.ok(json.toString()).build();
    }

}
