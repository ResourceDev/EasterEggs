package org.explorersbay.tasks;

import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class ParticleRunnable extends BukkitRunnable {

    private Location location;
    @Setter private boolean isCancel = false;
    private World world;

    public ParticleRunnable(Location location) {
        this.location = location;
        this.world = location.getWorld();
    }

    @Override
    public void run() {
        world.spawnParticle(Particle.SPELL_WITCH, location, 10, 0, 0, 0, 3);
        if (this.isCancel) {
            this.cancel();
        }
    }
}
