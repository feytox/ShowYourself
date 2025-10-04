package ru.feytox.showmeurnick.mixin;

import net.minecraft.client.input.KeyInput;
import ru.feytox.showmeurnick.client.ShowMeUrNickClient;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(method = "onKey", at = @At("RETURN"))
    public void onOnKey(long window, int action, KeyInput input, CallbackInfo ci) {
        if (MinecraftClient.getInstance().options.useKey.matchesKey(input)) {
            ShowMeUrNickClient.showNick();
        }
    }
}
