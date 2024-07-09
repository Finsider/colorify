package com.fin.colorify.mixin;


import com.fin.colorify.Main;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class BackgroundRendererMixin {

    @Inject(method = "render", at = @At(value = "TAIL"))
    private static void NightBackgroundColor(Camera camera, float tickDelta, ClientWorld world, int viewDistance, float skyDarkness, CallbackInfo ci) {
        if (!Main.config.isNightColor) {return;}
        if (world == null) {return;}

        long time = world.getTimeOfDay() % 24000L;

        if (time >= 13100L && time < 22800L) {
            int nightBackgroundColor = Main.config.FogColorNight; //the same as fog color otherwise they would look ugly

            // Extract RGB components
            float newRed = ((nightBackgroundColor >> 16) & 0xFF) / 255.0f;
            float newGreen = ((nightBackgroundColor >> 8) & 0xFF) / 255.0f;
            float newBlue = (nightBackgroundColor & 0xFF) / 255.0f;

            // Apply the new background color
            RenderSystem.clearColor(newRed, newGreen, newBlue, 0.0F);
        }
    }
}
