package me.chaounne.onenightcity.game;

import fr.mrmicky.fastboard.FastBoard;
import me.chaounne.onenightcity.OneNightCity;

import me.chaounne.onenightcity.villager.DarkHenryEntity;

import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class ONCGame implements Listener {

    private ArrayList<Team> teams = new ArrayList<>();

    private final List<GamePlayer> players = new ArrayList<>();

    private static ONCGame instance;

    private boolean started = false;

    private BukkitRunnable timer;
    private int time = 300;
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

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player realPlayer = event.getPlayer();
        GamePlayer player = GamePlayer.getInstance(realPlayer);
        FastBoard board = new FastBoard(realPlayer);
        board.updateTitle(ChatColor.DARK_BLUE + "Cité d'une nuit");
        boards.put(realPlayer.getUniqueId(), board);
        if(isStarted()){
            if(!players.contains(player)) realPlayer.setGameMode(GameMode.SPECTATOR);
        }
    }

    public void updateBoard() {
        for (GamePlayer player : players) {
            System.out.println(player.getPlayer().getName());
            FastBoard board = boards.get(player.getPlayer().getUniqueId());
            board.updateTitle(ChatColor.DARK_BLUE + "Cité d'une nuit");

            int hours = time / 3600; // nombre d'heures
            int minutes = (time % 3600) / 60; // nombre de minutes
            int seconds = time % 60; // nombre de secondes

            String timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds); // formatage de l'heure


            int countdown2 = time - 270;
            int countdown4 = time -250;
            int countdown = time - 280; // countdown de 10 secondes
            if (countdown >= 0) {
                String countdownString = String.format("%02d", countdown); // formatage du compte à rebours
                String countdownString1 = String.format("%02d", countdown2);
                String countdownString3 = String.format("%02d", countdown4);


                board.updateLines("",
                        ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                        ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + timeString,
                        ChatColor.GOLD + "PVP Activé dans : " + ChatColor.WHITE + countdownString + "s",
                        ChatColor.GOLD + "END Activé dans : " + ChatColor.WHITE + countdownString1 + "s",
                        ChatColor.GOLD + "Quine fini dans : " + ChatColor.WHITE + countdownString3 + "s",
                        ChatColor.GOLD + "Equipe : " + player.getTeam().getColor() + player.getTeam().getName(),
                        ChatColor.GOLD + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                        ChatColor.GOLD + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                        "");
            } else if(countdown<=0 && countdown2>=0) {
                String countdownString1 = String.format("%02d", countdown2);
                String countdownString3 = String.format("%02d", countdown4);


                board.updateLines("",
                        ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                        ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + timeString,
                        ChatColor.RED + "PVP ACTIVÉ !",
                        ChatColor.GOLD + "END Activé dans : " + ChatColor.WHITE + countdownString1 + "s",
                        ChatColor.GOLD + "Quine fini dans : " + ChatColor.WHITE + countdownString3 + "s",
                        ChatColor.GOLD + "Equipe : " + player.getTeam().getColor() + player.getTeam().getName(),
                        ChatColor.GOLD + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                        ChatColor.GOLD + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                        "");
            }else if(countdown<=0 && countdown2<=0 && countdown4>=0){
                String countdownString3 = String.format("%02d", countdown4);


                board.updateLines("",
                            ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                            ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + timeString,
                            ChatColor.RED + "PVP ACTIVÉ !",
                            ChatColor.RED + "END ACTIVÉ !",
                            ChatColor.GOLD + "Quine fini dans : " + ChatColor.WHITE + countdownString3 + "s",

                            ChatColor.GOLD + "Equipe : " + player.getTeam().getColor() + player.getTeam().getName(),
                            ChatColor.GOLD + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                            ChatColor.GOLD + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                            "");
            }else {
                board.updateLines("",
                        ChatColor.GOLD + "Joueurs : " + ChatColor.WHITE + Bukkit.getOnlinePlayers().size(),
                        ChatColor.GOLD + "Temps restant : " + ChatColor.WHITE + timeString,
                        ChatColor.RED + "PVP ACTIVÉ !",
                        ChatColor.RED + "END ACTIVÉ !",
                        ChatColor.RED + "QUINE FINI !",
                        ChatColor.GOLD + "Equipe : " + player.getTeam().getColor() + player.getTeam().getName(),
                        ChatColor.GOLD + "Poudres d'équipe : " + ChatColor.WHITE + player.getTeam().getScore(),
                        ChatColor.GOLD + "Poudres perso : " + ChatColor.WHITE + player.getScore(),
                        "");
            }

        }
    }

    public boolean isStarted(){
        return started;
    }

    public void createDark() {
        World world = Bukkit.getWorlds().get(0); // Récupère le premier monde de la liste


        timer = new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorlds().get(0); // Récupère le premier monde de la liste

                if (time > 14370 ) {
                    for (Entity entity : world.getEntities()) {
                        if (entity.getLocation().getBlockX() == 0 && entity.getLocation().getBlockY() == 62 && entity.getLocation().getBlockZ() == 1) {
                            entity.remove();
                        }
                        if (entity instanceof LivingEntity && entity.getName().equals("DARKHenry")) {
                            entity.remove();
                        }
                    }
                }
                if (time == 0) {
                    this.cancel();
                    endGame();
                }
                if (time == 290){
                    QuineItem.start();
                }


                if (time == 30) {

                    DarkHenryEntity.getEntity(new Location(Bukkit.getWorlds().get(0), 0, 62, 1));
                    Location location = new Location(world, 0, 62, 1);
                    Particle.DustOptions dustOptions = new Particle.DustOptions(Color.PURPLE, 4.0f);
                    world.spawnParticle(Particle.REDSTONE, location, 100, 2, 2, 2, 1, dustOptions);

                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendTitle(ChatColor.RED + "DARKHENRY vient d'arriver au marché", "", 10, 70, 20);
                        p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10f, 10f);
                        p.sendMessage(ChatColor.RED+"DARKHenry est là, il n'effectuera qu'UN SEUL ECHANGE. Soyez donc le premier à faire l'échange");
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
        int timeLeft = 10;
        createDark();
        GenerateChest generateChest = new GenerateChest();
        for(Player player : Bukkit.getOnlinePlayers()){
            player.getPlayer().getInventory().clear();
            FastBoard board = new FastBoard(player);
            board.updateTitle(ChatColor.DARK_BLUE + "Cité d'une nuit");
            boards.put(player.getUniqueId(), board);
            player.setFoodLevel(20);
            player.setHealth(20);
            player.setSaturation(20);
            player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
            player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
            player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 15));
            ItemStack[] armorContents = {
                    new ItemStack(Material.IRON_BOOTS),
                    new ItemStack(Material.IRON_LEGGINGS),
                    new ItemStack(Material.IRON_CHESTPLATE),
                    new ItemStack(Material.IRON_HELMET)
            };
            player.getInventory().setArmorContents(armorContents);
        }
        World world = Bukkit.getWorlds().get(0); // Récupère le premier monde de la liste
        world.setPVP(false);
        timer = new BukkitRunnable() {
            @Override
            public void run() {
                ClassementPoudre.showScoreboard();

                if (time == 3600) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle("1 heure restante", "", 10, 70, 20);
                        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 5f, 5f);
                    }

                }

                if(time==280) {
                    world.setPVP(true);
                    for(Player player:Bukkit.getOnlinePlayers()){
                        player.sendMessage("Le pvp est activé");
                    }
                }
                if(time==1){
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

        generateChest.spawnCoffre();
        //generateChest.spawnCoffre();
        if (!started) started = true;
        timer.runTaskTimerAsynchronously(OneNightCity.getInstance(), 0, 20);
    }



    public void endGame(){
        if(timer != null) timer.cancel();
        for(Player players : Bukkit.getOnlinePlayers()){
            players.getPlayer().teleport(new Location(players.getWorld(),122,154,-39));
            players.setGameMode(GameMode.ADVENTURE);
            FastBoard board = boards.get(players.getUniqueId());
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
