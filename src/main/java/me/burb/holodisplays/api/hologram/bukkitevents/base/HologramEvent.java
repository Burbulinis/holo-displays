package me.burb.holodisplays.api.hologram.bukkitevents.base;

import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.bukkit.event.Event;

public abstract class HologramEvent extends Event {
    private final Hologram hologram;

    protected HologramEvent(Hologram hologram) {
        this.hologram = hologram;
    }

    public Hologram getHologram() {
        return hologram;
    }

}
