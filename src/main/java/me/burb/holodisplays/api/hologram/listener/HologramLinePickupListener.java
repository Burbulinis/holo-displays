package me.burb.holodisplays.api.hologram.listener;

import me.burb.holodisplays.HoloDisplays;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.line.HologramLine;
import me.filoghost.holographicdisplays.api.hologram.line.HologramLinePickupEvent;
import org.jetbrains.annotations.NotNull;

public class HologramLinePickupListener implements me.filoghost.holographicdisplays.api.hologram.line.HologramLinePickupListener {

    private final Hologram hologram;
    private final HologramLine hologramLine;

    public HologramLinePickupListener(Hologram hologram, HologramLine hologramLine) {
        this.hologram = hologram;
        this.hologramLine = hologramLine;
    }

    @Override
    public void onPickup(@NotNull HologramLinePickupEvent e) {
        me.burb.holodisplays.api.hologram.bukkitevents.HologramLinePickupEvent event = new me.burb.holodisplays.api.hologram.bukkitevents.HologramLinePickupEvent(e.getPlayer(), hologram, hologramLine);
        HoloDisplays.getPluginManager().callEvent(event);
    }
}
