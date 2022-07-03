package name.uwu.feytox.showmeurnick.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.text.LiteralText;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import javax.annotation.Nullable;

@Environment(EnvType.CLIENT)
public class ShowMeUrNickClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ShowMeUrNickConfig.init("showmeurnick", ShowMeUrNickConfig.class);
    }

    public static void showNick() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null) {
            Entity targetedEntity = ShowMeUrNickClient.getTargetedEntity();
            if (targetedEntity == null) {
                targetedEntity = client.targetedEntity;
            }
            if (targetedEntity instanceof PlayerEntity && client.player.distanceTo(targetedEntity) <= ShowMeUrNickConfig.maxDistance
                    && client.player.distanceTo(targetedEntity) >= ShowMeUrNickConfig.minDistance) {
                client.player.sendMessage(new LiteralText(ShowMeUrNickConfig.format.replace("$NICK$",
                        targetedEntity.getName().getString()).replace("&", "§")), true);
            }
        }
    }

    @Nullable
    public static Entity getTargetedEntity() {
        float tickDelta = 1.0F;

        MinecraftClient client = MinecraftClient.getInstance();

        Entity entity = client.getCameraEntity();
        if (entity != null) {
            if (client.world != null) {
                client.getProfiler().push("pick");
                double d = ShowMeUrNickConfig.maxDistance;
                HitResult crosshairTarget = entity.raycast(d, tickDelta, false);
                Vec3d vec3d = entity.getCameraPosVec(tickDelta);
                double e = d;

                e *= e;
                if (crosshairTarget != null) {
                    e = crosshairTarget.getPos().squaredDistanceTo(vec3d);
                }

                Vec3d vec3d2 = entity.getRotationVec(1.0F);
                Vec3d vec3d3 = vec3d.add(vec3d2.x * d, vec3d2.y * d, vec3d2.z * d);
                Box box = entity.getBoundingBox().stretch(vec3d2.multiply(d)).expand(1.0, 1.0, 1.0);
                EntityHitResult entityHitResult = ProjectileUtil.raycast(entity, vec3d, vec3d3, box, (entityx) ->
                        !entityx.isSpectator() && entityx.collides(), e);
                if (entityHitResult != null) {
                    Entity entity2 = entityHitResult.getEntity();
                    Vec3d vec3d4 = entityHitResult.getPos();
                    double g = vec3d.squaredDistanceTo(vec3d4);
                    if (g > 9.0 && (g < e || crosshairTarget == null)) {
                        if (entity2 instanceof LivingEntity || entity2 instanceof ItemFrameEntity) {
                            return entity2;
                        }
                    }
                }
            }
        }
        return null;
    }
}
