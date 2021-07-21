package com.arialclient.utils;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public class DrawUtils {
	public static void drawHollowRect(int x, int y, int w, int h, int color) {

        drawHorizontalLine(x, x + w, y, color);
        drawHorizontalLine(x, x + w, y + h, color);

        drawVerticalLine(x, y + h, y, color);
        drawVerticalLine(x + w, y + h, y, color);

    }
	
	/**
     * Draw a 1 pixel wide horizontal line. Args: x1, x2, y, color
     */
    public static void drawHorizontalLine(int startX, int endX, int y, int color) {
        if (endX < startX)
        {
            int i = startX;
            startX = endX;
            endX = i;
        }

        drawRect(startX, y, endX + 1, y + 1, color);
    }

    /**
     * Draw a 1 pixel wide vertical line. Args : x, y1, y2, color
     */
    public static void drawVerticalLine(int x, int startY, int endY, int color) {
        if (endY < startY)
        {
            int i = startY;
            startY = endY;
            endY = i;
        }

        drawRect(x, startY + 1, x + 1, endY, color);
    }
    
    /**
     * Draws a solid color rectangle with the specified coordinates and color (ARGB format). Args: x1, y1, x2, y2, color
     */
    public static void drawRect(int left, int top, int right, int bottom, int color) {
    	if (left < right)
        {
            int i = left;
            left = right;
            right = i;
        }

        if (top < bottom)
        {
            int j = top;
            top = bottom;
            bottom = j;
        }

        float f3 = (float)(color >> 24 & 255) / 255.0F;
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos((double)left, (double)bottom, 0.0D).endVertex();
        worldrenderer.pos((double)right, (double)bottom, 0.0D).endVertex();
        worldrenderer.pos((double)right, (double)top, 0.0D).endVertex();
        worldrenderer.pos((double)left, (double)top, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawRectOutline(int left, int top, int right, int bottom, int color) {
        drawRect(left - 1, top - 1, right + 1, top, color);
        drawRect(right, top, right + 1, bottom, color);
        drawRect(left - 1, bottom, right + 1, bottom + 1, color);
        drawRect(left - 1, top, left, bottom, color);
      }
      
      /**public void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
        float f = (startColor >> 24 & 0xFF) / 255.0F;
        float f1 = (startColor >> 16 & 0xFF) / 255.0F;
        float f2 = (startColor >> 8 & 0xFF) / 255.0F;
        float f3 = (startColor & 0xFF) / 255.0F;
        float f4 = (endColor >> 24 & 0xFF) / 255.0F;
        float f5 = (endColor >> 16 & 0xFF) / 255.0F;
        float f6 = (endColor >> 8 & 0xFF) / 255.0F;
        float f7 = (endColor & 0xFF) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(right, top, this.zLevel).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, top, this.zLevel).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, bottom, this.zLevel).color(f5, f6, f7, f4).endVertex();
        worldrenderer.pos(right, bottom, this.zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
      }**/
      
      public static void drawRoundedRect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float radius, int color) {
        float f1 = (color >> 24 & 0xFF) / 255.0F;
        float f2 = (color >> 16 & 0xFF) / 255.0F;
        float f3 = (color >> 8 & 0xFF) / 255.0F;
        float f4 = (color & 0xFF) / 255.0F;
        GlStateManager.color(f2, f3, f4, f1);
        drawRoundedRect(paramInt1, paramInt2, paramInt3, paramInt4, radius);
      }
      
      public static void drawRoundedRect(float paramInt1, float paramInt2, float paramInt3, float paramInt4, float radius, int color) {
        float f1 = (color >> 24 & 0xFF) / 255.0F;
        float f2 = (color >> 16 & 0xFF) / 255.0F;
        float f3 = (color >> 8 & 0xFF) / 255.0F;
        float f4 = (color & 0xFF) / 255.0F;
        GlStateManager.color(f2, f3, f4, f1);
        drawRoundedRect(paramInt1, paramInt2, paramInt3, paramInt4, radius);
      }
    public static void drawRoundedOutlineFloat(float x, float y, float x2, float y2, float radius, float width, int color) {
        float f1c = (color >> 24 & 0xFF) / 255.0F;
        float f2c = (color >> 16 & 0xFF) / 255.0F;
        float f3c = (color >> 8 & 0xFF) / 255.0F;
        float f4c = (color & 0xFF) / 255.0F;
        GlStateManager.color(f2c, f3c, f4c, f1c);
        int i = 18;
        int j = 90 / i;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
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
        int k;
        for (k = 0; k <= i; k++) {
            int m = 90 - k * j;
            GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 - radius * MathUtils.getAngle(m)));
        }
        GL11.glEnd();
        f1 = x2 - radius;
        f2 = y2 - radius;
        GL11.glBegin(3);
        for (k = 0; k <= i; k++) {
            int m = k * j + 270;
            GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 - radius * MathUtils.getAngle(m)));
        }
        GL11.glEnd();
        GL11.glBegin(3);
        f1 = x + radius;
        f2 = y2 - radius;
        for (k = 0; k <= i; k++) {
            int m = k * j + 90;
            GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 + radius * MathUtils.getAngle(m)));
        }
        GL11.glEnd();
        GL11.glBegin(3);
        f1 = x + radius;
        f2 = y + radius;
        for (k = 0; k <= i; k++) {
            int m = 270 - k * j;
            GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 + radius * MathUtils.getAngle(m)));
        }
        GL11.glEnd();
        if (width != 1.0F)
            GL11.glLineWidth(1.0F);
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
    }
      public static void drawRoundedRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5) {
        int i = 18;
        float f1 = 90.0F / i;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glBegin(5);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat2);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat4);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat2);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat4);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f(paramFloat1, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat1, paramFloat4 - paramFloat5);
        GL11.glVertex2f(paramFloat1 + paramFloat5, paramFloat4 - paramFloat5);
        GL11.glEnd();
        GL11.glBegin(5);
        GL11.glVertex2f(paramFloat3, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat2 + paramFloat5);
        GL11.glVertex2f(paramFloat3, paramFloat4 - paramFloat5);
        GL11.glVertex2f(paramFloat3 - paramFloat5, paramFloat4 - paramFloat5);
        GL11.glEnd();
        GL11.glBegin(6);
        float f2 = paramFloat3 - paramFloat5;
        float f3 = paramFloat2 + paramFloat5;
        GL11.glVertex2f(f2, f3);
        int j;
        for (j = 0; j <= i; j++) {
          float f4 = j * f1;
          GL11.glVertex2f((float)(f2 + paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 - paramFloat5 * Math.sin(Math.toRadians(f4))));
        } 
        GL11.glEnd();
        GL11.glBegin(6);
        f2 = paramFloat1 + paramFloat5;
        f3 = paramFloat2 + paramFloat5;
        GL11.glVertex2f(f2, f3);
        for (j = 0; j <= i; j++) {
          float f4 = j * f1;
          GL11.glVertex2f((float)(f2 - paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 - paramFloat5 * Math.sin(Math.toRadians(f4))));
        } 
        GL11.glEnd();
        GL11.glBegin(6);
        f2 = paramFloat1 + paramFloat5;
        f3 = paramFloat4 - paramFloat5;
        GL11.glVertex2f(f2, f3);
        for (j = 0; j <= i; j++) {
          float f4 = j * f1;
          GL11.glVertex2f((float)(f2 - paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 + paramFloat5 * Math.sin(Math.toRadians(f4))));
        } 
        GL11.glEnd();
        GL11.glBegin(6);
        f2 = paramFloat3 - paramFloat5;
        f3 = paramFloat4 - paramFloat5;
        GL11.glVertex2f(f2, f3);
        for (j = 0; j <= i; j++) {
          float f4 = j * f1;
          GL11.glVertex2f((float)(f2 + paramFloat5 * Math.cos(Math.toRadians(f4))), (float)(f3 + paramFloat5 * Math.sin(Math.toRadians(f4))));
        } 
        GL11.glEnd();
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
      }
      
      public static void drawRoundedOutline(int x, int y, int x2, int y2, float radius, float width, int color) {
        float f1 = (color >> 24 & 0xFF) / 255.0F;
        float f2 = (color >> 16 & 0xFF) / 255.0F;
        float f3 = (color >> 8 & 0xFF) / 255.0F;
        float f4 = (color & 0xFF) / 255.0F;
        GlStateManager.color(f2, f3, f4, f1);
        drawRoundedOutline(x, y, x2, y2, radius, width);
      }
      
      public static void drawRoundedOutline(float x, float y, float x2, float y2, float radius, float width) {
        int i = 18;
        int j = 90 / i;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableCull();
        GlStateManager.enableColorMaterial();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
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
        int k;
        for (k = 0; k <= i; k++) {
          int m = 90 - k * j;
          GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 - radius * MathUtils.getAngle(m)));
        } 
        GL11.glEnd();
        f1 = x2 - radius;
        f2 = y2 - radius;
        GL11.glBegin(3);
        for (k = 0; k <= i; k++) {
          int m = k * j + 270;
          GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 - radius * MathUtils.getAngle(m)));
        } 
        GL11.glEnd();
        GL11.glBegin(3);
        f1 = x + radius;
        f2 = y2 - radius;
        for (k = 0; k <= i; k++) {
          int m = k * j + 90;
          GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 + radius * MathUtils.getAngle(m)));
        } 
        GL11.glEnd();
        GL11.glBegin(3);
        f1 = x + radius;
        f2 = y + radius;
        for (k = 0; k <= i; k++) {
          int m = 270 - k * j;
          GL11.glVertex2f((float)(f1 + radius * MathUtils.getRightAngle(m)), (float)(f2 + radius * MathUtils.getAngle(m)));
        } 
        GL11.glEnd();
        if (width != 1.0F)
          GL11.glLineWidth(1.0F); 
        GlStateManager.enableCull();
        GlStateManager.disableBlend();
        GlStateManager.disableColorMaterial();
        GlStateManager.enableTexture2D();
      }
      
      public static void drawCircle(float x, float y, float radius, float thickness, Color color, boolean smooth) {
    	  drawPartialCircle(x, y, radius, 0, 360, thickness, color, smooth);
      }
      
      public static void drawPartialCircle(int x, int y, float radius, int startAngle, int endAngle, float thickness, Color color, boolean smooth) {
    	  drawPartialCircle(x, y, radius, startAngle, endAngle, thickness, color, smooth);
      }
      
      public static void drawPartialCircle(float x, float y, float radius, int startAngle, int endAngle, float thickness, Color colour, boolean smooth) {
    	  GL11.glDisable(3553);
    	  GL11.glBlendFunc(770, 771);
    	  if (startAngle > endAngle) {
    		  int temp = startAngle;
    		  startAngle = endAngle;
    		  endAngle = temp;
		  } 
		  if (startAngle < 0)
		      startAngle = 0; 
		  if (endAngle > 360)
		      endAngle = 360; 
		  if (smooth) {
		      GL11.glEnable(2848);
		  } else {
		      GL11.glDisable(2848);
		  } 
		  GL11.glLineWidth(thickness);
		  GL11.glColor4f(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getAlpha() / 255.0F);
		  GL11.glBegin(3);
		  float ratio = 0.017453292F;
		  for (int i = startAngle; i <= endAngle; i++) {
		      float radians = (i - 90) * ratio;
		      GL11.glVertex2f(x + (float)Math.cos(radians) * radius, y + (float)Math.sin(radians) * radius);
		  } 
		  GL11.glEnd();
		  GL11.glEnable(3553);
		  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	  }
    	  
	  public static void drawFilledRect(float x1, float y1, float x2, float y2, int colour, boolean smooth) {
		  drawFilledShape(new float[] { x1, y1, x1, y2, x2, y2, x2, y1 }, new Color(colour, true), smooth);
	  }
	  
	  public static void drawFilledShape(float[] points, Color colour, boolean smooth) {
	      GL11.glPushMatrix();
	      GL11.glDisable(3553);
	      GL11.glBlendFunc(770, 771);
	      if (smooth) {
	    	  GL11.glEnable(2848);
	      } else {
	    	  GL11.glDisable(2848);
	      } 
	      GL11.glLineWidth(1.0F);
	      GL11.glColor4f(colour.getRed() / 255.0F, colour.getGreen() / 255.0F, colour.getBlue() / 255.0F, colour.getAlpha() / 255.0F);
	      GL11.glBegin(9);
	      for (int i = 0; i < points.length; i += 2)
	    	  GL11.glVertex2f(points[i], points[i + 1]); 
	      GL11.glEnd();
	      GL11.glEnable(3553);
	      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	      GL11.glPopMatrix();
	  }
}
