package me.chaounne.onenightcity.events;

import me.chaounne.onenightcity.game.GamePlayer;
import me.chaounne.onenightcity.game.PoudreItem;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class Handler implements Listener{

    @EventHandler
    public void onPlayerItemPickupEvent(EntityPickupItemEvent event) {
        if(event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            GamePlayer gamePlayer = GamePlayer.getInstance(player);
            if(player.getGameMode() == GameMode.SURVIVAL) {
                if(event.getItem().getItemStack().equals(PoudreItem.getItem())){
                    // ajoute autant de score à la team du joueur que de poudre ramassée
                    for (int i = 0; i < event.getItem().getItemStack().getAmount(); i++) {
                        gamePlayer.getTeam().addScore(1);
                    }
                    // enlève toutes les poudres de l'inventaire du joueur
                    player.getInventory().remove(PoudreItem.getItem());
                }
            }
        }
    }



}
