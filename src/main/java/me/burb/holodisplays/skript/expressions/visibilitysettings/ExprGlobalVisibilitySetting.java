package me.burb.holodisplays.skript.expressions.visibilitysettings;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.expressions.base.SimplePropertyExpression;
import ch.njol.util.coll.CollectionUtils;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.VisibilitySettings;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprGlobalVisibilitySetting extends SimplePropertyExpression<Hologram, VisibilitySettings.Visibility> {

    static {
        register(ExprGlobalVisibilitySetting.class, VisibilitySettings.Visibility.class, "global visibility setting[s]", "hologram");
    }

    @Override
    protected @NotNull String getPropertyName() {
        return "global visibility settings";
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, @NotNull Changer.ChangeMode mode) {
        if (delta == null)
            return;

        Hologram hologram = getExpr().getSingle(e);
        if (hologram == null)
            return;

        VisibilitySettings.Visibility visibility = (VisibilitySettings.Visibility) delta[0];

        if (mode == Changer.ChangeMode.SET)
            hologram.getVisibilitySettings().setGlobalVisibility(visibility);
    }

    @Override
    public Class<?>[] acceptChange(final @NotNull Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET) ? CollectionUtils.array(VisibilitySettings.Visibility.class) : null;
    }

    @Override
    public @Nullable VisibilitySettings.Visibility convert(Hologram hologram) {
        return hologram.getVisibilitySettings().getGlobalVisibility();
    }

    @Override
    public @NotNull Class<? extends VisibilitySettings.Visibility> getReturnType() {
        return VisibilitySettings.Visibility.class;
    }
}
