import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@JsonIgnoreProperties
public class Actors {
    public static final String API_KEY = "x0ci3kSFy4JSMDv6eYsK5gssHrrSpDWZHUGRsyow";
    /* Actor information such as name, net_worth, gender, nationality
     height, birthday or death.
     **/
    @JsonProperty("name")
    String name;
    @JsonProperty("net_worth")
    double netWorth;
    @JsonProperty(value = "is_alive")
    Boolean isAlive;
    @JsonProperty(value = "death")
    String dateOfDeath;
    @JsonProperty("gender")
    String gender;
    @JsonProperty("nationality")
    String nationality;
    @JsonProperty("height")
    double height;
    @JsonProperty("birthday")
    String birthday;

    public Actors(String netWorth, boolean isAlive) {
        this.netWorth = netWorth.isEmpty() ? 0.0 : Double.parseDouble(netWorth);
        this.isAlive = isAlive;
    }

    public Actors(double netWorth, Boolean isAlive, String dateOfDeath, String gender, String nationality, double height, String birthday) {
        this.netWorth = netWorth;
        this.isAlive = isAlive;
        this.dateOfDeath = dateOfDeath;
        this.gender = gender;
        this.nationality = nationality;
        this.height = height;
        this.birthday = birthday;
    }

    public Actors(String name) {
        Actors actor = Utils.getActorsMapper(getActorData(name));
        if (actor != null) {
            this.name = actor.name;
            netWorth = actor.netWorth;
            isAlive = actor.isAlive;
            dateOfDeath = actor.dateOfDeath;
            gender = actor.gender;
            nationality = actor.nationality;
            height = actor.height;
            birthday = actor.birthday;
        }
    }

    public Actors() {
    }

    @SuppressWarnings({"deprecation"})
    /**
     * Retrieves data for the specified actor.
     * @param name for which Actor should be retrieved
     * @return a string representation of the Actors info or null if an error occurred
     */
    public String getActorData(String name) {
        try {
            URL url = new URL("https://api.api-ninjas.com/v1/celebrity?name=" +
                    name.replace(" ", "+") + "&apikey=" + API_KEY);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("X-Api-Key", API_KEY);
            System.out.println(connection);
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                return response.toString();
            } else {
                return "Error: " + connection.getResponseCode() + " " + connection.getResponseMessage();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getNetWorthViaApi(String actorsInfoJson) {
        Actors actor = Utils.getActorsMapper(actorsInfoJson);
        return actor != null ? actor.netWorth : 0.0;
    }

    public double getHeightViaApi(String actorsInfoJson) {
        Actors actor = Utils.getActorsMapper(actorsInfoJson);
        return actor != null ? actor.height : 0.0;
    }

    public boolean isAlive(String actorsInfoJson) {
        Actors actor = Utils.getActorsMapper(actorsInfoJson);
        return actor == null || actor.isAlive != null && actor.isAlive;
    }

    public String getDateOfDeathViaApi(String actorsInfoJson) {
        Actors actor = Utils.getActorsMapper(actorsInfoJson);
        return actor == null || actor.dateOfDeath == null ? "" : actor.dateOfDeath;
    }

    public String getNameViaApi(String actorsInfoJson) {
        Actors actor = Utils.getActorsMapper(actorsInfoJson);
        return actor == null || actor.name == null ? "" : actor.name;
    }

    public String getGenderViaApi(String actorsInfoJson) {
        Actors actor = Utils.getActorsMapper(actorsInfoJson);
        return actor == null || actor.gender == null ? "" : actor.gender;
    }

    public String getBirthdayViaApi(String actorsInfoJson) {
        Actors actor = Utils.getActorsMapper(actorsInfoJson);
        return actor == null || actor.birthday == null ? "" : actor.birthday;
    }

    public String getNationalityViaApi(String actorsInfoJson) {
        Actors actor = Utils.getActorsMapper(actorsInfoJson);
        return actor == null || actor.nationality == null ? "" : actor.nationality;
    }
}