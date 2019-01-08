package si.strimr.track.metadata.services;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.strimr.track.metadata.models.entities.TrackMetadata;
import si.strimr.track.metadata.services.deezer.DeezerClient;
import si.strimr.track.metadata.services.deezer.models.SearchResponse;
import si.strimr.track.metadata.services.deezer.models.SearchResponseData;
import si.strimr.track.metadata.services.properties.AppProperties;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.UriInfo;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@RequestScoped
public class TrackMetadataBean {

    private Logger log = Logger.getLogger(TrackMetadataBean.class.getName());

    @Inject
    private EntityManager em;

    @Inject
    private TrackMetadataBean trackMetadataBean;

    @Inject
    private AppProperties appProperties;

    @Inject
    private DeezerClient deezerClient;

    public List<si.strimr.track.metadata.models.dtos.TrackMetadata> getTrackMetadataFilter(UriInfo uriInfo) {

        QueryParameters queryParameters = QueryParameters.query(uriInfo.getRequestUri().getQuery())//.defaultOffset(0)
                .build();

        return JPAUtils.queryEntities(em, TrackMetadata.class, queryParameters)
                .stream()
                .map(tm -> map(tm))
                .collect(Collectors.toList());
    }

    public si.strimr.track.metadata.models.dtos.TrackMetadata getTrackMetadata(Integer trackId) {

        TrackMetadata trackMetadata = em.find(TrackMetadata.class, trackId);

        if (trackMetadata == null) {
            throw new NotFoundException();
        }

        return map(trackMetadata);
    }

    public si.strimr.track.metadata.models.dtos.TrackMetadata createTrackMetadata(TrackMetadata trackMetadata) {

        try {
            beginTx();
            em.persist(trackMetadata);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return map(trackMetadata);
    }

    public si.strimr.track.metadata.models.dtos.TrackMetadata putTrackMetadata(String trackId, TrackMetadata trackMetadata) {

        TrackMetadata trackMetadata1 = em.find(TrackMetadata.class, trackId);

        if (trackMetadata1 == null) {
            return null;
        }

        try {
            beginTx();
            trackMetadata.setId(trackMetadata1.getId());
            trackMetadata = em.merge(trackMetadata);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        return map(trackMetadata);
    }

    public boolean deleteTrackMetadata(String trackId) {

        TrackMetadata trackMetadata = em.find(TrackMetadata.class, trackId);

        if (trackMetadata != null) {
            try {
                beginTx();
                em.remove(trackMetadata);
                commitTx();
            } catch (Exception e) {
                rollbackTx();
            }
        } else
            return false;

        return true;
    }

    private void beginTx() {
        if (!em.getTransaction().isActive())
            em.getTransaction().begin();
    }

    private void commitTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().commit();
    }

    private void rollbackTx() {
        if (em.getTransaction().isActive())
            em.getTransaction().rollback();
    }

    private si.strimr.track.metadata.models.dtos.TrackMetadata map(TrackMetadata tm) {

        if (tm == null)
            return null;

        si.strimr.track.metadata.models.dtos.TrackMetadata ntm = new si.strimr.track.metadata.models.dtos.TrackMetadata();

        ntm.setId(tm.getId());
        ntm.setTrackName(tm.getTrackName());
        ntm.setAuthorName(tm.getAuthorName());
        ntm.setTags(tm.getTags());

        try {
            SearchResponse res = deezerClient.searchTrackMetadata(tm.getAuthorName(), tm.getTrackName());

            if(res.getTotal() == 0)
                return ntm;

            SearchResponseData track = res.getData().get(0);

            ntm.setDeezerTrackId(track.getId());
            ntm.setAlbumPicture(track.getAlbum().getCover());
            ntm.setDuration(Integer.parseInt(track.getDuration()));
            ntm.setPreview(track.getPreview());

        } catch (Exception e) {
            log.warning(e.getMessage());
        } finally {
            return ntm;
        }
    }
}
