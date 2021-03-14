package org.explorersbay.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.explorersbay.EasterEggs;
import org.explorersbay.objects.EggPlayer;

public class EasterEvents implements Listener {

    EasterEggs main;
    public EasterEvents(EasterEggs main) {
        this.main = main;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoinEvent(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        EggPlayer eggPlayer = main.getPlayer(player.getUniqueId());
        if (eggPlayer == null) {
            eggPlayer = new EggPlayer(player);
            main.addEggPlayer(eggPlayer);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerLeaveEvent(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        EggPlayer eggPlayer = main.getPlayer(player.getUniqueId());
        if (eggPlayer != null) {
            main.removeEggPlayer(eggPlayer);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onRightClickEggEvent(PlayerInteractEvent e) {
        Action action = e.getAction();

        if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
            Block block = e.getClickedBlock();
            if (block.getType().equals(Material.PLAYER_HEAD)) {
                if (block instanceof Skull) {
                    System.out.println("Right Clicked a Skull Block");
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEggPlaceEvent(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        if (block instanceof Skull) {
            if (block.getType().equals(Material.PLAYER_HEAD)) {
                if (player.hasPermission("explorersbay.easter.place")) {
                    //Placing egg if you have the permission
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEggBreakEvent(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        Block block = e.getBlock();
        if (block instanceof Skull) {
            if (block.getType().equals(Material.PLAYER_HEAD)) {
                if (player.hasPermission("explorersbay.easter.break")) {
                    //Event for breaking explorers egg
                    
                }
            }
        }
    }

}
