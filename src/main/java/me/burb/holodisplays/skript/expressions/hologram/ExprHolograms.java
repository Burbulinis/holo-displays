package me.burb.holodisplays.skript.expressions.hologram;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import me.burb.holodisplays.HoloDisplays;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprHolograms extends SimpleExpression<Hologram> {

    static {
        Skript.registerExpression(ExprHolograms.class, Hologram.class, ExpressionType.SIMPLE, "[all [[of] the]] holograms");
    }

    private boolean isSingle;

    @Override
    protected Hologram @NotNull [] get(@NotNull Event e) {
        return HoloDisplays.getHDAPI().getHolograms().toArray(new Hologram[0]);
    }

    @Override
    public boolean isSingle() {
        return isSingle;
    }

    @Override
    public @NotNull Class<? extends Hologram> getReturnType() {
        return Hologram.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "all of the holograms";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        isSingle = HoloDisplays.getHDAPI().getHolograms().size() == 1;
        return true;
    }
}
