package net.craftingstore.nukkit.events;

import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;
import cn.nukkit.event.plugin.PluginEvent;
import net.craftingstore.core.models.donation.Donation;
import net.craftingstore.nukkit.CraftingStoreNukkit;
import net.craftingstore.nukkit.CraftingStoreNukkitImpl;

import java.util.UUID;

public class DonationReceivedEvent extends PluginEvent implements Cancellable {

    @Deprecated
    private String command;
    @Deprecated
    private String username;
    @Deprecated
    private UUID uuid;
    @Deprecated
    private String packageName;
    @Deprecated
    private int packagePrice;
    @Deprecated
    private int couponDiscount;

    private Donation donation;

    private boolean cancelled = false;

    @Deprecated
    public DonationReceivedEvent(CraftingStoreNukkit instance, String command, String username, UUID uuid, String packageName, int packagePrice, int couponDiscount) {
        super(instance);
        this.command = command;
        this.username = username;
        this.uuid = uuid;
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.couponDiscount = couponDiscount;
    }

    public DonationReceivedEvent(CraftingStoreNukkit instance, Donation donation) {
        super(instance);
        this.command = donation.getCommand();
        this.username = donation.getPlayer().getUsername();
        this.uuid = donation.getPlayer().getUUID();
        this.packageName = donation.getPackage().getName();
        this.packagePrice = donation.getPackage().getPrice();
        this.couponDiscount = donation.getDiscount();
        this.donation = donation;
    }

    public Donation getDonation() {
        return this.donation;
    }

    @Deprecated
    public String getCommand() {
        return command;
    }

    @Deprecated
    public String getUsername() {
        return username;
    }

    @Deprecated
    public UUID getUuid() {
        return uuid;
    }

    @Deprecated
    public String getPackageName() {
        return packageName;
    }

    @Deprecated
    public int getPackagePrice() {
        return packagePrice;
    }

    @Deprecated
    public int getCouponDiscount() {
        return couponDiscount;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    private static final HandlerList handlers = new HandlerList();

    public static HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

}