package com.arialclient.mods.implement;

import com.arialclient.gui.hud.ScreenPosition;
import com.arialclient.mods.ModDraggable;

import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.world.chunk.Chunk;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;

public class ModCoords extends ModDraggable {

    private Minecraft mc = Minecraft.getMinecraft();
    String biomeName;

    @Override
    public int getWidth() {
        int x = (int)Math.round(mc.thePlayer.posX);
        int y = (int)Math.round(mc.thePlayer.posY);
        int z = (int)Math.round(mc.thePlayer.posZ);

        return font.getStringWidth("Biome: " + biomeName);
    }

    @Override
    public int getHeight() {
        return font.FONT_HEIGHT * 4;
    }

    @Override
    public void render(ScreenPosition pos) {

        BlockPos blockpos = new BlockPos(this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getEntityBoundingBox().minY, this.mc.getRenderViewEntity().posZ);

        if (this.mc.theWorld != null && this.mc.theWorld.isBlockLoaded(blockpos)) {
            Chunk chunk = this.mc.theWorld.getChunkFromBlockCoords(blockpos);
            biomeName = chunk.getBiome(blockpos, this.mc.theWorld.getWorldChunkManager()).biomeName;
        }

        int x = (int) Math.round(mc.thePlayer.posX);
        int y = (int) Math.round(mc.thePlayer.posY);
        int z = (int) Math.round(mc.thePlayer.posZ);

        font.drawStringWithShadow("Biome: " + biomeName, pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        font.drawStringWithShadow("X: " + x, pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT, -1);
        font.drawStringWithShadow("Y: " + y, pos.getAbsoluteX(), pos.getAbsoluteY() + (font.FONT_HEIGHT * 2), -1);
        font.drawStringWithShadow("Z: " + z, pos.getAbsoluteX(), pos.getAbsoluteY() + (font.FONT_HEIGHT * 3), -1);
    }

    @Override
    public int render (UnsupportedZipFeatureException.Feature feature, float x, float y) {
        return 0;
    }

    @Override
    public void renderDummy(ScreenPosition pos) {

        BlockPos blockpos = new BlockPos(this.mc.getRenderViewEntity().posX, this.mc.getRenderViewEntity().getEntityBoundingBox().minY, this.mc.getRenderViewEntity().posZ);

        if (this.mc.theWorld != null && this.mc.theWorld.isBlockLoaded(blockpos))
        {
            Chunk chunk = this.mc.theWorld.getChunkFromBlockCoords(blockpos);
            biomeName = chunk.getBiome(blockpos, this.mc.theWorld.getWorldChunkManager()).biomeName;
        }

        int x = (int)Math.round(mc.thePlayer.posX);
        int y = (int)Math.round(mc.thePlayer.posY);
        int z = (int)Math.round(mc.thePlayer.posZ);

        font.drawStringWithShadow("Biome: " + biomeName, pos.getAbsoluteX(), pos.getAbsoluteY(), -1);
        font.drawStringWithShadow("X: " + x, pos.getAbsoluteX(), pos.getAbsoluteY() + font.FONT_HEIGHT, -1);
        font.drawStringWithShadow("Y: " + y, pos.getAbsoluteX(), pos.getAbsoluteY() + (font.FONT_HEIGHT * 2), -1);
        font.drawStringWithShadow("Z: " + z, pos.getAbsoluteX(), pos.getAbsoluteY() + (font.FONT_HEIGHT * 3), -1);
    }
}
