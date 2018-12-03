package net.craftingstore.bukkit.listeners;

import net.craftingstore.bukkit.CraftingStoreBukkit;
import net.craftingstore.core.models.api.misc.UpdateInformation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private CraftingStoreBukkit instance;

    public JoinListener(CraftingStoreBukkit instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!p.hasPermission(instance.getCraftingStore().ADMIN_PERMISSION)) {
            return;
        }
        UpdateInformation update = instance.getCraftingStore().getInformation().getUpdateInformation();
        // Update notification
        if (update != null) {
            p.sendMessage(instance.getPrefix() + update.getMessage());
        }

        if (!instance.getCraftingStore().isEnabled()) {
            if (update != null && update.shouldDisable()) {
                p.sendMessage(instance.getPrefix() + "The CraftingStore plugin has been disabled because this is an outdated version. Please update the plugin.");
            } else {
                p.sendMessage(instance.getPrefix() + "The CraftingStore plugin has not been set-up correctly. Please set your API key using /craftingstore key <your key>.");
            }
        }
    }
}
