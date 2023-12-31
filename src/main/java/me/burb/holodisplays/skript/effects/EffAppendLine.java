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

public class EffAppendLine extends Effect {

    static {
        Skript.registerEffect(EffAppendLine.class,
                "(append|add) %itemtype/string% to [[the] lines of [[the] hologram]] %hologram% [and store (the [new] line|it) in %object%]"
        );
    }

    private Expression<?> object;
    private Expression<?> saveTo;
    private Expression<Hologram> hologram;

    @Override
    protected void execute(@NotNull Event e) {
        if (object == null)
            return;
        Hologram hologram = this.hologram.getSingle(e);
        Object object = this.object.getSingle(e);

        if (hologram == null)
            return;

        ItemStack item = null;
        String text = null;
        if (object instanceof ItemType)
            item = Converters.convert(this.object.getSingle(e), ItemStack.class);
        else if (object instanceof String)
            text = (String) this.object.getSingle(e);

        if (hologram.getLines().size() != 0)
            if (text == null)
                hologram.getLines().appendItem(item);
            else
                hologram.getLines().appendText(text);

        if (saveTo != null)
            saveTo.change(e, new Object[]{hologram.getLines().get(hologram.getLines().size() - 1)}, Changer.ChangeMode.SET);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "append '" + object.toString(e, debug) + "' to the lines of the hologram '" + hologram.toString(e, debug) + "'" + ((saveTo != null) ? " and save to " + saveTo.toString(e, debug) : "");
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        object = exprs[0];
        hologram = (Expression<Hologram>) exprs[1];
        if (exprs.length == 3) {
            if (exprs[2].acceptChange(Changer.ChangeMode.SET) == null) {
                Skript.error(exprs[2] + " can't be set to anything");
                return false;
            }
            saveTo = exprs[2];
        }
        return true;
    }
}