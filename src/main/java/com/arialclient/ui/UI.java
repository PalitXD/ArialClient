package com.arialclient.ui;

import com.arialclient.ui.elements.Element;
import com.google.common.collect.Lists;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;
import java.util.List;

public class UI extends GuiScreen {

    protected final List<Element> elements = Lists.newArrayList();

    @Override
    public void initGui() {
        this.elements.forEach(Element::init);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.elements.forEach(Element::render);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.elements.forEach(element -> element.mouseClicked(mouseX, mouseY, mouseButton));
    }
}
