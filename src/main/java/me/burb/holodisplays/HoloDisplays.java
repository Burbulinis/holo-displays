package me.burb.holodisplays;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import me.filoghost.holographicdisplays.api.HolographicDisplaysAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class HoloDisplays extends JavaPlugin {

    private SkriptAddon addon;
    private static HolographicDisplaysAPI holographicDisplaysAPI;
    private static HoloDisplays instance;

    public static HoloDisplays getInstance() {
        return instance;
    }

    public static PluginManager getPluginManager() {
        return Bukkit.getPluginManager();
    }

    public static HolographicDisplaysAPI getHDAPI() {
        return holographicDisplaysAPI;
    }

    @Override
    public void onEnable() {

        instance = this;
        holographicDisplaysAPI = HolographicDisplaysAPI.get(this);

        if (!Bukkit.getPluginManager().isPluginEnabled("HolographicDisplays")) {
            getLogger().severe("*** HolographicDisplays is not installed or not enabled. ***");
            getLogger().severe("*** holo-displays will be disabled. ***");
            setEnabled(false);
            return;
        }

        if (!Bukkit.getPluginManager().isPluginEnabled("Skript")) {
            getLogger().severe("*** Skript is not installed or not enabled. ***");
            getLogger().severe("*** holo-displays will be disabled. ***");
            setEnabled(false);
            return;
        }

        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("me.burb.holodisplays", "skript");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
