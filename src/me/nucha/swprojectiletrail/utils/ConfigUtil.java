package me.nucha.swprojectiletrail.utils;

import me.nucha.swprojectiletrail.SWProjectileTrail;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ConfigUtil {

	public static int delay;

	public static void init(SWProjectileTrail plugin) {
		try {
			delay = plugin.getConfig().getInt("delay");
		} catch (Exception e) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (all.isOp()) {
					all.sendMessage("\n§8[§cSWProjectileTrail§8] §cconfig.yml の読み込み中にエラーが発生しました (" + e.getLocalizedMessage() + ")");
					all.sendMessage("§8[§cSWProjectileTrail§8] §cコンソールを見て、 config.yml を修正してください\n");
				}
			}
			e.printStackTrace();
		}
	}

}
