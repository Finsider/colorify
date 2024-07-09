package com.fin.colorify.mixin;

import com.fin.colorify.Main;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

    @Inject(method = "getSkyColor", at = @At("TAIL"), cancellable = true)
    public void NightSkyColor(Vec3d cameraPos, float tickDelta, CallbackInfoReturnable<Vec3d> cir) {
        if (!Main.config.isNightColor) {return;}
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) {return;}

        long time = client.world.getTimeOfDay() % 24000L;

        if (time >= 13100L && time < 22800L) {

            int nightSkyColor = Main.config.SkyColorNight;

            // Extract RGB components
            float red = ((nightSkyColor >> 16) & 0xFF) / 255.0f;
            float green = ((nightSkyColor >> 8) & 0xFF) / 255.0f;
            float blue = (nightSkyColor & 0xFF) / 255.0f;

            Vec3d color = new Vec3d(red, green, blue);
            cir.setReturnValue(color);
        }
    }
}

