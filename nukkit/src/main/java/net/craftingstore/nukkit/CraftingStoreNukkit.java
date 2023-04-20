package net.craftingstore.nukkit;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import net.craftingstore.nukkit.commands.CraftingStoreCommand;
import net.craftingstore.nukkit.listeners.AdminJoinListener;
import net.craftingstore.nukkit.listeners.PendingDonationJoinListener;
import net.craftingstore.core.CraftingStore;

public class CraftingStoreNukkit extends PluginBase {

    private Config config;
    private CraftingStore craftingStore;
    private String prefix = TextFormat.GRAY + "[" + TextFormat.RED + "CraftingStore" + TextFormat.GRAY + "] " + TextFormat.WHITE;

    @Override
    public void onEnable() {
        this.saveResource("config.yml", false);

        config = new Config(this.getDataFolder() + "/config.yml", Config.YAML);

        this.craftingStore = new CraftingStore(new CraftingStoreNukkitImpl(this));

        this.getServer().getCommandMap().register("craftingstore", new CraftingStoreCommand(this));

        this.getServer().getPluginManager().registerEvents(new AdminJoinListener(this), this);
        this.getServer().getPluginManager().registerEvents(new PendingDonationJoinListener(this), this);
    }

    @Override
    public void onDisable() {
        craftingStore.setEnabled(false);
        craftingStore.getLogger().debug("Shutdown complete");
    }

    @Override
    public Config getConfig(){
        return this.config;
    }

    public CraftingStore getCraftingStore() {
        return craftingStore;
    }

    public String getPrefix() {
        return prefix;
    }
}
