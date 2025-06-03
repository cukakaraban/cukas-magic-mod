package net.cukakaraban.cukasmagicmod.util;

import foundry.veil.api.client.render.VeilRenderSystem;
import foundry.veil.api.client.render.shader.program.ShaderProgram;
import foundry.veil.platform.VeilEventPlatform;
import net.cukakaraban.cukasmagicmod.CukasMagicMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.util.Identifier;
import org.joml.Matrix4f;

public class Utils {
    private static final FlashlightRenderer flashlightRenderer = new FlashlightRenderer();

    private static final Identifier COHESION_PIPELINE = id("cohesion");
    private static final Identifier COHESION_SHADER = id("cohesion");

    public static Identifier id(String path) {
        return Identifier.of(CukasMagicMod.MOD_ID, path);
    }

    public static void setupVeilCohesionPipeline() {
        VeilEventPlatform.INSTANCE.onVeilRenderLevelStage((stage, levelRenderer, bufferSource, matrixStack, frustumMatrix, projectionMatrix, renderTick, deltaTracker, camera, frustum) -> {
            float partialTick = renderTick;
            flashlightRenderer.renderFlashLightForEveryPlayer(partialTick);

            ClientPlayerEntity clientPlayer = MinecraftClient.getInstance().player;
            if (clientPlayer == null) return;

            boolean shouldApplyShader = true; // You can condition this further

            if (shouldApplyShader) {
                if (!VeilRenderSystem.renderer().getPostProcessingManager().isActive(COHESION_PIPELINE)) {
                    VeilRenderSystem.renderer().getPostProcessingManager().add(COHESION_PIPELINE);
                }
            } else {
                if (VeilRenderSystem.renderer().getPostProcessingManager().isActive(COHESION_PIPELINE)) {
                    VeilRenderSystem.renderer().getPostProcessingManager().remove(COHESION_PIPELINE);
                }
            }

            ShaderProgram shader = VeilRenderSystem.renderer().getShaderManager().getShader(COHESION_SHADER);
            if (shader != null) {
                updateUniforms(shader, partialTick);
            }
        });
    }

    private static void updateUniforms(ShaderProgram shader, float partialTick) {
        // Stub — fill in your shader uniform values here
        // Example:
        // shader.setFloat("u_Time", MinecraftClient.getInstance().world.getTime() + partialTick);
    }
}
