package org.explorersbay.objects;

import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class EggPlayer {

    @Getter private short eggAmount;
    @Getter private String username;
    @Getter private UUID uuid;
    private File dataFile;
    private FileConfiguration dataConfig;

    public EggPlayer(Player player) {

        this.uuid = player.getUniqueId();
        this.username = player.getName();

        //Generate configuration if it does not exist:
        dataFile = new File("plugins/EasterEggs/playerdata", uuid.toString() + ".yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
        }
        dataConfig = new YamlConfiguration();
        try {
            dataConfig.load(dataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        if (dataConfig.contains("eggsCollected")) {
            eggAmount = (short) dataConfig.getInt("eggsCollected");
        } else {
            eggAmount = 0;
        }

        saveData();

    }

    public void saveData() {
        dataConfig.set("eggsCollected", eggAmount);
        dataConfig.set("username", username);
        dataConfig.set("uuid", uuid.toString());
    }

}
