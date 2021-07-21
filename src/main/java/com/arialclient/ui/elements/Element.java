package com.arialclient.ui.elements;

import com.arialclient.ArialClient;
import com.arialclient.ui.utils.GuiUtils;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class Element {

    protected final int x;
    protected final int y;
    protected final int width;
    protected final int height;
    protected boolean visible;
    protected boolean enabled;

    public void init() {
    }

    public void render() {
        this.renderBackground();
        this.renderElement();
    }

    protected abstract void renderElement();

    protected void renderBackground() {
        GuiUtils.drawRoundedRect(x, y, x + width, y + height, 2.0F, ArialClient.MAIN_COLOR);
    }

    public boolean mouseClicked(int mouseX, int mouseY, int mouseButton) {
        return visible && enabled;
    }

}
