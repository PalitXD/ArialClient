package com.arialclient.ui.utils;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class GuiUtils {

    public static int getColor(int color, int alpha) {
        return new Color(color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, alpha).getRGB();
    }

    public static void setGlColor(int color) {
        float alpha = (color >> 24 & 0xFF) / 255.0f;
        float red = (color >> 16 & 0xFF) / 255.0f;
        float green = (color >> 8 & 0xFF) / 255.0f;
        float blue = (color & 0xFF) / 255.0f;
        GlStateManager.color(red, green, blue, alpha);
    }

    public static void setGlColor(int color, float alpha) {
        float red = (color >> 16 & 0xFF) / 255.0f;
        float green = (color >> 8 & 0xFF) / 255.0f;
        float blue = (color & 0xFF) / 255.0f;
        GlStateManager.color(red, green, blue, alpha);
    }

    public static void drawModalRectWithCustomSizedTexture(float x, float y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x, y + height, 0.0D).tex(u * f, (v + (float)height) * f1).endVertex();
        worldrenderer.pos(x + width, y + height, 0.0D).tex((u + (float)width) * f, (v + (float)height) * f1).endVertex();
        worldrenderer.pos(x + width, y, 0.0D).tex((u + (float)width) * f, v * f1).endVertex();
        worldrenderer.pos(x, y, 0.0D).tex(u * f, v * f1).endVertex();
        tessellator.draw();
    }

    public static void drawScaledCustomSizeModalRect(float x, float y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
        float f = 1.0F / tileWidth;
        float f1 = 1.0F / tileHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x, y + height, 0.0D).tex(u * f, (v + (float)vHeight) * f1).endVertex();
        worldrenderer.pos(x + width, y + height, 0.0D).tex((u + (float)uWidth) * f, (v + (float)vHeight) * f1).endVertex();
        worldrenderer.pos(x + width, y, 0.0D).tex((u + (float)uWidth) * f, v * f1).endVertex();
        worldrenderer.pos(x, y, 0.0D).tex(u * f, v * f1).endVertex();
        tessellator.draw();
    }

    public static void drawRect(float left, float top, float right, float bottom, int color) {
        if (left < right) {
            float i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            float j = top;
            top = bottom;
            bottom = j;
        }
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GuiUtils.setGlColor(color);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, bottom, 0.0D).endVertex();
        worldrenderer.pos(right, top, 0.0D).endVertex();
        worldrenderer.pos(left, top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawRectOutline(float left, float top, float right, float bottom, int color) {
        float width = 0.55F;
        drawRect(left - width, top - width, right + width, top, color);
        drawRect(right, top, right + width, bottom, color);
        drawRect(left - width, bottom, right + width, bottom + width, color);
        drawRect(left - width, top, left, bottom, color);
    }

    public static void drawRoundedRect(float paramInt1, float paramInt2, float paramInt3, float paramInt4, float radius, int color) {
        float f1 = (color >> 24 & 0xFF) / 255.0F;
        float f2 = (color >> 16 & 0xFF) / 255.0F;
        float f3 = (color >> 8 & 0xFF) / 255.0F;
        float f4 = (color & 0xFF) / 255.0F;
        GlStateManager.color(f2, f3, f4, f1);
        drawRoundedRect(paramInt1, paramInt2, paramInt3, paramInt4, radius);
    }

    private static void drawRoundedRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5) {
        int i = 18;
        float f1 = 90.0F / i;
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat2);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat4);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat2);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat4);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glVertex2f(paramFloat1, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat1, paramFloat4 - paramFloat5);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat4 - paramFloat5);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glVertex2f(paramFloat3, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat3, paramFloat4 - paramFloat5);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat4 - paramFloat5);
        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        float f2 = paramFloat3 - paramFloat5;
        float f3 = paramFloat2 + paramFloat5;
        GL11.glVertex2f(f2, f3);
        float f4;

        for (int j = 0; j <= i; j++) {
            f4 = j * f1;
            GL11.glVertex2f((float)(f2 + paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 - paramFloat5 * Math.sin(Math.toRadians(f4))));
        }

        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        f2 = paramFloat1 + paramFloat5;
        f3 = paramFloat2 + paramFloat5;
        GL11.glVertex2f(f2, f3);

        for (int j = 0; j <= i; j++) {
            f4 = j * f1;
            GL11.glVertex2f((float)(f2 - paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 - paramFloat5 * Math.sin(Math.toRadians(f4))));
        }

        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        f2 = paramFloat1 + paramFloat5;
        f3 = paramFloat4 - paramFloat5;
        GL11.glVertex2f(f2, f3);

        for (int j = 0; j <= i; j++) {
            f4 = j * f1;
            GL11.glVertex2f((float)(f2 - paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 + paramFloat5 * Math.sin(Math.toRadians(f4))));
        }

        GL11.glEnd();
        GL11.glBegin(GL11.GL_TRIANGLE_FAN);
        f2 = paramFloat3 - paramFloat5;
        f3 = paramFloat4 - paramFloat5;
        GL11.glVertex2f(f2, f3);

        for (int j = 0; j <= i; j++) {
            f4 = j * f1;
            GL11.glVertex2f((float)(f2 + paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 + paramFloat5 * Math.sin(Math.toRadians(f4))));
        }

        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static void drawRoundedOutline(int x, int y, int x2, int y2, float radius, float width, int color) {
        GuiUtils.setGlColor(color);
        drawRoundedOutline(x, y, x2, y2, radius, width);
    }

    public static void drawRoundedOutline(float x, float y, float x2, float y2, float radius, float width, int color) {
        GuiUtils.setGlColor(color);
        drawRoundedOutline(x, y, x2, y2, radius, width);
    }

    private static void drawRoundedOutline(float x, float y, float x2, float y2, float radius, float width) {
        int i = 18;
        int j = 90 / i;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);

        if (width != 1.0F)
            GL11.glLineWidth(width);

        GL11.glBegin(3);
        GL11.glVertex2f(x + radius, y);
        GL11.glVertex2f(x2 - radius, y);
        GL11.glEnd();

        GL11.glBegin(3);
        GL11.glVertex2f(x2, y + radius);
        GL11.glVertex2f(x2, y2 - radius);
        GL11.glEnd();

        GL11.glBegin(3);
        GL11.glVertex2f(x2 - radius, y2 - 0.1F);
        GL11.glVertex2f(x + radius, y2 - 0.1F);
        GL11.glEnd();

        GL11.glBegin(3);
        GL11.glVertex2f(x + 0.1F, y2 - radius);
        GL11.glVertex2f(x + 0.1F, y + radius);
        GL11.glEnd();

        float f1 = x2 - radius;
        float f2 = y + radius;

        GL11.glBegin(3);
        for (int k = 0; k <= i; k++) {
            int m = 90 - k * j;
            GL11.glVertex2f((float)(f1 + radius * MathUtil.getRightAngle(m)), (float)(f2 - radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();

        f1 = x2 - radius;
        f2 = y2 - radius;

        GL11.glBegin(3);
        for (int k = 0; k <= i; k++) {
            int m = k * j + 270;
            GL11.glVertex2f((float)(f1 + radius * MathUtil.getRightAngle(m)), (float)(f2 - radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();

        GL11.glBegin(3);

        f1 = x + radius;
        f2 = y2 - radius;
        for (int k = 0; k <= i; k++) {
            int m = k * j + 90;
            GL11.glVertex2f((float)(f1 + radius * MathUtil.getRightAngle(m)), (float)(f2 + radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();

        GL11.glBegin(3);
        f1 = x + radius;
        f2 = y + radius;
        for (int k = 0; k <= i; k++) {
            int m = 270 - k * j;
            GL11.glVertex2f((float)(f1 + radius * MathUtil.getRightAngle(m)), (float)(f2 + radius * MathUtil.getAngle(m)));
        }
        GL11.glEnd();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);

        if (width != 1.0F)
            GL11.glLineWidth(1.0F);

        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
    }

    public static void drawCenteredString (String s, int i, int i1) {
    }
}
