package me.nucha.swprojectiletrail.listeners;

import me.nucha.swprojectiletrail.effects.ProjectileTrail;
import me.nucha.swprojectiletrail.manager.ProjectileTrailManager;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class ProjectileListener implements Listener {

	private ProjectileTrailManager projectileTrailManager;

	public ProjectileListener(ProjectileTrailManager projectileTrailManager) {
		this.projectileTrailManager = projectileTrailManager;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		if (projectileTrailManager.getProjectileTrail(p) == null) {
			projectileTrailManager.setProjectileTrail(p, "VANILLA");
		}
	}

	@EventHandler
	public void onProjectileShoot(ProjectileLaunchEvent event) {
		Projectile e = event.getEntity();
		if (e.getShooter() instanceof Player) {
			Player p = (Player) e.getShooter();
			ProjectileTrail trail = projectileTrailManager.getProjectileTrail(p);
			if (trail != null) {
				trail.play(e);
			}
		}
	}
}
