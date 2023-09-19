package me.burb.holodisplays.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EffVisible extends Effect {

    static {
        Skript.registerEffect(EffVisible.class,
                "holo[-displays] remove [all [of [the]]] individual visibilities of [[the] hologram] %hologram%",
                "holo[-displays] remove %hologram%'[s] individual visibilities"
        );
    }

    private Expression<Hologram> hologram;

    @Override
    protected void execute(@NotNull Event e) {
        Hologram hologram = this.hologram.getSingle(e);
        if (hologram == null)
            return;

        hologram.getVisibilitySettings().clearIndividualVisibilities();
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "remove all of the individual visibilities";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        hologram = (Expression<Hologram>) exprs[0];
        return true;
    }
}
