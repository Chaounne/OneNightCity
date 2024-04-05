package me.chaounne.onenightcity.game;

import fr.mrmicky.fastboard.FastBoard;
import me.chaounne.onenightcity.OneNightCity;
import me.chaounne.onenightcity.villager.*;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Instant;
import java.util.*;

public class ONCGame {

    private final ArrayList<GameTeam> teams = new ArrayList<>();

    private final List<GamePlayer> players = new ArrayList<>();

    private static ONCGame instance;

    private boolean hasStarted = false;
    Random random = new Random();

    private BukkitRunnable timer;

    private int time;

    private int randomTime;

    private final Map<UUID, FastBoard> boards = new HashMap<>();

    private boolean quine;
    private boolean end;
    private boolean darkHenry;
    private boolean uneHeureRestante;
    private boolean pvp;
    private boolean annonceFin;

    private ONCGame() {
        instance = this;
    }

    public static ONCGame getInstance() {
        return instance == null ? new ONCGame() : instance;
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

    public ArrayList<GameTeam> getTeams() {
        return teams;
    }

    public List<GamePlayer> getPlayers() {
        return players;
    }

    public Map<UUID, FastBoard> getBoards() {
        return boards;
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
            int countdownQuineStart = time - 9000; // au bout de 1 heure  quine commence
            int countdownQuineEnd = time - 7200; // au bout de 1 heure  quine commence
            int countdown = time - 10200; // countdown de 10 minutes pour pvp


            if (countdown >= 0) {
                String countdownString = String.format("%02d:%02d:%02d", countdown / 3600, (countdown % 3600) / 60, countdown % 60);
                String countdownString2 = String.format("%02d:%02d:%02d", countdown2 / 3600, (countdown2 % 3600) / 60, countdown2 % 60);
                String countdownString3 = String.format("%02d:%02d:%02d", countdownQuineStart / 3600, (countdownQuineStart % 3600) / 60, countdownQuineStart % 60);

                board.updateLines("",
                        ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                        ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + timeString,
                        ChatColor.GOLD + "PVP activé dans : " + ChatColor.WHITE + countdownString + "s",
                        ChatColor.GOLD + "END activé dans : " + ChatColor.WHITE + countdownString2 + "s",
                        ChatColor.GOLD + "Quine dans : " + ChatColor.WHITE + countdownString3 + "s",
                        ChatColor.DARK_GREEN + "Equipe : " + player.getTeam().getColor() + player.getTeam().getDisplayName(),
                        ChatColor.BLUE + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                        ChatColor.DARK_BLUE + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                        ChatColor.RED + "Kills : " + ChatColor.WHITE + player.getKills(),
                        ChatColor.RED + "Morts : " + ChatColor.WHITE + player.getDeaths(),
                        "");
            } else if(countdown<=0 && countdown2>=0 && countdownQuineStart>=0) {
                String countdownString1 = String.format("%02d:%02d:%02d", countdown2 / 3600, (countdown2 % 3600) / 60, countdown2 % 60);
                String countdownString2 = String.format("%02d:%02d:%02d", countdownQuineStart / 3600, (countdownQuineStart % 3600) / 60, countdownQuineStart % 60);

                board.updateLines("",
                        ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                        ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + timeString,
                        ChatColor.RED + "PVP ACTIVÉ !",
                        ChatColor.GOLD + "END activé dans : " + ChatColor.WHITE + countdownString1 + "s",
                        ChatColor.GOLD + "Quine dans : " + ChatColor.WHITE + countdownString2 + "s",
                        ChatColor.DARK_GREEN + "Equipe : " + player.getTeam().getColor() + player.getTeam().getDisplayName(),
                        ChatColor.BLUE + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                        ChatColor.DARK_BLUE + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                        ChatColor.RED + "Kills : " + ChatColor.WHITE + player.getKills(),
                        ChatColor.RED + "Morts : " + ChatColor.WHITE + player.getDeaths(),

                        "");
            } else if(countdown<=0 && countdown2>=0 && countdownQuineStart<=0) {
                String countdownString1 = String.format("%02d:%02d:%02d", countdown2 / 3600, (countdown2 % 3600) / 60, countdown2 % 60);
                String countdownString2 = String.format("%02d:%02d:%02d", countdownQuineEnd / 3600, (countdownQuineEnd % 3600) / 60, countdownQuineEnd % 60);

                board.updateLines("",
                        ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                        ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + timeString,
                        ChatColor.RED + "PVP ACTIVÉ !",
                        ChatColor.GOLD + "END activé dans : " + ChatColor.WHITE + countdownString1 + "s",
                        ChatColor.GOLD + "Quine fini dans : " + ChatColor.WHITE + countdownString2 + "s",
                        ChatColor.DARK_GREEN + "Equipe : " + player.getTeam().getColor() + player.getTeam().getDisplayName(),
                        ChatColor.BLUE + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                        ChatColor.DARK_BLUE + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                        ChatColor.RED + "Kills : " + ChatColor.WHITE + player.getKills(),
                        ChatColor.RED + "Morts : " + ChatColor.WHITE + player.getDeaths(),

                        "");
            } else {
                board.updateLines("",
                        ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                        ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + timeString,
                        ChatColor.RED + "PVP ACTIVÉ !",
                        ChatColor.DARK_PURPLE + "END ACTIVÉ !",
                        ChatColor.AQUA + "QUINE FINI !",
                        ChatColor.DARK_GREEN + "Equipe : " + player.getTeam().getColor() + player.getTeam().getDisplayName(),
                        ChatColor.BLUE + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                        ChatColor.DARK_BLUE + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                        ChatColor.RED + "Kills : " + ChatColor.WHITE + player.getKills(),
                        ChatColor.RED + "Morts : " + ChatColor.WHITE + player.getDeaths(),

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

                World world = Bukkit.getWorlds().get(0);

                if (time <= 1) {
                    for(Player player:Bukkit.getOnlinePlayers()) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 50, 1));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 50, 1));
                    }
                }
                if (time <= 0) {
                    this.cancel();
                    endGame();
                }
                if (time <= 9000 && !quine) { // au bout de 30 min la quine commence
                    QuineItem.start();
                    quine = true;
                }
                if (time <= 7200 && !end) { // end au bout d'une heure
                    Location[] endGateBlocks = new Location[] {
                            new Location(world, 2, 64, 7),
                            new Location(world, 2, 63, 7),
                            new Location(world, 2, 62, 7),
                            new Location(world, 1, 64, 7),
                            new Location(world, 1, 63, 7),
                            new Location(world, 1, 62, 7)
                    };
                    for (Location location : endGateBlocks) {
                        Block block = location.getBlock();
                        if (block.getType() != Material.AIR) {
                            block.breakNaturally();
                        }
                    }
                    Location[] endChests = new Location[] {
                            new Location(world, -8, 54, 16),
                            new Location(world, -5, 54, 11),
                            new Location(world, -14, 52, 8),
                            new Location(world, -21, 52, 13),
                            new Location(world, -20, 52, 9),
                            new Location(world, -4, 54, 20),
                            new Location(world, -9, 54, 28),
                            new Location(world, -16, 54, 23),
                            new Location(world, -19, 54, 15),
                            new Location(world, 1, 54, 26),
                    };
                    for (Location location : endChests) {
                        GenerateChest.spawnEndChest(location);
                    }
                    for(Player player: Bukkit.getOnlinePlayers()){
                        player.sendMessage(ChatColor.DARK_PURPLE+"L'end est ouvert le premier à récupérer l'oeuf du dragon recevra"+ChatColor.GOLD+" 25 000"+ChatColor.DARK_PURPLE+" poudres");
                    }
                    end = true;
                }
                if (time <= randomTime && !darkHenry) { // Dark Henry spawn entre 30m et 1h20 ; remettre à 6250
                    new DarkHenry(new Location(Bukkit.getWorlds().get(0), 0, 62.5, 1.5, 90, 0));
                    Location location = new Location(world, 0, 62, 1);
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.PURPLE, 4.0f);
                    world.spawnParticle(Particle.REDSTONE, location, 100, 2, 2, 2, 1, dustOptions);

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendTitle(ChatColor.RED + "Dark Henry vient d'arriver au marché", "", 10, 70, 20);
                        p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10f, 10f);
                        p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10f, 10f);

                        p.sendMessage(ChatColor.RED+"Le frère maléfique de Henry est là, il est pressé, il ne vous accorde qu'un seul échange, dépéchez-vous !");
                    }
                    darkHenry = true;
                }

            }
        };
        timer.runTaskTimer(OneNightCity.getInstance(), 0, 20);
    }

    public void startGame() {
        hasStarted = true;

        //Bukkit.broadcastMessage(ChatColor.YELLOW + "VERSION TEST, PENSER A REMETTRE LES TIMER CORRECT (mettre en commentaire quand c'est bon)");

        long startingTime = Instant.now().getEpochSecond();
        long endingTime = startingTime + 3 * 3600;
        time = (int) (endingTime - startingTime);

        // events
        pvp = false;
        quine = false;
        end = false;
        darkHenry = false;
        uneHeureRestante = false;
        annonceFin = false;

        EventGame.LancerConcours();
        EventGame.revealPlayerPositions();
        createDark();
        GenerateChest.spawnCoffre();

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getPlayer().getInventory().clear();
            GamePlayer gamePlayer = GamePlayer.getInstance(player);

            gamePlayer.resetKills();
            gamePlayer.resetDeaths();
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
        World world = Bukkit.getWorlds().get(0);
        world.setPVP(false);
        
        List<Player> playersWithEgg = new ArrayList<>();
        randomTime = random.nextInt(3001) + 6000;

        timer = new BukkitRunnable() {
            @Override
            public void run() {
                if(ONCGame.getInstance().hasStarted())
                    ClassementPoudre.showScoreboard();

                time = (int) (endingTime - Instant.now().getEpochSecond());

                if (time <= 3600 && !uneHeureRestante) { // Si il reste 1 heure
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle("1 heure restante", "", 10, 70, 20);
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 5f, 5f);
                    }
                    uneHeureRestante = true;
                }
                if (playersWithEgg.isEmpty()) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.getInventory().contains(Material.DRAGON_EGG)) {
                            Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "§lL'oeuf de Dragon' a été récupéré !§r\n"
                                    + ChatColor.DARK_PURPLE + "§oVoici le joueur qui a récuperer l'oeuf : §r"
                                    + player.getName() + ChatColor.DARK_PURPLE + "§o, il a gagné  25 000 poudres");
                            Bukkit.getOnlinePlayers().forEach(p -> p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 10f, 10f));
                            GamePlayer gamePlayer = GamePlayer.getInstance(player);
                            gamePlayer.getTeam().addScore(25000);
                            gamePlayer.addScore(25000);
                            playersWithEgg.add(player);
                        }
                    }
                }
                if (time <= 10200 && !pvp) { // Temps au bout de 10 min de jeu
                    world.setPVP(true);

                    // Envoyer le titre à tous les joueurs en ligne
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        // Envoyer le titre avec un sous-titre vide, aucun temps de fade-in ou fade-out et une durée d'affichage de 20 ticks
                        player.sendTitle(ChatColor.GREEN + "Le PVP est activé", ChatColor.GRAY + "Préparez-vous à combattre !", 0, 40, 10);
                        // Jouer un son à tous les joueurs
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    }
                    pvp = true;
                }
                if (time <= 10) { // Pour la fin de la partie
                    if (!annonceFin) {
                        Bukkit.broadcastMessage(ChatColor.RED + "Fin de la partie dans 10...");
                        annonceFin = true;
                    } else {
                        if (time > 0)
                            Bukkit.broadcastMessage(ChatColor.RED + "..." + time);
                        else {
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.getInventory().clear();
                                player.sendMessage(ChatColor.RED + "FIN DE LA PARTIE, GG À TOUS");
                                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 100, 100); // Joue le son de l'enderman
                                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 100, 100); // Joue le son de l'enderman
                                player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_SCREAM, 100, 100); // Joue le son de l'enderman
                            }
                        }
                    }
                }
                updateBoard();
            }
        };
        timer.runTaskTimerAsynchronously(OneNightCity.getInstance(), 0, 20);

        // nord-est
        new SombreHeros(new Location(world, 16, 71, -21));
        new Ikikomori(new Location(world, 20.5, 71, -20.5, 45, 0));
        new NegeuxDemo(new Location(world, 22, 71, -16, 90, 0));
        // sud-est
        new LucieAcier(new Location(world, 22, 70, 16, 90, 0));
        new SylvainDurif(new Location(world, 21.5, 70, 20.5, 135, 0));
        new DrRaoult(new Location(world, 17, 70, 22, 180, 0));
        // sud-ouest
        new KylianMBouffe(new Location(world, -15, 70, 22, 180, 0));
        new JeanMineur(new Location(world, -19.5, 70, 21.5, -135, 0));
        new LesPierres(new Location(world, -21, 70, 17, -90, 0));
        // nord-ouest
        new MicoseMicode(new Location(world, -21, 71, -15, -90, 0));
        new HutilItaire(new Location(world, -20.5, 71, -19.5, -45, 0));
        new BeauThony(new Location(world, -16, 71, -21));
        // henry
        new Henry(new Location(world, 36.5, 68.5, 19));
        // port
        new Dream(new Location(world, 45, 68, -11.5, 90, 0));
        new JykaRouler(new Location(world, 50, 68, -11.5, -90, 0));
        new FrancisClodo(new Location(world, 45, 68, -18.5, 90, 0));
        new Jeaneau(new Location(world, 50, 68, -18.5, -90, 0));
        new CheepCheap(new Location(world, 57.5, 69, -17.5, 90, 0));
        new JustinPuech(new Location(world, 55.5, 68, 1, 90, 0));
        new Legias(new Location(world, 55.5, 68, 4, 90, 0));
        new VigneHill(new Location(world, 52.5, 69, 16.5, 0, 0));
    }

    public void endGame(){
        hasStarted = false;
        if (timer != null) timer.cancel();
        World overworld = Bukkit.getWorlds().get(0);

        overworld.setGameRule(GameRule.FALL_DAMAGE, false);
        overworld.setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        overworld.setTime(0);

        // stop trackers
        for (Map.Entry<UUID, PlayerTracker> entry : PlayerTracker.getTrackers().entrySet())
            entry.getValue().stop();

        for (Player player : Bukkit.getOnlinePlayers()) {

            player.getPlayer().teleport(new Location(overworld,122,154,-39));
            player.setGameMode(GameMode.ADVENTURE);
            player.getInventory().clear();
            FastBoard board = boards.get(player.getUniqueId());
            board.delete();
            player.setExp(0);
            player.setLevel(0);

            // Créer un feu d'artifice près de la localisation spécifiée
            Location fireworkLocation = new Location(overworld, 122, 154, -39);

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
                p.sendTitle(ChatColor.GREEN + " Félicitations à l'équipe gagnante !", ChatColor.AQUA + winningTeam.getDisplayName(), 10, 70, 20);
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

        // supprime les villageois à 100 blocks de rayon du spawn
        Location spawn = new Location(overworld, 0, 70, 0);
        for (Entity entity : overworld.getEntities()) {
            if (entity.getType() == EntityType.VILLAGER) {
                if (entity.getLocation().distance(spawn) <= 100)
                    entity.remove();
            }
        }

        for (GameTeam team : teams)
            team.reset();

        teams.clear();
        players.clear();
    }

    public void resetTeams(){
        for (GameTeam team : teams)
            team.reset();
        teams.clear();
        players.clear();
    }

}