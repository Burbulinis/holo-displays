package me.burb.holodisplays.api.hologram.listener;

import me.burb.holodisplays.HoloDisplays;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.line.HologramLine;
import me.filoghost.holographicdisplays.api.hologram.line.HologramLineClickEvent;
import org.jetbrains.annotations.NotNull;

public class HologramLineClickListener implements me.filoghost.holographicdisplays.api.hologram.line.HologramLineClickListener {

    private final Hologram hologram;
    private final HologramLine hologramLine;

    public HologramLineClickListener(Hologram hologram, HologramLine hologramLine) {
        this.hologram = hologram;
        this.hologramLine = hologramLine;
    }

    @Override
    public void onClick(@NotNull HologramLineClickEvent e) {
        me.burb.holodisplays.api.hologram.bukkitevents.HologramLineClickEvent event = new me.burb.holodisplays.api.hologram.bukkitevents.HologramLineClickEvent(e.getPlayer(), hologram, hologramLine);
        HoloDisplays.getPluginManager().callEvent(event);
    }
}
