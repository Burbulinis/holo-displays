package me.burb.holodisplays.skript.conditions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CondIsVisibleTo extends Condition {

    static {
        Skript.registerCondition(CondIsVisibleTo.class,
                "is %hologram% visible to %player%",
                "can %player% see %hologram%",
                "%player% is able to see %hologram%"
        );
    }

    private Expression<Hologram> hologram;
    private Expression<Player> player;

    @Override
    public boolean check(@NotNull Event e) {
        Player player = this.player.getSingle(e);
        if (player == null) return false;

        Hologram hologram = this.hologram.getSingle(e);
        if (hologram == null) return false;

        return hologram.getVisibilitySettings().isVisibleTo(player);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "is hologram '" + hologram.toString(e, debug) + "' visible to player " + player.toString(e, debug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        player = (Expression<Player>) (matchedPattern == 1 ? exprs[1] : exprs[0]);
        hologram = (Expression<Hologram>) (matchedPattern == 1 ? exprs[0] : exprs[1]);
        return true;
    }
}
