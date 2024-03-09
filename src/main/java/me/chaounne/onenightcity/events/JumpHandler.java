package me.chaounne.onenightcity.events;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.jump.Checkpoint;
import me.chaounne.onenightcity.game.jump.JumpManager;
import me.chaounne.onenightcity.utils.JumpScore;

public class JumpHandler implements Listener {

    private Hologram leaderBoard;
    private HashMap<Player, Long> bestScores;
    private List<Checkpoint> checkpoints;
    private HashMap<Player, JumpManager> playerManagers;

    public JumpHandler(Hologram holo, Checkpoint... cps) {
        this.leaderBoard = holo;
        this.checkpoints = new ArrayList<Checkpoint>();
        for (Checkpoint cp : cps) {
            this.checkpoints.add(cp);
        }
        this.playerManagers = new HashMap<Player, JumpManager>();
        this.bestScores = new HashMap<Player, Long>();

        this.updateHologram();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!ONCGame.getInstance().isStarted()) {

            Player player = event.getPlayer();
            ItemStack item = event.getItem();
            Block clickedBlock = event.getClickedBlock();

            if (clickedBlock != null) {
                if (checkCheckpoint(clickedBlock, this.checkpoints.get(0))) {
                    if (!this.playerManagers.containsKey(player)) {
                        this.playerManagers.put(player, new JumpManager(player, this.checkpoints.get(0)));
                    }
                } 
                else if (checkLastCheckpoint(clickedBlock, this.checkpoints.get(this.checkpoints.size() - 1))) {
                    if (!this.playerManagers.containsKey(player)) return;
                    if (!this.playerManagers.get(player).isFinished()) {
                        this.playerManagers.get(player).finish();
                        if (this.bestScores.containsKey(player)) {
                            if (this.bestScores.get(player) > this.playerManagers.get(player).getTime()) {
                                this.bestScores.put(player, this.playerManagers.get(player).getTime());
                            }
                        } else {
                            this.bestScores.put(player, this.playerManagers.get(player).getTime());
                        }
                        this.updateHologram();
                    }
                }
                else {
                    if (!this.playerManagers.containsKey(player)) return;
                    int size = this.checkpoints.size() - 1;
                    for (int i = this.playerManagers.get(player).getCheckpointIndex() + 1; i < size; i++) {
                        if (this.checkCheckpoint(clickedBlock, this.checkpoints.get(i))) {
                            this.playerManagers.get(player).setCheckpoint(this.checkpoints.get(i));
                            this.playerManagers.get(player).setCheckpointIndex(i);
                        }
                    }
                }
            }
            
            if (item != null && item.getType() == Material.RED_WOOL && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                this.deactivateJumpManagerForPlayer(player, true);
            }
            else if (item != null && item.getType() == Material.ORANGE_WOOL && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                this.playerManagers.get(player).teleport(true);
            } 
            else if (item != null && item.getType() == Material.EMERALD && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                this.deactivateJumpManagerForPlayer(player, false);
                this.playerManagers.put(player, new JumpManager(player, this.checkpoints.get(0)));
                this.playerManagers.get(player).teleport(false);
            }
        }
    }

    private boolean checkCheckpoint(Block clickedBlock, Checkpoint checkpoint) {
        return clickedBlock.getType().equals(Material.HEAVY_WEIGHTED_PRESSURE_PLATE) &&
                clickedBlock.getLocation().equals(checkpoint.getLocation());
    }

    private boolean checkLastCheckpoint(Block clickedBlock, Checkpoint checkpoint) {
        return clickedBlock.getType().equals(Material.LIGHT_WEIGHTED_PRESSURE_PLATE) &&
                clickedBlock.getLocation().equals(checkpoint.getLocation());
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        deactivateJumpManagerForPlayer(player, false);
    }

    private void deactivateJumpManagerForPlayer(Player p, boolean msg) {
        if (this.playerManagers.containsKey(p)) {
            this.playerManagers.get(p).deactivate(msg);
            this.playerManagers.remove(p);
        }
    }

    private void updateHologram() {
        // Récupérez l'hologramme existant ou créez-en un nouveau
        Hologram leaderboardHologram = DHAPI.getHologram("Classement");

        if (leaderboardHologram == null) {
            // Créez un nouvel hologramme si celui-ci n'existe pas
            Location hologramLocation = new Location(Bukkit.getWorlds().get(0), 118, 218, -36);
            leaderboardHologram = DHAPI.createHologram("Classement", hologramLocation);
        } else {
            // Effacez toutes les lignes de l'hologramme existant
            DHAPI.setHologramLines(leaderboardHologram, Collections.emptyList());
        }

        // Ajoutez les nouvelles lignes à l'hologramme
        
        List<Map.Entry<Player, Long>> sortedTimes = this.bestScores.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        int count = 1;
        for (Map.Entry<Player, Long> entry : sortedTimes.subList(0, Math.min(sortedTimes.size(), 10))) {
            String playerName = entry.getKey().getName();
            long time = entry.getValue();
            String formattedTime = ChatColor.YELLOW + "Top " + count + ": " + playerName + " - " + JumpScore.formatJumpTime(time);

            // Ajoutez la nouvelle ligne à l'hologramme
            DHAPI.addHologramLine(leaderboardHologram, formattedTime);
            count++;
        }
    }

}
/* 
    public final Checkpoint checkpoint;
    private final Checkpoint secondCheckpoint;
    private final Checkpoint thirdCheckpoint;
    private final Checkpoint fourthCheckpoint;
    private final Checkpoint fifthCheckpoint;
    private final Checkpoint sixthCheckpoint;
    private final Checkpoint seventhCheckpoint;
    private final Checkpoint eighthCheckpoint;
    private final Checkpoint ninthCheckpoint;
    private final Checkpoint finalCheckpoint;
    private Checkpoint lastCheckpoint;
    private final JavaPlugin plugin;
    private Hologram leaderboardHologram;

    private Map<String, Long> playerBestTimes = new HashMap<>();

    public CheckpointListener(JavaPlugin plugin, Hologram leaderboardHologram, Checkpoint checkpoint, Checkpoint secondCheckpoint,
                              Checkpoint thirdCheckpoint, Checkpoint fourthCheckpoint, Checkpoint fifthCheckpoint,
                              Checkpoint sixthCheckpoint, Checkpoint seventhCheckpoint, Checkpoint eighthCheckpoint,
                              Checkpoint ninthCheckpoint, Checkpoint finalCheckpoint) {
        this.plugin = plugin;
        this.leaderboardHologram = leaderboardHologram;
        this.checkpoint = checkpoint;
        this.secondCheckpoint = secondCheckpoint;
        this.thirdCheckpoint = thirdCheckpoint;
        this.fourthCheckpoint = fourthCheckpoint;
        this.fifthCheckpoint = fifthCheckpoint;
        this.sixthCheckpoint = sixthCheckpoint;
        this.seventhCheckpoint = seventhCheckpoint;
        this.eighthCheckpoint = eighthCheckpoint;
        this.ninthCheckpoint = ninthCheckpoint;
        this.finalCheckpoint = finalCheckpoint;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        deactivateCheckpoint(player);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!ONCGame.getInstance().isStarted()) {

            Player player = event.getPlayer();
            ItemStack item = event.getItem();
            Block clickedBlock = event.getClickedBlock();

            // Vérifiez si le joueur a interagi avec la plaque de pression en fer
            if (clickedBlock != null) {
                // Checkpoints
                if (checkCheckpoint(clickedBlock, checkpoint)) {
                    // Associez le checkpoint au joueur
                    playerCheckpoints.put(player.getName(), checkpoint);
                    lastCheckpoint = checkpoint;
                    sendFirstCheckpointMessage(player, checkpoint);
                    startCheckpoint(player);
                } else if (checkCheckpoint(clickedBlock, secondCheckpoint)) {
                    playerCheckpoints.put(player.getName(), secondCheckpoint);
                    lastCheckpoint = secondCheckpoint;
                    sendCheckpointMessage(player, secondCheckpoint);
                } else if (checkCheckpoint(clickedBlock, thirdCheckpoint)) {
                    playerCheckpoints.put(player.getName(), thirdCheckpoint);
                    lastCheckpoint = thirdCheckpoint;
                    sendCheckpointMessage(player, thirdCheckpoint);
                } else if (checkCheckpoint(clickedBlock, fourthCheckpoint)) {
                    playerCheckpoints.put(player.getName(), fourthCheckpoint);
                    lastCheckpoint = fourthCheckpoint;
                    sendCheckpointMessage(player, fourthCheckpoint);
                } else if (checkCheckpoint(clickedBlock, fifthCheckpoint)) {
                    playerCheckpoints.put(player.getName(), fifthCheckpoint);
                    lastCheckpoint = fifthCheckpoint;
                    sendCheckpointMessage(player, fifthCheckpoint);
                } else if (checkCheckpoint(clickedBlock, sixthCheckpoint)) {
                    playerCheckpoints.put(player.getName(), sixthCheckpoint);
                    lastCheckpoint = sixthCheckpoint;
                    sendCheckpointMessage(player, sixthCheckpoint);
                } else if (checkCheckpoint(clickedBlock, seventhCheckpoint)) {
                    playerCheckpoints.put(player.getName(), seventhCheckpoint);
                    lastCheckpoint = seventhCheckpoint;
                    sendCheckpointMessage(player, seventhCheckpoint);
                } else if (checkCheckpoint(clickedBlock, eighthCheckpoint)) {
                    playerCheckpoints.put(player.getName(), eighthCheckpoint);
                    lastCheckpoint = eighthCheckpoint;
                    sendCheckpointMessage(player, eighthCheckpoint);
                } else if (checkCheckpoint(clickedBlock, ninthCheckpoint)) {
                    playerCheckpoints.put(player.getName(), ninthCheckpoint);
                    lastCheckpoint = ninthCheckpoint;
                    sendCheckpointMessage(player, ninthCheckpoint);
                } else if (clickedBlock.getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE && clickedBlock.getLocation().equals(finalCheckpoint.getLocation())) {
                    playerCheckpoints.put(player.getName(), finalCheckpoint);
                    lastCheckpoint = finalCheckpoint;

                    sendFinalCheckpointMessage(player, finalCheckpoint);
                    // Réinitialiser les messages de point de contrôle (sauf le dernier)
                    resetAllCheckpointMessagesExceptFinal();

                    // Réinitialiser le statut des laines
                    checkpoint.setWoolGiven(false);

                    // Annuler la tâche du timer
                    cancelTimer(player);

                    // Retirer les laines du joueur
                    removeWools(player);

                    // Retirer le scoreboard du joueur
                    player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);

                    // Vérifier si le joueur n'a pas déjà l'émeraude nommée "Recommencer"
                    if (!player.getInventory().contains(Material.EMERALD_BLOCK)) {
                        // Donnez au joueur une émeraude nommée "Recommencer"
                        ItemStack restartEmerald = createRestartEmerald();
                        player.getInventory().addItem(restartEmerald);
                        player.sendMessage(ChatColor.GREEN + "Vous avez obtenu une émeraude pour recommencer le jump.");
                    }
                }
            }

            // Vérifiez si le joueur a fait un clic droit avec la laine rouge "Abandonner"
            if (item != null && item.getType() == Material.RED_WOOL && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                // Téléportez le joueur aux coordonnées spécifiées
                player.teleport(new Location(player.getWorld(), 121, 154, -41));
                // Arrêtez le système de points de contrôle
                stopCheckpointSystem(player);
            }

            // Vérifiez si le joueur a fait un clic droit avec la laine orange "Revenir au point de contrôle"
            else if (item != null && item.getType() == Material.ORANGE_WOOL && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                // Vérifiez s'il y a un dernier point de contrôle enregistré pour ce joueur
                Checkpoint lastCheckpoint = playerCheckpoints.get(player.getName());
                if (lastCheckpoint != null) {
                    // Téléportez le joueur au dernier point de contrôle
                    player.teleport(lastCheckpoint.getLocation());
                    // Affichez un message privé dans le chat
                    player.sendMessage(ChatColor.GOLD + "Vous êtes revenu au dernier checkpoint !");
                } else {
                    // Aucun point de contrôle enregistré, informez le joueur
                    player.sendMessage(ChatColor.RED + "Aucun checkpoint précédent trouvé !");
                }
            } else if (item != null && item.getType() == Material.EMERALD_BLOCK && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {

                // Téléportez le joueur aux coordonnées spécifiées
                player.teleport(new Location(player.getWorld(), 121, 154, -41));
                // Réinitialiser le statut du message finalCheckpointMessageSent
                finalCheckpoint.setFinalCheckpointMessageSent(false);

                playerWoolStatus.remove(player); // Retirez le joueur de la map des statuts des laines

                // Vous pouvez également ajouter d'autres actions ou messages si nécessaire
                player.sendMessage(ChatColor.GREEN + "Vous avez choisi de recommencer le saut.");

                // Retirez l'émeraude du joueur après utilisation
                removeRestartEmerald(player);
            }
        }
    }


    private Map<Player, Boolean> playerWoolStatus = new HashMap<>();

    private Map<String, Checkpoint> playerCheckpoints = new HashMap<>();

    // Dans la méthode startCheckpoint
    private void startCheckpoint(Player player) {
        // Réinitialisez le point de contrôle si déjà activé
        if (checkpoint.isActivated()) {
            deactivateCheckpoint(player);
        }

        // Associez le checkpoint au joueur
        playerCheckpoints.put(player.getName(), lastCheckpoint);

        // Vérifiez si les laines ont déjà été données au joueur
        if (!playerWoolStatus.getOrDefault(player, false)) {
            // Donnez au joueur des objets spéciaux pour gérer le point de contrôle
            player.getInventory().addItem(createOrangeWool());
            player.getInventory().addItem(createRedWool());
            // Marquez que les laines ont été données au joueur
            playerWoolStatus.put(player, true);
        }

        // Réinitialisez le timestamp
        checkpoint.resetTimestamp();

        // Afficher la zone de timer
        startTimer(player);
    }
    private boolean checkCheckpoint(Block clickedBlock, Checkpoint checkpoint) {
        return clickedBlock.getType().equals(Material.HEAVY_WEIGHTED_PRESSURE_PLATE) &&
                clickedBlock.getLocation().equals(checkpoint.getLocation());
    }

    private void deactivateCheckpoint(Player player) {
        checkpoint.setActivated(false);
        checkpoint.resetTimestamp();

        // Retirez les laines du joueur s'il les a dans son inventaire
        removeWools(player);
    }

    // Ajouter une nouvelle méthode pour réinitialiser les messages de tous les points de contrôle
    private void resetAllCheckpointMessagesExceptFinal() {
        for (Checkpoint checkpoint : getAllCheckpoints()) {
            if (checkpoint != finalCheckpoint) {
                checkpoint.setFirstCheckpointMessageSent(false);
                checkpoint.setCheckpointMessageSent(false);
                checkpoint.setFinalCheckpointMessageSent(false);
            }
        }
    }


    // Ajouter une nouvelle méthode pour obtenir tous les points de contrôle dans une liste
    private List<Checkpoint> getAllCheckpoints() {
        return Arrays.asList(
                checkpoint, secondCheckpoint, thirdCheckpoint, fourthCheckpoint, fifthCheckpoint,
                sixthCheckpoint, seventhCheckpoint, eighthCheckpoint, ninthCheckpoint, finalCheckpoint
        );
    }

    private void stopCheckpointSystem(Player player) {
        if(!ONCGame.getInstance().isStarted()) {

            // Afficher "Jump annulé" en rouge dans le chat privé
            player.sendMessage(ChatColor.RED + "Jump annulé.");

            // Retirez le checkpoint associé au joueur de la HashMap
            playerCheckpoints.remove(player.getName());

            // Réinitialiser le statut des messages
            resetAllCheckpointMessagesExceptFinal();

            // Réinitialiser le statut des laines
            playerWoolStatus.remove(player);

            // Annuler la tâche du timer s'il est en cours
            cancelTimer(player);

            // Retirer les laines du joueur
            removeWools(player);

            // Retirer le scoreboard du joueur
            player.getScoreboard().clearSlot(DisplaySlot.SIDEBAR);
        }
    }

    private void sendFirstCheckpointMessage(Player player, Checkpoint checkpoint) {
        // Vérifier si le message de point de contrôle a déjà été envoyé
        if (!checkpoint.isFirstCheckpointMessageSent()) {
            player.sendMessage(ChatColor.DARK_GREEN + "Le jump commence.");
            checkpoint.setFirstCheckpointMessageSent(true);
        }
    }

    private void sendCheckpointMessage(Player player, Checkpoint checkpoint) {
        // Vérifier si le message de point de contrôle a déjà été envoyé
        if (!checkpoint.isCheckpointMessageSent()) {
            player.sendMessage(ChatColor.GREEN + "Checkpoint passé.");
            checkpoint.setCheckpointMessageSent(true);

        }
    }

    private void sendFinalCheckpointMessage(Player player, Checkpoint checkpoint) {
        // Vérifier si le message de point de contrôle final a déjà été envoyé
        if (!checkpoint.isFinalCheckpointMessageSent()) {
            player.sendMessage(ChatColor.GOLD + "Bravo, vous avez terminé le jump !");
            checkpoint.setFinalCheckpointMessageSent(true);

            // Réinitialiser les messages de point de contrôle (sauf le dernier)
            resetAllCheckpointMessagesExceptFinal();

            // Enregistrer le temps final et gérer le classement
            handleFinalTime(player);
        }
    }

    private void cancelTimer(Player player) {
        // Annuler la tâche du timer en utilisant le plugin
        plugin.getServer().getScheduler().cancelTasks(plugin);

        // Effacer la barre de titre du joueur
        player.sendTitle("", "", 0, 0, 0);
    }

    private void startTimer(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("chrono", "dummy", ChatColor.BLUE + "Chrono");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // Créez une équipe pour le joueur
        Team team = scoreboard.registerNewTeam(player.getName());
        team.addEntry(player.getName());

        // Ajoutez le joueur à l'équipe avec une entrée vide (sinon l'équipe ne sera pas affichée dans le scoreboard)
        team.addEntry("");
        checkpoint.setWoolGiven(false);

        new BukkitRunnable() {
            @Override
            public void run() {
                long elapsedTime = (System.currentTimeMillis() - checkpoint.getTimestamp()) / 1000;
                int minutes = (int) (elapsedTime / 60);
                int seconds = (int) (elapsedTime % 60);

                // Mettez à jour la valeur de l'objectif
                String formattedTime = ChatColor.WHITE + String.format("%02d:%02d", minutes, seconds);
                objective.setDisplayName(ChatColor.BLUE + "Chrono");

                // Mettez à jour le score existant
                team.getEntries().forEach(entry -> objective.getScore(entry).setScore((int) elapsedTime));

                // Attachez le Scoreboard au joueur
                //player.setScoreboard(scoreboard);
                if (ONCGame.getInstance().isStarted()) {
                    checkpoint.setWoolGiven(false);

                    for (Player player : Bukkit.getOnlinePlayers()) {
                        checkpoint.setWoolGiven(false);

                        // Annuler la tâche du timer pour chaque joueur
                        cancelTimer(player);
                    }
                }
            }
        }.runTaskTimer(plugin, 0, 20); // Exécutez la tâche toutes les secondes
    }

    private ItemStack createOrangeWool() {
        ItemStack orangeWool = new ItemStack(Material.ORANGE_WOOL);
        ItemMeta orangeMeta = orangeWool.getItemMeta();
        orangeMeta.setDisplayName(ChatColor.GOLD + "Revenir au checkpoint");
        orangeWool.setItemMeta(orangeMeta);
        return orangeWool;
    }

    private ItemStack createRedWool() {
        ItemStack redWool = new ItemStack(Material.RED_WOOL);
        ItemMeta redMeta = redWool.getItemMeta();
        redMeta.setDisplayName(ChatColor.RED + "Abandonner");
        redWool.setItemMeta(redMeta);
        return redWool;
    }

    private void removeWools(Player player) {
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && (item.getType() == Material.ORANGE_WOOL || item.getType() == Material.RED_WOOL)) {
                item.setAmount(0);
            }
        }
    }

    private ItemStack createRestartEmerald() {
        ItemStack restartEmerald = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta restartEmeraldMeta = restartEmerald.getItemMeta();
        restartEmeraldMeta.setDisplayName(ChatColor.GREEN + "Recommencer");
        restartEmerald.setItemMeta(restartEmeraldMeta);
        return restartEmerald;
    }

    private void removeRestartEmerald(Player player) {
        // Retirez l'émeraude nommée "Recommencer" du joueur
        ItemStack restartEmerald = createRestartEmerald();
        player.getInventory().removeItem(restartEmerald);
    }

    private long getBestTime(String playerName) {
        return playerBestTimes.getOrDefault(playerName, Long.MAX_VALUE);
    }

    private void handleFinalTime(Player player) {
        long elapsedTime = (System.currentTimeMillis() - checkpoint.getTimestamp()) / 1000;
        String playerName = player.getName();

        // Mettez à jour le classement uniquement si le temps actuel est meilleur que le meilleur temps
        if (elapsedTime < getBestTime(playerName)) {
            playerBestTimes.put(playerName, elapsedTime);
            updateLeaderboard();
            player.sendMessage(ChatColor.GREEN + "Nouveau meilleur temps enregistré : " + elapsedTime + " secondes.");
        } else {
            player.sendMessage(ChatColor.YELLOW + "Temps actuel non amélioré par rapport au meilleur temps.");
        }
    }

    public void updateLeaderboard() {
        // Récupérez l'hologramme existant ou créez-en un nouveau
        Hologram leaderboardHologram = DHAPI.getHologram("Classement");

        if (leaderboardHologram == null) {
            // Créez un nouvel hologramme si celui-ci n'existe pas
            Location hologramLocation = new Location(Bukkit.getWorlds().get(0), 118, 218, -36);
            leaderboardHologram = DHAPI.createHologram("Classement", hologramLocation);
        } else {
            // Effacez toutes les lignes de l'hologramme existant
            DHAPI.setHologramLines(leaderboardHologram, Collections.emptyList());
        }

        // Ajoutez les nouvelles lignes à l'hologramme
        List<Map.Entry<String, Long>> sortedTimes = playerBestTimes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        int count = 1;
        for (Map.Entry<String, Long> entry : sortedTimes.subList(0, Math.min(sortedTimes.size(), 10))) {
            String playerName = entry.getKey();
            long time = entry.getValue();
            String formattedTime = ChatColor.YELLOW + "Top " + count + ": " + playerName + " - " + formatTime(time);

            // Ajoutez la nouvelle ligne à l'hologramme
            DHAPI.addHologramLine(leaderboardHologram, formattedTime);
            count++;
        }
    }

    private String formatTime(long seconds) {
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
*/
