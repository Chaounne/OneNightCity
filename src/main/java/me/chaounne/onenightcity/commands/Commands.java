package me.chaounne.onenightcity.commands;

import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.game.GenerateChest;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.Team;
import me.chaounne.onenightcity.villager.HenryEntity;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commands implements CommandExecutor {

    private ONCGame game;

    private List<ChatColor> availableColors = new ArrayList<>(Arrays.asList(ChatColor.values()));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED+"You must be a player to execute OneNightCity commands!");
            return false;
        }


        Player player = (Player) sender;
        if(command.getName().equals("test")) {
            GenerateChest generateChest = new GenerateChest();
            generateChest.spawnCoffre();
            player.sendMessage(("re"));
            return true;
        }

        // commande principale
        if(command.getName().equals("city")){
            if(args.length<=0){
                player.sendMessage(ChatColor.RED+"usage: /city <start|stop|team|entity>");
                return false;
            }
            game = ONCGame.getInstance();
            String subCommand = args[0];
            // sous commandes
            if(subCommand.equals("start")){
                if (!(sender.isOp())) {
                    sender.sendMessage(ChatColor.RED+"You must be an operator to execute OneNightCity commands!");
                    return false;
                }
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

                Bukkit.broadcastMessage(ChatColor.GREEN+"The game has started!");
                for( Player players : Bukkit.getOnlinePlayers()) {
                    players.teleport(new Location(player.getWorld(), 0, 70, 0));
                    players.setGameMode(GameMode.SURVIVAL);

                }

                return true;
            } else if(subCommand.equals("stop")){
                if (!(sender.isOp())) {
                    sender.sendMessage(ChatColor.RED+"You must be an operator to execute OneNightCity commands!");
                    return false;
                }
                if(!game.isStarted()){
                    player.sendMessage(ChatColor.RED+"The game is not started!");
                    return false;
                }
                game.endGame();
                Bukkit.broadcastMessage(ChatColor.GREEN+"The game has ended!");
                return true;
            }
            else if(subCommand.equals("team")){
                if(args.length<=1){
                    player.sendMessage(ChatColor.RED+"Usage : /city team <add|disband|create|list> <player|teamname>");
                    return false;
                }
                String teamCommand = args[1];
                if(teamCommand.equals("add")) {
                    Team team = GamePlayer.getInstance(player).getTeam();
                    if(team == null){
                        player.sendMessage(ChatColor.RED+"You are not in a team!");
                        return false;
                    }
                    if(args.length<=2){
                        player.sendMessage(ChatColor.RED+"Usage : /city team add <player>");
                        return false;
                    }
                    //get player
                    Player player1 = player.getServer().getPlayer(args[2]);
                    if(player1 == null){
                        player.sendMessage(ChatColor.RED+"Player not found!");
                        return false;
                    }
                    if(GamePlayer.getInstance(player1).getTeam() != null){
                        player.sendMessage(ChatColor.RED+"Player "+player1.getName()+" is already in a team!");
                        return false;
                    }
                    if(player1.equals(player)){
                        player.sendMessage(ChatColor.RED+"You are already in your team!");
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
                    if(!(player.equals(team.getLeader()))){
                        player.sendMessage(ChatColor.RED+"You are not the leader of your team!");
                        return false;
                    }

                    if(sender.isOp() && args.length>2){
                        String teamName = args[2];
                        for(Team t : game.getTeams()){
                            if(t.getName().equals(teamName)){
                                team = t;
                                break;
                            }
                        }
                        if(team == null){
                            player.sendMessage(ChatColor.RED+"Team not found!");
                            return false;
                        }
                    }

                    //remove team from players
                    for(Player p : team.getPlayers()){
                        p.sendMessage(ChatColor.RED+"Your team has been disbanded!");
                        team.removePlayer(p);
                        GamePlayer.getInstance(p).removeTeam();
                    }
                    game.removeTeam(team);
                } else if(teamCommand.equals("create")) {
                    if(args.length<=2){
                        player.sendMessage(ChatColor.RED+"Usage : /city team create <teamname>");
                        return false;
                    }
                    if(game.isStarted()){
                        player.sendMessage(ChatColor.RED+"The game is already started!");
                        return false;
                    }
                    if(args.length<=2){
                        player.sendMessage(ChatColor.RED+"Usage : /city team create <teamname>");
                        return false;
                    }
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
                    team.setLeader(player);

                    //set random color to a team
                    int random = (int) (Math.random() * availableColors.size());
                    ChatColor color = availableColors.get(random);
                    team.setColor(color);
                    team.getScoreboardTeam().setPrefix(color.toString());
                    team.getScoreboardTeam().setSuffix(ChatColor.RESET.toString());
                    availableColors.remove(random);

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
                    Team team = GamePlayer.getInstance(player).getTeam();
                    if(args.length<=2){
                        player.sendMessage(ChatColor.RED+"Usage : /city team remove <player>");
                        return false;
                    }
                    if(!player.equals(team.getLeader())){
                        player.sendMessage(ChatColor.RED+"You are not the leader of your team!");
                        return false;
                    }
                    //get player to remove
                    Player playerARemove = player.getServer().getPlayer(args[2]);
                    if(playerARemove == null){
                        player.sendMessage(ChatColor.RED+"Player not found!");
                        return false;
                    }

                    //remove player from team
                    GamePlayer.getInstance(playerARemove).getTeam().removePlayer(playerARemove);
                    GamePlayer.getInstance(playerARemove).removeTeam();
                    player.sendMessage(ChatColor.GREEN+"Player "+playerARemove.getName()+" removed from your team!");
                    playerARemove.sendMessage(ChatColor.GREEN+"You have been removed from "+team.getName()+"!");
                    return true;
                } else if(teamCommand.equals("list")) {
                    if(game.getTeams().size()<=0){
                        player.sendMessage(ChatColor.RED+"No teams!");
                        return false;
                    }
                    Team team = GamePlayer.getInstance(player).getTeam();
                    if(team == null){
                        player.sendMessage(ChatColor.RED+"You are not in a team!");
                        return false;
                    }
                    player.sendMessage(ChatColor.GREEN+"Team "+team.getName()+" :");
                    for(Player p : team.getPlayers()){
                        if(p.equals(team.getLeader())){
                            player.sendMessage(ChatColor.GREEN+"- "+ ChatColor.GOLD +p.getName()+ ChatColor.GREEN +" (leader)");
                        } else {
                            player.sendMessage(ChatColor.GREEN+"- "+p.getName());
                        }
                    }
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED+"Usage : /city team <add|disband|create|list> <player|teamname>");
                    return false;
                }
            }
            else if(subCommand.equals("entity")){
                if(args.length<=1){
                    player.sendMessage(ChatColor.RED+"Usage : /city entity <henry|??>");
                    return false;
                }
                String entityName = args[1];
                if(entityName.equals("henry")){
                    HenryEntity.getEntity(player.getLocation());
                    return true;
                }
            }
        } else{
            player.sendMessage(ChatColor.RED+"usage: /city <start|stop|team|entity>");
            return false;
        }
        return false;
    }
}
