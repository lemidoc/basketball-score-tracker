package com.basketballscoretracker.project;

import java.util.ArrayList;

public class Team {

    private ArrayList<Player> players;
    private String teamName;

    public Team(String teamName) {
        this.teamName = teamName;
        this.players = new ArrayList<>();
    }
    public void addPlayer(Player player){
        if (players.size()<5){
            players.add(player);
        }
        else {
            System.out.println("Can not add more than 5 players to the team.");
        }
    }
    public void printTeam(){
        System.out.println("Team: "+this.teamName);
        for (Player player: players){
            System.out.println(player);
        }

    }
}
