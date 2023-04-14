package me.chaounne.onenightcity.commands;

import me.chaounne.onenightcity.OneNightCity;
import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commands implements CommandExecutor {

    private ONCGame game = ONCGame.getInstance();

    private List<ChatColor> availableColors = new ArrayList<>(Arrays.asList(ChatColor.values()));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+"You must be a player to execute OneNightCity commands!");
            return false;
        }
        if (!(sender.isOp())) {
            sender.sendMessage(ChatColor.RED+"You must be an operator to execute OneNightCity commands!");
            return false;
        }

        Player player = (Player) sender;


        // commande principale
        if(command.getName().equals("city")){
            String subCommand = args[0];

            // sous commandes
            if(subCommand.equals("start")){
                if(game.getTeams().size()<=1){
                    player.sendMessage(ChatColor.RED+"You need at least 2 teams to start the game!");
                    return false;
                }
                if(GamePlayer.getInstance(player).getTeam() == null){
                    player.sendMessage(ChatColor.RED+"You are not in a team!");
                    return false;
                }
                if(game.isStarted()){
                    player.sendMessage(ChatColor.RED+"The game is already started!");
                    return false;
                }
                game.startGame();
                return true;
            } else if(subCommand.equals("stop")){
                if(!game.isStarted()){
                    player.sendMessage(ChatColor.RED+"The game is not started!");
                    return false;
                }
                game.endGame();
                return true;
            }
            else if(subCommand.equals("team")){
                String teamCommand = args[1];
                if(teamCommand.equals("add")) {
                    Team team = GamePlayer.getInstance(player).getTeam();
                    if(team == null){
                        player.sendMessage(ChatColor.RED+"You are not in a team!");
                        return false;
                    }

                    //get player
                    Player player1 = player.getServer().getPlayer(args[2]);
                    if(player1 == null){
                        player.sendMessage(ChatColor.RED+"Player not found!");
                        return false;
                    }

                    //add player to team
                    team.addPlayer(player1);
                    GamePlayer.getInstance(player1).setTeam(team);
                    player.sendMessage(ChatColor.GREEN+"Player "+player1.getName()+" added to your team!");
                    player1.sendMessage(ChatColor.GREEN+"You have been added to "+team.getName()+"!");
                    return true;
                } else if(teamCommand.equals("disband")) {
                    Team team = GamePlayer.getInstance(player).getTeam();
                    if(team == null){
                        player.sendMessage(ChatColor.RED+"You are not in a team!");
                        return false;
                    }

                    //remove team from players
                    for(Player p : team.getPlayers()){
                        p.sendMessage(ChatColor.RED+"Your team has been disbanded!");
                        team.removePlayer(p);
                        GamePlayer.getInstance(p).removeTeam();
                    }
                    game.removeTeam(team);
                } else if(teamCommand.equals("create")) {
                    String teamName = args[2];
                    if(GamePlayer.getInstance(player).getTeam() != null){
                        player.sendMessage(ChatColor.RED+"You are already in a team!");
                        return false;
                    }
                    for(Team team : game.getTeams()){
                        if(team.getName().equals(teamName)){
                            player.sendMessage(ChatColor.RED+"Team "+teamName+" already exists!");
                            return false;
                        }
                    }
                    //create a new team
                    Team team = new Team(teamName);
                    team.addPlayer(player);

                    //set random color to a team
                    int random = (int) (Math.random() * availableColors.size());
                    ChatColor color = availableColors.get(random);
                    availableColors.remove(random);
                    team.setColor(color);

                    // set team to player
                    GamePlayer.getInstance(player).setTeam(team);
                    game.addTeam(team);
                    player.sendMessage(ChatColor.GREEN+"Team "+teamName+" created!");
                    return true;
                } else if(teamCommand.equals("remove")) {
                    if(GamePlayer.getInstance(player).getTeam() == null){
                        player.sendMessage(ChatColor.RED+"You are not in a team!");
                        return false;
                    }

                    //get player to remove
                    Player player1 = player.getServer().getPlayer(args[2]);
                    if(player1 == null){
                        player.sendMessage(ChatColor.RED+"Player not found!");
                        return false;
                    }

                    //remove player from team
                    GamePlayer.getInstance(player1).getTeam().removePlayer(player1);
                    GamePlayer.getInstance(player1).removeTeam();
                    player.sendMessage(ChatColor.GREEN+"Player "+player1.getName()+" removed from your team!");
                    player1.sendMessage(ChatColor.GREEN+"You have been removed from "+GamePlayer.getInstance(player).getTeam().getName()+"!");
                    return true;
                }
            }
        }


        return false;
    }
}
