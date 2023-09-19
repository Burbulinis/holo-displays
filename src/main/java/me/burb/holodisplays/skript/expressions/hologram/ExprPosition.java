package me.burb.holodisplays.skript.expressions.hologram;

import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprPosition extends SimplePropertyExpression<Hologram, Location> {

    static {
        register(ExprPosition.class, Location.class, "position", "hologram");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "position";
    }

    @Override
    public void change(@NotNull Event e, Object[] delta, @NotNull ChangeMode mode) {
        if (delta == null)
            return;

        Hologram hologram = getExpr().getSingle(e);
        if (hologram == null)
            return;

        Location location = (Location) delta[0];

        if (mode == ChangeMode.SET)
            hologram.setPosition(location);
    }

    @Override
    public Class<?>[] acceptChange(final @NotNull ChangeMode mode) {
        return (mode == ChangeMode.SET) ? CollectionUtils.array(Location.class) : null;
    }

    @Override
    public @Nullable Location convert(Hologram hologram) {
        return hologram.getPosition().toLocation();
    }

    @Override
    public @NotNull Class<? extends Location> getReturnType() {
        return Location.class;
    }
}
