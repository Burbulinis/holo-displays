package me.burb.holodisplays.skript;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import ch.njol.util.coll.CollectionUtils;
import me.filoghost.holographicdisplays.api.hologram.Hologram;
import me.filoghost.holographicdisplays.api.hologram.HologramLines;
import me.filoghost.holographicdisplays.api.hologram.PlaceholderSetting;
import me.filoghost.holographicdisplays.api.hologram.VisibilitySettings;
import me.filoghost.holographicdisplays.api.hologram.line.HologramLine;
import me.filoghost.holographicdisplays.api.hologram.line.ItemHologramLine;
import me.filoghost.holographicdisplays.api.hologram.line.TextHologramLine;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Types {

    public Types() {}

    static {
        Classes.registerClass(new ClassInfo<>(Hologram.class, "hologram")
                .user("holograms?")
                .name("Hologram")
                .description("Represents a HolographicDisplays hologram")
                .examples("")
                .since("1.0")
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
                        return "hologram at '" + Classes.toString(o.getPosition().toLocation()) + "'";
                    }

                    @Override
                    public @NotNull String toVariableNameString(Hologram o) {
                        return "hologram at '" + Classes.toString(o.getPosition().toLocation()) + "'";
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
        );

        Classes.registerClass(new ClassInfo<>(HologramLines.class, "hologramlines")
                .user("hologram lines?")
                .name("Hologram Lines")
                .description("Hologram Lines. Represents the lines of a hologram. This accounts for every line, different from a Text Hologram Line and Item Hologram Line")
                .examples("")
                .requiredPlugins("HolographicDisplays")
                .since("1.0")
                .changer(new Changer<>() {
                    @Override
                    public Class<?>[] acceptChange(@NotNull ChangeMode mode) {
                        return (mode == ChangeMode.DELETE) ? CollectionUtils.array() : null;
                    }

                    @Override
                    public void change(HologramLines @NotNull [] what, Object @NotNull [] delta, @NotNull ChangeMode mode) {
                        if (mode != ChangeMode.DELETE) return;

                        for (HologramLines lines : what) {
                            lines.clear();
                        }
                    }
                })
                .parser(new Parser<>() {

                    @Override
                    public @NotNull String toString(HologramLines o, int flags) {
                        return "hologram lines";
                    }

                    @Override
                    public @NotNull String toVariableNameString(HologramLines o) {
                        return "hologram lines";
                    }

                    @Override
                    @Nullable
                    public HologramLines parse(@NotNull String input, @NotNull ParseContext context) {
                        return null;
                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return false;
                    }
                })
        );

        Classes.registerClass(new ClassInfo<>(HologramLine.class, "hologramline")
                .user("hologram lines?")
                .name("Hologram Line")
                .description("Hologram Line. Represents a hologram line, may be an Item Hologram Line, or a Text Hologram Line")
                .examples("")
                .requiredPlugins("HolographicDisplays")
                .since("1.0")
        );

        Classes.registerClass(new ClassInfo<>(ItemHologramLine.class, "itemhologramline")
                .user("item hologram lines?")
                .name("Item Hologram Line")
                .description("Item Hologram Line. Represents a line of a hologram, that is always an item")
                .examples("")
                .requiredPlugins("HolographicDisplays")
                .since("1.0")
                .parser(new Parser<>() {

                    @Override
                    public @NotNull String toString(ItemHologramLine o, int flags) {
                        return "item hologram line with item '" + Classes.toString(o.getItemStack()) + "'";
                    }

                    @Override
                    public @NotNull String toVariableNameString(ItemHologramLine o) {
                        return "item hologram line with item '" + Classes.toString(o.getItemStack()) + "'";
                    }

                    @Override
                    @Nullable
                    public ItemHologramLine parse(@NotNull String input, @NotNull ParseContext context) {
                        return null;
                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return false;
                    }
                })
        );

        Classes.registerClass(new ClassInfo<>(TextHologramLine.class, "texthologramline")
                .user("text hologram lines?")
                .name("Text Hologram Line")
                .description("Text Hologram Line. Represents a line of a hologram, that is always a text")
                .examples("")
                .requiredPlugins("HolographicDisplays")
                .since("1.0")
                .parser(new Parser<>() {

                    @Override
                    public @NotNull String toString(TextHologramLine o, int flags) {
                        return "text hologram line with text '" + o.getText() + "'";
                    }

                    @Override
                    public @NotNull String toVariableNameString(TextHologramLine o) {
                        return "text hologram line with text '" + o.getText() + "'";
                    }

                    @Override
                    @Nullable
                    public TextHologramLine parse(@NotNull String input, @NotNull ParseContext context) {
                        return null;
                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return false;
                    }
                })
        );

        Classes.registerClass(new ClassInfo<>(VisibilitySettings.Visibility.class, "visibilitysettings")
                .user("visibility( settings?)?")
                .name("Visibility")
                .description("Visibility Settings. Specify whether HIDDEN or VISIBLE visibility settings of a hologram")
                .examples("")
                .requiredPlugins("HolographicDisplays")
                .since("1.0")
        );

        Classes.registerClass(new ClassInfo<>(PlaceholderSetting.class, "placeholdersetting")
                .user("placeholder( settings?)?")
                .name("Placeholder Setting")
                .description("A placeholder setting. A hologram may have a placeholder setting: DEFAULT (equivalent to DISABLE ALL), DISABLE ALL, and ENABLE ALL")
                .examples("")
                .requiredPlugins("HolographicDisplays")
                .since("1.0")
        );
    }
}
