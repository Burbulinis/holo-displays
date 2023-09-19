package me.burb.holodisplays.skript.events;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import me.burb.holodisplays.api.hologram.bukkitevents.HologramLineClickLineEvent;
import me.burb.holodisplays.skript.sections.EffSecCreateHologram;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.line.HologramLine;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class SimpleEvents {
    static {
        Skript.registerEvent("Hologram Create", SimpleEvent.class, EffSecCreateHologram.HologramCreateEvent.class, "hologram create")
                .description("This event is only called when the hologram is made using the create hologram effect section.",
                        "If you make a hologram through the HolographicDisplays plugin, this will not get called")
                .since("1.0")
                .examples("on hologram create")
                .requiredPlugins("HolographicDisplays");
        // register hologram eventvalue for this event ^

        Skript.registerEvent("Hologram Line Click", SimpleEvent.class, HologramLineClickLineEvent.class, "hologram line click")
                .description("Called when a player clicks on a line of a hologram")
                .since("1.0")
                .requiredPlugins("HolographicDisplays")
                .examples("on hologram line click");
        EventValues.registerEventValue(HologramLineClickLineEvent.class, Hologram.class, new Getter<>() {
            @Override
            public @Nullable Hologram get(HologramLineClickLineEvent e) {
                return e.getHologram();
            }
        }, EventValues.TIME_NOW);
        EventValues.registerEventValue(HologramLineClickLineEvent.class, HologramLine.class, new Getter<>() {
            @Override
            public @Nullable HologramLine get(HologramLineClickLineEvent e) {
                return e.getHologramLine();
            }
        }, EventValues.TIME_NOW);
        EventValues.registerEventValue(HologramLineClickLineEvent.class, Player.class, new Getter<>() {
            @Override
            public @Nullable Player get(HologramLineClickLineEvent e) {
                return e.getPlayer();
            }
        }, EventValues.TIME_NOW);
    }
}
