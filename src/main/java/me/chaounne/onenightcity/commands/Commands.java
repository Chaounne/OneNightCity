package me.chaounne.onenightcity.commands;

import me.chaounne.onenightcity.OneNightCity;
import me.chaounne.onenightcity.events.JumpHandler;
import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.game.GameTeam;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.jump.JumpManager;
import me.chaounne.onenightcity.utils.ColorHelper;
import me.chaounne.onenightcity.utils.RandomFromList;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Vous devez être un joueur pour exécuter cette commande !");
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.RED + "/city <calc | powder | start | stop | team>");
            return false;
        }

        ONCGame game = ONCGame.getInstance();

        String subCommand = args[0];
        if (subCommand.equals("calc")) {
            if (args.length != 4) {
                player.sendMessage(ChatColor.RED + "Usage : /city calc <trade_price> <trade_amount> <item_stack>");
                return false;
            }

            int tradePrice, tradeAmount, itemStack;
            try {
                tradePrice = Integer.parseInt(args[1]);
                tradeAmount = Integer.parseInt(args[2]);
                itemStack = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                player.sendMessage(ChatColor.RED + "Les arguments doivent être des nombres entiers.");
                return false;
            }

            double itemValue = (double) tradePrice / tradeAmount;
            int stack = (int) (itemValue * itemStack);
            int chest = stack * 27;
            int inv = stack * 37;
            player.sendMessage(String.format("§6Item = §l%.1f§r§6 | Stack = §l%d§r§6 | Chest = §l%d§r§6 | Inv = §l%d§r§6", itemValue, stack, chest, inv));
            return true;
        }
        else if (subCommand.equals("jump")) {
            if (args.length != 2) {
                player.sendMessage(ChatColor.RED + "Usage : /city jump <restart | start | stop>");
                return false;
            }
            if (game.hasStarted()) {
                player.sendMessage(ChatColor.RED + "Vous ne pouvez pas aire ça pendant une partie !");
                return false;
            }

            JumpHandler jumpHandler = OneNightCity.getInstance().getJumpHandler();

            switch (args[1]) {
                case "restart": {
                    if (!jumpHandler.getPlayerManagers().containsKey(player)) {
                        player.sendMessage(ChatColor.RED + "Vous n'avez pas commencé le jump.");
                        return false;
                    }
                    jumpHandler.deactivateJumpManagerForPlayer(player);
                    JumpManager jumpManager = new JumpManager(player);
                    jumpHandler.getPlayerManagers().put(player, jumpManager);
                    jumpManager.teleport();
                    break;
                }
                case "start": {
                    if (jumpHandler.getPlayerManagers().containsKey(player)) {
                        player.sendMessage(ChatColor.RED + "Vous avez déjà commencé le jump.");
                        return false;
                    }
                    JumpManager jumpManager = new JumpManager(player);
                    jumpHandler.getPlayerManagers().put(player, jumpManager);
                    jumpManager.teleport();
                    break;
                }
                case "stop": {
                    if (!jumpHandler.getPlayerManagers().containsKey(player)) {
                        player.sendMessage(ChatColor.RED + "Vous n'avez pas commencé le jump.");
                        return false;
                    }
                    jumpHandler.deactivateJumpManagerForPlayer(player);
                    break;
                }
                default: {
                    player.sendMessage(ChatColor.RED + "Usage : /city jump <restart | start | stop>");
                    return false;
                }
            }
            return true;
        }
        else if (subCommand.equals("start")) {
            GamePlayer gamePlayer = GamePlayer.getInstance(player);

            GameTeam team = gamePlayer.getTeam();
            if (team != null && team.getPlayers().isEmpty()) {
                game.removeTeam(team);
            }
            if (!(sender.isOp())) {
                sender.sendMessage(ChatColor.RED + "Vous devez être OP pour exécuter cette commande !");
                return false;
            }
            if (game.getTeams().size() <= 1) {
                player.sendMessage(ChatColor.RED + "Il doit y avoir au moins 2 équipes pour démarrer le jeu !");
                return false;
            }
            if (GamePlayer.getInstance(player).getTeam() == null) {
                player.sendMessage(ChatColor.RED + "Vous n'êtes pas dans une équipe !");
                return false;
            }
            if (game.hasStarted()) {
                player.sendMessage(ChatColor.RED + "Le jeu est déjà en cours !");
                return false;
            }

            World world = Bukkit.getWorlds().get(0);
            world.setGameRule(GameRule.FALL_DAMAGE, true);
            world.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, true);

            game.startGame();

            Bukkit.broadcastMessage(ChatColor.GREEN + "La partie commence !");
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.teleport(new Location(player.getWorld(), 0, 70, 0));
                players.setBedSpawnLocation(new Location(player.getWorld(), 0, 71, 0),true);
                players.setGameMode(GameMode.SURVIVAL);
                players.sendTitle(ChatColor.RED + "La partie COMMENCE !", "Bonne chance à tous les joueurs !", 10, 70, 20);
                players.playSound(players.getLocation(), Sound.ENTITY_VILLAGER_YES, 1f, 1f);
            }

            return true;
        }
        else if (subCommand.equals("stop")) {
            if (!(sender.isOp())) {
                sender.sendMessage(ChatColor.RED + "Vous devez être OP pour exécuter cette commande.");
                return false;
            }
            if (!game.hasStarted()) {
                player.sendMessage(ChatColor.RED + "Le jeu n'a pas démarré !");
                return false;
            }

            game.endGame();
            return true;
        }
        else if (subCommand.equals("team")) {
            if (args.length == 1) {
                player.sendMessage(ChatColor.RED + "Usage : /city team <color | create | disband | fire | hire | leave | members | marianne | purge | rename> <player | teamname>");
                return false;
            }
            String teamCommand = args[1];
            switch (teamCommand) {
                case "color": {
                    if (GamePlayer.getInstance(player).getTeam() == null) {
                        player.sendMessage(ChatColor.RED + "Vous devez etre dans une team !");
                        return false;
                    }
                    GameTeam team = GamePlayer.getInstance(player).getTeam();
                    if (args.length == 2) {
                        player.sendMessage(ChatColor.RED + "Usage : /city team color <COLOR>");
                        return false;
                    }

                    String colorString = args[2]; // Récupérer la couleur spécifiée en argument

                    ChatColor chosenColor;

                    // Vérifier si la couleur spécifiée existe
                    try {
                        chosenColor = ChatColor.valueOf(colorString.toUpperCase());
                    } catch (IllegalArgumentException e) {

                        player.sendMessage(ChatColor.RED + "Couleur introuvable ! Voici les couleurs disponibles : ");
                        StringBuilder availableColorsMessage = new StringBuilder();
                        for (ChatColor color : ChatColor.values()) {
                            if (color.isColor())
                                availableColorsMessage.append(color).append(color.name()).append(ChatColor.RESET).append(", ");
                        }
                        String colorsList = availableColorsMessage.toString();
                        // Retirer la virgule et l'espace en trop à la fin de la liste
                        colorsList = colorsList.substring(0, colorsList.length() - 2);
                        player.sendMessage(colorsList);
                        return false;
                    }
                    // Définir la couleur de l'équipe
                    if (chosenColor.isFormat()) {
                        player.sendMessage(ChatColor.RED + "Vous ne pouvez pas utiliser de format, seulement des couleurs !");
                        return false;
                    }
                    team.setColor(chosenColor);
                    team.getScoreboardTeam().setColor(chosenColor);
                    team.getScoreboardTeam().setPrefix("[" + team.getName() + "] ");
                    player.sendMessage("Vous avez changé de couleur " + team.getColor() + chosenColor);
                    break;
                }
                case "rename": {
                    if (GamePlayer.getInstance(player).getTeam() == null) {
                        player.sendMessage(ChatColor.RED + "Vous devez être dans une team !");
                        return false;
                    }

                    GameTeam playerTeam = GamePlayer.getInstance(player).getTeam();
                    if (args.length == 2) {
                        player.sendMessage(ChatColor.RED + "Usage : /city team rename <newname>");
                        return false;
                    }

                    String teamName = args[2];
                    teamName = teamName.replace("_", " ");
                    if (teamName.length() > 20) {
                        player.sendMessage(ChatColor.RED + "Nom de team trop long (20 caractères max)");
                        return false;
                    }

                    for (GameTeam team : game.getTeams()) {
                        if (team.getName().equals(teamName)) {
                            player.sendMessage(ChatColor.RED + "L'équipe " + teamName + " existe déjà !");
                            return false;
                        }
                    }


                    playerTeam.setName(teamName);
                    playerTeam.getScoreboardTeam().setColor(playerTeam.getColor());
                    playerTeam.getScoreboardTeam().setPrefix("[" + playerTeam.getName() + "] ");
                    player.sendMessage("Vous avez changé de nom : " + playerTeam.getName() + " !");

                    break;
                }
                case "hire": {
                    GameTeam team = GamePlayer.getInstance(player).getTeam();
                    if (team == null) {
                        player.sendMessage(ChatColor.RED + "Vous n'êtes pas dans une équipe !");
                        return false;
                    }
                    if (args.length == 2) {
                        player.sendMessage(ChatColor.RED + "Usage : /city team hire <joueur>");
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
                }
                case "disband": {
                    if (game.hasStarted()) {
                        player.sendMessage(ChatColor.RED + "Vous ne pouvez pas faire ça lorsque la partie est en cours.");
                        return false;
                    }
                    GameTeam team = GamePlayer.getInstance(player).getTeam();
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
                        for (GameTeam t : game.getTeams()) {
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
                        GamePlayer.getInstance(p).setTeam(null);
                        game.removePlayer(GamePlayer.getInstance(p));
                        // Supprimer l'équipe dissoute
                        game.removeTeam(team);
                        // Désenregistrer l'équipe du scoreboard
                        team.getScoreboardTeam().unregister();
                    }
                    game.removeTeam(team);
                    break;
                }
                case "create": {
                    if (args.length == 2) {
                        player.sendMessage(ChatColor.RED + "Usage : /city team create <team_name>");
                        return false;
                    }
                    if (game.hasStarted()) {
                        player.sendMessage(ChatColor.RED + "Vous ne pouvez pas faire ça lorsque la partie est en cours.");
                        return false;
                    }
                    String teamName = args[2];
                    teamName = teamName.replace("_", " ");
                    if (teamName.length() > 20) {
                        player.sendMessage(ChatColor.RED + "Nom de team trop long (20 caractères max)");
                        return false;
                    }
                    if (GamePlayer.getInstance(player).getTeam() != null) {
                        player.sendMessage(ChatColor.RED + "Vous êtes déjà dans une équipe !");
                        return false;
                    }
                    for (GameTeam team : game.getTeams()) {
                        if (team.getName().equals(teamName)) {
                            player.sendMessage(ChatColor.RED + "L'équipe " + teamName + " existe déjà !");
                            return false;
                        }
                    }
                    // créer une nouvelle équipe
                    GameTeam team = new GameTeam(teamName);
                    game.addTeam(team);
                    team.addPlayer(player);
                    team.setLeader(player);
                    List<ChatColor> availableColors = new ArrayList<>(Arrays.asList(ChatColor.values()));
                    // attribuer une couleur aléatoire à une équipe
                    int random = (int) (Math.random() * availableColors.size());
                    ChatColor color = availableColors.get(random);

                    // Vérifier si la couleur est une couleur et non un format
                    while (color.isFormat()) {
                        random = (int) (Math.random() * availableColors.size());
                        color = availableColors.get(random);
                    }
                    team.setColor(color);
                    team.getScoreboardTeam().setColor(color);
                    team.getScoreboardTeam().setPrefix("[" + teamName + "] ");
                    availableColors.remove(random);
                    // attribuer l'équipe au joueur
                    GamePlayer.getInstance(player).setTeam(team);
                    game.addPlayer(GamePlayer.getInstance(player));
                    player.sendMessage(ChatColor.GREEN + "Vous avez créé l'équipe " + team.getColor() + teamName + ChatColor.GREEN + " !");
                    return true;
                }
                case "fire": {
                    if (GamePlayer.getInstance(player).getTeam() == null) {
                        player.sendMessage(ChatColor.RED + "Vous n'êtes pas dans une équipe !");
                        return false;
                    }
                    GameTeam team = GamePlayer.getInstance(player).getTeam();
                    if (args.length == 2) {
                        player.sendMessage(ChatColor.RED + "Usage : /city team fire <joueur>");
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
                    game.removePlayer(GamePlayer.getInstance(playerARemove));
                    player.sendMessage(ChatColor.GREEN + "Joueur " + playerARemove.getName() + " supprimé de votre équipe !");
                    playerARemove.sendMessage(ChatColor.GREEN + "Vous avez été supprimé de " + team.getName() + " !");
                    return true;
                }
                case "marianne": {
                    if (!(sender.isOp())) {
                        sender.sendMessage(ChatColor.RED + "Vous devez être OP pour exécuter cette commande !");
                        return false;
                    }
                    if (args.length == 2) {
                        player.sendMessage(ChatColor.RED + "Usage : /city team marianne <team_amount>");
                        return false;
                    }
                    if (game.hasStarted()) {
                        player.sendMessage(ChatColor.RED + "Vous ne pouvez pas faire ça lorsque la partie est en cours.");
                        return false;
                    }
                    String nbTeamStr = args[2];
                    int nbTeam;
                    try {
                        nbTeam = Integer.parseInt(nbTeamStr);
                    } catch (Exception e) {
                        player.sendMessage(ChatColor.RED + "Le nombre d'équipe est invalide, ce doit être un nombre !");
                        return false;
                    }
                    if (nbTeam < 2) {
                        player.sendMessage(ChatColor.RED + "Nombre d'équipe trop faible, le nombre d'équipe doit être supérieur ou égal à 2");
                        return false;
                    }
                    Collection<? extends Player> players = Bukkit.getOnlinePlayers();
                    if (nbTeam > players.size()) {
                        player.sendMessage(ChatColor.RED + "Nombre d'équipe trop élevé choisi, le nombre d'équipe doit être inférieur ou égal au nombre de joueur");
                        return false;
                    }
                    for (Player p : players) {
                        GameTeam team = GamePlayer.getInstance(p).getTeam();

                        if (team != null) {
                            team.reset();
                            ONCGame.getInstance().removeTeam(team);
                        }
                    }

                    List<Player> unassignedPlayers = new ArrayList<>(players);

                    GameTeam[] teams = new GameTeam[nbTeam];
                    // création des équipes
                    for (int i = 0; i < nbTeam; i++) {

                        GameTeam team = new GameTeam("Team " + (i + 1));
                        game.addTeam(team);
                        teams[i] = team;
                        team.setColor(ColorHelper.getRandomChatColor());
                        Player teamLeader = RandomFromList.get(unassignedPlayers);
                        teamLeader.sendMessage(ChatColor.AQUA + "Vous avez été séléctionné comme leader de l'équipe " + team.getName());
                        team.addPlayer(teamLeader);
                        team.setLeader(teamLeader);
                        unassignedPlayers.remove(teamLeader);
                        GamePlayer.getInstance(teamLeader).setTeam(team);
                        game.addPlayer(GamePlayer.getInstance(teamLeader));
                        team.getScoreboardTeam().setColor(team.getColor());
                        team.getScoreboardTeam().setPrefix("[" + team.getName() + "] ");
                    }
                    int teamIndex = 0;
                    while (!unassignedPlayers.isEmpty()) {
                        Player toAssign = RandomFromList.get(unassignedPlayers);
                        teams[teamIndex].addPlayer(toAssign);
                        unassignedPlayers.remove(toAssign);
                        GamePlayer.getInstance(toAssign).setTeam(teams[teamIndex]);
                        game.addPlayer(GamePlayer.getInstance(toAssign));
                        teams[teamIndex].getScoreboardTeam().setColor(teams[teamIndex].getColor());
                        teams[teamIndex].getScoreboardTeam().setPrefix("[" + teams[teamIndex].getName() + "] ");
                        teamIndex++;
                        if (teamIndex >= nbTeam) teamIndex = 0;
                    }
                    Bukkit.broadcastMessage(ChatColor.GREEN + "Chaque joueur a été attribué à une équipe aléatoirement !");
                    return true;
                }
                case "purge": {
                    if (game.hasStarted()) {
                        player.sendMessage(ChatColor.RED + "Vous ne pouvez pas faire ça lorsque la partie est en cours.");
                        return false;
                    }
                    if (!(sender.isOp())) {
                        sender.sendMessage(ChatColor.RED + "Vous devez être OP pour exécuter cette commande !");
                        return false;
                    }
                    if (game.getTeams().isEmpty()) {
                        player.sendMessage(ChatColor.RED + "Aucune équipe !");
                        return false;
                    }
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        GamePlayer gp = GamePlayer.getInstance(p);
                        GameTeam team = gp.getTeam();
                        if (team != null) {
                            team.reset();
                            game.removePlayer(gp);
                            if (team.getPlayers().isEmpty()) {
                                game.removeTeam(team);
                            }
                        }
                    }
                    Bukkit.broadcastMessage(ChatColor.GOLD + "Toutes les équipes ont été supprimées.");
                    return true;
                }
                case "leave": {
                    if (game.hasStarted()) {
                        player.sendMessage(ChatColor.RED + "Vous ne pouvez pas faire ça lorsque la partie est en cours.");
                        return false;
                    }
                    GamePlayer gamePlayer = GamePlayer.getInstance(player);
                    GameTeam team = gamePlayer.getTeam();
                    if (team == null) {
                        player.sendMessage(ChatColor.RED + "Vous n'êtes pas dans une équipe !");
                        return false;
                    }
                    team.reset();
                    game.removePlayer(gamePlayer);
                    if (team.getPlayers().isEmpty()) {
                        game.removeTeam(team);
                    }
                    player.sendMessage(ChatColor.GREEN + "Vous avez quitté votre équipe !");
                    return true;
                }
                case "members": {
                    if (game.getTeams().isEmpty()) {
                        player.sendMessage(ChatColor.RED + "Aucune équipe !");
                        return false;
                    }
                    GameTeam team = GamePlayer.getInstance(player).getTeam();
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
                }
                default:
                    player.sendMessage(ChatColor.RED + "Usage : /city team <color | create | disband | fire | hire | leave | members | marianne | purge | rename> <player | team_name>");
                    return false;
            }
            return true;
        }
        else if (subCommand.equals("powder")) {
            if (!(sender.isOp())) {
                sender.sendMessage(ChatColor.RED + "Vous devez être OP pour exécuter cette commande !");
                return false;
            }
            if (args.length == 1) {
                player.sendMessage(ChatColor.RED + "Usage : /city powder <give | remove> <player> <amount>");
                return false;
            }
            String powderCommand = args[1];
            if (powderCommand.equals("give")) {
                if (args.length <= 3) {
                    player.sendMessage(ChatColor.RED + "Usage : /city powder give <player> <amount>");
                    return true;
                }
                Player playerToGive = player.getServer().getPlayer(args[2]);
                if (playerToGive == null) {
                    player.sendMessage(ChatColor.RED + "Joueur introuvable !");
                    return true;
                }
                int amount;
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
            } else if (powderCommand.equals("remove")) {
                if (args.length <= 3) {
                    player.sendMessage(ChatColor.RED + "Usage : /city powder remove <player> <amount>");
                    return true;
                }
                Player playerToRemove = player.getServer().getPlayer(args[2]);
                if (playerToRemove == null) {
                    player.sendMessage(ChatColor.RED + "Joueur introuvable !");
                    return true;
                }
                int amount;
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
                GamePlayer.getInstance(playerToRemove).substractScore(amount);
                GamePlayer.getInstance(playerToRemove).getTeam().substractScore(amount);
                player.sendMessage(ChatColor.RED + "Vous avez retiré " + amount + " poudre à " + playerToRemove.getName() + " !");
                playerToRemove.sendMessage(ChatColor.RED + "Vous avez perdu " + amount + " poudre de la part de " + player.getName() + " !");
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "Usage : /city powder <give | remove> <player> <amount>");
                return true;
            }
        }

        return false;
    }

}