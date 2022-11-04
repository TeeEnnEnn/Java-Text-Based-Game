package Game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Scanner scanner;
    String PlayerStory = """
            ...CAVER...

            The Story

            The player has lived in a cave their whole life. The player lives in a small village in the cave.
            The player may live in the cave, but it is clear that the player does not come from the cave.
            Compared to the other people that live in the cave the player has more muscle and a more well-defined bone structure.
            The player has been in the cave for as long as they can remember, but the people of the cave could remember more.
            The player knows that they are different from the people of the cave but still treats them as their own.

            Tales have been told. Tales of a bright yellow ball that floats far above.
            Tales of a bright white ball that floats with many small white dots to accompany it.
            Tales of a vast expanse of land that is not confined by the dark walls of a cave.
            These are the tales that the character has grown up to. However, the player has never left the cave.
            No one dares to try and leave the cave. There is only one way to leave the cave. It is treacherous and unforgiving.
            It holds many vicious beasts. No one can make it through.

            No one, except one man. An old man, who many in the village called senile. In the days of his prime, he left the cave\s
            and witnessed the outside world. He saw first hand what the outside world was like. He now can only tell the tales of\s
            technology that surpassed his comprehension and people who, to him looked like muscular giants. He is the only one\s
            who has seen the legendary yellow ball, the one called ‘sun’. The tales of the outside world where he was known as a\s
            Caver.

            Alas, the people of the cave are slowly dying. Their resources are dwindling. The old risk their lives every time\s
            they awake from their slumber. The old man is at the greatest risk. He is the oldest of all the people of the cave.\s
            He was the one to find the player and rescue him from death's door. Once upon a time. The player sees the old man as\s
            a father figure. The love he had for the old man was great.

            The player has to save the people of the cave. The player has to save their friends. The player has to save the old man.\s
            Save his father. The player will now have to journey out of the cave. The player will encounter many dangers,\s
            but the player must survive or else.

            You are the player. You are a Caver.
            Will you survive?
            """;


    public Game(){
        this.scanner = new Scanner(System.in);
    }

    public void leaderboard(){
        System.out.println("Leaderboard");
    }

    public void credits(){
        System.out.println("Credits");
    }

    public void exitGame(){
        System.out.println("Exit Game");
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
                    System.out.println(this.PlayerStory);
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
                Reading the story is recommended.\s
                This will give you a background of the game.\s
                                
                Hope you enjoy!\s
                """);

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

    public void playerStory() {
        System.out.println(this.PlayerStory);
    }

    public void playGame() {

    }

}