package me.burb.holodisplays.skript.elements.expressions;

import ch.njol.skript.expressions.base.SimplePropertyExpression;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprHeight extends SimplePropertyExpression<Hologram, Double> {

    static {
        register(ExprHeight.class, Double.class, "height", "hologram");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "height";
    }

    @Override
    public @Nullable Double convert(Hologram hologram) {
        return hologram.getLines().getHeight();
    }

    @Override
    public @NotNull Class<? extends Double> getReturnType() {
        return Double.class;
    }
}
