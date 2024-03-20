package me.chaounne.onenightcity.events;

import me.chaounne.onenightcity.OneNightCity;
import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.game.ONCGame;
import me.chaounne.onenightcity.game.PoudreItem;
import me.chaounne.onenightcity.villager.*;
import me.chaounne.onenightcity.villager.spawners.Spawner;
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
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class Handler implements Listener {

    private final ONCGame game = ONCGame.getInstance();

    private final Map<Player, Boolean> playerDeathStatus = new HashMap<>();

    private final List<Spawner> spawners = new ArrayList<>();

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        if (event.getCause() == PlayerPortalEvent.TeleportCause.END_PORTAL) {
            Player player = event.getPlayer();
            player.setBedSpawnLocation(new Location(player.getWorld(), 0,70,0), true);
       }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        if (!ONCGame.getInstance().hasStarted()) {
            event.setRespawnLocation(new Location(Bukkit.getWorlds().get(0), 122, 154, -39));
            return;
        }
        new BukkitRunnable() {
            int count = 5; // Compte à rebours initial de 5 secondes

            @Override
            public void run() {
                if (count > 0) {
                    // Envoyer le titre avec le compte à rebours
                    player.sendTitle("Respawn :", count + " secondes", 10, 30, 10);
                    count--;

                    // Appliquer les effets de potion
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 2 * 20, 3));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 3));
                    player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                } else {
                    player.playSound(player.getLocation(), Sound.ENCHANT_THORNS_HIT, 1.0f, 1.0f);
                    this.cancel(); // Arrêter le compte à rebours une fois qu'il atteint 0
                    // Vous pouvez ajouter d'autres actions ici si nécessaire
                }
            }
        }.runTaskTimer(OneNightCity.getInstance(), 0L, 20L);

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);

        if (playerDeathStatus.getOrDefault(player, false)) {
            // marche aussi avec le respawn anchor
            Location bedSpawnPoint = player.getBedSpawnLocation();
            if (bedSpawnPoint == null) {
                World overworld = Bukkit.getWorlds().get(0);
                event.setRespawnLocation(new Location(overworld, 0, 70, 0));
            }
            playerDeathStatus.put(player, false);
            player.getInventory().clear();
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
            player.setFireTicks(0);
            player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5 * 20, 3));

            ItemStack[] armorContents = {
                    boots,
                    leggings,
                    chestplate,
                    helmet
            };
            player.getInventory().setArmorContents(armorContents);
        }
    }

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
        String message = event.getMessage();

        GamePlayer gamePlayer = GamePlayer.getInstance(player);

        if (gamePlayer.getTeam() != null) {
            event.setFormat(gamePlayer.getTeam().getColor() + "[" + gamePlayer.getTeam().getName() + "] " + player.getName() + " : " + ChatColor.RESET + message);

            if (message.startsWith("@")) {
                event.setCancelled(true);
                for (Player teamMember : gamePlayer.getTeam().getPlayers())
                    teamMember.sendMessage("" + gamePlayer.getTeam().getColor() + ChatColor.BOLD + "<Privé> [" + gamePlayer.getTeam().getName() + "] " + player.getName() + " : " + ChatColor.RESET + message.substring(1));
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!ONCGame.getInstance().hasStarted()) return;
        Player player = event.getEntity();
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 5 * 20, 3));

        Player killer = player.getKiller();

        if (killer != null) {
            GamePlayer killerGamePlayer = GamePlayer.getInstance(killer);
            killerGamePlayer.getTeam().addScore(500);
            killerGamePlayer.addScore(500);
            killer.sendMessage(ChatColor.GOLD + "Vous avez gagné 500 poudres pour avoir tué " + ChatColor.GOLD + player.getName() + " !");
        }

        playerDeathStatus.put(player, true);
    }

    @EventHandler
    public void onPlayerItemDropped(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!ONCGame.getInstance().hasStarted()) {
            player.sendMessage("Tu peux pas drop ici :)");
            event.setCancelled(true); // Annuler le drop si le jeu n'est pas démarré
            return; // Sortir de la méthode, car le drop est déjà annulé
        }
        if (player.getGameMode() == GameMode.SURVIVAL) {
            Item item = event.getItemDrop();
            if (Objects.equals(item.getItemStack().getItemMeta(), PoudreItem.getItem().getItemMeta())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if(!ONCGame.getInstance().hasStarted()){
            Player player = event.getPlayer();
            World overworld = Bukkit.getWorlds().get(0);
            player.getPlayer().teleport(new Location(overworld, 122, 154, -39));
            player.setGameMode(GameMode.ADVENTURE);
            PlayerInventory playerInventory = player.getInventory();
            playerInventory.clear();

        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getPlayer();

        if (inventory.getHolder() instanceof Merchant) {
            Merchant merchant = (Merchant) inventory.getHolder();
            List<MerchantRecipe> recipes = merchant.getRecipes();

            for (MerchantRecipe recipe : recipes) {
                if (player.getInventory().containsAtLeast(recipe.getResult(), recipe.getResult().getAmount())) {
                    World world = Bukkit.getWorlds().get(0);

                    for (Entity entity : world.getEntities()) {
                        if (entity.getWorld().equals(player.getWorld())) { // Vérifier si les mondes sont les mêmes
                            if (entity.getName().equals(ChatColor.DARK_RED + "Dark Henry")) {
                                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                                    if (onlinePlayer.getWorld().equals(entity.getWorld())) {
                                        if (player.getLocation().distance(entity.getLocation()) <= 6) {
                                            onlinePlayer.sendMessage(ChatColor.RED + "Dark Henry a échangé l'item spécial avec " + player.getName() + " ! Dark Henry s'en va");
                                            onlinePlayer.playSound(onlinePlayer.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_4, 1f, 1f);
                                            entity.remove();
                                        }
                                    }
                                }
                            }
                        }
                    }

                    handlePoudreItems(player);
                    return;
                }
            }
        }

        if (event.getPlayer() instanceof Player) {
            Player player3 = (Player) event.getPlayer();
            if (player3.getGameMode() == GameMode.SURVIVAL && !player.getInventory().isEmpty()) {
                handlePoudreItems(player);
            }
        }
    }

    private void handlePoudreItems(Player player) {
        GamePlayer gamePlayer = GamePlayer.getInstance(player);
        int poudresPersoAvantEchange = gamePlayer.getScore();

        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null) {
                if (Objects.equals(item.getItemMeta(), PoudreItem.getItem().getItemMeta())) {
                    handlePoudreItem(player, gamePlayer, item, 1);
                } else if (Objects.equals(item.getItemMeta(), PoudreItem.getSuperPoudre().getItemMeta())) {
                    handlePoudreItem(player, gamePlayer, item, 1000);
                }
            }
        }

        int poudresPersoApresEchange = gamePlayer.getScore();
        int poudresGagnees = poudresPersoApresEchange - poudresPersoAvantEchange;

        if (poudresGagnees > 0 && gamePlayer.getTeam() != null) {
            // Choisissons un message aléatoire pour ajouter un peu de magie à l'annonce !
            ChatColor[] colors = {
                    ChatColor.LIGHT_PURPLE,
                    ChatColor.AQUA,
                    ChatColor.DARK_PURPLE,
                    ChatColor.GOLD,
                    ChatColor.RED,
                    ChatColor.GREEN,
                    ChatColor.BLUE,
                    ChatColor.WHITE,
                    ChatColor.GRAY,
                    ChatColor.DARK_GREEN,
                    ChatColor.DARK_RED,
                    ChatColor.YELLOW
            };
            // Choisissons un message aléatoire pour ajouter un peu de magie à l'annonce !
            String[] messages = {
                    "Félicitations ! " + poudresGagnees + " poudre(s) magiques pour votre équipe ! ",
                    "Gloire ! " + poudresGagnees + " poudre(s) magiques ont été récoltées ! ",
                    "Seulement " + poudresGagnees + " poudre(s) ont été ajouté à votre équipe ! ",
                    "Bon y'a mieux mais " + poudresGagnees + " poudre(s) gagnées ! ",
                    "Encore toi ? " + poudresGagnees + " poudre(s) encore obtenues ! ",
                    "WOW mais quel montant incroyable y'a " + poudresGagnees + " poudre(s) ajoutées à votre équipe ! ",
                    "Merveilleux ! " + poudresGagnees + " poudre(s) magiques ont été gagnées ! ",
                    "Ronpich Zzzz ! " + poudresGagnees + " poudre(s), c'est tout ?! ",
                    "Espece de rat, t'as que " + poudresGagnees + " poudre(s) en echange pour la peine ! ",
                    "Oula, c'est peu quand meme : " + poudresGagnees + " poudre(s) ajoutées ! "
            };

            // Choisissons au hasard un des messages magiques à envoyer !
            Random random = new Random();
            int randomColorIndex = random.nextInt(colors.length);
            ChatColor randomColor = colors[randomColorIndex];

            int randomMessageIndex = random.nextInt(messages.length);
            String randomMessage = randomColor + messages[randomMessageIndex];

            // Envoyons la nouvelle enchantée à chaque membre de l'équipe !
            for (Player teamMember : gamePlayer.getTeam().getPlayers()) {
                // Pour que chacun vive la magie à sa manière, offrons-lui son propre message !
                teamMember.sendMessage(randomMessage);
            }
        }
    }

    private void handlePoudreItem(Player player, GamePlayer gamePlayer, ItemStack item, int scoreToAdd) {
        for (int i = 0; i < item.getAmount(); i++) {
            gamePlayer.getTeam().addScore(scoreToAdd);
            gamePlayer.addScore(scoreToAdd);
        }
        player.getInventory().removeItem(item);
    }

    // cut clean
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event){
        if(event.getPlayer().getGameMode() == GameMode.SURVIVAL) {
            int skilkTouchLevel = event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.SILK_TOUCH);
            if(skilkTouchLevel > 0) return;
            if(event.getBlock().getType().equals(Material.GOLD_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_GOLD_ORE)){
                event.setCancelled(true);
                int fortuneLevel = event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                int drops = fortuneLevel > 0 ? (int) (Math.random() * (2 + fortuneLevel)) + 1 : 1; // calcul du nombre de lingots d'or générés
                event.getBlock().setType(Material.AIR);
                for (int i = 0; i < drops; i++) { // boucle pour générer le nombre de lingots d'or spécifié
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT));
                    if(event.getPlayer().getExp() +0.2f >1){
                        event.getPlayer().setExp(0);
                        event.getPlayer().setLevel(event.getPlayer().getLevel() + 1);
                    }
                    event.getPlayer().setExp(event.getPlayer().getExp() + 0.2f);
                }
            } else if(event.getBlock().getType().equals(Material.IRON_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_IRON_ORE)){
                event.setCancelled(true);
                int fortuneLevel = event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                int drops = fortuneLevel > 0 ? (int) (Math.random() * (2 + fortuneLevel)) + 1 : 1; // calcul du nombre de lingots de fer générés
                event.getBlock().setType(Material.AIR);
                for (int i = 0; i < drops; i++) { // boucle pour générer le nombre de lingots de fer spécifié
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT));
                    if(event.getPlayer().getExp() +0.1f >1){
                        event.getPlayer().setExp(0);
                        event.getPlayer().setLevel(event.getPlayer().getLevel() + 1);
                    }
                    event.getPlayer().setExp(event.getPlayer().getExp() + 0.1f);
                }
            } else if(event.getBlock().getType().equals(Material.COPPER_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_COPPER_ORE)){
                event.setCancelled(true);
                int fortuneLevel = event.getPlayer().getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
                int drops = fortuneLevel > 0 ? (int) (Math.random() * (2 + fortuneLevel)) + 1 : 1; // calcul du nombre de lingots de cuivre générés
                event.getBlock().setType(Material.AIR);
                for (int i = 0; i < drops; i++) { // boucle pour générer le nombre de lingots de cuivre spécifié
                    event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.COPPER_INGOT));
                    if(event.getPlayer().getExp() +0.1f >1){
                        event.getPlayer().setExp(0);
                        event.getPlayer().setLevel(event.getPlayer().getLevel() + 1);
                    }
                    event.getPlayer().setExp(event.getPlayer().getExp() + 0.1f);
                }
            }
        }

        Location location = event.getBlock().getLocation();
        int size = this.spawners.size();
        for (int i = size - 1; i >= 0; i--) {
            if (this.spawners.get(i).checkIfBroken(location)) {
                this.spawners.remove(i);
            }
        }
    }

    @EventHandler
    public void onBlockPlaced(BlockPlaceEvent event){
        Location blockPos = event.getBlock().getLocation();
        if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Spawner de diamant")) {
            this.spawners.add(new Spawner(Material.DIAMOND, blockPos));
        }
        if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Spawner d'or")) {
            this.spawners.add(new Spawner(Material.GOLD_INGOT, blockPos));
        }
        if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Spawner de fer")) {
            this.spawners.add(new Spawner(Material.IRON_INGOT, blockPos));
        }
        if (event.getItemInHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN + "Spawner d'émeraude")) {
            this.spawners.add(new Spawner(Material.EMERALD, blockPos));
        }
    }

    // bouffe déjà cuite
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if (event.getEntity() instanceof Player)
            return;

        for (ItemStack item : event.getDrops()) {
            if (item.getType().equals(Material.BEEF))
                item.setType(Material.COOKED_BEEF);
            else if (item.getType().equals(Material.CHICKEN))
                item.setType(Material.COOKED_CHICKEN);
            else if (item.getType().equals(Material.COD))
                item.setType(Material.COOKED_COD);
            else if (item.getType().equals(Material.MUTTON))
                item.setType(Material.COOKED_MUTTON);
            else if (item.getType().equals(Material.RABBIT))
                item.setType(Material.COOKED_RABBIT);
            else if (item.getType().equals(Material.SALMON))
                item.setType(Material.COOKED_SALMON);
            else if (item.getType().equals(Material.PORKCHOP))
                item.setType(Material.COOKED_PORKCHOP);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (!game.hasStarted()) event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event){
        if(!game.hasStarted()) event.setCancelled(true);

        if(event.getEntity() instanceof Villager){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (event.getRightClicked() instanceof Villager) {
            Player player = event.getPlayer();
            Villager villager = (Villager) event.getRightClicked();
            if (villager.getCustomName() != null && villager.getCustomName().equals("Henry")) {
                event.setCancelled(true);
                Henry.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Beau Thony")) {
                event.setCancelled(true);
                BeauThony.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Cheep Cheap")) {
                event.setCancelled(true);
                CheepCheap.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Francis Clodo")) {
                event.setCancelled(true);
                FrancisClodo.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Hutil Itaire")) {
                event.setCancelled(true);
                HutilItaire.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Jeaneau")) {
                event.setCancelled(true);
                Jeaneau.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Jean Mineur")) {
                event.setCancelled(true);
                JeanMineur.openInventory(player);
            } else if(villager.getCustomName() != null && villager.getCustomName().equals("Justin Puech")){
                event.setCancelled(true);
                JustinPuech.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Jyka Rouler")){
                event.setCancelled(true);
                JykaRouler.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Kylian MBouffé")){
                event.setCancelled(true);
                KylianMBouffe.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Legias")){
                event.setCancelled(true);
                Legias.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Les Pierres")){
                event.setCancelled(true);
                LesPierres.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Lucie Acier")){
                event.setCancelled(true);
                LucieAcier.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Micose Micode")){
                event.setCancelled(true);
                MicoseMicode.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Négeux Demo")){
                event.setCancelled(true);
                NegeuxDemo.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Dr. Raoult")) {
                event.setCancelled(true);
                DrRaoult.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Civique Cosmique Durif")){
                event.setCancelled(true);
                SylvainDurif.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Ikikomori")){
                event.setCancelled(true);
                Ikikomori.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Sombre Héros")){
                event.setCancelled(true);
                SombreHeros.openInventory(player);
            } else if (villager.getCustomName() != null && villager.getCustomName().equals("Vigne Hill")){
                event.setCancelled(true);
                VigneHill.openInventory(player);
            }
        }
    }

    @EventHandler
    public void onPlayerItemDamage(PlayerItemDamageEvent e) {
        e.setCancelled(true);
    }

}
