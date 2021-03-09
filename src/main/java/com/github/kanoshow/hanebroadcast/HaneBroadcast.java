package com.github.kanoshow.hanebroadcast;

import com.github.kanoshow.hanebroadcast.Listener.EveJoin;
import com.github.kanoshow.hanebroadcast.Utils.FirstSP;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class HaneBroadcast extends JavaPlugin {
    private static HaneBroadcast instance;

    @Override
    public void onEnable() {
        instance = this;
        this.saveDefaultConfig();
        Bukkit.getServer().getPluginManager().registerEvents(new FirstSP(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new EveJoin(), this);
    }
    @Override
    public void onDisable() {
        this.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender s, org.bukkit.command.Command cmd, String label, String[] args) {
        Player p = null;
        if (s instanceof Player) {
            p = (Player) s;
        } else {
            s.sendMessage("コンソールから実行は出来ないっぽいです!");
            return true;
        }
        if (cmd.getName().equals("sp")) {
            FirstSP.OpenGUI(p);
            return true;
        }
        return false;
    }

    public static HaneBroadcast getInstance() {
        return instance;
    }
}