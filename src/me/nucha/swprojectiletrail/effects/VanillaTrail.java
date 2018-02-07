package me.nucha.swprojectiletrail.effects;

import me.nucha.swprojectiletrail.SWProjectileTrail;
import me.nucha.swprojectiletrail.utils.ConfigUtil;
import me.nucha.swprojectiletrail.utils.ParticleEffect;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

public class VanillaTrail extends ProjectileTrail {

	public VanillaTrail() {
		super("Vanilla Trail", "VANILLA", Material.SAND);
	}

	@Override
	public void play(Entity target) {
		BukkitRunnable runnable = new BukkitRunnable() {
			@Override
			public void run() {
				if (target == null || target.isOnGround() || target.isDead()) {
					cancel();
				}
				ParticleEffect.CRIT.display(0, 0, 0, 0, 1, target.getLocation(), 256);
			}

		};
		runnable.runTaskTimer(SWProjectileTrail.getInstance(), ConfigUtil.delay, ConfigUtil.delay);
	}

}
