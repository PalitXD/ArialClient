package com.arialclient.mixins.client;

import com.arialclient.ArialClient;
import com.arialclient.event.implement.KeyPressEvent;
import com.arialclient.event.implement.TickEvent;
import com.arialclient.gui.splash.SplashProgress;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.lib.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Minecraft.class)
public class MixinMinecraft {

    @Shadow public GuiScreen currentScreen;
    @Shadow public EntityPlayerSP thePlayer;
    @Shadow private int leftClickCounter;
    @Shadow public MovingObjectPosition objectMouseOver;
    @Shadow public WorldClient theWorld;
    @Shadow public EffectRenderer effectRenderer;
    @Shadow public PlayerControllerMP playerController;

    @Inject(method = "createDisplay", at = @At("RETURN"))
    private void createDisplay(CallbackInfo callbackInfo) {
        Display.setTitle(ArialClient.NAME + " " + ArialClient.VERSION);
    }

    @Inject(method = "startGame", at = @At("HEAD"))
    private void onGameStart(CallbackInfo callbackInfo) {
        ArialClient.INSTANCE.init();
    }

    @Inject(method = "startGame", at = @At("RETURN"))
    private void onGameStartPost(CallbackInfo callbackInfo) {
        ArialClient.INSTANCE.start();
    }

