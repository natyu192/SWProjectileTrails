package me.nucha.swprojectiletrail.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.nucha.swprojectiletrail.SWProjectileTrail;
import me.nucha.swprojectiletrail.utils.ConfigUtil;

public class CommandSWProjectileTrail implements CommandExecutor {

	private SWProjectileTrail plugin;
	public String prefix = "§8[§cSWProjectileTrail§8] §r";

	public CommandSWProjectileTrail(SWProjectileTrail plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("gui")) {
				if (!(sender instanceof Player)) {
					return true;
				}
				Player p = (Player) sender;
				plugin.getGuiProjectileTrailSelector().open(p);
				return true;
			}
			if (args[0].equalsIgnoreCase("reload")) {
				plugin.getProjectileTrailManager().saveProjectileTrails();
				plugin.reloadConfig();
				ConfigUtil.init(plugin);
				sender.sendMessage(prefix + "§aconfig.yml reloaded!");
				return true;
			}
		}
		if (args.length == 2) {
			if (args[0].equalsIgnoreCase("gui")) {
				Player t = Bukkit.getPlayer(args[1]);
				if (t != null) {
					plugin.getGuiProjectileTrailSelector().open(t);
				}
				return true;
			}
		}
		sender.sendMessage(prefix + "§a-- §cSWProjectileTrail §aby §eNucha §a--");
		sender.sendMessage(prefix + "§a/swpt gui [player] §2--- §bOpen Projectile Trail selector");
		sender.sendMessage(prefix + "§a/swpt reload §2--- §bReloads config.yml");
		return true;
	}

}
