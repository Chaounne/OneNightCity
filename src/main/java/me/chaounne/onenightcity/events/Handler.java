package me.chaounne.onenightcity.events;

import me.chaounne.onenightcity.OneNightCity;
import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.PoudreItem;
import me.chaounne.onenightcity.game.Team;
import me.chaounne.onenightcity.villager.HenryEntity;
import me.chaounne.onenightcity.villager.spawners.Spawners;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static me.chaounne.onenightcity.villager.DarkHenryEntity.henry2;

public class Handler implements Listener {

    public final List<GamePlayer> test = new ArrayList<>();
   private int poudresPersoAvantEchange;
    private int poudresPersoApresEchange;
    private ONCGame game = ONCGame.getInstance();

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();

            if (player.getGameMode() == GameMode.SURVIVAL) {
                if (player.getInventory().isEmpty()) return;
                GamePlayer gamePlayer = GamePlayer.getInstance(player);
                if (gamePlayer.getTeam() == null) return;
                // regarde si le joueur à de la poudre
                for (ItemStack item : player.getInventory().getContents()) {


                    poudresPersoAvantEchange = gamePlayer.getScore();

                    // ...



                    if (item != null && Objects.equals(item.getItemMeta(), PoudreItem.getSuperPoudre().getItemMeta())) {
                        // ajoute autant de score à la team du joueur que de poudre ramassée
                        for (int i = 0; i < item.getAmount(); i++) {
                            gamePlayer.getTeam().addScore(10);
                            gamePlayer.addScore(10);
                        }
                        player.getInventory().removeItem(item);
                    }
                }
            }
        }
    }


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        GamePlayer gamePlayer = GamePlayer.getInstance(player);
        if (gamePlayer.getTeam() != null) {
            event.setFormat(gamePlayer.getTeam().getColor() + "[" + gamePlayer.getTeam().getName() + "] " + player.getName() + ChatColor.RESET + " : " + event.getMessage());
        } else {
            event.setFormat(ChatColor.GRAY + player.getName() + ChatColor.RESET + " : " + event.getMessage());
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        GamePlayer gamePlayer = GamePlayer.getInstance(player);

        // si le joueur n'a pas de getBedSpawnLocation(), il est tp au 0,0
        if (player.getBedSpawnLocation() == null) {
            player.teleport(new Location(player.getWorld(), 0, 70, 0));
        }

        if (gamePlayer.hasBounty()) {
            Player killer = player.getKiller();
            if (killer != null) {
                GamePlayer gameKiller = GamePlayer.getInstance(killer);
                GamePlayer beter = gamePlayer.getBeter();

                if (beter != null && beter.getTeam() != null && beter.getTeam() != gameKiller.getTeam() && beter.getTeam() != gamePlayer.getTeam()) {
                    beter.addScore((int) (gamePlayer.getBounty() * 1.25));
                    beter.getTeam().addScore((int) (gamePlayer.getBounty() * 1.25));

                    gameKiller.addScore(gamePlayer.getBounty());
                    gameKiller.getTeam().addScore(gamePlayer.getBounty());

                    beter.getPlayer().sendMessage("Vous avez récupéré la prime de " + gamePlayer.getBounty() + " points multipliée par 1.25 !");
                    gameKiller.getPlayer().sendMessage("Vous avez tué " + player.getName() + " et récupéré sa prime de " + gamePlayer.getBounty() + " points !");
                    gamePlayer.getPlayer().sendMessage("Vous avez été tué par " + killer.getName() + " et perdu votre prime de " + gamePlayer.getBounty() + " points !");

                    gamePlayer.removeBounty();
                    gamePlayer.removeBeter();
                }
            }
        }
    }

    @EventHandler
    public void onPlayerItemDropped(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.SURVIVAL) {
            Item item = event.getItemDrop();
            if (Objects.equals(item.getItemStack().getItemMeta(), PoudreItem.getItem().getItemMeta())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getPlayer();
        GamePlayer gamePlayer = GamePlayer.getInstance(player);
        Player players = (Player) event.getPlayer();


        if (inventory.getHolder() instanceof Merchant) {
            Merchant merchant = (Merchant) inventory.getHolder();
            List<MerchantRecipe> recipes = merchant.getRecipes();




            // Vérifier si le joueur a effectué un trade avec le villageois
            for (MerchantRecipe recipe : recipes) {




                //TEST2 quand je trade avec un villageois avant que DARKHENRY spawn
                if (players.getInventory().containsAtLeast(recipe.getResult(), recipe.getResult().getAmount())) {

                    World world = Bukkit.getWorlds().get(0); // Récupère le premier monde de la liste
                    Location entityLocation = new Location(world, 0, 62, 1);

                    for (Entity entity : world.getEntities()) {
                        if (entity.getLocation().equals(entityLocation)) {
                            for (Player playere : Bukkit.getOnlinePlayers()) {
                                if (playere.getLocation().distance(entityLocation) <= 3) {
                                    // Le joueur est à moins de 3 blocs de l'entité
                                    playere.sendMessage(ChatColor.RED + "DARKHenry à échangé l'ITEM spécial avec " + players.getName() + " !" + " DARKHenry s'en va");
                                    playere.playSound(playere.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_4, 1f, 1f);
                                    // Ajoute le nombre de poudres à la team du joueur
                                    entity.remove(); // Supprime l'entité
                                }
                            }
                        }
                    }





                    for(ItemStack item : players.getInventory().getContents()){
                        if(item != null&& Objects.equals(item.getItemMeta(), PoudreItem.getItem().getItemMeta())){
                            GamePlayer gamePlayer1 = GamePlayer.getInstance(players);

                            // ajoute autant de score à la team du joueur que de poudre ramassée
                            for (int i = 0; i < item.getAmount(); i++) {
                                gamePlayer1.getTeam().addScore(1);
                                gamePlayer1.addScore(1);
                            }
                            players.getInventory().removeItem(item);
                        }

                        if(item != null&& Objects.equals(item.getItemMeta(), PoudreItem.getSuperPoudre().getItemMeta())){
                            GamePlayer gamePlayer2 = GamePlayer.getInstance(players);

                            // ajoute autant de score à la team du joueur que de poudre ramassée
                            for (int i = 0; i < item.getAmount(); i++) {
                                gamePlayer2.getTeam().addScore(100);
                                gamePlayer2.addScore(100);
                            }
                            players.getInventory().removeItem(item);
                        }
                    }
                    poudresPersoApresEchange = gamePlayer.getScore();

                    int poudresGagnees = poudresPersoApresEchange - poudresPersoAvantEchange; // Calcule le nombre de poudres gagnées
                    if (poudresGagnees > 0) {
                        Team team = gamePlayer.getTeam();
                        if (team != null) {
                            String message = "Votre équipe à gagnée " + poudresGagnees + " poudres  !";
                            for (Player teamMember : team.getPlayers()) {
                                teamMember.sendMessage(message);
                            }
                        }
                    }
                    return; // Sortir de la boucle une fois qu'un trade est trouvé
                }
            }
        }

        if(event.getPlayer() instanceof Player){
            Player player3 = (Player) event.getPlayer();
            if(player3.getGameMode() == GameMode.SURVIVAL){
                if(player.getInventory().isEmpty()) return;
                GamePlayer gamePlayer3 = GamePlayer.getInstance(player);
                if(gamePlayer.getTeam() == null) return;
                // regarde si le joueur à de la poudre
                for(ItemStack item : player.getInventory().getContents()){
                    if(item != null&& Objects.equals(item.getItemMeta(), PoudreItem.getItem().getItemMeta())){
                        // ajoute autant de score à la team du joueur que de poudre ramassée
                        for (int i = 0; i < item.getAmount(); i++) {
                            gamePlayer3.getTeam().addScore(1);
                            gamePlayer3.addScore(1);
                        }
                        player3.getInventory().removeItem(item);
                    }

                    if(item != null&& Objects.equals(item.getItemMeta(), PoudreItem.getSuperPoudre().getItemMeta())){
                        // ajoute autant de score à la team du joueur que de poudre ramassée
                        for (int i = 0; i < item.getAmount(); i++) {
                            gamePlayer3.getTeam().addScore(100);
                            gamePlayer3.addScore(100);
                        }
                        player3.getInventory().removeItem(item);
                    }
                }
            }
        }
    }

    // cut clean
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        if(event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            if(event.getBlock().getType().equals(Material.GOLD_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_GOLD_ORE)){
                event.setCancelled(true);
                int fortuneLevel = event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                int drops = fortuneLevel > 0 ? (int) (Math.random() * (2 + fortuneLevel)) + 1 : 1; // calcul du nombre de lingots d'or générés
                event.getBlock().setType(Material.AIR);
                for (int i = 0; i < drops; i++) { // boucle pour générer le nombre de lingots d'or spécifié
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
                    event.getPlayer().setExp(event.getPlayer().getExp() + 1);
                }
            } else if(event.getBlock().getType().equals(Material.IRON_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_IRON_ORE)){
                event.setCancelled(true);
                int fortuneLevel = event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                int drops = fortuneLevel > 0 ? (int) (Math.random() * (2 + fortuneLevel)) + 1 : 1; // calcul du nombre de lingots de fer générés
                event.getBlock().setType(Material.AIR);
                for (int i = 0; i < drops; i++) { // boucle pour générer le nombre de lingots de fer spécifié
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                    event.getPlayer().setExp(event.getPlayer().getExp() + 1);
                }
            } else if(event.getBlock().getType().equals(Material.COPPER_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_COPPER_ORE)){
                event.setCancelled(true);
                int fortuneLevel = event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                int drops = fortuneLevel > 0 ? (int) (Math.random() * (2 + fortuneLevel)) + 1 : 1; // calcul du nombre de lingots de cuivre générés
                event.getBlock().setType(Material.AIR);
                for (int i = 0; i < drops; i++) { // boucle pour générer le nombre de lingots de cuivre spécifié
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.COPPER_INGOT));
                    event.getPlayer().setExp(event.getPlayer().getExp() + 1);
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event){
        // if the block is spawner.getDiamondSpawner
        ItemStack item = event.getItemInHand();
        ItemStack diamondSpawner = Spawners.getDiamondSpawner();
        ItemStack goldSpawner = Spawners.getGoldSpawner();
        ItemStack ironSpawner = Spawners.getIronSpawner();
        ItemStack emeraldSpawner = Spawners.getEmeraldSpawner();
        if(item.equals(diamondSpawner)){
            // spawn diamond on top of the spawner
            BukkitRunnable runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    if(!game.isStarted()) this.cancel();
                    event.getBlockPlaced().getWorld().dropItemNaturally(event.getBlockPlaced().getLocation().add(0, 1, 0), new ItemStack(Material.DIAMOND));
                }};
            runnable.runTaskTimer(OneNightCity.getInstance(), 0, 200);
        } else if(item.equals(goldSpawner)){
            // spawn gold on top of the spawner
            BukkitRunnable runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    if(!game.isStarted()) this.cancel();
                    event.getBlockPlaced().getWorld().dropItemNaturally(event.getBlockPlaced().getLocation().add(0, 1, 0), new ItemStack(Material.GOLD_INGOT));
                }};
            runnable.runTaskTimer(OneNightCity.getInstance(), 0, 200);
        } else if(item.equals(ironSpawner)){
            // spawn iron on top of the spawner
            BukkitRunnable runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    if(!game.isStarted()) this.cancel();
                    event.getBlockPlaced().getWorld().dropItemNaturally(event.getBlockPlaced().getLocation().add(0, 1, 0), new ItemStack(Material.IRON_INGOT));
                }};
            runnable.runTaskTimer(OneNightCity.getInstance(), 0, 200);
        } else if(item.equals(emeraldSpawner)){
            // spawn emerald on top of the spawner
            BukkitRunnable runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    if(!game.isStarted()) this.cancel();
                    event.getBlockPlaced().getWorld().dropItemNaturally(event.getBlockPlaced().getLocation().add(0, 1, 0), new ItemStack(Material.EMERALD));
                }};
            runnable.runTaskTimer(OneNightCity.getInstance(), 0, 200);
        }
    }

    // bouffe déjà cuite
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        Entity entity = event.getEntity();
        if (entity instanceof Pig){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.PORKCHOP)){
                    item.setType(Material.COOKED_PORKCHOP);
                }
            }
        } else if(entity instanceof Cow){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.BEEF)){
                    item.setType(Material.COOKED_BEEF);
                }
            }
        } else if (entity instanceof Sheep){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.MUTTON)){
                    item.setType(Material.COOKED_MUTTON);
                }
            }
        } else if (entity instanceof Chicken){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.CHICKEN)){
                    item.setType(Material.COOKED_CHICKEN);
                }
            }
        } else if(entity instanceof Rabbit){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.RABBIT)){
                    item.setType(Material.COOKED_RABBIT);
                }
            }
        } else if(entity instanceof Salmon){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.SALMON)){
                    item.setType(Material.COOKED_SALMON);
                }
            }
        } else if(entity instanceof Cod){
            for (ItemStack item : event.getDrops()) {
                if(item.getType().equals(Material.COD)){
                    item.setType(Material.COOKED_COD);
                }
            }
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event){if(!game.isStarted()) event.setCancelled(true);}

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
        if(!game.isStarted()) event.setCancelled(true);

        if(event.getEntity() instanceof Villager){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent e) {e.setCancelled(true);}
}
