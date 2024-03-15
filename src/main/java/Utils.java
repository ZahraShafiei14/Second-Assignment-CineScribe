import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Utils {
    public static Actors getActorsMapper(String actorsInfoJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            Actors[] actor = mapper.readValue(actorsInfoJson, Actors[].class);
            return actor[0];
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }

    public static Movie getMoviesMapper(String actorsInfoJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(actorsInfoJson, Movie.class);
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
