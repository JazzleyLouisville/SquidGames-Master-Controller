package softwaredesign;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    Map<String, String> games;

    public CommandHandler() {
        games = new HashMap<>();
    }

    protected void setGameState(String command) {
        String[] commandSplitted = command.split(" ");
        if (commandSplitted.length != 4) {
            System.out.println("Invalid command");
            return;
        }
        String gameName = commandSplitted[2];
        String state = commandSplitted[3];
        if (!(state.equals("PLAY") || state.equals("PAUSED") || state.equals("FINISHED"))) {
            System.out.println("Invalid gamestate");
            return;
        }
        games.put(gameName, state);
        System.out.println("Set the game " + gameName + " to " + state);
    }

    protected void getGameState(String command) {
        String[] commandSplitted = command.split(" ");
        if (commandSplitted.length > 3) {
            System.out.println("Invalid command");
            return;
        }
        String gameName = commandSplitted[2];
        String state = games.get(gameName);
        if (state == null) {
            System.out.println("The game " + gameName + " does not exist!");
            return;
        }
        System.out.println("The gamestate of " + gameName + " is " + state);
    }
}
