package me.nucha.swprojectiletrail.gui;

import me.nucha.swprojectiletrail.manager.ProjectileTrailManager;
import me.nucha.swprojectiletrail.utils.CustomItem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiProjectileTrailSelector implements Listener {

	private ProjectileTrailManager trailManager;

	public GuiProjectileTrailSelector(ProjectileTrailManager killEffectManager) {
		this.trailManager = killEffectManager;
	}

	public void open(Player p) {
		Inventory inventory = Bukkit.createInventory(null, 54, "Projectile Trails");
		ItemStack VANILLA = new CustomItem(trailManager.getProjectileTrailById("VANILLA").getIcon(), 1,
				"§aVanilla Trail",
				"§7Select the Vanilla Trail.",
				"§7This change is cosmetic.",
				(!trailManager.hasSelected(p, "VANILLA") ? "§eClick to select!" : "§aSELECTED!"));
		ItemStack HEARTS = new CustomItem(trailManager.getProjectileTrailById("HEARTS").getIcon(), 1,
				"§aHearts Trail",
				"§7Select the Hearts Trail.",
				"§7This change is cosmetic.",
				(!trailManager.hasSelected(p, "HEARTS") ? "§eClick to select!" : "§aSELECTED!"));
		ItemStack NOTES = new CustomItem(trailManager.getProjectileTrailById("NOTES").getIcon(), 1,
				"§aNotes Trail",
				"§7Select the Notes Trail.",
				"§7This change is cosmetic.",
				(!trailManager.hasSelected(p, "NOTES") ? "§eClick to select!" : "§aSELECTED!"));
		ItemStack GREEN_STAR = new CustomItem(trailManager.getProjectileTrailById("GREEN_STAR").getIcon(), 1,
				"§aGreen Star Trail",
				"§7Select the Green Star Trail.",
				"§7This change is cosmetic.",
				(!trailManager.hasSelected(p, "GREEN_STAR") ? "§eClick to select!" : "§aSELECTED!"));
		ItemStack close = new CustomItem(Material.BARRIER, 1, "§cClose");
		inventory.setItem(11, VANILLA);
		inventory.setItem(12, HEARTS);
		inventory.setItem(13, unavailableItem("Black Smoke"));
		inventory.setItem(14, unavailableItem("White Smoke"));
		inventory.setItem(15, unavailableItem("Fire"));
		inventory.setItem(20, unavailableItem("Blood"));
		inventory.setItem(21, unavailableItem("Magic"));
		inventory.setItem(22, NOTES);
		inventory.setItem(23, GREEN_STAR);
		inventory.setItem(24, unavailableItem("Slime"));
		inventory.setItem(29, unavailableItem("Ender"));
		inventory.setItem(49, close);
		p.openInventory(inventory);
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		if (p.getOpenInventory().getTopInventory() != null) {
			if (p.getOpenInventory().getTopInventory().getName().equalsIgnoreCase("Projectile Trails")) {
				event.setCancelled(true);
				if (event.getSlotType().equals(SlotType.OUTSIDE) || event.getCurrentItem().getType().equals(Material.AIR)) {
					return;
				}
				if (event.getClickedInventory().equals(p.getOpenInventory().getTopInventory())) {
					event.setCancelled(true);
					int slot = event.getSlot();
					if (slot == 49) {
						p.closeInventory();
					}
					if (slot == 11) {
						if (!trailManager.hasSelected(p, "VANILLA")) {
							trailManager.setProjectileTrail(p, "VANILLA");
							open(p);
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1f, 2f);
							p.sendMessage("§6You selected §aVanilla Trail§6!");
						} else {
							p.sendMessage("§cYou have already selected this item!");
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0.5f);
						}
						return;
					}
					if (slot == 12) {
						if (!trailManager.hasSelected(p, "HEARTS")) {
							trailManager.setProjectileTrail(p, "HEARTS");
							open(p);
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1f, 2f);
							p.sendMessage("§6You selected §aHearts Trail§6!");
						} else {
							p.sendMessage("§cYou have already selected this item!");
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0.5f);
						}
						return;
					}
					if (slot == 22) {
						if (!trailManager.hasSelected(p, "NOTES")) {
							trailManager.setProjectileTrail(p, "NOTES");
							open(p);
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1f, 2f);
							p.sendMessage("§6You selected §aNotes Trail§6!");
						} else {
							p.sendMessage("§cYou have already selected this item!");
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0.5f);
						}
						return;
					}
					if (slot == 23) {
						if (!trailManager.hasSelected(p, "GREEN_STAR")) {
							trailManager.setProjectileTrail(p, "GREEN_STAR");
							open(p);
							p.playSound(p.getLocation(), Sound.NOTE_PLING, 1f, 2f);
							p.sendMessage("§6You selected §aGreen Star Trail§6!");
						} else {
							p.sendMessage("§cYou have already selected this item!");
							p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0.5f);
						}
						return;
					}
					if (slot == 13 || slot == 14 || slot == 20 || slot == 21 || slot == 22 || slot == 24 || slot == 29) {
						p.sendMessage("§cNot available yet!");
						p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0.5f);
						return;
					}
				}
			}
		}
	}

	private ItemStack unavailableItem(String name) {
		return new CustomItem(Material.INK_SACK, 1, "§c" + name + " Trail", 8,
				"§7???");
	}

}
