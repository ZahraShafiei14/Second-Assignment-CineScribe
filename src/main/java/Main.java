import exceptions.ActorsNotFoundException;
import exceptions.MovieNotFoundException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) throws IOException {
        runMenu();
    }

    public static void runMenu() throws IOException {
        System.out.println("-------------MENU-------------");
        System.out.println();
        System.out.println(" 1) Enter movie's title : ");
        System.out.println(" 2) Enter actor's name : ");
        System.out.println(" 3) Exit");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        input.nextLine();
        try {
            switch (choice) {
                case 1:
                    System.out.println("Enter movie's title: ");
                    String title = input.nextLine();
                    movieInformation(new Movie(title));
                case 2:
                    System.out.println("Enter actor's name: ");
                    String name = input.nextLine();
                    actorInformation(new Actors(name));
                case 3:
                    System.exit(0);
            }
        } catch (MovieNotFoundException | ActorsNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void movieInformation(Movie movie) throws IOException {
        System.out.flush();
        System.out.println(ANSI_RESET + ":::   << Movie Info >>   :::");
        System.out.println();
        System.out.println(ANSI_PURPLE + movie.title.toUpperCase() +
                ANSI_RESET + " is a film directed by " + ANSI_BLUE + movie.director);
        System.out.println(ANSI_RESET + "and written by " + ANSI_GREEN + movie.writer + ANSI_RESET + ".");
        System.out.println("It stars " + ANSI_YELLOW + movie.actors + ".");
        System.out.println();
        System.out.println(ANSI_BLUE + "* Synopsis: ");
        System.out.println(movie.plot);
        System.out.println();
        System.out.println(ANSI_CYAN + "* Year: " + movie.year);
        System.out.println(ANSI_GREEN + "* Rated: " + movie.rated);
        System.out.println(ANSI_YELLOW + "* Released: " + movie.released);
        System.out.println(ANSI_RED + "* Running time: " + movie.runtime);
        System.out.println(ANSI_PURPLE + "* Genre: " + movie.genre);
        System.out.println(ANSI_BLUE + "* Language: " + movie.language);
        System.out.println(ANSI_GREEN + "* Country: " + movie.country);
        System.out.println(ANSI_YELLOW + "* IMDB votes: " + movie.imdbVotes);
        System.out.println(ANSI_RED + "* Rating: " + movie.ratings);
        System.out.println(ANSI_RESET + "* Awards: " + movie.awards);
        System.out.println("_______________________________________");
        actorInformation(movie.actor);
        System.out.println("_______________________________________");
        System.out.println();
        System.out.println("Enter any key to back to menu...");
        Scanner key = new Scanner(System.in);
        key.nextLine();
        System.out.flush();
        runMenu();
    }

    public static void actorInformation(Actors actor) throws IOException {
        System.out.flush();
        System.out.println(ANSI_RESET + ":::   << " + actor.name.toUpperCase() + " >>   :::");
        System.out.println(ANSI_PURPLE + "----------------------------");
        System.out.println(ANSI_BLUE + "* netWorth: " + actor.netWorth);
        System.out.println(ANSI_CYAN + "* Gender: " + actor.gender);
        System.out.println(ANSI_GREEN + "* Nationality: " + actor.nationality);
        System.out.println(ANSI_YELLOW + "* Height: " + actor.height + " m");
        System.out.println(ANSI_RED + "* Birthday: " + actor.birthday);
        if (actor.isAlive) {
            System.out.println(ANSI_RESET + actor.name.toUpperCase() + " is alive.");
        } else {
            System.out.println(ANSI_RESET + "* Date of death: " + actor.dateOfDeath);
        }
        System.out.println();
        System.out.println("Enter any key to back to menu...");
        Scanner key = new Scanner(System.in);
        key.nextLine();
        System.out.flush();
        runMenu();
    }
}