package me.chaounne.onenightcity.villager;

import fr.mrmicky.fastinv.ItemBuilder;
import me.chaounne.onenightcity.inventory.SampleInventory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import java.util.ArrayList;

public class CheepCheapEntity {

    private static SampleInventory sampleInventory;

    public static Villager getEntity(Location loc) {
        setInventory();
        Villager cheap = (Villager) loc.getWorld().spawnEntity(loc, EntityType.VILLAGER);

        cheap.setCustomName("Cheep Cheap");
        cheap.setCustomNameVisible(true);
        cheap.setVillagerType(Villager.Type.TAIGA);
        cheap.setProfession(Villager.Profession.SHEPHERD);
        cheap.setAI(false);
        cheap.setSilent(true);
        cheap.setCollidable(false);
        cheap.setVillagerExperience(5);
        cheap.setVillagerLevel(5);
        cheap.setAdult();
        cheap.setCanPickupItems(false);
        cheap.setRemoveWhenFarAway(false);
        cheap.setRecipes(new ArrayList<>());

        return cheap;
    }

    public static void setInventory() {
        sampleInventory = new SampleInventory(18, "Cheep Cheap");

        String[] woolColors = {
                "WHITE_WOOL", "RED_WOOL", "BLUE_WOOL", "GREEN_WOOL", "YELLOW_WOOL",
                "PURPLE_WOOL", "ORANGE_WOOL", "MAGENTA_WOOL", "LIGHT_BLUE_WOOL",
                "LIGHT_GRAY_WOOL", "GRAY_WOOL", "BLACK_WOOL", "BROWN_WOOL",
                "CYAN_WOOL", "LIME_WOOL"
        };

        for (int i = 0; i < woolColors.length; i++) {
            int price = (int) (Math.random() * 23) + 2;
            int amount = (int) (Math.random() * 9) + 1;

            Material woolMaterial = Material.getMaterial(woolColors[i]);
            if (woolMaterial != null) {
                sampleInventory.addItem(
                        new ItemBuilder(woolMaterial)
                                .amount(amount)
                                .addLore(price + " Poudres")
                                .build(),
                        i,
                        price
                );
            }
        }
    }

    public static void openInventory(Player player) {
        sampleInventory.open(player);
    }

}