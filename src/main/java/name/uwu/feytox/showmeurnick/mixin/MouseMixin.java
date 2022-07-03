package name.uwu.feytox.showmeurnick.mixin;

import name.uwu.feytox.showmeurnick.client.ShowMeUrNickClient;
import name.uwu.feytox.showmeurnick.client.ShowMeUrNickConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.LiteralText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MouseMixin {

    @Inject(method = "onMouseButton", at = @At("RETURN"))
    public void onOnMouseButton(long window, int button, int action, int mods, CallbackInfo ci) {
        if (MinecraftClient.getInstance().options.useKey.matchesMouse(button)) {
            ShowMeUrNickClient.showNick();
        }
    }
}
