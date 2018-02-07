package me.nucha.swprojectiletrail.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.nucha.swprojectiletrail.SWProjectileTrail;
import me.nucha.swprojectiletrail.effects.GreenStarTrail;
import me.nucha.swprojectiletrail.effects.HeartsTrail;
import me.nucha.swprojectiletrail.effects.NotesTrail;
import me.nucha.swprojectiletrail.effects.ProjectileTrail;
import me.nucha.swprojectiletrail.effects.VanillaTrail;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ProjectileTrailManager {

	private List<ProjectileTrail> projectileTrails;
	private HashMap<String, ProjectileTrail> projectileTrailMap;
	private SWProjectileTrail plugin;

	public ProjectileTrailManager(SWProjectileTrail plugin) {
		projectileTrails = new ArrayList<>();
		registerProjectileTrail(new VanillaTrail());
		registerProjectileTrail(new HeartsTrail());
		registerProjectileTrail(new GreenStarTrail());
		registerProjectileTrail(new NotesTrail());
		this.plugin = plugin;
		loadProjectileTrails();
	}

	public void loadProjectileTrails() {
		projectileTrailMap = new HashMap<>();
		FileConfiguration dataYml = plugin.getDataYml();
		for (String uuid : dataYml.getKeys(false)) {
			String effectId = dataYml.getString(uuid + ".selected-effect");
			projectileTrailMap.put(uuid, getProjectileTrailById(effectId));
		}
	}

	public void saveProjectileTrails() {
		FileConfiguration dataYml = plugin.getDataYml();
		for (String uuid : projectileTrailMap.keySet()) {
			dataYml.set(uuid + ".selected-effect", projectileTrailMap.get(uuid).getId());
		}
		plugin.saveDataYml();
	}

	public ProjectileTrail getProjectileTrailByName(String name) {
		for (ProjectileTrail projectileTrail : projectileTrails) {
			if (projectileTrail.getName().equalsIgnoreCase(name)) {
				return projectileTrail;
			}
		}
		return null;
	}

	public void registerProjectileTrail(ProjectileTrail projectileTrail) {
		projectileTrails.add(projectileTrail);
	}

	public ProjectileTrail getProjectileTrailById(String id) {
		for (ProjectileTrail projectileTrail : projectileTrails) {
			if (projectileTrail.getId().equalsIgnoreCase(id)) {
				return projectileTrail;
			}
		}
		return null;
	}

	public ProjectileTrail getProjectileTrail(Player p) {
		String uuid = p.getUniqueId().toString();
		if (projectileTrailMap.containsKey(uuid)) {
			return projectileTrailMap.get(uuid);
		}
		return null;
	}

	public void setProjectileTrail(Player p, ProjectileTrail trail) {
		String uuid = p.getUniqueId().toString();
		if (projectileTrailMap.containsKey(uuid)) {
			projectileTrailMap.remove(uuid);
		}
		if (trail != null) {
			projectileTrailMap.put(uuid, trail);
		}
	}

	public void setProjectileTrail(Player p, String trailId) {
		String uuid = p.getUniqueId().toString();
		if (projectileTrailMap.containsKey(uuid)) {
			projectileTrailMap.remove(uuid);
		}
		ProjectileTrail trail = getProjectileTrailById(trailId);
		if (trail != null) {
			projectileTrailMap.put(uuid, trail);
		}
	}

	public boolean hasSelected(Player p, ProjectileTrail trail) {
		if (trail.equals(getProjectileTrail(p))) {
			return true;
		}
		return false;
	}

	public boolean hasSelected(Player p, String trailId) {
		if (getProjectileTrail(p).getId().contentEquals(trailId)) {
			return true;
		}
		return false;
	}

}
