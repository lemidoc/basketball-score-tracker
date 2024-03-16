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

    public synchronized void startGame() {
        if (status == GameStatus.FINISHED) {
            System.out.println("Cannot start, the game is already finished.");
        } else if (status == GameStatus.PAUSED) {
            status = GameStatus.ONGOING;
            if (timer == null) {
                timer = new Timer(); // Create a new timer only if it hasn't been created yet
                startTimer(); // Start the timer when resuming the game
            }
            System.out.println("Game resumed.");
        } else if (status == GameStatus.ONGOING) {
            System.out.println("The game is already ongoing.");
        } else {
            // If the game has not yet started, initialize the timer and start the game
            timer = new Timer();
            startTimer();
            System.out.println("Game starts now.");
            status = GameStatus.ONGOING;
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
                synchronized (Game.this) { // Synchronize to avoid race conditions
                    durationInSeconds--;
                    if (durationInSeconds <= 0) {
                        stopTimer();
                        status = GameStatus.FINISHED;
                        System.out.println("Time's up. Game over!");
                    } else if (durationInSeconds % 5 == 0) {
                        System.out.println("Time remaining: " + durationInSeconds + " seconds");
                    }
                }
            }
        }, 0, 1000);
    }

    // Internal method to stop the timer
    private synchronized void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null; // Reset the timer reference
        }
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