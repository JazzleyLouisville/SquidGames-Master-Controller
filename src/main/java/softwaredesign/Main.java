package softwaredesign;

import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        while (true) {
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            System.out.println(command);
            if(command.contains("SET GAMESTATE")) {
                commandHandler.setGameState(command);
            } else if(command.contains("GET GAMESTATE")) {
                commandHandler.getGameState(command);
            }
        }
    }
}