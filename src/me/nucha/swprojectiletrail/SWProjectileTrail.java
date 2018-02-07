package me.nucha.swprojectiletrail;

import java.io.File;
import java.io.IOException;

import me.nucha.swprojectiletrail.commands.CommandSWProjectileTrail;
import me.nucha.swprojectiletrail.gui.GuiProjectileTrailSelector;
import me.nucha.swprojectiletrail.listeners.ProjectileListener;
import me.nucha.swprojectiletrail.manager.ProjectileTrailManager;
import me.nucha.swprojectiletrail.utils.ConfigUtil;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SWProjectileTrail extends JavaPlugin {

	private static SWProjectileTrail instance;
	private ProjectileTrailManager projectileTrailManager;
	private FileConfiguration dataYml;
	private GuiProjectileTrailSelector guiProjectileTrailSelector;

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		ConfigUtil.init(this);
		loadDataYml();
		projectileTrailManager = new ProjectileTrailManager(this);
		Bukkit.getPluginManager().registerEvents(new ProjectileListener(projectileTrailManager), this);
		Bukkit.getPluginManager().registerEvents(guiProjectileTrailSelector = new GuiProjectileTrailSelector(projectileTrailManager), this);
		getCommand("swprojectiletrail").setExecutor(new CommandSWProjectileTrail(this));
	}

	@Override
	public void onDisable() {
		projectileTrailManager.saveProjectileTrails();
	}

	public void loadDataYml() {
		saveResource("data.yml", false);
		File dataYmlFile = new File(getDataFolder() + "/data.yml");
		if (!dataYmlFile.exists()) {
			try {
				dataYmlFile.createNewFile();
			} catch (IOException e) {
				log("§cdata.yml の読み込み中にエラーが発生しました");
				e.printStackTrace();
				return;
			}
		}
		dataYml = YamlConfiguration.loadConfiguration(dataYmlFile);
		log("§adata.yml を読み込みました");
	}

	public void saveDataYml() {
		File dataYmlFile = new File(getDataFolder() + "/data.yml");
		if (dataYmlFile.exists()) {
			try {
				dataYml.save(dataYmlFile);
			} catch (IOException e) {
				log("§cdata.yml のセーブ中にエラーが発生しました");
				e.printStackTrace();
			}
		}
		log("§adata.yml をセーブしました");
	}

	public FileConfiguration getDataYml() {
		return dataYml;
	}

	public static SWProjectileTrail getInstance() {
		return instance;
	}

	public static void log(String text) {
		Bukkit.getConsoleSender().sendMessage("§8[§cSWProjectileTrail§8] §r" + text);
	}

	public ProjectileTrailManager getProjectileTrailManager() {
		return projectileTrailManager;
	}

	public GuiProjectileTrailSelector getGuiKillEffectSelector() {
		return guiProjectileTrailSelector;
	}

}
