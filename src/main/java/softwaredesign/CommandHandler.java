package softwaredesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CommandHandler {
    Map<String, String> games;
    ArrayList<String> gameSequence;
    public CommandHandler() {
        games = new HashMap<>();
        gameSequence = new ArrayList<String>();
    }

    protected void setGameState(String command) {
        String[] commandSplitted = command.split(" ");
        if (commandSplitted.length != 4) {
            if(commandSplitted[0].equals("EXIT") && commandSplitted.length == 1){
                System.out.println("Exiting control room");
                System.exit(0);
            }
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

    protected void setGameSequence(String command) {
        String[] commandSplitted = command.split(" ");
        if (commandSplitted.length < 3) {
            System.out.println("Invalid command");
            return;
        }
        for(int i = 2; i < commandSplitted.length; i++) {
            gameSequence.add(commandSplitted[i]);
        }

        System.out.println("Set the game sequence to:");
        for(int i = 0; i < gameSequence.toArray().length; i++) {
            System.out.println((i + 1) + ": " + gameSequence.get(i));
        }
    }

    protected void getGameSequence() {
        if (gameSequence.toArray().length == 0) {
            System.out.println("No game sequence set yet");
            return;
        }
        System.out.println("Current game sequence: ");
        for(int i = 0; i < gameSequence.toArray().length; i++) {
            System.out.println((i + 1) + ": " + gameSequence.get(i));
        }
    }
}
