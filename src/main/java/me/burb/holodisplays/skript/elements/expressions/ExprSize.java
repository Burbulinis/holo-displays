package me.burb.holodisplays.skript.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.filoghost.holographicdisplays.api.hologram.HologramLines;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprSize extends SimplePropertyExpression<HologramLines, Integer> {

    static {
        register(ExprSize.class, Integer.class, "hologram size", "hologramlines");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "hologram size";
    }

    @Override
    public @Nullable Integer convert(HologramLines hologramLines) {
        return hologramLines.size();
    }

    @Override
    public @NotNull Class<? extends Integer> getReturnType() {
        return Integer.class;
    }
}
