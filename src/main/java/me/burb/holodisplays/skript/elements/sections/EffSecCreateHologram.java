package me.burb.holodisplays.skript.elements.sections;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.config.SectionNode;
import ch.njol.skript.lang.*;
import ch.njol.skript.util.Direction;
import ch.njol.util.Kleenean;
import me.burb.holodisplays.HoloDisplays;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EffSecCreateHologram extends EffectSection {

    public static class HologramCreateEvent extends Event {

        public Hologram lastCreated;

        public HologramCreateEvent(Hologram hologram) {
            lastCreated = hologram;
        }

        @Override
        public @NotNull HandlerList getHandlers() {
            throw new IllegalStateException();
        }
    }

    static {
        Skript.registerSection(EffSecCreateHologram.class,
                "create [a [new]] hologram %direction% %location% [and store ([the] hologram|it) in %object%]"
        );
    }

    private Trigger trigger;
    private Expression<Direction> direction;
    private Expression<Location> location;
    private Expression<?> object;

    @Override
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult, @Nullable SectionNode sectionNode, @Nullable List<TriggerItem> triggerItems) {
        if (exprs.length == 3 && exprs[2].acceptChange(Changer.ChangeMode.SET) == null)  {
            Skript.error(exprs[2] + " can't be set to anything");
            return false;
        }

        direction = (Expression<Direction>) exprs[0];
        location = (Expression<Location>) exprs[1];
        object = exprs.length == 3 ? exprs[2] : null;

        if (sectionNode != null) {
            trigger = loadCode(sectionNode, "Hologram Create", HologramCreateEvent.class);
        }

        return true;
    }

    @Override
    protected @Nullable TriggerItem walk(@NotNull Event e) {

        Location loc = Direction.combine(direction, location).getSingle(e);
        if (loc != null) {
            Hologram hologram = HoloDisplays.getHDAPI().createHologram(loc);

            if (object != null)
                object.change(e, new Hologram[]{hologram}, Changer.ChangeMode.SET);

            if (trigger != null)
                return walk(e, true);
        }
        return walk(e, false);
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "create hologram at direction " + direction.toString(e, debug) + ", location " + location.toString(e, debug);
    }
}
