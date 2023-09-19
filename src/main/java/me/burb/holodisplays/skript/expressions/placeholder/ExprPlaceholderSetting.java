package me.burb.holodisplays.skript.expressions.placeholder;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.PlaceholderSetting;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprPlaceholderSetting extends SimplePropertyExpression<Hologram, PlaceholderSetting> {

    static {
        register(ExprPlaceholderSetting.class, PlaceholderSetting.class, "placeholder setting", "hologram");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "placeholder setting";
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, @NotNull Changer.ChangeMode mode) {
        if (delta == null)
            return;

        Hologram hologram = getExpr().getSingle(e);
        if (hologram == null)
            return;

        PlaceholderSetting setting = (PlaceholderSetting) delta[0];

        if (mode == Changer.ChangeMode.SET)
            hologram.setPlaceholderSetting(setting);
    }

    @Override
    public Class<?>[] acceptChange(final @NotNull Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(PlaceholderSetting.class) : null;
    }

    @Override
    public @Nullable PlaceholderSetting convert(Hologram hologram) {
        return hologram.getPlaceholderSetting();
    }

    @Override
    public @NotNull Class<? extends PlaceholderSetting> getReturnType() {
        return PlaceholderSetting.class;
    }
}
