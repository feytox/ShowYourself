package ru.feytox.showmeurnick.mixin;

import net.minecraft.client.gui.Click;
import net.minecraft.client.input.MouseInput;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import ru.feytox.showmeurnick.client.ShowMeUrNickClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public abstract class MouseMixin {

    @Shadow
    @Final
    private MinecraftClient client;

    @Shadow
    public abstract double getScaledX(Window window);

    @Shadow
    public abstract double getScaledY(Window window);

    @Inject(method = "onMouseButton", at = @At("RETURN"))
    public void onOnMouseButton(long window, MouseInput input, int action, CallbackInfo ci) {
        Window window2 = client.getWindow();
        double x = getScaledX(window2);
        double y = getScaledY(window2);
        Click click = new Click(x, y, input);
        if (MinecraftClient.getInstance().options.useKey.matchesMouse(click)) {
            ShowMeUrNickClient.showNick();
        }
    }
}
