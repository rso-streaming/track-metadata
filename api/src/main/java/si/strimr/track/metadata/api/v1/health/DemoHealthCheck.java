package si.strimr.track.metadata.api.v1.health;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import si.strimr.track.metadata.services.properties.AppProperties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Health
@ApplicationScoped
public class DemoHealthCheck implements HealthCheck {

    @Inject
    private AppProperties appProperties;

    public HealthCheckResponse call() {

        HealthCheckResponseBuilder healthCheckResponseBuilder =
                HealthCheckResponse.named(DemoHealthCheck.class.getSimpleName());

        if (appProperties.isDemoHealthy()) {
            return healthCheckResponseBuilder.up().build();
        } else {
            return healthCheckResponseBuilder.down().build();
        }
    }

}