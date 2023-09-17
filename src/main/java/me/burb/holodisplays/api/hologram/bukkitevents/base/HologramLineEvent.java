package me.burb.holodisplays.api.hologram.bukkitevents.base;

import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.line.HologramLine;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

public abstract class HologramLineEvent extends Event {

    private final Player player;
    private final Hologram hologram;
    private final HologramLine hologramLine;

    protected HologramLineEvent(Player player, Hologram hologram, HologramLine hologramLine) {
        this.player = player;
        this.hologram = hologram;
        this.hologramLine = hologramLine;
    }

    public Player getPlayer() {
        return player;
    }

    public Hologram getHologram() {
        return hologram;
    }

    public HologramLine getHologramLine() {
        return hologramLine;
    }
}
