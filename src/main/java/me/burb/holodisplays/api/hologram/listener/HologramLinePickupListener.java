package me.burb.holodisplays.api.hologram.listener;

import me.burb.holodisplays.HoloDisplays;
import me.burb.holodisplays.api.hologram.bukkitevents.HologramLinePickupLineEvent;
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
        HologramLinePickupLineEvent event = new HologramLinePickupLineEvent(e.getPlayer(), hologram, hologramLine);
        HoloDisplays.getPluginManager().callEvent(event);
    }
}
