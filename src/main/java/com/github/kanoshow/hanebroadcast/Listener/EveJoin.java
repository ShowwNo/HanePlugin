package com.github.kanoshow.hanebroadcast.Listener;

import com.github.kanoshow.hanebroadcast.HaneBroadcast;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;

public class EveJoin implements Listener {
    @EventHandler
    public void firstJoinEvent(PlayerJoinEvent e) {
        FileConfiguration c = HaneBroadcast.getInstance().getConfig();
        Player p = e.getPlayer();
        String msg = "§7» §e§l" + p.getName() + " §7さんが初めてサーバーに参加しました!";
        if (!p.hasPlayedBefore()) {
            if (c.getBoolean("sp.status")) {
                Location loc = c.getLocation("sp.location");
                assert loc != null;
                p.teleport(loc);
            }
            if (!Bukkit.getOnlinePlayers().isEmpty()) Bukkit.getOnlinePlayers().forEach(player -> {
                player.sendMessage(msg);
                Objects.requireNonNull(player.getLocation().getWorld()).playSound(
                        player.getLocation(),
                        Sound.ENTITY_PLAYER_LEVELUP,
                        1,
                        1
                );
            });
        }
    }
}
