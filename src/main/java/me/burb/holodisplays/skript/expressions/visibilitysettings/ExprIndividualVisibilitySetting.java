package me.burb.holodisplays.skript.expressions.visibilitysettings;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.VisibilitySettings;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ExprIndividualVisibilitySetting extends SimpleExpression<VisibilitySettings.Visibility> {

    static {
        Skript.registerExpression(ExprIndividualVisibilitySetting.class, VisibilitySettings.Visibility.class, ExpressionType.COMBINED,
                "[individual] visibility setting[s] (of|for|from) [[the] player] %player% (of|for|from) [[the] hologram] %hologram%"
        );
    }

    private Expression<Player> player;
    private Expression<Hologram> hologram;

    @Override
    protected VisibilitySettings.Visibility @NotNull [] get(@NotNull Event e) {
        Hologram hologram = this.hologram.getSingle(e);
        if (hologram == null)
            return new VisibilitySettings.Visibility[0];
        VisibilitySettings visibilitySettings = hologram.getVisibilitySettings();

        Player player = this.player.getSingle(e);
        if (player == null)
            return new VisibilitySettings.Visibility[0];

        return new VisibilitySettings.Visibility[]{visibilitySettings.isVisibleTo(player) ? VisibilitySettings.Visibility.VISIBLE : VisibilitySettings.Visibility.HIDDEN};
    }

    @Override
    public void change(@NotNull Event e, Object @NotNull [] delta, @NotNull Changer.ChangeMode mode) {
        if (delta == null)
            return;

        Hologram hologram = this.hologram.getSingle(e);
        if (hologram == null)
            return;

        Player player = this.player.getSingle(e);
        if (player == null) {
            return;
        }

        VisibilitySettings.Visibility visibility = (VisibilitySettings.Visibility) delta[0];

        switch (mode) {
            case SET -> hologram.getVisibilitySettings().setIndividualVisibility(player, visibility);
            case DELETE -> hologram.getVisibilitySettings().removeIndividualVisibility(player);
        }
    }

    @Override
    public Class<?>[] acceptChange(final @NotNull Changer.ChangeMode mode) {
        return (mode == Changer.ChangeMode.SET || mode == Changer.ChangeMode.DELETE) ? CollectionUtils.array(VisibilitySettings.Visibility.class) : null;
    }

    @Override
    public boolean isSingle() {
        return true;
    }

    @Override
    public @NotNull Class<? extends VisibilitySettings.Visibility> getReturnType() {
        return VisibilitySettings.Visibility.class;
    }

    @Override
    public @NotNull String toString(@Nullable Event e, boolean debug) {
        return "individual visibility settings of player " + player.toString(e, debug) + " of the hologram '" + hologram.toString(e, debug) + "'";
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean init(Expression<?> @NotNull [] exprs, int matchedPattern, @NotNull Kleenean isDelayed, SkriptParser.@NotNull ParseResult parseResult) {
        player = (Expression<Player>) exprs[0];
        hologram = (Expression<Hologram>) exprs[1];
        return true;
    }
}
