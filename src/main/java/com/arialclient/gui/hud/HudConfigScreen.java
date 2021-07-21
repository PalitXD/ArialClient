/*
We got this working to some extent, there are still some issues.
Dragging overlaps other elements, if anyone can find a fix PLEASE send a pull request.
Possibly fixed by Jonathan Halterman#5542

Please use the ScreenPosition.java file that is included in this repository.
Thanks caterpillow#3310

Enjoy smooth dragging for your PvP Client!
*/

package com.arialclient.gui.hud;

import com.arialclient.ArialClient;
import com.arialclient.gui.clickgui.ClickGUI;
import com.arialclient.ui.utils.GuiUtils;
import com.arialclient.utils.DrawUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

public class HudConfigScreen extends GuiScreen {

    int i = 0;

    // ADDED FOR SMOOTH DRAGGING

    private int smX, smY;

    private boolean dragged = false;

    protected boolean hovered;


    private final HashMap<IRenderer, ScreenPosition> renderers = new HashMap<IRenderer, ScreenPosition>();

    private Optional<IRenderer> selectedRenderer = Optional.empty();

    private int prevX, prevY;

    @Override
    public void initGui() {

        this.buttonList.add(new GuiButton(69, this.width / 2 - 40,  this.height / 2 + -5, 80, 20, "Mods"));
        drawRect(0, 0, width, height, new Color (0, 0, 0, 100).getRGB());
        GuiUtils.drawCenteredString(EnumChatFormatting.DARK_PURPLE + "A" + EnumChatFormatting.WHITE + "rial " + EnumChatFormatting.GRAY + "C" + EnumChatFormatting.WHITE + "lient", width / 2 + 5, height / 2 - mc.fontRendererObj.FONT_HEIGHT - 15);
        int b = 12;

        // modified to add your own buttons <3

        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 69) {
            this.mc.displayGuiScreen(new ClickGUI(ArialClient.INSTANCE.getHudManager()));
        }
        super.actionPerformed(button);
    }

    public HudConfigScreen(HudManager api) {

        Collection<IRenderer> registeredRenderers = api.getRegisteredRenderers();

        for (IRenderer ren : registeredRenderers) {
            if (!ren.isEnabled()) {
                continue;
            }

            ScreenPosition pos = ren.load();
            if (pos == null) {
                pos = ScreenPosition.fromRelativePosition(0.5, 0.5);
            }

            adjustBounds(ren, pos);
            this.renderers.put(ren, pos);
        }

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        super.drawDefaultBackground();

        final float zBackup = this.zLevel;
        this.zLevel = 200;

        for (IRenderer renderer : renderers.keySet()) {
            // This if statement insures that random pixels will not be drawn for mods that don't render anything / have 0 width and 0 height
            if (!(renderer.getWidth() == 0 && renderer.getHeight() == 0)) {
                ScreenPosition pos = renderers.get(renderer);

                //Gui.drawRect(pos.getAbsoluteX() - 6, pos.getAbsoluteY() - 6, pos.getAbsoluteX() + renderer.getWidth() - 5, pos.getAbsoluteY() + renderer.getHeight(), 0x33FFFFFF);
                //if (renderer.getHeight() > 4) this.drawHollowRect(pos.getAbsoluteX() - 6, pos.getAbsoluteY() - 6, renderer.getWidth() - 1, renderer.getHeight() + 9, 0x88FFFFFF);

                DrawUtils.drawHollowRect(pos.getAbsoluteX() - 2, pos.getAbsoluteY() - 2, renderer.getWidth() + 2, renderer.getHeight() + 1, -1);

                renderer.renderDummy(pos);

                // START OF SMOOTH DRAGGING

                // Thanks ESS_Si1kn#0481 for pointing out that I forgot to add these back.
                int absoluteX = pos.getAbsoluteX();
                int absoluteY = pos.getAbsoluteY();

                this.hovered = mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth() && mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight();

                if (this.hovered) {
                    if (dragged) {
                        pos.setAbsolute(pos.getAbsoluteX() + mouseX - this.prevX, pos.getAbsoluteY() + mouseY - this.prevY);

                        adjustBounds(renderer, pos);

                        this.prevX = mouseX;
                        this.prevY = mouseY;
                    }
                }

                // END OF SMOOTH DRAGGING
            }
        }

        this.smX = mouseX;
        this.smY = mouseY;

        this.zLevel = zBackup;
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private void drawHollowRect(int x, int y, int w, int h, int color) {

        this.drawHorizontalLine(x, x + w, y, color);
        this.drawHorizontalLine(x, x + w, y + h, color);

        this.drawVerticalLine(x, y + h, y, color);
        this.drawVerticalLine(x + w, y + h, y, color);

    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == Keyboard.KEY_ESCAPE) {
            renderers.entrySet().forEach((entry) -> {
                entry.getKey().save(entry.getValue());
            });
            this.mc.displayGuiScreen(null);
        }
    }

    @Override
    protected void mouseClickMove(int x, int y, int button, long time) {
        if (selectedRenderer.isPresent()) {
            moveSelectedRenderBy(x - prevX, y - prevY);
        }

        this.prevX = x;
        this.prevY = y;
    }

    private void moveSelectedRenderBy(int offsetX, int offsetY) {
        IRenderer renderer = selectedRenderer.get();
        ScreenPosition pos = renderers.get(renderer);

        pos.setAbsolute(pos.getAbsoluteX() + offsetX, pos.getAbsoluteY() + offsetY);

        if (pos.getAbsoluteX() == 0 << 1) {
            pos.setAbsolute(1, pos.getAbsoluteY());
        }

        if (pos.getAbsoluteY() == 0 << 1) {
            pos.setAbsolute(pos.getAbsoluteX(), 1);
        }

        adjustBounds(renderer, pos);
    }

    @Override
    public void onGuiClosed() {

        for (IRenderer renderer : renderers.keySet()) {
            renderer.save(renderers.get(renderer));
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    private void adjustBounds(IRenderer renderer, ScreenPosition pos) {

        ScaledResolution res = new ScaledResolution(Minecraft.getMinecraft());

        int screenWidth = res.getScaledWidth();
        int screenHeight = res.getScaledHeight();

        int absoluteX = Math.max(0, Math.min(pos.getAbsoluteX(), Math.max(screenWidth - renderer.getWidth(), 0)));
        int absoluteY = Math.max(0, Math.min(pos.getAbsoluteY(), Math.max(screenHeight - renderer.getHeight(), 0)));

        pos.setAbsolute(absoluteX, absoluteY);
    }

    @Override
    protected void mouseClicked(int x, int y, int button) throws IOException {
        this.prevX = x;
        this.prevY = y;

        // NEEDED FOR SMOOTH DRAGGING
        dragged = true;

        loadMouseOver(x, y);
        super.mouseClicked(x, y, button);
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {

        // NEEDED FOR SMOOTH DRAGGING
        dragged = false;

        super.mouseReleased(mouseX, mouseY, state);
    }

    private void loadMouseOver(int x, int y) {
        this.selectedRenderer = renderers.keySet().stream().filter(new MouseOverFinder(x, y)).findFirst();
    }

    private class MouseOverFinder implements Predicate<IRenderer> {

        private int mouseX, mouseY;

        public MouseOverFinder(int x, int y) {
            this.mouseX = x;
            this.mouseY = y;
        }

        @Override
        public boolean test(IRenderer renderer) {

            ScreenPosition pos = renderers.get(renderer);

            int absoluteX = pos.getAbsoluteX();
            int absoluteY = pos.getAbsoluteY();

            if (mouseX >= absoluteX && mouseX <= absoluteX + renderer.getWidth()) {

                if (mouseY >= absoluteY && mouseY <= absoluteY + renderer.getHeight()) {

                    return true;

                }

            }

            return false;
        }

    }

}
