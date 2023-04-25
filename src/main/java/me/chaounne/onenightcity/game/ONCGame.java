package me.chaounne.onenightcity.game;

import eu.decentsoftware.holograms.api.DHAPI;

import eu.decentsoftware.holograms.api.holograms.Hologram;
import fr.mrmicky.fastboard.FastBoard;
import me.chaounne.onenightcity.OneNightCity;
import org.bukkit.*;
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
    private FastBoard board;

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

    public void updateBoard(){
        for(Player players : Bukkit.getOnlinePlayers()){
            GamePlayer player = GamePlayer.getInstance(players);
            board = new FastBoard(player.getPlayer());
            board.updateTitle(ChatColor.DARK_BLUE + "Cité d'une nuit");
            board.updateLines("",
                    ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                    ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + time,
                    ChatColor.GOLD + "Equipe : " + player.getTeam().getColor() + player.getTeam().getName(),
                    ChatColor.GOLD + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                    "",
                    ChatColor.GOLD + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                    "---------------------------------");
        }
    }

    public boolean isStarted(){
        return started;
    }

    public void startGame() {
        GenerateChest generateChest = new GenerateChest();
        //Bukkit.getScheduler().scheduleSyncDelayedTask(OneNightCity.getInstance(), () -> generateChest.spawnCoffre());
        for(Player players : Bukkit.getOnlinePlayers()){
            players.sendMessage("avant");
        }
        ClassementPoudre.showScoreboard();
        for(Player players : Bukkit.getOnlinePlayers()){
            players.sendMessage("apres");
        }
        timer = new BukkitRunnable() {
            @Override
            public void run() {
                time--;
                updateBoard();
                if (time == 0) {
                    this.cancel();
                    endGame();
                }
            }
        };

        if (!started) started = true;
        timer.runTaskTimer(OneNightCity.getInstance(), 0, 20);
     }


    public void endGame(){
        if(timer != null) timer.cancel();
        for(Player players : Bukkit.getOnlinePlayers()){
            players.getPlayer().teleport(new Location(players.getWorld(),122,154,-39));
            players.setGameMode(GameMode.ADVENTURE);
            board = new FastBoard(players);
            board.delete();
        }
        for(Team team : teams){
            team.reset();
        }
        teams.clear();
        players.clear();
        if(started) started = false;
    }




}
