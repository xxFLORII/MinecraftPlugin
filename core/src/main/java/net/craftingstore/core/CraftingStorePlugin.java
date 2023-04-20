package net.craftingstore.core;

import net.craftingstore.core.models.donation.Donation;

public interface CraftingStorePlugin {

    boolean executeDonation(Donation donation);

    cn.nukkit.utils.MainLogger getLogger();

    void registerRunnable(Runnable runnable, int delay, int interval);

    void runAsyncTask(Runnable runnable);

    String getToken();

    PluginConfiguration getConfiguration();
}
