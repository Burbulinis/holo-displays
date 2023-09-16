package me.burb.holodisplays.skript.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.burb.holodisplays.HoloDisplays;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

public class ExprNearestHologram extends SimpleExpression<Hologram> {

    static {
        Skript.registerExpression(ExprNearestHologram.class, Hologram.class, ExpressionType.COMBINED,
                "[the] (nearest|closest) hologram (around|at|from|of) %location%"
        );
    }

    private Expression<Location> location;

    @Override
    protected Hologram @NotNull [] get(@NotNull Event e) {
        Location location = this.location.getSingle(e);
        if (location == null)
            return new Hologram[0];
        if (HoloDisplays.getHDAPI().getHolograms().isEmpty())
            return new Hologram[0];

        Optional<Hologram> hologram = HoloDisplays.getHDAPI().getHolograms().stream()
                .filter(holo -> holo.getPosition().getWorldIfLoaded().equals(location.getWorld()))
                .map(holo -> Map.entry(holo, holo.getPosition().distanceSquared(location)))
                .sorted(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .findFirst();
        return new Hologram[]{hologram.orElse(null)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends Hologram> getReturnType() {
        return Hologram.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "nearest hologram around " + location.toString(e, debug);
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        location = (Expression<Location>) exprs[0];
        return true;
    }
}
