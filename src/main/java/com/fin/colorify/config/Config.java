package com.fin.colorify.config;


import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@me.shedaniel.autoconfig.annotation.Config(name = "Colorify")
public class Config implements ConfigData {

    public boolean isCloudColor = true;
    @ConfigEntry.ColorPicker
    public int CloudY = 0xdacbcb;
    @ConfigEntry.ColorPicker
    public int CloudX = 0xF2F2F2;
    @ConfigEntry.ColorPicker
    public int CloudZ = 0xf0e5e5;

    public boolean isSkyColor = true;
    @ConfigEntry.ColorPicker
    public int SkyColorDay = 0x8FC0FF;
    @ConfigEntry.ColorPicker
    public int FogColorDay = 0xd6edff;

    @Comment("Night Color implementation is stupid and not recommended.")
    public boolean isNightColor = false;
    @ConfigEntry.ColorPicker
    public int SkyColorNight = 0x070d15;
    @ConfigEntry.ColorPicker
    public int FogColorNight = 0x27364c;
}
