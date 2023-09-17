package me.burb.holodisplays.api.hologram.bukkitevents;

import me.burb.holodisplays.api.hologram.bukkitevents.base.HologramEvent;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class HologramCreateEvent extends HologramEvent {

    private static final HandlerList HANDLERS = new HandlerList();

    public HologramCreateEvent(Hologram hologram) {
        super(hologram);
    }

    @Override
    public @NotNull HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
