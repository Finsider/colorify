package com.fin.colorify.mixin;

import com.fin.colorify.Main;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(RenderSystem.class)
public abstract class RenderSystemMixin {

    @Inject(method = "getShaderFogColor", remap = false, at = @At("RETURN"), cancellable = true)
    private static void NightFogColor(CallbackInfoReturnable<float[]> cir) {
        if (!Main.config.isNightColor) {return;}
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) {return;}

        long time = client.world.getTimeOfDay() % 24000L;

        if (time >= 13100L && time <= 22800L) {

            int nightFogColor = Main.config.FogColorNight;

            float red = ((nightFogColor >> 16) & 0xFF) / 255.0f;
            float green = ((nightFogColor >> 8) & 0xFF) / 255.0f;
            float blue = (nightFogColor & 0xFF) / 255.0f;
            float[] color = cir.getReturnValue();

            color[0] = red;
            color[1] = green;
            color[2] = blue;
            cir.setReturnValue(color);
        }
    }
}

