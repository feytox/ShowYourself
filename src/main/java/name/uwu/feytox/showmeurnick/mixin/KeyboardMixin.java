package name.uwu.feytox.showmeurnick.mixin;

import name.uwu.feytox.showmeurnick.client.ShowMeUrNickClient;
import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(method = "onKey", at = @At("RETURN"))
    public void onOnKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (MinecraftClient.getInstance().options.useKey.matchesKey(key, scancode)) {
            ShowMeUrNickClient.showNick();
        }
    }
}
