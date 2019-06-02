package be.alexandre01.moderation;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Moderation extends JavaPlugin {
    String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    public HashMap<Player, Integer> playersmap;
    public ArrayList<Player> playerslist = new ArrayList<Player>();
    public ArrayList<Player> players = new ArrayList<Player>();

    public Moderation() {
        playersmap = new HashMap<Player,Integer>();
    }

    public void onEnable() {

        System.out.println("Moderation plugin  est active");
        Bukkit.getPluginManager().registerEvents(new Events(this), this);
        getCommand("mod").setExecutor(new Commands(this));
    }

    public HashMap<Player, Integer> getKey() {
        return playersmap;
    }
    public ArrayList<Player> getList() {
        return playerslist;
    }
    public List<Player> getPlayers(){
        return players;
    }
    public void onDisable() {

    }

}

