package me.burb.holodisplays.skript.expressions.line;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.filoghost.holographicdisplays.api.hologram.line.TextHologramLine;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprLineText extends SimpleExpression<String> {

    static {
        Skript.registerExpression(ExprLineText.class, String.class, ExpressionType.COMBINED,
                "text of [[the] line] %texthologramline%",
                "%texthologramline%'[s] text"
        );
    }

    private Expression<TextHologramLine> line;

    @Override
    protected String @NotNull [] get(@NotNull Event e) {
        TextHologramLine line = this.line.getSingle(e);
        if (line == null)
            return new String[0];

        return new String[]{line.getText()};
    }

    @Override
    public void change(@NotNull Event e, Object[] delta, @NotNull Changer.ChangeMode mode) {
        if (delta == null)
            return;

        TextHologramLine line = this.line.getSingle(e);
        if (line == null)
            return;

        String text = (String) delta[0];

        if (mode == Changer.ChangeMode.SET)
            line.setText(text);
    }

    @Override
    public Class<?>[] acceptChange(final @NotNull Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(String.class) : null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends String> getReturnType() {
        return String.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "text of the line '" + line.toString(e, debug) + "'";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        line = (Expression<TextHologramLine>) exprs[0];
        return true;
    }
}
