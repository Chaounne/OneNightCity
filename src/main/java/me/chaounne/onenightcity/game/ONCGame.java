package me.chaounne.onenightcity.game;

import java.util.ArrayList;
import java.util.List;

public class ONCGame {

    private ArrayList<Team> teams = new ArrayList<>();

    private final List<GamePlayer> players = new ArrayList<>();

    private static ONCGame instance;

    private ONCGame(){

    }

    public static ONCGame getInstance(){
        if(instance==null){
            new ONCGame();
        }
        return instance;
    }
}
