package me.burb.holodisplays.skript.elements;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.classes.Serializer;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.util.coll.CollectionUtils;
import ch.njol.yggdrasil.Fields;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.VisibilitySettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.StreamCorruptedException;

public class Types {

    public Types() {}

    static {
        Classes.registerClass(new ClassInfo<>(Hologram.class, "hologram")
                .user("holograms?")
                .name("Hologram")
                .description("Represents a HolographicDisplays hologram")
                .examples("")
                .since("1.0-SNAPSHOT")
                .requiredPlugins("HolographicDisplays")
                .changer(new Changer<>() {
                    @Override
                    public @Nullable Class<?>[] acceptChange(@NotNull ChangeMode mode) {
                        return mode == ChangeMode.DELETE ? CollectionUtils.array() : null;
                    }

                    @Override
                    public void change(Hologram @NotNull [] what, Object @NotNull [] delta, @NotNull ChangeMode mode) {
                        if (mode != ChangeMode.DELETE) return;
                        for (Hologram hologram : what) {
                            hologram.delete();
                        }
                    }
                })
                .parser(new Parser<>() {
                    @Override
                    public @NotNull String toString(Hologram o, int flags) {
                        return "hologram at'" + Classes.toString(o.getPosition().toLocation()) + "'";
                    }

                    @Override
                    public @NotNull String toVariableNameString(Hologram o) {
                        return "hologram at'" + Classes.toString(o.getPosition().toLocation()) + "'";
                    }

                    @Override
                    @Nullable
                    public Hologram parse(@NotNull String input, @NotNull ParseContext context) {
                        return null;
                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return false;
                    }
                })
                .serializer(new Serializer<>() {
                    @Override
                    public @NotNull Fields serialize(Hologram o) {
                        Fields f = new Fields();
                        f.putObject("hologram", o);
                        return f;
                    }

                    @Override
                    public Hologram deserialize(@NotNull Fields f) {
                        try {
                            return f.getAndRemoveObject("hologram", Hologram.class);
                        } catch (StreamCorruptedException e) {
                            return null;
                        }
                    }

                    @Override
                    public void deserialize(Hologram o, @NotNull Fields f) {
                        assert false;
                    }

                    @Override
                    public boolean mustSyncDeserialization() {
                        return true;
                    }

                    @Override
                    protected boolean canBeInstantiated() {
                        return false;
                    }
                })
        );

        Classes.registerClass(new ClassInfo<>(VisibilitySettings.Visibility.class, "visibility settings")
                .user("visibility( settings?)?")
                .name("Visibility")
                .description("Visibility Settings. Specify whether HIDDEN or VISIBLE visibility settings of a hologram")
                .examples("")
                .requiredPlugins("HolographicDisplays")
                .since("1.0-SNAPSHOT")
        );
    }
}
