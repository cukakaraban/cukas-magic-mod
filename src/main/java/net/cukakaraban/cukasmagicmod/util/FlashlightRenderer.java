package net.cukakaraban.cukasmagicmod.util;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.light.AreaLight;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaternionf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FlashlightRenderer {
    private final MinecraftClient client;
    private final HashMap<AbstractClientPlayerEntity, ArrayList<AreaLight>> flashLightList2;

    public FlashlightRenderer() {
        this.client = MinecraftClient.getInstance();
        this.flashLightList2 = new HashMap<>();
    }

    public void renderFlashLightForEveryPlayer(float tickDelta) {
        if (client.world == null || client.player == null) return;

        List<AbstractClientPlayerEntity> playerList = client.world.getPlayers().stream()
                .filter(p -> p instanceof AbstractClientPlayerEntity)
                .map(p -> (AbstractClientPlayerEntity) p)
                .toList();

        for (AbstractClientPlayerEntity player : playerList) {
            if (true) {
                Vec3d playerPos = player.getCameraPosVec(tickDelta);

                if (!flashLightList2.containsKey(player)) {
                    AreaLight areaLight1 = new AreaLight();
                    AreaLight areaLight2 = new AreaLight();

                    float yaw = MathHelper.lerp(tickDelta, player.prevYaw, player.getYaw());
                    float pitch = MathHelper.lerp(tickDelta, player.prevPitch, player.getPitch());

                    Quaternionf orientation = new Quaternionf()
                            .rotateXYZ((float) -Math.toRadians(pitch), (float) Math.toRadians(yaw), 0.0f);

                    VeilRenderSystem.renderer().getLightRenderer().addLight(areaLight1
                            .setBrightness(1f)
                            .setDistance(25f)
                            .setSize(0, 0)
                            .setPosition((float) playerPos.x, (float) playerPos.y, (float) playerPos.z)
                            .setOrientation(orientation)
                    );
                    VeilRenderSystem.renderer().getLightRenderer().addLight(areaLight2
                            .setBrightness(1f)
                            .setAngle(0.25f)
                            .setDistance(25f)
                            .setSize(0, 0)
                            .setPosition((float) playerPos.x, (float) playerPos.y, (float) playerPos.z)
                            .setOrientation(orientation)
                    );

                    ArrayList<AreaLight> list = new ArrayList<>();
                    list.add(areaLight1);
                    list.add(areaLight2);
                    flashLightList2.put(player, list);
                } else {
                    ArrayList<AreaLight> areaLightList = flashLightList2.get(player);
                    float yaw = MathHelper.lerp(tickDelta, player.prevYaw, player.getYaw());
                    float pitch = MathHelper.lerp(tickDelta, player.prevPitch, player.getPitch());

                    Quaternionf orientation = new Quaternionf()
                            .rotateXYZ((float) -Math.toRadians(pitch), (float) Math.toRadians(yaw), 0.0f);

                    for (AreaLight light : areaLightList) {
                        light.setOrientation(orientation);
                        light.setPosition((float) playerPos.x, (float) playerPos.y, (float) playerPos.z);
                    }
                }
            } else {
                tryToRemoveFlashlight(player);
            }
        }
    }

    private void tryToRemoveFlashlight(AbstractClientPlayerEntity player) {
        if (flashLightList2.containsKey(player)) {
            ArrayList<AreaLight> areaLights = flashLightList2.get(player);
            for (AreaLight light : areaLights) {
                VeilRenderSystem.renderer().getLightRenderer().removeLight(light);
            }
            flashLightList2.remove(player);
        }
    }

    public void clearFlashlights() {
        flashLightList2.clear();
    }
}
