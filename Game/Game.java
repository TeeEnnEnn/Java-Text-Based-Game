package Game;

import Actions.Action;
import Files.FileRead;
import Files.FileWrite;
import Player.Player;
import World.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Scanner scanner;
    public static World world;


    public Game(){
        this.scanner = new Scanner(System.in);
        world = new World();
    }

    public void playerStory(){
        FileRead fileRead = new FileRead("Files/playerStory.txt");
        fileRead.printContents();
        System.out.println("\nWhat would you like to do\n");
    }

    public void leaderboard(){
        FileRead fileRead = new FileRead("Files/leaderboard.dat");
        if (!fileRead.isEmpty()){
            fileRead.printContents();
            System.out.println("\nWhat would you like to do\n");
        }
        System.out.println("\nThe leaderboard is empty --- Play the game first");
        System.out.println("\nWhat would you like to do\n");
    }

    public void credits(){
        FileRead fileRead = new FileRead("Files/credits.txt");
        fileRead.printContents();
        System.out.println("\nWhat would you like to do\n");
    }

    public void exitGame(){
        System.out.println("\nGoodbye\n");
        System.exit(0);
    }
    public void exitScreen(){
        System.out.println("---------CAVER---------\n");
        System.out.println("Thank you for playing the game.");
        System.out.println("""
                1. Read the story
                2. Leaderboard
                3. Credits
                4. Quit Game
                """);

        while (true){
            String choice = "";
            System.out.println("\nWhat would you like to do");
            // TODO: Add error checking for input types.
            try{
                choice = this.scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Incorrect input type");
                // exitScreen();
            }

            switch (choice){
                case "1" -> {
                    this.playerStory();
                    exitScreen();
                }
                case "2" -> {this.leaderboard();}
                case "3" -> {this.credits();}
                case "4" -> {this.exitGame();}
                default -> System.out.println("Incorrect Input");
            }
        }
    }

    public void startScreen(){
        System.out.println("---------CAVER---------");
        System.out.println("Welcome to the game.");
        System.out.println("""
                1. Play Caver
                2. Read The Story
                3. Leaderboard
                4. Credits
                5. Quit Game
                                
                If it is your first time playing the game.
                Reading the story is recommended.
                This will give you a background of the game.
                                
                Hope you enjoy!
                """);
        System.out.println("What would you like to do");

        while (true){
            String choice = "";
            try {
                choice = this.scanner.nextLine();
            }catch (InputMismatchException e){
                System.out.println("Incorrect input type");
            }

            switch (choice){
                case "1" -> {this.playGame();}
                case "2" -> {this.playerStory();}
                case "3" -> {this.leaderboard();}
                case "4" -> {this.credits();}
                case "5" -> {this.exitGame();}
                default -> System.out.println("Incorrect Input");
            }

        }
    }

    public void playGame() {
        System.out.println("-----CAVER-----\n");

        world.parseWorld();
        int[] startLocation = world.startLocation;
        Player player = new Player(startLocation[0], startLocation[1]);

        while(player.isAlive() && !player.victory){
            MapTile room = world.getTile(player.x, player.y);
            room.introText();
            room.modifyPlayer(player);

            if (player.victory && player.isAlive()){
                end(player);
            }else if (player.isAlive() && !player.victory){
                chooseAction(room, player);
            }else if (!player.isAlive()) {
                System.out.println("""
                        Your journey has come to an early end.
                                ---GAME OVER---
                        """);
                end(player);
            }
        }
    }

    public void chooseAction(MapTile room, Player player){
        // TODO: Add implementation from python source (This is the reason nothing happens on the starting tile.)
        Action action = null;

        ArrayList<Action> availableActions = new ArrayList<>();
        availableActions = this.getAvailableActions(room, player);
        String actionInput = scanner.nextLine();
        for (Action act: availableActions){
            if(act.hotkey.equals(actionInput) || act.name.equals(actionInput) ){
                act.actionPerformed();
            }
        }
    }


    public void end(Player player){
        if (player.hasName()){
            ;
        }else{
            System.out.println("Enter your name: \n");
            String name = this.scanner.nextLine();
            if (name.equals("")){
                end(player);
            }
            player.name = name;
        }
        // How to clear the console: https://stackoverflow.com/questions/2979383/how-to-clear-the-console

        try {
            FileWrite leaderboardWriter = new FileWrite("Files/leaders.txt");
            leaderboardWriter.fileWrite(player.name + "-" + player.score);
            leaderboardWriter.fileClose();
        } catch (RuntimeException e){
            System.out.println("Error: Failed to write to file");
        }

        System.out.println("\n You will now be taken to the exit screen \n");
        exitScreen();
    }

    public ArrayList<Action> getAvailableActions(MapTile room, Player player){
        ArrayList<Action> actions = new ArrayList<Action>();
        System.out.println("\nChoose an Action:");
        if (player != null){
            Action action = new Action.details(player);
            actions.add(action);
            System.out.println(action.hotkey + ": " + action.name);
        }
        if (player.inventory != null){
            Action action = new Action.inventory(player);
            actions.add(action);
            System.out.println(action.hotkey + ": " + action.name);
        }

        if (world.getTile(room.x,room.y - 1 ) != null){
            Action action = new Action.north(player);
            actions.add(action);
            System.out.println(action.hotkey + ": " + action.name);
        }

        if (world.getTile(room.x,room.y + 1 ) != null){
            Action action = new Action.south(player);
            actions.add(action);
            System.out.println(action.hotkey + ": " + action.name);
        }

        if (world.getTile(room.x+1,room.y) != null){
            Action action = new Action.east(player);
            actions.add(action);
            System.out.println(action.hotkey + ": " + action.name);
        }

        if (world.getTile(room.x-1,room.y + 1 ) != null){
            Action action = new Action.west(player);
            actions.add(action);
            System.out.println(action.hotkey + ": " + action.name);
        }

        if (world.getTile(room.x, room.y) instanceof EnemyTile){
            Action action = new Action.attack(player);
            actions.add(action);
            System.out.println(action.hotkey + ": " + action.name);
        }

        return actions;
    }

}
