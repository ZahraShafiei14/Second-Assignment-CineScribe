import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties
public class Movie {
    public static final String API_KEY = "7b9d4b9a";
    /* Movie information such as actors, writers, directors,
    imdbvotes, rating, runtime , rated, plot , language, country and genre.
    **/
    @JsonProperty("Released")
    String released;
    @JsonProperty("Awards")
    String awards;
    @JsonProperty("Title")
    String title;
    @JsonProperty("Year")
    String year;
    @JsonProperty("Actors")
    String actors;
    @JsonIgnoreProperties
    List<String> actorsList;
    @JsonProperty("imdbVotes")
    String imdbVotes;
    @JsonProperty("Ratings")
    List<Rating> ratings;
    @JsonProperty("Runtime")
    String runtime;
    @JsonProperty("Rated")
    String rated;
    @JsonProperty("Plot")
    String plot;
    @JsonProperty("Language")
    String language;
    @JsonProperty("Country")
    String country;
    @JsonProperty("Genre")
    String genre;
    @JsonIgnoreProperties
    List<String> genresList;
    @JsonProperty("Writer")
    String writer;
    @JsonIgnoreProperties
    List<String> writers;
    @JsonProperty("Director")
    String director;
    @JsonIgnoreProperties
    List<String> directors;

    @JsonIgnoreProperties
    Actors actor;


    static class Rating {
        @JsonProperty("Source")
        String source;
        @JsonProperty("Value")
        String value;

        @Override
        public String toString() {
            return
                    "source: " + source + '\'' +
                            "value: " + value + '\'';
        }
    }

    public Movie(String released, String awards, String title, String year, String actors, String imdbVotes, List<Rating> ratings, String runtime, String rated, String plot, String language, String country, String genre, String writer, String director) {
        this.awards = awards;
        this.title = title;
        this.year = year;
        this.actors = actors;
        this.actorsList = List.of(actors.split(","));
        this.imdbVotes = imdbVotes;
        this.ratings = ratings;
        this.runtime = runtime;
        this.rated = rated;
        this.released = released;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.genre = genre;
        this.genresList = List.of(genre.split(","));
        this.writer = writer;
        this.writers = List.of(writer.split(","));
        this.director = director;
        this.directors = List.of(director.split(","));
    }

    public Movie(ArrayList<String> actorsList, String rating, int ImdbVotes) {
        this.actorsList = actorsList;
        this.imdbVotes = String.valueOf(ImdbVotes);
    }

    public Movie(String name) {
        Movie movie = Utils.getMoviesMapper(getMovieData(name));
        if (movie != null) {
            title = movie.title;
            year = movie.year;
            awards = movie.awards;
            released = movie.released;
            actors = movie.actors;
            actorsList = List.of(actors.split(","));
            imdbVotes = movie.imdbVotes;
            ratings = movie.ratings;
            runtime = movie.runtime;
            rated = movie.rated;
            plot = movie.plot;
            language = movie.language;
            country = movie.country;
            genre = movie.genre;
            genresList = List.of(genre.split(","));
            writer = movie.writer;
            writers = List.of(writer.split(","));
            director = movie.director;
            directors = List.of(director.split(","));
        }
        if (!actorsList.isEmpty()) {
            actor = new Actors(actorsList.get(0));
        }
    }

    public Movie() {
    }

    @SuppressWarnings("deprecation")
    /**
     * Retrieves data for the specified movie.
     *
     * @param title the name of the title for which MovieData should be retrieved
     * @return a string representation of the MovieData, or null if an error occurred
     */

    public String getMovieData(String title) {
        try {
            URL url = new URL("https://www.omdbapi.com/?t=" + title + "&apikey=" + API_KEY);
            URLConnection Url = url.openConnection();
            Url.setRequestProperty("Authorization", "Key" + API_KEY);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Url.getInputStream()));
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            reader.close();
            //handle an error if the chosen movie is not found
            return stringBuilder.toString();
        } catch (IOException e) {
            System.err.println("Error: Movie Was Not Found! Please Try Again...");
            return "";
        }

    }

    public int getImdbVotesViaApi(String moviesInfoJson) {
        Movie movie = Utils.getMoviesMapper(moviesInfoJson);
        return movie != null ? Integer.parseInt(movie.imdbVotes.replaceAll(",", "")) : 0;
    }

    public String getRatingViaApi(String moviesInfoJson) {
        String rating = "";
        Movie movie = Utils.getMoviesMapper(moviesInfoJson);
        if (movie == null) return rating;
        for (Rating rate : movie.ratings) {
            if (rate.source.equalsIgnoreCase("Internet Movie Database"))
                rating = rate.value;
        }
        return rating;
    }

    public void getActorListViaApi(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        if (movie != null) {
            actorsList = Arrays.stream(movie.actors.split(",")).toList();
        }
    }

    public String getRuntimeViaApi(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        return movie == null || movie.runtime == null ? "" : movie.runtime;
    }

    public String getRatedViaApi(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        return movie == null || movie.rated == null ? "" : movie.rated;
    }

    public void getGenreList(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        if (movie != null) {
            genresList = Arrays.stream(movie.genre.split(",")).toList();
        }
    }

    public String getPlotViaApi(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        return movie == null || movie.plot == null ? "" : movie.plot;
    }

    public String getLanguageViaApi(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        return movie == null || movie.language == null ? "" : movie.language;
    }
    public String getTitleViaApi(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        return movie == null || movie.title == null ? "" : movie.title;
    }
    public String getCountryViaApi(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        return movie == null || movie.country == null ? "" : movie.country;
    }

    public void getWriterViaApi(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        if (movie != null) {
            writers = Arrays.stream(movie.writer.split(",")).toList();
        }
    }

    public void getDirectorViaApi(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        if (movie != null) {
            directors = Arrays.stream(movie.director.split(",")).toList();
        }
    }

    public String getYearViaApi(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        return movie == null || movie.year == null ? "" : movie.year;
    }

    public String getReleasedViaApi(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        return movie == null || movie.released == null ? "" : movie.released;
    }

    public String getAwardsViaApi(String movieInfoJson) {
        Movie movie = Utils.getMoviesMapper(movieInfoJson);
        return movie == null || movie.awards == null ? "" : movie.awards;
    }

}