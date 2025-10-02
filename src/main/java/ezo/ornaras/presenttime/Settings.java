package ezo.ornaras.presenttime;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

class Settings {
    private static int durationMobSpawn;
    private static int durationPeacetime;
    private static boolean initializeNewPlayers;
    private static List<String> disabledWorld;

    public static void load() {
        FileConfiguration conf = PresentTime.singleton.getConfig();

        durationMobSpawn = conf.getInt("durationMobSpawn", 300);
        durationPeacetime = conf.getInt("durationPeacetime", 300);
        initializeNewPlayers = conf.getBoolean("initializeNewPlayers", true);
        disabledWorld = conf.getStringList("disabledWorld");
    }

    public static int getDurationMobSpawn() { return durationMobSpawn; }
    public static int getDurationPeacetime() { return durationPeacetime; }
    public static boolean getInitializeNewPlayers() { return initializeNewPlayers; }
    public static List<String> getDisabledWorld() { return disabledWorld; }
}
