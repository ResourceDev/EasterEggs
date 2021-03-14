package org.explorersbay;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.explorersbay.events.EasterEvents;
import org.explorersbay.objects.EggPlayer;
import org.explorersbay.objects.EggReward;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class EasterEggs extends JavaPlugin {

    @Getter private File dataFile;
    @Getter private FileConfiguration dataConfig;
    @Getter private File configFile;
    @Getter private FileConfiguration configActual;
    @Getter private List<String> base64 = new ArrayList<>();
    @Getter private List<EggReward> rewards = new ArrayList<>();
    @Getter private List<EggPlayer> players = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        generateConfigs();
        base64 = configActual.getStringList("head_list");
        System.out.println(base64);

        for (String s : configActual.createSection("rewards").getKeys(false)) {
            List<String> commands = configActual.getStringList("rewards." + s + ".commands");
            double chance = configActual.getDouble("rewards." + s + ".chance");
            EggReward eggReward = new EggReward(chance, commands);
            rewards.add(eggReward);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            boolean hasEggPlayer = false;
            for (EggPlayer eggPlayer : players) {
                UUID uuid = player.getUniqueId();
                if (eggPlayer.getUuid().equals(uuid)) {
                    hasEggPlayer = true;
                }
            }

            if (!hasEggPlayer) {
                EggPlayer eggPlayer = new EggPlayer(player);
                players.add(eggPlayer);
            }
        }

        getServer().getPluginManager().registerEvents(new EasterEvents(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        for (EggPlayer player : players) {
            player.saveData();
        }

    }

    public void addEggPlayer(EggPlayer player) {
        players.add(player);
    }

    public void removeEggPlayer(EggPlayer player) {
        players.remove(player);
    }

    public void generateConfigs() {
        dataFile = new File(getDataFolder(), "data.yml");
        configFile = new File(getDataFolder(), "config.yml");

        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }

        if (!configFile.exists()) {
            configFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        dataConfig = new YamlConfiguration();
        configActual = new YamlConfiguration();
        try {
            dataConfig.load(dataFile);
            configActual.load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public EggPlayer getPlayer(UUID uuid) {
        for (EggPlayer eggPlayer : players) {
            if (eggPlayer.getUuid().equals(uuid)) {
                return eggPlayer;
            }
        }

        return null;
    }

    public EggReward generateRandomReward() {
        return null;
    }
}
