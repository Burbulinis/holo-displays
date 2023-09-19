package me.burb.holodisplays.skript.expressions.line;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.filoghost.holographicdisplays.api.hologram.HologramLines;
import me.filoghost.holographicdisplays.api.hologram.line.HologramLine;
import me.filoghost.holographicdisplays.api.hologram.line.ItemHologramLine;
import me.filoghost.holographicdisplays.api.hologram.line.TextHologramLine;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ExprLine extends SimpleExpression<HologramLine> {

    static {
        Skript.registerExpression(ExprLine.class, HologramLine.class, ExpressionType.COMBINED,
                "[the] line %integer% of [[[the] hologram] lines] %hologramlines%",
                "%integer%(st|nd|rd|th) line of [[[the] hologram] lines] %hologramlines%"
        );
    }

    private Expression<Integer> integer;
    private Expression<HologramLines> hologramLines;

    @Override
    protected HologramLine @NotNull [] get(@NotNull Event e) {

        HologramLines hologramLines = this.hologramLines.getSingle(e);
        if (hologramLines == null)
            return new HologramLine[0];


        int line = Math.abs(Optional.ofNullable(this.integer.getSingle(e)).orElse(0));

        if (hologramLines.size() < line || line == 0)
            return new HologramLine[0];

        line -= 1;
        HologramLine otherLine = hologramLines.get(line);
        return new HologramLine[]{otherLine instanceof TextHologramLine ? ((TextHologramLine)otherLine) : ((ItemHologramLine)otherLine)};
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public void change(@NotNull Event e, Object[] delta, @NotNull Changer.ChangeMode mode) {

        HologramLines hologramLines = this.hologramLines.getSingle(e);
        int line = Math.abs(Optional.ofNullable(this.integer.getSingle(e)).orElse(0));
        if (hologramLines == null || hologramLines.size() < line || line == 0)
            return;
        line -= 1;

        if (mode == Changer.ChangeMode.DELETE)
            hologramLines.remove(line);
    }

    @Override
    public Class<?>[] acceptChange(final @NotNull Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.REMOVE) ? CollectionUtils.array() : null;
    }

    @Override
    public @NotNull Class<? extends HologramLine> getReturnType() {
        return HologramLine.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "line " + integer.toString(e, debug) + " of the hologram '" + hologramLines.toString(e, debug) + "'";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        integer = (Expression<Integer>) exprs[0];
        hologramLines = (Expression<HologramLines>) exprs[1];
        return true;
    }
}
