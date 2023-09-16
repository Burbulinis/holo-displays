package me.burb.holodisplays.api.hologram.bukkitevents;

import me.burb.holodisplays.api.hologram.bukkitevents.base.HologramEvent;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.line.HologramLine;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class HologramLinePickupEvent extends HologramEvent {
    private static final HandlerList HANDLERS = new HandlerList();

    public HologramLinePickupEvent(Player player, Hologram hologram, HologramLine hologramLine) {
        super(player, hologram, hologramLine);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
