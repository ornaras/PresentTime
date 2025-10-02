package ezo.ornaras.presenttime;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import java.io.File;
import java.util.*;

public final class PresentTime extends JavaPlugin {

    public Map<UUID,BukkitTask> clients = new HashMap<>();
    public static PresentTime singleton;

    public void createConfig(){
        File f = new File(getDataFolder() + File.separator + "config.yml");
        if (!f.exists()) {
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        }
    }

    @Override
    public void onEnable() {
        createConfig();
        singleton = this;
        for (World w: getServer().getWorlds()) {
            if(!getConfig().getStringList("disabledWorld").contains(w.getName()))
                w.setGameRuleValue("doDaylightCycle", "false");
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                for (World w: getServer().getWorlds()) {
                    if(!getConfig().getStringList("disabledWorld").contains(w.getName()))
                        w.setTime(w.getTime()==6000?18000:6000);
                }
            }
        }.runTaskTimer(this,0L, 20L*getConfig().getInt("delayDayCycle"));
        getServer().getPluginManager().registerEvents(new Events(), this);

    }

    @Override
    public void onDisable() {
        for (World w: getServer().getWorlds()) {
            if(!getConfig().getStringList("disabledWorld").contains(w.getName()))
                w.setGameRuleValue("doDaylightCycle", "true");
        }
    }
}
