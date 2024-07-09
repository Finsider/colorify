package com.fin.colorify.mixin;

import com.fin.colorify.Main;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import me.jellysquid.mods.sodium.client.render.immediate.CloudRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static org.objectweb.asm.Opcodes.GETSTATIC;

@Mixin(CloudRenderer.class)
public abstract class CloudRendererMixin {

    @ModifyExpressionValue(method = "*", remap = false, at = {
            @At(value = "FIELD", opcode = GETSTATIC, target = "Lme/jellysquid/mods/sodium/client/render/immediate/CloudRenderer;CLOUD_COLOR_NEG_Y:I"),
    })
    private static int changeCloudColor(final int color) {
        return Main.config.isCloudColor ? Main.config.CloudY | 0xFF000000 : color;
    }

    @ModifyExpressionValue(method = "*", remap = false, at = {
            @At(value = "FIELD", opcode = GETSTATIC, target = "Lme/jellysquid/mods/sodium/client/render/immediate/CloudRenderer;CLOUD_COLOR_NEG_Z:I"),
            @At(value = "FIELD", opcode = GETSTATIC, target = "Lme/jellysquid/mods/sodium/client/render/immediate/CloudRenderer;CLOUD_COLOR_POS_Z:I")
    })
    private static int changeCloudColorZ(final int color) {
        return Main.config.isCloudColor ? Main.config.CloudZ | 0xFF000000 : color;
    }

    @ModifyExpressionValue(method = "*", remap = false, at = {
            @At(value = "FIELD", opcode = GETSTATIC, target = "Lme/jellysquid/mods/sodium/client/render/immediate/CloudRenderer;CLOUD_COLOR_NEG_X:I"),
            @At(value = "FIELD", opcode = GETSTATIC, target = "Lme/jellysquid/mods/sodium/client/render/immediate/CloudRenderer;CLOUD_COLOR_POS_X:I")
    })
    private static int changeCloudColorX(final int color) {
        return Main.config.isCloudColor ? Main.config.CloudX | 0xFF000000 : color;
    }
}

