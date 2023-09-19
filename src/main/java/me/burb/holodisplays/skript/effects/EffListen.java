package me.burb.holodisplays.skript.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import me.burb.holodisplays.api.hologram.listener.HologramLineClickListener;
import me.burb.holodisplays.api.hologram.listener.HologramLinePickupListener;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.line.HologramLine;
import me.filoghost.holographicdisplays.api.hologram.line.ItemHologramLine;
import me.filoghost.holographicdisplays.api.hologram.line.TextHologramLine;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EffListen extends Effect {

    static {
        Skript.registerEffect(EffListen.class,
                ":(start|stop) listening to [the] click event[s] of [[the] [hologram] line] %hologramline% of [[the] hologram] %hologram%",
                ":(start|stop) listening to %hologramline%'[s] click event[s] of [[the] hologram] %hologram%",
                ":(start|stop) listening to [the] pick[ ]up event[s] of [[[the] item] [hologram] line] %itemhologramline% of [[the] hologram] %hologram%",
                ":(start|stop) listening to %hologramline%'[s] pick[ ]up event[s] of [[the] hologram] %hologram%",
                ":(start|stop) listening to [all [[of] the]] events of [[the] [hologram] line] %hologramline% of [[the] hologram] %hologram%"
        );
    }

    private boolean start;
    private boolean clickEvent;
    private boolean allEvents;
    private Expression<HologramLine> line;
    private Expression<Hologram> hologram;

    @Override
    protected void execute(@NotNull Event e) {
        HologramLine line = this.line.getSingle(e);
        if (line == null)
            return;

        Hologram hologram = this.hologram.getSingle(e);
        if (hologram == null)
            return;

        boolean isItem = !(line instanceof TextHologramLine);

        if (clickEvent) {
            if (!isItem)
                ((TextHologramLine) line).setClickListener(new HologramLineClickListener(hologram, line));
            else
                ((ItemHologramLine) line).setClickListener(new HologramLineClickListener(hologram, line));
        } else if (!clickEvent) {
            ((ItemHologramLine) line).setPickupListener(new HologramLinePickupListener(hologram, line));
        } else if (allEvents) {
            (isItem ? ((ItemHologramLine) line) : ((TextHologramLine) line)).setClickListener(new HologramLineClickListener(hologram, line));
            if (isItem)
                ((ItemHologramLine) line).setPickupListener(new HologramLinePickupListener(hologram, line));
            }
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return (start) ? "start" : "stop" + " listening to the " + (clickEvent ? "click events " : !allEvents ? "pick up events " : "all of the events ") + " of the hologram line " + line.toString(e, debug);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        start = parseResult.hasTag("start");
        clickEvent = matchedPattern == 0 || matchedPattern == 1;
        allEvents = matchedPattern == 4;
        line = (Expression<HologramLine>) exprs[0];
        hologram = (Expression<Hologram>) exprs[1];
        return true;
    }
}
