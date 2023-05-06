package me.chaounne.onenightcity.commands;

import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.Team;
import me.chaounne.onenightcity.villager.*;
import org.bukkit.*;
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

        // commande principale
        if(command.getName().equals("city")){
            if(args.length<=0){
                player.sendMessage(ChatColor.RED+"usage: /city <start|poudre|stop|team|entity>");
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

                Bukkit.broadcastMessage(ChatColor.GREEN+"La partie commence !");
                for( Player players : Bukkit.getOnlinePlayers()) {
                    players.teleport(new Location(player.getWorld(), 0, 70, 0));
                    players.setBedSpawnLocation(new Location(player.getWorld(), 0, 71, 0),true);
                    players.setGameMode(GameMode.SURVIVAL);
                    players.sendTitle(ChatColor.RED + "La partie COMMENCE !", "Bonne chance à tous les joueurs !", 10, 70, 20);
                    players.playSound(players.getLocation(), Sound.ENTITY_VILLAGER_YES, 1f, 1f);


                }

                return true;
            }
            else if(subCommand.equals("stop")){
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
                    player.sendMessage(ChatColor.RED+"Usage : /city team <add|disband|create|list|remove> <player|teamname>");
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
                    game.addPlayer(GamePlayer.getInstance(player1));
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
                        game.removePlayer(GamePlayer.getInstance(p));
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
                    game.addTeam(team);
                    team.addPlayer(player);
                    team.setLeader(player);

                    //set random color to a team
                    int random = (int) (Math.random() * availableColors.size());
                    ChatColor color = availableColors.get(random);
                    team.setColor(color);
                    team.getScoreboardTeam().setPrefix(color+"["+teamName+"] ");
                    team.getScoreboardTeam().setSuffix(ChatColor.RESET+"");
                    availableColors.remove(random);

                    // set team to player
                    GamePlayer.getInstance(player).setTeam(team);
                    game.addPlayer(GamePlayer.getInstance(player));
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
                    game.removePlayer(GamePlayer.getInstance(playerARemove));
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
                    player.sendMessage(ChatColor.RED+"Usage : /city team <add|disband|create|list|remove> <player|teamname>");
                    return false;
                }
            }
            else if(subCommand.equals("entity")){
                if(args.length<=1){
                    player.sendMessage(ChatColor.RED+"Usage : /city entity <henry|??>");
                    return false;
                }
                String entityName = args[1];
                switch (entityName) {
                    case "henry":
                        HenryEntity.getEntity(player.getLocation());
                        return true;
                    case "jeaneau":
                        JeaneauEntity.getEntity(player.getLocation());
                        return true;

                    case "aypierre":
                        LesPierresEntity.getEntity(player.getLocation());
                        return true;
                    case "kiks":
                        KilianMBoufféEntity.getEntity(player.getLocation());
                        return true;
                    case "justin":
                        JustinPuechEntity.getEntity(player.getLocation());
                        return true;
                    case "cheep":
                        CheepCheapEntity.getEntity(player.getLocation());
                        return true;
                    case "mineur":
                        JeanMineurEntity.getEntity(player.getLocation());
                        return true;
                    case "dream":
                        DreamEntity.getEntity(player.getLocation());
                        return true;
                    case "legolas":
                        LegiasEntity.getEntity(player.getLocation());
                        return true;
                    case "potter":
                        JykaRoulerEntity.getEntity(player.getLocation());
                        return true;
                    case "francois":
                        ClodoFrancisEntity.getEntity(player.getLocation());
                        return true;
                    case "dose":
                        PfizerEntity.getEntity(player.getLocation());
                        return true;
                    case "lucie":
                        LucieAcierEntity.getEntity(player.getLocation());
                        return true;
                    case "cosmique":
                        DurifSylvainEntity.getEntity(player.getLocation());
                        return true;
                    case "nolife":
                        IkikomoriEntity.getEntity(player.getLocation());
                        return true;
                    case "neigeux":
                        NeigeuDemotEntity.getEntity(player.getLocation());
                        return true;
                    case "warden":
                        SombreHeroEntity.getEntity(player.getLocation());
                        return true;
                    case "plante":
                        BeauThonyEntity.getEntity(player.getLocation());
                        return true;
                    case "champi":
                        MicoseMicodeEntity.getEntity(player.getLocation());
                        return true;
                    case "util":
                        HutilItaireEntity.getEntity(player.getLocation());
                        return true;
                    default:
                        player.sendMessage(ChatColor.RED + "Usage : /city entity <henry|??>");
                        return false;
                }
            }
            else if(subCommand.equals("poudre")){
                if(!player.isOp()){
                    player.sendMessage(ChatColor.RED+"You are not allowed to do this!");
                    return true;
                }
                if(args.length<=1){
                    player.sendMessage(ChatColor.RED+"Usage : /city poudre <give|remove>");
                    return true;
                }
                String poudreCommand = args[1];
                if(poudreCommand.equals("give")){
                    if(args.length<=3){
                        player.sendMessage(ChatColor.RED+"Usage : /city poudre give <player> <amount>");
                        return true;
                    }
                    Player playerToGive = player.getServer().getPlayer(args[2]);
                    if(playerToGive == null){
                        player.sendMessage(ChatColor.RED+"Player not found!");
                        return true;
                    }
                    int amount = 0;
                    try{
                        amount = Integer.parseInt(args[3]);
                    } catch(NumberFormatException e){
                        player.sendMessage(ChatColor.RED+"Amount must be a number!");
                        return true;
                    }
                    if(amount<=0){
                        player.sendMessage(ChatColor.RED+"Amount must be positive!");
                        return true;
                    }
                    if(GamePlayer.getInstance(playerToGive).getTeam() == null){
                        player.sendMessage(ChatColor.RED+"Player is not in a team!");
                        return true;
                    }
                    GamePlayer.getInstance(playerToGive).addScore(amount);
                    GamePlayer.getInstance(playerToGive).getTeam().addScore(amount);
                    player.sendMessage(ChatColor.GREEN+"You gave "+amount+" poudre to "+playerToGive.getName()+"!");
                    playerToGive.sendMessage(ChatColor.GREEN+"You received "+amount+" poudre from "+player.getName()+"!");
                    return true;
                } else if(poudreCommand.equals("remove")){
                    if(args.length<=3){
                        player.sendMessage(ChatColor.RED+"Usage : /city poudre remove <player> <amount>");
                        return true;
                    }
                    Player playerToRemove = player.getServer().getPlayer(args[2]);
                    if(playerToRemove == null){
                        player.sendMessage(ChatColor.RED+"Player not found!");
                        return true;
                    }
                    int amount = 0;
                    try{
                        amount = Integer.parseInt(args[3]);
                    } catch(NumberFormatException e){
                        player.sendMessage(ChatColor.RED+"Amount must be a number!");
                        return true;
                    }
                    if(amount<=0){
                        player.sendMessage(ChatColor.RED+"Amount must be positive!");
                        return true;
                    }
                    if(GamePlayer.getInstance(playerToRemove).getTeam() == null){
                        player.sendMessage(ChatColor.RED+"Player is not in a team!");
                        return true;
                    }
                    GamePlayer.getInstance(playerToRemove).removeScore(amount);
                    GamePlayer.getInstance(playerToRemove).getTeam().removeScore(amount);
                    player.sendMessage(ChatColor.RED+"You removed "+amount+" poudre to "+playerToRemove.getName()+"!");
                    playerToRemove.sendMessage(ChatColor.RED+"You lost "+amount+" poudre from "+player.getName()+"!");
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED+"Usage : /city poudre <give|remove>");
                    return true;
                }

            }
        } else if (command.getName().equals("bounty")) {
            if(args.length<=1){
                player.sendMessage(ChatColor.RED+"Usage : /bounty <amount> <player> ");
                return true;
            }
            int amount = 0;
            try{
                amount = Integer.parseInt(args[0]);
            } catch(NumberFormatException e){
                player.sendMessage(ChatColor.RED+"Amount must be a number!");
                return true;
            }
            if(amount<=0){
                player.sendMessage(ChatColor.RED+"Amount must be positive!");
                return true;
            }
            Player playerToBounty = player.getServer().getPlayer(args[1]);
            if(playerToBounty == null){
                player.sendMessage(ChatColor.RED+"Player not found!");
                return true;
            }
            GamePlayer bountyPlayer = GamePlayer.getInstance(playerToBounty);
            GamePlayer gamePlayer = GamePlayer.getInstance(player);
            if(bountyPlayer.getTeam() == null){
                player.sendMessage(ChatColor.RED+"Player is not in a team!");
                return true;
            }
            if(bountyPlayer.getTeam().equals(gamePlayer.getTeam())){
                player.sendMessage(ChatColor.RED+"You can't bounty a player from your team!");
                return true;
            }
            if(gamePlayer.getScore()<amount){
                player.sendMessage(ChatColor.RED+"You don't have enough poudre!");
                return true;
            }
            gamePlayer.removeScore(amount);
            gamePlayer.getTeam().removeScore(amount);
            bountyPlayer.addScore(amount);
            bountyPlayer.getTeam().addScore(amount);
            bountyPlayer.setBounty(amount);
            bountyPlayer.setBeter(gamePlayer);

            Bukkit.broadcastMessage(ChatColor.GREEN+player.getName()+" bountied " + ChatColor.RED +amount+" powder to "+playerToBounty.getName()+"!");
            System.out.println(bountyPlayer.getBounty());
            return true;
        }

        return false;
    }
}
