package si.strimr.track.metadata.services.properties;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("app-properties")
public class AppProperties {
    @ConfigValue(value = "api.filtering.enabled", watch = true)
    private boolean apiFilteringEnabled;

    @ConfigValue(value = "endpoint.zc", watch = true)
    private String endpointZc;

    @ConfigValue(value = "endpoint.mm", watch = true)
    private String endpointMm;

    public void setApiFilteringEnabled(boolean apiFilteringEnabled) {
        this.apiFilteringEnabled = apiFilteringEnabled;
    }

    public boolean isApiFilteringEnabled() {
        return apiFilteringEnabled;
    }

    public String getEndpointZc() {
        return endpointZc;
    }

    public void setEndpointZc(String endpointZc) {
        this.endpointZc = endpointZc;
    }

    public String getEndpointMm() {
        return endpointMm;
    }

    public void setEndpointMm(String endpointMm) {
        this.endpointMm = endpointMm;
    }
}
