package softwaredesign;


import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main (String[] args) throws Exception {
        CommandHandler commandHandler = new CommandHandler();
        API api = new API();
        System.out.println("Welcome to the Squid Game Control Room");
        while (true) {
            System.out.print("> ");
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if(command.contains("SET GAMESTATE")) {
                commandHandler.setGameState(command);
            } else if(command.contains("GET GAMESTATE")) {
                commandHandler.getGameState(command);
            } else if(command.contains("SET GAMESEQUENCE")) {
                commandHandler.setGameSequence(command);
            } else if(command.contains("GET GAMESEQUENCE")) {
                commandHandler.getGameSequence();
            } else if(command.contains("HTTP TEST")) {
                MainResponse res = api.get("https://squidgameserver.herokuapp.com", new MainResponse());
                System.out.println(res.message);
            } else if(command.contains("HTTP USERS")) {
                UserResponse res = api.get("https://squidgameserver.herokuapp.com/users", new UserResponse());
                System.out.println(res.usernames);
            }
            else if(command.contains("EXIT")) {
                System.out.println("Exited the Squid Game Control Room");
                break;
            }
        }
    }
}