package com.basketballscoretracker.project;

public class Scoreboard {
    private Game game;
    private String displayFormat;
    private int homeTeamScore;
    private int awayTeamScore;

    public Scoreboard(Game game) {
        this.game = game;
    }

    public void displayScoreboard() {
        System.out.println("Home team score: " + game.getHomeTeamScore());
        System.out.println("Away team score: " + game.getAwayTeamScore());
    }

    public String getDisplayFormat() {
        return displayFormat;
    }

    public void setDisplayFormat(String displayFormat) {
        this.displayFormat = displayFormat;
    }
}