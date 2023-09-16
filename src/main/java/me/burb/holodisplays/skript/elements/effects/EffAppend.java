package me.burb.holodisplays.skript.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
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

public class EffAppend extends Effect {

    static {
        Skript.registerEffect(EffAppend.class,
                "append %itemtype/string% to [[the] lines of [[the] hologram]] %hologram%"
        );
    }

    private Expression<?> object;
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
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "append '" + object.toString(e, debug) + "' to the lines of the hologram '" + hologram.toString(e, debug) + "'";
    }

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        object = exprs[0];
        hologram = (Expression<Hologram>) exprs[1];
        return true;
    }
}
