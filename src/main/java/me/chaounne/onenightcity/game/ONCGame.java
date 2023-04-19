package me.chaounne.onenightcity.game;
import me.chaounne.onenightcity.game.GenerateChest;

import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ONCGame {

    private ArrayList<Team> teams = new ArrayList<>();

    private final List<GamePlayer> players = new ArrayList<>();

    private static ONCGame instance;

    private boolean started = false;

    private BukkitRunnable timer;
    private int time = 14400;

    private ONCGame(){

    }

    public static ONCGame getInstance(){
        if(instance==null){
            instance = new ONCGame();
        }
        return instance;
    }

    public void addPlayer(GamePlayer player){
        players.add(player);
    }

    public void removePlayer(GamePlayer player){
        players.remove(player);
    }

    public List<GamePlayer> getPlayers(){
        return players;
    }

    public void addTeam(Team team){
        teams.add(team);
    }

    public void removeTeam(Team team){
        teams.remove(team);
    }

    public ArrayList<Team> getTeams(){
        return teams;
    }

    public void randomTeams(){
        Collections.shuffle(players);
        Collections.shuffle(teams);

        int team_size = players.size()/teams.size();

        for(GamePlayer player : players){
            for(Team team : teams){
                if(team.getPlayers().size() < team_size){
                    team.addPlayer(player.getPlayer());
                    player.setTeam(team);
                    break;
                }
            }
        }
    }

    public boolean isStarted(){
        return started;
    }

    public void startGame(){

        timer = new BukkitRunnable() {
            @Override
            public void run() {

                time--;
                if(time == 0){
                    this.cancel();
                    endGame();
                }
                GenerateChest generateChest = new GenerateChest();
                generateChest.spawnCoffre();

            }

        };
        if(!started) started = true;

        timer.runTaskTimer(OneNightCity.getInstance(), 0, 20);
    }

    public void endGame(){
        if(timer != null) timer.cancel();
        if(started) started = false;
    }




}
