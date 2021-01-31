/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client/).
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient;

import com.g00fy2.versioncompare.Version;
import minegame159.meteorclient.gui.GuiConfig;
import minegame159.meteorclient.rendering.Fonts;
import minegame159.meteorclient.utils.Utils;
import minegame159.meteorclient.utils.files.Savable;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.nbt.CompoundTag;

import java.io.File;

public class Config extends Savable<Config> {
    public static Config INSTANCE;

    public final Version version = new Version("0.3.9");
    public String devBuild;
    private String prefix = ".";
    public GuiConfig guiConfig = new GuiConfig();

    public boolean customFont = true;

    public boolean chatCommandsInfo = true;
    public boolean deleteChatCommandsInfo = true;

    public Config() {
        super(new File(MeteorClient.FOLDER, "config.nbt"));

        devBuild = FabricLoader.getInstance().getModContainer("fit-mclient").get().getMetadata().getCustomValue("meteor-client:devbuild").getAsString();
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        save();
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public CompoundTag toTag() {
        CompoundTag tag = new CompoundTag();

        tag.putString("version", version.getOriginalString());
        tag.putString("prefix", prefix);
        tag.putBoolean("customFont", customFont);
        tag.put("guiConfig", guiConfig.toTag());
        tag.putBoolean("chatCommandsInfo", chatCommandsInfo);
        tag.putBoolean("deleteChatCommandsInfo", deleteChatCommandsInfo);

        return tag;
    }

    @Override
    public Config fromTag(CompoundTag tag) {
        prefix = tag.getString("prefix");
        guiConfig.fromTag(tag.getCompound("guiConfig"));
        if (tag.contains("customFont")) customFont = tag.getBoolean("customFont");
        chatCommandsInfo = !tag.contains("chatCommandsInfo") || tag.getBoolean("chatCommandsInfo");
        deleteChatCommandsInfo = !tag.contains("deleteChatCommandsInfo") || tag.getBoolean("deleteChatCommandsInfo");

        // In 0.2.9 the default font was changed, detect when people load up 0.2.9 for the first time
        Version lastVer = new Version(tag.getString("version"));
        Version v029 = new Version("0.2.9");

        if (lastVer.isLowerThan(v029) && version.isAtLeast(v029)) {
            Fonts.reset();
        }

        // If you run 0.3.7 for the first time add meteor pvp to server list
        Version v037 = new Version("0.3.7");

        if (lastVer.isLowerThan(v037) && version.isAtLeast(v037)) {
            Utils.addMeteorPvpToServerList();
        }

        return this;
    }
}
