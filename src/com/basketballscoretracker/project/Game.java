package com.basketballscoretracker.project;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    private Team homeTeam;
    private Team awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private int durationInSeconds;
    private Timer timer;
    private GameStatus status;

    public Game(Team homeTeam, Team awayTeam, int durationInSeconds) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.durationInSeconds = durationInSeconds;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.status = GameStatus.PAUSED;
        this.timer = new Timer();
    }

    public enum GameStatus { // inicijalizacija statusa tekme
        ONGOING,
        FINISHED,
        PAUSED
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    public void startGame() {
        if (status == GameStatus.FINISHED) {
            System.out.println("Cannot resume, the game is already finished.");
        } else if (status == GameStatus.PAUSED) {
            status = GameStatus.ONGOING;
            startTimer(); // Start the timer when resuming the game
            System.out.println("Game starts now.");
        } else {
            System.out.println("The game is already ongoing.");
        }
    }

    public void pauseGame() {
        if (status == GameStatus.ONGOING) {
            status = GameStatus.PAUSED;
            stopTimer(); // Stop the timer when pausing the game
            System.out.println("Game paused.");
        } else if (status == GameStatus.FINISHED) {
            System.out.println("Cannot pause, the game is already finished.");
        } else {
            System.out.println("The game is already paused.");
        }
    }

    private void startTimer() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                durationInSeconds--; // Decrement the duration every tick
                if (durationInSeconds <= 0) {
                    stopTimer(); // Stop the timer when duration reaches zero
                    status = GameStatus.FINISHED; // Mark the game as finished
                    System.out.println("Time's up. Game over!");
                } else {
                    if (durationInSeconds % 5 == 0) {
                        System.out.println("Time remaining: " + durationInSeconds + " seconds");
                    }
                }
            }
        }, 0, 1000); // Schedule the timer to run every second (1000 milliseconds)
    }

    // Internal method to stop the timer
    private void stopTimer() {
        timer.cancel();
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void updateScore(int homeTeamScore, int awayTeamScore) {
        this.homeTeamScore += homeTeamScore;
        this.awayTeamScore += awayTeamScore;
    }
}