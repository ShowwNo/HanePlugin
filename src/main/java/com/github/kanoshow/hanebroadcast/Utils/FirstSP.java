package com.github.kanoshow.hanebroadcast.Utils;

import com.github.kanoshow.hanebroadcast.HaneBroadcast;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class FirstSP implements Listener {
    private static Inventory i;

    public FirstSP() {
        i = Bukkit.createInventory(null, 9, "§8» §c§l実行しますか?");
        i.setItem(2, Gui.createGuiItem(Material.RED_STAINED_GLASS_PANE, "§7» §c§lやめとく!", ""));
        i.setItem(4, Gui.createGuiItem(Material.BLUE_STAINED_GLASS_PANE, "§7» §9§lやっちまえ!", ""));
    }

    public static void OpenGUI(Player p) {
        p.sendMessage("現在の座標: §b" + p.getLocation().toString());
        p.openInventory(i);
    }

    @EventHandler
    private void clickEvent(InventoryClickEvent e) {
        if (e.getInventory() != (i)) { return; }
        e.setCancelled(true);
        HumanEntity p = e.getWhoClicked();
        if (e.getSlot()== 2) {
            p.closeInventory();
            p.sendMessage("§cキャンセルされました!");
        }
        else if (e.getSlot()== 4) {
            p.closeInventory();
            p.sendMessage("§b実行されました!");
            setSpawnPoint(p.getLocation());
        }
    }

    private void setSpawnPoint(Location l) {
        HaneBroadcast.getInstance().getConfig().set("sp.location", l);
        HaneBroadcast.getInstance().getConfig().set("sp.status", true);
        HaneBroadcast.getInstance().saveConfig();
    }
}
