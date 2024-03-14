package com.basketballscoretracker.project;

import java.util.Scanner;

public class InputHandler {
    private Game game;
    private Scoreboard scoreboard;

    public InputHandler(Game game, Scoreboard scoreboard) {
        this.game = game;
        this.scoreboard = scoreboard;
    }

    public void handleInput() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("(To update score, you need to Start the game first (2.) ");
            System.out.println("Choose an option: ");
            System.out.println("1. Update scores");
            System.out.println("2. Pause/Resume game");
            System.out.println("3. Display scoreboard");
            System.out.println("4. Quit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    updateScores(scanner);
                    break;
                case 2:
                    togglePauseResume();
                    break;
                case 3:
                    displayScoreboard();
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void updateScores(Scanner scanner) {
        if (game.getStatus() != Game.GameStatus.ONGOING) {
            System.out.println("Cannot update scores. Game is not ongoing.");
            return;
        }

        System.out.println("Enter home team score:");
        int homeTeamScore = scanner.nextInt();
        System.out.println("Enter away team score:");
        int awayTeamScore = scanner.nextInt();

        game.updateScore(homeTeamScore, awayTeamScore); // Update scores in the game
        System.out.println("Scores updated successfully.");
    }


    private void togglePauseResume() {
        if (game.getStatus() == Game.GameStatus.PAUSED) {
            game.startGame();
            System.out.println("Game resumed.");
        } else if (game.getStatus() == Game.GameStatus.ONGOING) {
            game.pauseGame();
            System.out.println("Game paused.");
        } else {
            System.out.println("Cannot pause/resume. Game is finished.");
        }
    }

    private void displayScoreboard() {
        System.out.println("Current scoreboard:");
        scoreboard.displayScoreboard();
    }
}