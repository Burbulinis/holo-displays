package me.burb.holodisplays.skript.elements.expressions;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
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
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        line = (Expression<ItemHologramLine>) exprs[0];
        return true;
    }
}
