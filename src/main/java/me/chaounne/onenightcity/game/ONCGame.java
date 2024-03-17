package me.chaounne.onenightcity.game;

import fr.mrmicky.fastboard.FastBoard;
import me.chaounne.onenightcity.OneNightCity;
import me.chaounne.onenightcity.villager.DarkHenryEntity;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class ONCGame implements Listener {

    private final ArrayList<GameTeam> teams = new ArrayList<>();

    private final List<GamePlayer> players = new ArrayList<>();

    private static ONCGame instance;

    private boolean hasStarted = false;
    Random random = new Random();

    private BukkitRunnable timer;

    private int time = 3 * 60 * 60;

    private final Map<UUID, FastBoard> boards = new HashMap<>();

    private ONCGame(){
        Bukkit.getPluginManager().registerEvents(this, OneNightCity.getInstance());
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

    public void addTeam(GameTeam team){
        teams.add(team);
    }

    public void removeTeam(GameTeam team){
        teams.remove(team);
    }

    public ArrayList<GameTeam> getTeams(){
        return teams;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player realPlayer = event.getPlayer();
        GamePlayer player = GamePlayer.getInstance(realPlayer);
        FastBoard board = new FastBoard(realPlayer);
        board.updateTitle(ChatColor.DARK_BLUE + "Cité d'une nuit");
        boards.put(realPlayer.getUniqueId(), board);
        if(hasStarted()){
            if(!players.contains(player)) realPlayer.setGameMode(GameMode.SPECTATOR);
        }
    }

    public void updateBoard() {
        for (GamePlayer player : players) {
            FastBoard board = boards.get(player.getPlayer().getUniqueId());
            board.updateTitle(ChatColor.DARK_BLUE + "Cité d'une nuit");

            int hours = time / 3600; // nombre d'heures
            int minutes = (time % 3600) / 60; // nombre de minutes
            int seconds = time % 60; // nombre de secondes

            String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds); // formatage de l'heure


            int countdown2 = time - 7200;// au bout de 1heure  end open
            int countdown4 = time - 7200; // au bout de 1 heure  quine commence
            int countdown = time - 10200; // countdown de 10 minutes pour pvp


            if (countdown >= 0) {
                String countdownString = String.format("%02d:%02d:%02d", countdown / 3600, (countdown % 3600) / 60, countdown % 60);
                String countdownString2 = String.format("%02d:%02d:%02d", countdown2 / 3600, (countdown2 % 3600) / 60, countdown2 % 60);
                String countdownString3 = String.format("%02d:%02d:%02d", countdown4 / 3600, (countdown4 % 3600) / 60, countdown4 % 60);

                board.updateLines("",
                        ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                        ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + timeString,
                        ChatColor.GOLD + "PVP Activé dans : " + ChatColor.WHITE + countdownString + "s",
                        ChatColor.GOLD + "END Activé dans : " + ChatColor.WHITE + countdownString2 + "s",
                        ChatColor.GOLD + "Quine fini dans : " + ChatColor.WHITE + countdownString3 + "s",
                        ChatColor.DARK_GREEN + "Equipe : " + player.getTeam().getColor() + player.getTeam().getName(),
                        ChatColor.BLUE + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                        ChatColor.DARK_BLUE + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                        "");
            } else if(countdown<=0 && countdown2>=0) {
                String countdownString1 = String.format("%02d:%02d:%02d", countdown2 / 3600, (countdown2 % 3600) / 60, countdown2 % 60);
                String countdownString2 = String.format("%02d:%02d:%02d", countdown4 / 3600, (countdown4 % 3600) / 60, countdown4 % 60);

                board.updateLines("",
                        ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                        ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + timeString,
                        ChatColor.RED + "PVP ACTIVÉ !",
                        ChatColor.GOLD + "END Activé dans : " + ChatColor.WHITE + countdownString1 + "s",
                        ChatColor.GOLD + "Quine fini dans : " + ChatColor.WHITE + countdownString2 + "s",
                        ChatColor.DARK_GREEN + "Equipe : " + player.getTeam().getColor() + player.getTeam().getName(),
                        ChatColor.BLUE + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                        ChatColor.DARK_BLUE + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                        "");
            }else if(countdown<=0 && countdown2<=0 && countdown4>=0){
                String countdownString = String.format("%02d:%02d:%02d", countdown4 / 3600, (countdown4 % 3600) / 60, countdown4 % 60);

                board.updateLines("",
                            ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                            ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + timeString,
                        ChatColor.RED + "PVP ACTIVÉ !",
                        ChatColor.DARK_PURPLE + "END ACTIVÉ !",
                        ChatColor.GOLD + "Quine fini dans : " + ChatColor.WHITE + countdownString + "s",

                        ChatColor.DARK_GREEN + "Equipe : " + player.getTeam().getColor() + player.getTeam().getName(),
                        ChatColor.BLUE + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                        ChatColor.DARK_BLUE + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                        "");
            }else {
                board.updateLines("",
                        ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                        ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + timeString,
                        ChatColor.RED + "PVP ACTIVÉ !",
                        ChatColor.DARK_PURPLE + "END ACTIVÉ !",
                        ChatColor.AQUA + "QUINE FINI !",
                        ChatColor.DARK_GREEN + "Equipe : " + player.getTeam().getColor() + player.getTeam().getName(),
                        ChatColor.BLUE + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                        ChatColor.DARK_BLUE + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                        "");
            }

        }
    }

    public boolean hasStarted(){
        return hasStarted;
    }

    public void createDark() {
        timer = new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorlds().get(0); // Récupère le premier monde de la liste

                if (time > 10750) {// Pour supprimer darkHenry le cas ou il spawn
                    for (Entity entity : world.getEntities()) {
                        if (entity instanceof LivingEntity && entity.getName().equals(ChatColor.DARK_RED + "DARKHenry")) {
                            entity.remove();
                        }
                    }
                }
                if (time == 0) {
                    this.cancel();
                    endGame();
                }
                if (time == 9000) {//au bout de 30 min la quine commence
                    QuineItem.start();

                }
                if (time==7200) { // au bout d'une heure
                    Location[] locations = new Location[]{
                            new Location(Bukkit.getWorld("world"), 2, 64, 7),
                            new Location(Bukkit.getWorld("world"), 2, 63, 7),
                            new Location(Bukkit.getWorld("world"), 2, 62, 7),
                            new Location(Bukkit.getWorld("world"), 1, 64, 7),
                            new Location(Bukkit.getWorld("world"), 1, 63, 7),
                            new Location(Bukkit.getWorld("world"), 1, 62, 7)
                    };
                    for(Player player: Bukkit.getOnlinePlayers()){
                        player.sendMessage(ChatColor.DARK_PURPLE+"L'end est ouvert le premier à récupérer l'oeuf du dragon recevra"+ChatColor.GOLD+" 10 000"+ChatColor.DARK_PURPLE+" points");
                    }
                    for (Location location : locations) {
                        Block block = location.getBlock();
                        if (block.getType() != Material.AIR) {
                            block.breakNaturally();
                        }
                    }
                }

                int randomTime = random.nextInt(3001) + 6000;
                if (time == randomTime) { //Darkhenry spawn au bout de 2 heures  et quelques je crois ; remettre a 6250
                    DarkHenryEntity.getEntity(new Location(Bukkit.getWorlds().get(0), 0, 62, 1));
                    Location location = new Location(world, 0, 62, 1);
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.PURPLE, 4.0f);
                    world.spawnParticle(Particle.REDSTONE, location, 100, 2, 2, 2, 1, dustOptions);

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendTitle(ChatColor.RED + "DARKHENRY vient d'arriver au marché", "", 10, 70, 20);
                        p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10f, 10f);
                        p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10f, 10f);

                        p.sendMessage(ChatColor.RED+"Le frère maléfique de Henry est là, il est pressé, il ne vous accorde qu'un seul échange, dépéchez-vous !");
                    }
                }
                if(time==1){
                    for(Player player:Bukkit.getOnlinePlayers()) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 1));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 50, 1));
                    }
                }
            }
        };
        timer.runTaskTimer(OneNightCity.getInstance(), 0, 20);
    }

    public void startGame() {
        if (!hasStarted) hasStarted = true;

        //Bukkit.broadcastMessage(ChatColor.YELLOW + "VERSION TEST, PENSER A REMETTRE LES TIMER CORRECT (mettre en commentaire quand c'est bon)");

        EventGame.LancerConcours();
        EventGame.revealPlayerPositions(); // Appel de la méthode statique
        createDark();
        GenerateChest.spawnCoffre();
        for (Player player : Bukkit.getOnlinePlayers()) {

            player.getPlayer().getInventory().clear();
            FastBoard board = new FastBoard(player);
            board.updateTitle(ChatColor.DARK_BLUE + "Cité d'une nuit");
            boards.put(player.getUniqueId(), board);
            player.setFoodLevel(20);
            player.setHealth(20);
            player.setSaturation(20);
            ItemStack ironSword = new ItemStack(Material.IRON_SWORD);
            ironSword.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            player.getInventory().addItem(ironSword);
            ItemStack ironPick = new ItemStack(Material.IRON_PICKAXE);
            ironPick.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            player.getInventory().addItem(ironPick);
            player.getInventory().addItem(new ItemStack(Material.COOKED_SALMON, 15));
            ItemStack helmet = new ItemStack(Material.IRON_HELMET);
            helmet.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE);
            chestplate.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS);
            leggings.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            ItemStack boots = new ItemStack(Material.IRON_BOOTS);
            boots.addEnchantment(Enchantment.VANISHING_CURSE, 1);
            ItemStack[] armorContents = {
                    boots,
                    leggings,
                    chestplate,
                    helmet
            };
            player.getInventory().setArmorContents(armorContents);
        }
        World world = Bukkit.getWorlds().get(0); // Récupère le premier monde de la liste
        world.setPVP(false);
        List<Player> playersWithEgg = new ArrayList<>();
        timer = new BukkitRunnable() {
            @Override
            public void run() {
                if(ONCGame.getInstance().hasStarted()) {
                    ClassementPoudre.showScoreboard();
                }

                if (time == 3600) { // Si il reste 1 heure
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle("1 heure restante", "", 10, 70, 20);
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 5f, 5f);
                    }

                }
                if (playersWithEgg.isEmpty()) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.getInventory().contains(Material.DRAGON_EGG)) {
                            for(Player player1:Bukkit.getOnlinePlayers()){
                                player1.sendMessage(ChatColor.DARK_PURPLE + "§lL'oeuf de Dragon' a été récupéré !§r\n" + ChatColor.DARK_PURPLE + "§oVoici le joueur qui a récuperer l'oeuf : §r"+player.getName()+ChatColor.DARK_PURPLE+"§o, il a gagné  10 000 poudres");
                                player1.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 10f, 10f);

                            }
                            GamePlayer gamePlayer = GamePlayer.getInstance(player);
                            gamePlayer.getTeam().addScore(10000);
                            gamePlayer.addScore(10000);

                            playersWithEgg.add(player);
                        }
                    }

                }
                if(time==10200) {// Temps au bout de 10 min de jeu
                    world.setPVP(true);

                    // Envoyer le titre à tous les joueurs en ligne
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        // Envoyer le titre avec un sous-titre vide, aucun temps de fade-in ou fade-out et une durée d'affichage de 20 ticks
                        player.sendTitle(ChatColor.GREEN + "Le PVP est activé", ChatColor.GRAY + "Préparez-vous à combattre !", 0, 40, 10);
                        // Jouer un son à tous les joueurs
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    }
                }
                if(time==1){ // Pour la fin de la partie
                    for(Player player:Bukkit.getOnlinePlayers()){
                        player.getInventory().clear();
                        player.sendMessage(ChatColor.RED+"FIN DE LA PARTIE, GG A TOUS");
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 100, 100); // Joue le son de l'enderman
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 100, 100); // Joue le son de l'enderman

                        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 100, 100); // Joue le son de l'enderman

                    }
                }
                if(time==11){
                    for(Player player:Bukkit.getOnlinePlayers()){

                        player.sendMessage(ChatColor.RED+"Fin de la partie, dans 10 ");

                    }
                }
                if(time==10){
                    for(Player player:Bukkit.getOnlinePlayers()){

                        player.sendMessage(ChatColor.RED+"...9");

                    }
                }
                if(time==9){
                    for(Player player:Bukkit.getOnlinePlayers()){

                        player.sendMessage(ChatColor.RED+"...8");

                    }
                }
                if(time==8){
                    for(Player player:Bukkit.getOnlinePlayers()){

                        player.sendMessage(ChatColor.RED+"...7");

                    }
                }
                if(time==7){
                    for(Player player:Bukkit.getOnlinePlayers()){

                        player.sendMessage(ChatColor.RED+"...6");

                    }
                }
                if(time==6){
                    for(Player player:Bukkit.getOnlinePlayers()){

                        player.sendMessage(ChatColor.RED+"...5");

                    }
                }
                if(time==5){
                    for(Player player:Bukkit.getOnlinePlayers()){

                        player.sendMessage(ChatColor.RED+"...4");

                    }
                }
                if(time==4){
                    for(Player player:Bukkit.getOnlinePlayers()){

                        player.sendMessage(ChatColor.RED+"...3");

                    }
                }
                if(time==3){
                    for(Player player:Bukkit.getOnlinePlayers()){

                        player.sendMessage(ChatColor.RED+"...2");

                    }
                }
                if(time==2){
                    for(Player player:Bukkit.getOnlinePlayers()){

                        player.sendMessage(ChatColor.RED+"...1");

                    }
                }





                time--;
                updateBoard();



            }
        };




        timer.runTaskTimerAsynchronously(OneNightCity.getInstance(), 0, 20);
    }

    public void endGame(){
            //faire l'appel pour reset ici


        if (hasStarted) hasStarted = false;
        if (timer != null) timer.cancel();
        World overworld = Bukkit.getWorld("world"); // Make sure "world" is the name of your overworld

        for (Player player : Bukkit.getOnlinePlayers()) {

            player.getPlayer().teleport(new Location(overworld,122,154,-39));
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
            FastBoard board = boards.get(player.getUniqueId());
            board.delete();
            player.setExp(0);
            player.setLevel(0);

            // Créer un feu d'artifice près de la localisation spécifiée
            Location fireworkLocation = new Location(player.getWorld(), 122, 154, -39);

            Firework firework = player.getWorld().spawn(fireworkLocation, Firework.class);
            FireworkMeta fireworkMeta = firework.getFireworkMeta();

            // Création de l'effet de feu d'artifice
            FireworkEffect effect = FireworkEffect.builder()
                    .flicker(false)
                    .trail(true)
                    .with(FireworkEffect.Type.BURST)
                    .withColor(Color.RED)
                    .withFade(Color.ORANGE)
                    .build();

            fireworkMeta.addEffect(effect);
            fireworkMeta.setPower(1);
            firework.setFireworkMeta(fireworkMeta);
        }

        // Calcul des points de chaque équipe
        Map<GameTeam, Integer> teamScores = new HashMap<>();
        for (Player player : Bukkit.getOnlinePlayers()) {
            GamePlayer gamePlayer = GamePlayer.getInstance(player);
            GameTeam playerTeam = gamePlayer.getTeam();
            int playerScore = gamePlayer.getScore();
            teamScores.put(playerTeam, teamScores.getOrDefault(playerTeam, 0) + playerScore);
        }

// Vérifier s'il y a un gagnant ou une égalité
        boolean hasWinner = false;
        GameTeam winningTeam = null;
        int maxScore = 0;
        for (Map.Entry<GameTeam, Integer> entry : teamScores.entrySet()) {
            int score = entry.getValue();
            if (score > maxScore) {
                maxScore = score;
                winningTeam = entry.getKey();
                hasWinner = true;
            } else if (score == maxScore) {
                hasWinner = false; // Égalité détectée
            }
        }

// Diffuser le message aux joueurs en fonction du résultat
        if (hasWinner) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.sendTitle(ChatColor.GREEN + " Félicitations à l'équipe gagnante !", ChatColor.AQUA + winningTeam.getName(), 10, 70, 20);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
            }
        } else {
            // Gérer le cas d'égalité ou de l'absence de gagnant
            if (maxScore == 0) {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle(ChatColor.GREEN + " Le jeu s'est terminé", ChatColor.GREEN + "mais personne n'a marqué de points.", 10, 70, 20);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                }

            } else {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendTitle(ChatColor.GREEN + " Egalitée entre plusieurs equipe !.","", 10, 70, 20);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);
                } }
        }

        for (GameTeam team : teams) {
            team.reset();
        }
        teams.clear();
        players.clear();
        if (hasStarted) hasStarted = false;
    }

    public void resetTeams(){
        for (GameTeam team : teams)
            team.reset();
        teams.clear();
        players.clear();
    }

}