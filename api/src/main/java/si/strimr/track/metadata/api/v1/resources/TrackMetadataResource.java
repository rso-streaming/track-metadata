package si.strimr.track.metadata.api.v1.resources;

//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import si.strimr.track.metadata.models.entities.TrackMetadata;
import si.strimr.track.metadata.services.TrackMetadataBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;


@RequestScoped
@Path("/track-metadata")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TrackMetadataResource {

    @Inject
    private TrackMetadataBean trackMetadataBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    @Path("/filtered")
    public Response getTrackMetadataFiltered() {

        List<TrackMetadata> trackMetadata;

        trackMetadata = trackMetadataBean.getTrackMetadataFilter(uriInfo);

        return Response.status(Response.Status.OK).entity(trackMetadata).build();
    }

    @GET
    @Path("/{trackId}")
    public Response getTrackMetadata(@PathParam("trackId") Integer trackId) {

        TrackMetadata trackMetadata = trackMetadataBean.getTrackMetadata(trackId);

        if (trackMetadata == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(trackMetadata).build();
    }

    @POST
    public Response createTrackMetadata(TrackMetadata trackMetadata) {

        if ((trackMetadata.getTrackName() == null
                || trackMetadata.getTrackName().isEmpty())
                || (trackMetadata.getAuthorName() == null
                || trackMetadata.getAuthorName().isEmpty())) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            trackMetadata = trackMetadataBean.createTrackMetadata(trackMetadata);
        }

        if (trackMetadata.getId() != null) {
            return Response.status(Response.Status.CREATED).entity(trackMetadata).build();
        } else {
            return Response.status(Response.Status.CONFLICT).entity(trackMetadata).build();
        }
    }

    @PUT
    @Path("{trackId}")
    public Response putTrackMetadata(@PathParam("trackId") String trackId, TrackMetadata trackMetadata) {

        trackMetadata = trackMetadataBean.putTrackMetadata(trackId, trackMetadata);

        if (trackMetadata == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            if (trackMetadata.getId() != null)
                return Response.status(Response.Status.OK).entity(trackMetadata).build();
            else
                return Response.status(Response.Status.NOT_MODIFIED).build();
        }
    }

    @DELETE
    @Path("{trackId}")
    public Response deleteTrackMetadata(@PathParam("trackId") String trackId) {

        boolean deleted = trackMetadataBean.deleteTrackMetadata(trackId);

        if (deleted) {
            return Response.status(Response.Status.GONE).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
