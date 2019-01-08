package si.strimr.track.metadata.services.properties;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("app-properties")
public class AppProperties {

    @ConfigValue(value = "deezer.baseUrl", watch = true)
    private String deezerBaseUrl;

    @ConfigValue(value = "demo.healthy", watch = true)
    private boolean demoHealthy;

    @ConfigValue(value = "demo.timeout", watch = true)
    private boolean demoTimeout;

    public String getDeezerBaseUrl() {
        return deezerBaseUrl;
    }
    public void setDeezerBaseUrl(String deezerBaseUrl) {
        this.deezerBaseUrl = deezerBaseUrl;
    }

    public boolean isDemoHealthy() { return demoHealthy; }
    public void setDemoHealthy(boolean demoHealthy) { this.demoHealthy = demoHealthy; }

    public boolean isDemoTimeout() { return demoTimeout; }
    public void setDemoTimeout(boolean demoTimeout) { this.demoTimeout = demoTimeout; }
}
