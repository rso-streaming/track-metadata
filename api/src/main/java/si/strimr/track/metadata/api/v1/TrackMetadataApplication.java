package si.strimr.track.metadata.api.v1;

//import io.swagger.annotations.FirstMilestoneInfo;
//import io.swagger.annotations.SwaggerDefinition;
import com.kumuluz.ee.discovery.annotations.RegisterService;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@RegisterService
@ApplicationPath("/v1")
public class TrackMetadataApplication extends Application {
}
