package com.arialclient.mods.implement;

import com.arialclient.gui.hud.ScreenPosition;
import com.arialclient.mods.ModDraggable;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.lwjgl.opengl.GL11;

public class ModArmorStatus extends ModDraggable {

    @Override
    public int getWidth() {
        return 64;
    }

    @Override
    public int getHeight() {
        return 64;
    }

    @Override
    public void render(ScreenPosition pos) {
        for(int i = 0; i < mc.thePlayer.inventory.armorInventory.length; i++) {
            renderArmor(i, mc.thePlayer.inventory.armorInventory[i]);
        }
    }

    @Override
    public int render (UnsupportedZipFeatureException.Feature feature, float x, float y) {
        return 0;
    }

    @Override
    public void renderDummy(ScreenPosition pos) {
        renderArmor(3, new ItemStack(Items.diamond_helmet));
        renderArmor(2, new ItemStack(Items.diamond_chestplate));
        renderArmor(1, new ItemStack(Items.diamond_leggings));
        renderArmor(0, new ItemStack(Items.diamond_boots));
    }

    public void renderArmor(int i, ItemStack itemStack) {
        if(itemStack == null) {
            return;
        }

        GL11.glPushMatrix();

        int yAdd = (-16 * i) + 48;
        if(itemStack.getItem().isDamageable()) {
            double damage = ((itemStack.getMaxDamage() - itemStack.getItemDamage()) / (double) itemStack.getMaxDamage()) * 100;
            font.drawString(String.format("", damage), pos.getAbsoluteX() + 20, pos.getAbsoluteY() + yAdd + 5, -1);
        }

        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, pos.getAbsoluteX(), pos.getAbsoluteY() + yAdd);
        GL11.glPopMatrix();
    }
}
