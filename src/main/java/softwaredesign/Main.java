package softwaredesign;

import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        CommandHandler commandHandler = new CommandHandler();
        System.out.println("Welcome to the Squid Game Control Room");
        while (true) {
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if(command.contains("SET GAMESTATE")) {
                commandHandler.setGameState(command);
            } else if(command.contains("GET GAMESTATE")) {
                commandHandler.getGameState(command);
            } else if(command.contains("EXIT")) {
                System.out.println("Exited the Squid Game Control Room");
                break;
            }
        }
    }
}