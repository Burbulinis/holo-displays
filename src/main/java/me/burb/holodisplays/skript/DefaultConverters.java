package me.burb.holodisplays.skript;

import ch.njol.skript.classes.Converter;
import ch.njol.skript.registrations.Converters;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class DefaultConverters {

    static {
        Converters.registerConverter(Hologram.class, Location.class, new Converter<>() {
            @Override
            public @NotNull Location convert(Hologram hologram) {
                return hologram.getPosition().toLocation();
            }
        });
    }
}