    @Inject(method = "shutdown", at = @At("HEAD"))
    private void shutdown(CallbackInfo callbackInfo) {
        ArialClient.INSTANCE.shutdown();
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/profiler/Profiler;endSection()V"))
    private void tick(CallbackInfo ci) {
        new TickEvent().call();
    }

    @Inject(method = "runTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;onTick(I)V"), slice = @Slice(from = @At(value = "INVOKE", target = "Lorg/lwjgl/input/Keyboard;next()Z"), to = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;debugCrashKeyPressTime:J")), locals = LocalCapture.CAPTURE_FAILSOFT)
    private void keyPress(CallbackInfo info, int keyCode) {
        if (currentScreen != null) new KeyPressEvent(keyCode);
    }

    /**
     * @author Erick golddeeee
     */
    @Overwrite
    private void drawSplashScreen(TextureManager textureManagerInstance) throws LWJGLException
    {
        SplashProgress.drawSplash(Minecraft.getMinecraft().getTextureManager());
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;skinManager:Lnet/minecraft/client/resources/SkinManager;", opcode = Opcodes.PUTFIELD))
    private void mixin1 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(3, "Minecraft - Initalizing SkinManager");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;mcSoundHandler:Lnet/minecraft/client/audio/SoundHandler;", opcode = Opcodes.PUTFIELD))
    private void mixin2 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(4, "Minecraft - Initalizing SoundHandler");
    }

    @Inject(
            method = "startGame",
            slice = @Slice(
                    from = @At(
                            value = "FIELD",
                            target = "Lnet/minecraft/client/Minecraft;mcSoundHandler:Lnet/minecraft/client/audio/SoundHandler;",
                            opcode = Opcodes.PUTFIELD
                    )
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/resources/IReloadableResourceManager;registerReloadListener(Lnet/minecraft/client/resources/IResourceManagerReloadListener;)V",
                    ordinal = 0
            )
    )

    private void inject (CallbackInfo callbackInfo){
        SplashProgress.setProgress(5, "Minecraft - Registering SoundHandler ReloadListener");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;mcMusicTicker:Lnet/minecraft/client/audio/MusicTicker;", opcode = Opcodes.PUTFIELD))
    private void mixin3 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(6, "Minecraft - Initalizing MusicTicker");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;fontRendererObj:Lnet/minecraft/client/gui/FontRenderer;", opcode = Opcodes.PUTFIELD))
    private void mixin4 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(7, "Minecraft - Initalizing FontRenderer");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;standardGalacticFontRenderer:Lnet/minecraft/client/gui/FontRenderer;", opcode = Opcodes.PUTFIELD))
    private void inject1 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(8, "Minecraft - Initalizing GalacticFontRenderer");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;mouseHelper:Lnet/minecraft/util/MouseHelper;", opcode = Opcodes.PUTFIELD))
    private void mixin5 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(9, "Minecraft - Initalizing MouseHelper");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;textureMapBlocks:Lnet/minecraft/client/renderer/texture/TextureMap;", opcode = Opcodes.PUTFIELD))
    private void mixin6 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(10, "Minecraft - Initalizing TextureMap");
    }

    @Inject(method = "startGame", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureMap;setMipmapLevels(I)V"))
    private void inject2 (CallbackInfo callbackInfo) {
        SplashProgress.setProgress(11, "Minecraft - Setting Mipmap Levels");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;renderItem:Lnet/minecraft/client/renderer/entity/RenderItem;", opcode = Opcodes.PUTFIELD))
    private void mixin7 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(12, "Minecraft - Initalizing RenderItem");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;renderManager:Lnet/minecraft/client/renderer/entity/RenderManager;", opcode = Opcodes.PUTFIELD))
    private void mixin8 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(13, "Minecraft - Initalizing RenderManager");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;itemRenderer:Lnet/minecraft/client/renderer/ItemRenderer;", opcode = Opcodes.PUTFIELD))
    private void mixin9 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(14, "Minecraft - Initalizing ItemRenderer");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;entityRenderer:Lnet/minecraft/client/renderer/EntityRenderer;", opcode = Opcodes.PUTFIELD))
    private void mixin10 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(15, "Minecraft - Initalizing EntityRenderer");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;blockRenderDispatcher:Lnet/minecraft/client/renderer/BlockRendererDispatcher;", opcode = Opcodes.PUTFIELD))
    private void mixin11 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(16, "Minecraft - Initalizing BlockRendererDispatcher");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;entityRenderer:Lnet/minecraft/client/renderer/EntityRenderer;", opcode = Opcodes.PUTFIELD))
    private void mixin12 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(17, "Minecraft - Initalizing RenderGlobal");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;guiAchievement:Lnet/minecraft/client/gui/achievement/GuiAchievement;", opcode = Opcodes.PUTFIELD))
    private void mixin13 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(18, "Minecraft - Initalizing GuiAchievement");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;effectRenderer:Lnet/minecraft/client/particle/EffectRenderer;", opcode = Opcodes.PUTFIELD))
    private void mixin14 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(19, "Minecraft - Initalizing EffectRenderer");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;ingameGUI:Lnet/minecraft/client/gui/GuiIngame;", opcode = Opcodes.PUTFIELD))
    private void mixin15 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(20, "Minecraft - Initalizing GuiIngame");
    }

    @Inject(method = "startGame", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;loadingScreen:Lnet/minecraft/client/LoadingScreenRenderer;", opcode = Opcodes.PUTFIELD))
    private void mixin16 (CallbackInfo callbackInfo){
        SplashProgress.setProgress(21, "Minecraft - Initalizing LoadingScreenRenderer");
    }

    /**
     * @author EldoDebug
     */
    @Overwrite
    private void sendClickBlockToController(boolean leftClick)
    {
        if (!leftClick)
        {
            this.leftClickCounter = 0;
        }
        if (this.leftClickCounter <= 0 && !this.thePlayer.isUsingItem()) {
            if (leftClick && this.objectMouseOver != null && this.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                final BlockPos blockpos = this.objectMouseOver.getBlockPos();
                if (this.theWorld.getBlockState(blockpos).getBlock().getMaterial() != Material.air && this.playerController.onPlayerDamageBlock(blockpos, this.objectMouseOver.sideHit)) {
                    this.effectRenderer.addBlockHitEffects(blockpos, this.objectMouseOver.sideHit);
                    this.thePlayer.swingItem();
                }
            }
            else {
                this.playerController.resetBlockRemoving();
            }
        }
        else if (this.leftClickCounter <= 0 && leftClick && this.objectMouseOver != null && this.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            final BlockPos blockpos = this.objectMouseOver.getBlockPos();
            if (this.theWorld.getBlockState(blockpos).getBlock().getMaterial() != Material.air) {
                //((EntityPlayerSPFakeswing)this.thePlayer).FakeswingItem();
            }
        }
    }
}
