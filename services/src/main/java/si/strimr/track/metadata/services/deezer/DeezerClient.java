package si.strimr.track.metadata.services.deezer;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import si.strimr.track.metadata.services.deezer.models.SearchResponse;
import si.strimr.track.metadata.services.properties.AppProperties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.criteria.Order;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@ApplicationScoped
public class DeezerClient {

    private Logger log = Logger.getLogger(DeezerClient.class.getName());

    @Inject
    private AppProperties appProperties;

    private Client httpClient;

    private String baseUrl;

    private WebTarget base;

    @PostConstruct
    private void init() {
        // https://developers.deezer.com/api/explorer
        httpClient = ClientBuilder.newClient();
        baseUrl = "https://api.deezer.com";
    }

    public SearchResponse searchTrackMetadata(String artist, String track) {
        try {
            return httpClient
                    .target(baseUrl + "/search")
                    .queryParam("q", String.format("artist:\"%s\" track:\"%s\"", artist, track))
                    .request().get(SearchResponse.class);
        } catch (Exception e) {
            log.severe(e.getMessage());
            throw e;
        }
    }
}
