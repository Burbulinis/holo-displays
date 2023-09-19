package me.burb.holodisplays.skript.expressions.line;

import ch.njol.skript.Skript;
import ch.njol.skript.aliases.ItemType;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.skript.registrations.Converters;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.filoghost.holographicdisplays.api.hologram.line.ItemHologramLine;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprLineItem extends SimpleExpression<ItemStack> {

    static {
        Skript.registerExpression(ExprLineItem.class, ItemStack.class, ExpressionType.COMBINED,
                "item of [[the] line] %itemhologramline%",
                "%itemhologramline%'[s] item"
        );
    }
    
    private Expression<ItemHologramLine> line;

    @Override
    protected ItemStack @NotNull [] get(@NotNull Event e) {
        ItemHologramLine line = this.line.getSingle(e);
        if (line == null) 
            return new ItemStack[0];
        
        return new ItemStack[]{line.getItemStack()};
    }

    @Override
    public void change(@NotNull Event e, Object[] delta, @NotNull Changer.ChangeMode mode) {
        if (delta == null)
            return;

        ItemHologramLine line = this.line.getSingle(e);
        if (line == null)
            return;

        ItemStack item = Converters.convert(delta[0], ItemStack.class); // imagine i didnt have converter here
        if (item == null)
            return;

        if (mode == Changer.ChangeMode.SET)
            line.setItemStack(item);
    }

    @Override
    public Class<?>[] acceptChange(final @NotNull Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(ItemType.class) : null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends ItemStack> getReturnType() {
        return ItemStack.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "item of the line '" + line.toString(e, debug) + "'";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        line = (Expression<ItemHologramLine>) exprs[0];
        return true;
    }
}
