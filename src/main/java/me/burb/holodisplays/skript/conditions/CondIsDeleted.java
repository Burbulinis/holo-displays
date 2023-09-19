package me.burb.holodisplays.skript.conditions;

import ch.njol.skript.conditions.base.PropertyCondition;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import org.jetbrains.annotations.NotNull;

public class CondIsDeleted extends PropertyCondition<Hologram> {

    static {
        register(CondIsDeleted.class, "deleted", "holograms");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "deleted";
    }

    @Override
    public boolean check(Hologram hologram) {
        return hologram.isDeleted();
    }

}