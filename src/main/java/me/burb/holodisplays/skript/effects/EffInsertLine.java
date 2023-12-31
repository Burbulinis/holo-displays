package me.burb.holodisplays.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.registrations.Converters;
import ch.njol.util.Kleenean;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class EffInsertLine extends Effect {

    static {
        Skript.registerEffect(EffInsertLine.class,
                "insert %itemtype/string% [a] line (1:after|before) [the] line %integer% of [[the] hologram] %hologram% [and store (the [new] line|it) in %object%]",
                "insert %itemtype/string% [a] line (1:after|before) [the] %integer%(st|nd|rd|th) line of [[the] hologram] %hologram% [and store (the [new] line|it) in %object%]"
        );
    }

    private Expression<?> object;
    private Expression<?> saveTo;
    private Expression<Integer> line;
    private Expression<Hologram> hologram;
    private boolean after;

    @Override
    protected void execute(@NotNull Event e) {
        if (object == null)
            return;

        Hologram hologram = this.hologram.getSingle(e);
        if (hologram == null)
            return;

        Object object = this.object.getSingle(e);
        int line = Math.abs(Optional.ofNullable(this.line.getSingle(e)).orElse(0));

        if (hologram.getLines().size() < line || line == 0)
            return;

        ItemStack item = null;
        String text = null;
        if (object instanceof ItemType) {
            item = Converters.convert(object, ItemStack.class);
        } else if (object instanceof String) {
            text = (String) object;
        }

        if (hologram.getLines().size() != 0)
            if (text == null)
                hologram.getLines().insertItem(after ? line+1 : line-1, item);
            else
                hologram.getLines().insertText(after ? line+1 : line-1, text);

        if (saveTo != null)
            saveTo.change(e, new Object[]{hologram.getLines().get(hologram.getLines().size() - 1)}, Changer.ChangeMode.SET);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "insert '" + object.toString(e, debug) + "' to the line " + ((after) ? "after" : "before") + " the line " + line.toString(e, debug) + " of the hologram '" + hologram.toString(e, debug) + "'" + ((saveTo != null) ? " and save to " + saveTo.toString(e, debug) : "");
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        object = exprs[0];
        line = (Expression<Integer>) exprs[1];
        hologram = (Expression<Hologram>) exprs[2];
        after = parseResult.hasTag("1");
        if (exprs.length == 4) {
            if (exprs[3].acceptChange(Changer.ChangeMode.SET) == null) {
                Skript.error(exprs[3] + " can't be set to anything");
                return false;
            }
            saveTo = exprs[3];
        }
        return true;
    }
}
