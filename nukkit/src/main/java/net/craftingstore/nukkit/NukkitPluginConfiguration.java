package net.craftingstore.nukkit;

import net.craftingstore.core.PluginConfiguration;

public class NukkitPluginConfiguration implements PluginConfiguration {

    private CraftingStoreNukkit plugin;

    NukkitPluginConfiguration(CraftingStoreNukkit plugin) {
        this.plugin = plugin;
    }

    @Override
    public String getName() {
        return "Nukkit";
    }

    @Override
    public String[] getMainCommands() {
        return new String[]{"craftingstore", "cs"};
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String getPlatform() {
        return plugin.getServer().getVersion();
    }

    @Override
    public boolean isBuyCommandEnabled() {
        return !plugin.getConfig().getBoolean("disable-buy-command", false);
    }

    @Override
    public int getTimeBetweenCommands() {
        return plugin.getConfig().getInt("time-between-commands", 200);
    }

    @Override
    public String getNotEnoughBalanceMessage() {
        return plugin.getConfig().getString("not-enough-balance-message", "&4You do not have enough in-game money in your account! You can also buy this package on the website:");
    }

    @Override
    public boolean isUsingAlternativeApi() {
        return plugin.getConfig().getBoolean("use-alternative-api", false);
    }
}
