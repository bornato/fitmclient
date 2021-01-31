/*
 * This file is part of the Meteor Client distribution (https://github.com/MeteorDevelopment/meteor-client/).
 * Copyright (c) 2021 Meteor Development.
 */

package minegame159.meteorclient.mixin;

import net.minecraft.client.resource.SplashTextResourceSupplier;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(SplashTextResourceSupplier.class)
public class SplashTextResourceSupplierMixin {
    @Inject(method = "apply", at = @At("HEAD"))
    private void onApply(List<String> list, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
        list.add("FitMC foda!");
        list.add("the odset anarchy in miamecrat!");
        list.add("minde papai.");
        list.add("If you look at the top, there's a giant letter \"N\"!");
        list.add(":EZ:");
        list.add(":kekw:");
        list.add("1FitPop.");
        list.add("vai toma no cu!");
        list.add("sexo momento");
        list.add("fitsex");
        list.add("Fit on Crack!");
        list.add("fit cumshot!");
        list.add("Fit groaned as he woke up!");
        list.add("fitmc apple");
        list.add("jogador de inertia aff");
        list.add("#1 FitFan!");
    }
}
