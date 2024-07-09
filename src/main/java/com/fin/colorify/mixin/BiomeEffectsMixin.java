package com.fin.colorify.mixin;

import com.fin.colorify.Main;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.biome.BiomeEffects;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Environment(EnvType.CLIENT)
@Mixin(BiomeEffects.class)
public abstract class BiomeEffectsMixin
{

	@ModifyReturnValue(
		method = "getSkyColor",
		at = @At(value = "RETURN")
	)
	private int getSkyColor(int original) {
		return Main.config.isSkyColor ? Main.config.SkyColorDay : original;
	}

	@ModifyReturnValue(
			method = "getFogColor",
			at = @At(value = "RETURN")
	)
	private int getFogColor(int original) {
		return Main.config.isSkyColor ? Main.config.FogColorDay : original;
	}
}