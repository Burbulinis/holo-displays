package me.burb.holodisplays.api.hologram.bukkitevents;

import me.burb.holodisplays.api.hologram.bukkitevents.base.HologramEvent;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class HologramDeleteEvent extends HologramEvent {

    private static Hologram lastDeleted;
    private static final HandlerList HANDLERS = new HandlerList();

    public HologramDeleteEvent(Hologram hologram) {
        super(hologram);
        lastDeleted = hologram;
    }

    public static Hologram getLastDeleted() {
        return lastDeleted;
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
