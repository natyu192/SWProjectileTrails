package me.nucha.swprojectiletrail.effects;

import org.bukkit.Material;
import org.bukkit.entity.Entity;

public abstract class ProjectileTrail {

	protected String name;
	protected String id;
	protected Material icon;

	public ProjectileTrail(String name, String id, Material icon) {
		this.name = name;
		this.id = id;
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public Material getIcon() {
		return icon;
	}

	public abstract void play(Entity target);

}
