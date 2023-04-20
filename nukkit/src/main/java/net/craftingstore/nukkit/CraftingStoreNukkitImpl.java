package net.craftingstore.nukkit;

import cn.nukkit.Server;
import cn.nukkit.utils.MainLogger;
import net.craftingstore.nukkit.events.DonationReceivedEvent;
import net.craftingstore.core.CraftingStorePlugin;
import net.craftingstore.core.PluginConfiguration;
import net.craftingstore.core.models.donation.Donation;

public class CraftingStoreNukkitImpl implements CraftingStorePlugin {

    private CraftingStoreNukkit nukkitPlugin;
    private MainLogger logger;
    private PluginConfiguration pluginConfiguration;

    CraftingStoreNukkitImpl(CraftingStoreNukkit nukkitPlugin) {
        this.nukkitPlugin = nukkitPlugin;
        this.pluginConfiguration = new NukkitPluginConfiguration(nukkitPlugin);
        this.logger = nukkitPlugin.getServer().getLogger();
    }

    public boolean executeDonation(Donation donation) {
        if (donation.getPlayer().isRequiredOnline()) {
            if (Server.getInstance().getPlayerExact(donation.getPlayer().getUsername()) == null) {
                return false;
            }
        }
        Server server = nukkitPlugin.getServer();

        DonationReceivedEvent event = new DonationReceivedEvent(nukkitPlugin, donation);
        server.getPluginManager().callEvent(event);
        if (event.isCancelled()) {
            return false;
        }

        server.getScheduler().scheduleTask(nukkitPlugin, () -> server.dispatchCommand(server.getConsoleSender(), donation.getCommand()));
        return true;
    }

    public MainLogger getLogger() {
        return this.logger;
    }

    public void registerRunnable(Runnable runnable, int delay, int interval) {
        nukkitPlugin.getServer().getScheduler().scheduleDelayedRepeatingTask(nukkitPlugin, runnable, delay * 20, interval * 20, true);
    }

    public void runAsyncTask(Runnable runnable) {
        nukkitPlugin.getServer().getScheduler().scheduleTask(nukkitPlugin, runnable, true);
    }

    public String getToken() {
        return nukkitPlugin.getConfig().getString("api-key");
    }

    @Override
    public PluginConfiguration getConfiguration() {
        return this.pluginConfiguration;
    }
}
