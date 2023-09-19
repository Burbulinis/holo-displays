package me.burb.holodisplays.skript.expressions.line;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.HologramLines;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprLines extends SimplePropertyExpression<Hologram, HologramLines> {

    static {
        register(ExprLines.class, HologramLines.class, "lines", "hologram");
    }

    @Override
    public @NotNull Class<? extends HologramLines> getReturnType() {
        return HologramLines.class;
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "lines";
    }

    @Override
    public @Nullable HologramLines convert(Hologram hologram) {
        return hologram.getLines();
    }
}
