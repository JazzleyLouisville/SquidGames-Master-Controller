package softwaredesign.constants;

public class NetworkingConstants {
    //  Using local server
    public final static boolean IS_LOCAL = true;
    //  Servers
    public final static String BASE_URL = "https://squidgameserver.herokuapp.com/";
    public final static String LOCALHOST_SERVER = "http://localhost:3333/";
    //  Routes
    public final static String GET_USER_PATH = "users";
    public final static String POST_PLAYERS_PATH = "players";
    public final static String GET_INVITATION_PATH = "invitation";
    public final static String GET_PASSWORD_PATH = "get_password";
    public final static String POST_USER_PATH = "user";
    public final static String GET_DEV_GAMES_PATH = "all_games" ;
    public final static String POST_DEV_GAME_PATH = "add_game" ;
    //  Error responses
    public final static String NO_URL = "No URL provided.";

}
