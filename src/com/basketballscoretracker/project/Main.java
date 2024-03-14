package com.basketballscoretracker.project;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Create players
        Player player1 = new Player("LeBron James", 23);
        Player player2 = new Player("Anthony Davis", 3);
        Player player3 = new Player("Jason Tatum", 0);
        Player player4 = new Player("Jalen Brown", 7);

        // Create teams
        Team lakers = new Team("Los Angeles Lakers");
        lakers.addPlayer(player1);
        lakers.addPlayer(player2);

        Team boston = new Team("Boston Celtics");
        boston.addPlayer(player3);
        boston.addPlayer(player4);

        // Create game and scoreboard
        Game game = new Game(lakers, boston, 300); // 5 minutes game (300 seconds)
        Scoreboard scoreboard = new Scoreboard(game);

        // Create input handler
        InputHandler inputHandler = new InputHandler(game, scoreboard);

        // Start handling user input
        inputHandler.handleInput();
    }
}