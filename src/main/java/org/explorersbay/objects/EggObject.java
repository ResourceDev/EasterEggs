package org.explorersbay.objects;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitScheduler;
import org.explorersbay.EasterEggs;
import org.explorersbay.tasks.ParticleRunnable;

public class EggObject {

    private EasterEggs main = (EasterEggs) Bukkit.getPluginManager().getPlugin("EasterEggs");
    @Getter private int x;
    @Getter private int y;
    @Getter private int z;
    @Getter private World world;
    @Getter private Location location;
    @Getter private int taskId;

    public EggObject(int x, int y, int z, World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.world = world;
        this.location = new Location(world,x,y,z);
        runParticles();
    }

    public EggObject(Location location) {
        this.x = location.getBlockX();
        this.y = location.getBlockY();
        this.z = location.getBlockZ();
        this.world = location.getWorld();
        this.location = location;
        runParticles();
    }

    public void runParticles() {
        ParticleRunnable runnable = new ParticleRunnable(location);
        runnable.runTaskTimerAsynchronously(main, 0L, 20L);
    }

}
