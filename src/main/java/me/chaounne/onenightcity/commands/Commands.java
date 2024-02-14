package me.chaounne.onenightcity.commands;

import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.game.GenerateChest;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.Team;
import me.chaounne.onenightcity.villager.*;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Commands implements CommandExecutor {

    private ONCGame game;

    private List<ChatColor> availableColors = new ArrayList<>(Arrays.asList(ChatColor.values()));
    World world = Bukkit.getWorlds().get(0); // Récupère le premier monde de la liste

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Vous devez être un joueur pour exécuter cette commande !");
            return false;
        }

        Player player = (Player) sender;
        Location location = new Location(world, 23, 67, 1);

        // commande principale
        if(command.getName().equals("city")){
            if(args.length<=0){
                player.sendMessage(ChatColor.RED+"/city <start|poudre|stop|team|entity>");
                return false;
            }

            game = ONCGame.getInstance();
            String subCommand = args[0];
            if(subCommand.equals("chest") && sender instanceof Player){
                player = (Player) sender;
                Location playerLocation = player.getLocation();
                GenerateChest.spawnChest(playerLocation);
                player.sendMessage("Coffre placé");
            }
            // sous commandes
            if (subCommand.equals("start")) {
                if (!(sender.isOp())) {
                    sender.sendMessage(ChatColor.RED + "Vous devez être OP pour exécuter cette commande !");
                    return false;
                }
                if (game.getTeams().size() <= 1) {
                    player.sendMessage(ChatColor.RED + "Vous avez besoin d'au moins 2 équipes pour démarrer le jeu !");
                    return false;
                }
                if (GamePlayer.getInstance(player).getTeam() == null) {
                    player.sendMessage(ChatColor.RED + "Vous n'êtes pas dans une équipe !");
                    return false;
                }
                if (game.isStarted()) {
                    player.sendMessage(ChatColor.RED + "Le jeu est déjà en cours !");
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

        } else if (subCommand.equals("stop")) {
            if (!(sender.isOp())) {
                sender.sendMessage(ChatColor.RED + "Vous devez être opérateur pour exécuter les commandes OneNightCity !");
                return false;
            }
            if (!game.isStarted()) {
                player.sendMessage(ChatColor.RED + "Le jeu n'est pas démarré !");
                return false;
            }
            game.endGame();
           return true;
            }
            else if (subCommand.equals("team")) {
                if (args.length <= 1) {
                    player.sendMessage(ChatColor.RED + "Utilisation : /city team <add|disband|create|list|remove> <joueur|noméquipe>");
                    return false;
                }
                String teamCommand = args[1];
                if (teamCommand.equals("add")) {
                    Team team = GamePlayer.getInstance(player).getTeam();
                    if (team == null) {
                        player.sendMessage(ChatColor.RED + "Vous n'êtes pas dans une équipe !");
                        return false;
                    }
                    if (args.length <= 2) {
                        player.sendMessage(ChatColor.RED + "Utilisation : /city team add <joueur>");
                        return false;
                    }
                    // obtenir le joueur
                    Player player1 = player.getServer().getPlayer(args[2]);
                    if (player1 == null) {
                        player.sendMessage(ChatColor.RED + "Joueur introuvable !");
                        return false;
                    }
                    if (GamePlayer.getInstance(player1).getTeam() != null) {
                        player.sendMessage(ChatColor.RED + "Le joueur " + player1.getName() + " est déjà dans une équipe !");
                        return false;
                    }
                    if (player1.equals(player)) {
                        player.sendMessage(ChatColor.RED + "Vous êtes déjà dans votre équipe !");
                        return false;
                    }
                    // ajouter le joueur à l'équipe
                    team.addPlayer(player1);
                    GamePlayer.getInstance(player1).setTeam(team);
                    game.addPlayer(GamePlayer.getInstance(player1));
                    player.sendMessage(ChatColor.GREEN + "Joueur " + player1.getName() + " ajouté à votre équipe !");
                    player1.sendMessage(ChatColor.GREEN + "Vous avez été ajouté à " + team.getName() + " !");
                    return true;
                } else if (teamCommand.equals("disband")) {
                    Team team = GamePlayer.getInstance(player).getTeam();
                    if (team == null) {
                        player.sendMessage(ChatColor.RED + "Vous n'êtes pas dans une équipe !");
                        return false;
                    }
                    if (!(player.equals(team.getLeader()))) {
                        player.sendMessage(ChatColor.RED + "Vous n'êtes pas le chef de votre équipe !");
                        return false;
                    }
                    if (sender.isOp() && args.length > 2) {
                        String teamName = args[2];
                        for (Team t : game.getTeams()) {
                            if (t.getName().equals(teamName)) {
                                team = t;
                                break;
                            }
                        }
                        if (team == null) {
                            player.sendMessage(ChatColor.RED + "Équipe introuvable !");
                            return false;
                        }
                    }
                    // retirer l'équipe des joueurs
                    Iterator<Player> iterator = team.getPlayers().iterator();
                    while (iterator.hasNext()) {
                        Player p = iterator.next();
                        p.sendMessage(ChatColor.RED + "Votre équipe a été dissoute !");
                        iterator.remove(); // Utiliser l'itérateur pour supprimer l'élément
                        GamePlayer.getInstance(p).removeTeam();
                        game.removePlayer(GamePlayer.getInstance(p));
                        // Supprimer l'équipe dissoute
                        game.removeTeam(team);
                        // Désenregistrer l'équipe du scoreboard
                        team.getScoreboardTeam().unregister();
                    }
                    game.removeTeam(team);
                } else if (teamCommand.equals("create")) {
                    if (args.length <= 2) {
                        player.sendMessage(ChatColor.RED + "Utilisation : /city team create <noméquipe>");
                        return false;
                    }
                    if (game.isStarted()) {
                        player.sendMessage(ChatColor.RED + "Le jeu a déjà commencé !");
                        return false;
                    }
                    String teamName = args[2];
                    if (GamePlayer.getInstance(player).getTeam() != null) {
                        player.sendMessage(ChatColor.RED + "Vous êtes déjà dans une équipe !");
                        return false;
                    }
                    for (Team team : game.getTeams()) {
                        if (team.getName().equals(teamName)) {
                            player.sendMessage(ChatColor.RED + "L'équipe " + teamName + " existe déjà !");
                            return false;
                        }
                    }
                    // créer une nouvelle équipe
                    Team team = new Team(teamName);
                    game.addTeam(team);
                    team.addPlayer(player);
                    team.setLeader(player);
                    // attribuer une couleur aléatoire à une équipe
                    int random = (int) (Math.random() * availableColors.size());
                    ChatColor color = availableColors.get(random);
                    team.setColor(color);
                    team.getScoreboardTeam().setPrefix(color + "[" + teamName + "] ");
                    team.getScoreboardTeam().setSuffix(ChatColor.RESET + "");
                    availableColors.remove(random);
                    // attribuer l'équipe au joueur
                    GamePlayer.getInstance(player).setTeam(team);
                    game.addPlayer(GamePlayer.getInstance(player));
                    player.sendMessage(ChatColor.GREEN + "Vous avez créé l'équipe " +team.getColor()+ teamName +ChatColor.GREEN +  " !");
                    return true;
                } else if (teamCommand.equals("remove")) {
                    if (GamePlayer.getInstance(player).getTeam() == null) {
                        player.sendMessage(ChatColor.RED + "Vous n'êtes pas dans une équipe !");
                        return false;
                    }
                    Team team = GamePlayer.getInstance(player).getTeam();
                    if (args.length <= 2) {
                        player.sendMessage(ChatColor.RED + "Utilisation : /city team remove <joueur>");
                        return false;
                    }
                    if (!player.equals(team.getLeader())) {
                        player.sendMessage(ChatColor.RED + "Vous n'êtes pas le chef de votre équipe !");
                        return false;
                    }
                    // obtenir le joueur à supprimer
                    Player playerARemove = player.getServer().getPlayer(args[2]);
                    if (playerARemove == null) {
                        player.sendMessage(ChatColor.RED + "Joueur introuvable !");
                        return false;
                    }
                    // supprimer le joueur de l'équipe
                    GamePlayer.getInstance(playerARemove).getTeam().removePlayer(playerARemove);
                    GamePlayer.getInstance(playerARemove).removeTeam();
                    game.removePlayer(GamePlayer.getInstance(playerARemove));
                    player.sendMessage(ChatColor.GREEN + "Joueur " + playerARemove.getName() + " supprimé de votre équipe !");
                    playerARemove.sendMessage(ChatColor.GREEN + "Vous avez été supprimé de " + team.getName() + " !");
                    return true;
                }  else if (teamCommand.equals("leave")) {
                GamePlayer gamePlayer = GamePlayer.getInstance(player);
                Team team = gamePlayer.getTeam();
                if (team == null) {
                    player.sendMessage(ChatColor.RED + "Vous n'êtes pas dans une équipe !");
                    return false;
                }
                team.removePlayer(player);
                gamePlayer.removeTeam();
                game.removePlayer(gamePlayer);
                player.sendMessage(ChatColor.GREEN + "Vous avez quitté votre équipe !");
                return true;

                } else if (teamCommand.equals("list")) {
                    if (game.getTeams().size() <= 0) {
                        player.sendMessage(ChatColor.RED + "Aucune équipe !");
                        return false;
                    }
                    Team team = GamePlayer.getInstance(player).getTeam();
                    if (team == null) {
                        player.sendMessage(ChatColor.RED + "Vous n'êtes pas dans une équipe !");
                        return false;
                    }
                    player.sendMessage(ChatColor.GREEN + "Équipe " + team.getName() + " :");
                    for (Player p : team.getPlayers()) {
                        if (p.equals(team.getLeader())) {
                            player.sendMessage(ChatColor.GREEN + "- " + ChatColor.GOLD + p.getName() + ChatColor.GREEN + " (chef)");
                        } else {
                            player.sendMessage(ChatColor.GREEN + "- " + p.getName());
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
                    case "spawn":
                        // Liste des noms des entités à supprimer

// Parcours de toutes les entités du monde


                                // Vérifie si l'entité est une LivingEntity
                                for (Entity entity : world.getEntities()) {

                                        if (entity instanceof LivingEntity && entity.getName().equals("Henry")) {
                                            entity.remove();
                                            System.out.println("HENRY");
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("Jeaneau")) {
                                            entity.remove();
                                            System.out.println("AUTRE");
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("kiks")) {
                                            entity.remove();
                                            System.out.println("AUTRE");
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("aypierre")) {
                                            entity.remove();
                                            System.out.println("AUTRE");

                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("cheep")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("mineur")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("dream")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("legolas")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("potter")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("francois")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("dose")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("lucie")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("cosmique")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("nolife")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("neigeux")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("warden")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("plante")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("champi")) {
                                            entity.remove();
                                        }
                                        if (entity instanceof LivingEntity && entity.getName().equals("util")) {
                                            entity.remove();
                                        }

                                }



                        HenryEntity.getEntity( new Location(Bukkit.getWorld("world"), -25 , 69, -62)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        LesPierresEntity.getEntity( new Location(Bukkit.getWorld("world"), -21 , 70, 17)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        KilianMBoufféEntity.getEntity( new Location(Bukkit.getWorld("world"), -14 , 70, 22)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        HutilItaireEntity.getEntity( new Location(Bukkit.getWorld("world"), -20 , 71, -20)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        CheepCheapEntity.getEntity( new Location(Bukkit.getWorld("world"), 57 , 69, -17)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        JeanMineurEntity.getEntity( new Location(Bukkit.getWorld("world"), -19 , 70, 21)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        DreamEntity.getEntity( new Location(Bukkit.getWorld("world"), 45 , 68, -11)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        LegiasEntity.getEntity( new Location(Bukkit.getWorld("world"), 55 , 68, 4)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        JykaRoulerEntity.getEntity( new Location(Bukkit.getWorld("world"), 50 , 68, -11)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        ClodoFrancisEntity.getEntity( new Location(Bukkit.getWorld("world"), 44 , 68, -18)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        PfizerEntity.getEntity( new Location(Bukkit.getWorld("world"), 17 , 70, 22)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        LucieAcierEntity.getEntity( new Location(Bukkit.getWorld("world"), 22 , 70, 16)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        DurifSylvainEntity.getEntity( new Location(Bukkit.getWorld("world"), 22 , 70, 21)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        IkikomoriEntity.getEntity( new Location(Bukkit.getWorld("world"), 21 , 71, -20)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        NeigeuDemotEntity.getEntity( new Location(Bukkit.getWorld("world"), 22 , 71, -16)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        SombreHeroEntity.getEntity( new Location(Bukkit.getWorld("world"), 16 , 71, -21)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        BeauThonyEntity.getEntity( new Location(Bukkit.getWorld("world"), -16 , 71, -20)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        MicoseMicodeEntity.getEntity( new Location(Bukkit.getWorld("world"), -21 , 71, -15)
                        ); // Supposons que 100, 70, 100 sont les coordonnées où vous voulez faire apparaître l'entité

                        return true;


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

            else if (subCommand.equals("poudre")) {
                if (!player.isOp()) {
                    player.sendMessage(ChatColor.RED + "Vous n'êtes pas autorisé à faire cela !");
                    return true;
                }
                if (args.length <= 1) {
                    player.sendMessage(ChatColor.RED + "Utilisation : /city poudre <give|remove>");
                    return true;
                }
                String poudreCommand = args[1];
                if (poudreCommand.equals("give")) {
                    if (args.length <= 3) {
                        player.sendMessage(ChatColor.RED + "Utilisation : /city poudre give <joueur> <montant>");
                        return true;
                    }
                    Player playerToGive = player.getServer().getPlayer(args[2]);
                    if (playerToGive == null) {
                        player.sendMessage(ChatColor.RED + "Joueur introuvable !");
                        return true;
                    }
                    int amount = 0;
                    try {
                        amount = Integer.parseInt(args[3]);
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "Le montant doit être un nombre !");
                        return true;
                    }
                    if (amount <= 0) {
                        player.sendMessage(ChatColor.RED + "Le montant doit être positif !");
                        return true;
                    }
                    if (GamePlayer.getInstance(playerToGive).getTeam() == null) {
                        player.sendMessage(ChatColor.RED + "Le joueur n'est pas dans une équipe !");
                        return true;
                    }
                    GamePlayer.getInstance(playerToGive).addScore(amount);
                    GamePlayer.getInstance(playerToGive).getTeam().addScore(amount);
                    player.sendMessage(ChatColor.GREEN + "Vous avez donné " + amount + " poudre à " + playerToGive.getName() + " !");
                    playerToGive.sendMessage(ChatColor.GREEN + "Vous avez reçu " + amount + " poudre de la part de " + player.getName() + " !");
                    return true;
                } else if (poudreCommand.equals("remove")) {
                    if (args.length <= 3) {
                        player.sendMessage(ChatColor.RED + "Utilisation : /city poudre remove <joueur> <montant>");
                        return true;
                    }
                    Player playerToRemove = player.getServer().getPlayer(args[2]);
                    if (playerToRemove == null) {
                        player.sendMessage(ChatColor.RED + "Joueur introuvable !");
                        return true;
                    }
                    int amount = 0;
                    try {
                        amount = Integer.parseInt(args[3]);
                    } catch (NumberFormatException e) {
                        player.sendMessage(ChatColor.RED + "Le montant doit être un nombre !");
                        return true;
                    }
                    if (amount <= 0) {
                        player.sendMessage(ChatColor.RED + "Le montant doit être positif !");
                        return true;
                    }
                    if (GamePlayer.getInstance(playerToRemove).getTeam() == null) {
                        player.sendMessage(ChatColor.RED + "Le joueur n'est pas dans une équipe !");
                        return true;
                    }
                    GamePlayer.getInstance(playerToRemove).removeScore(amount);
                    GamePlayer.getInstance(playerToRemove).getTeam().removeScore(amount);
                    player.sendMessage(ChatColor.RED + "Vous avez retiré " + amount + " poudre à " + playerToRemove.getName() + " !");
                    playerToRemove.sendMessage(ChatColor.RED + "Vous avez perdu " + amount + " poudre de la part de " + player.getName() + " !");
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "Utilisation : /city poudre <give|remove>");
                    return true;
                }
            }

        } /*else if (command.getName().equals("bounty")) {
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
        }*/

        return false;
    }
}
