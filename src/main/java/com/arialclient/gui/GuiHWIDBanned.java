package com.arialclient.gui;

import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.arialclient.ArialClient;
import com.arialclient.mixins.client.DefaultResourcePackAccessor;
import com.arialclient.utils.AnimatedResourceLocation;
import com.arialclient.utils.HWID;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class GuiHWIDBanned extends GuiScreen {

    private IChatComponent[] message;
    private int messageLengthTimesFontHeight;

    public GuiHWIDBanned(String reason)
    {

        message = new IChatComponent[] {
                new ChatComponentText(EnumChatFormatting.GOLD + "" + EnumChatFormatting.BOLD + "You have been HWID banned from " + ArialClient.NAME + " " + ArialClient.VERSION + "!"),
                new ChatComponentText(""),
                new ChatComponentText("You have been banned for: "),
                new ChatComponentText(""),
                new ChatComponentText(EnumChatFormatting.AQUA + reason),
                new ChatComponentText(""),
                new ChatComponentText("You can appeal your ban at " + EnumChatFormatting.YELLOW + "http://arialclient.com/").setChatStyle(
                        new ChatStyle()
                                .setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "http://arialclient.com/"))
                                .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText("Click to open link").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN))))),
                new ChatComponentText(""),
                new ChatComponentText("Your HWID is: " + EnumChatFormatting.RED + HWID.getHWID()),
                new ChatComponentText("")
        };

    }


    @Override
    public void initGui()
    {
        super.initGui();
        this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height - 30, "Guess i'll just go outside"));
        this.messageLengthTimesFontHeight = this.message.length * this.fontRendererObj.FONT_HEIGHT;

    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawGradientRect(0, 0, this.width, this.height, -12574688, -11530224);
        int i = 50 - this.messageLengthTimesFontHeight / 2;

        for (IChatComponent s : this.message)
        {
            this.drawCenteredString(this.fontRendererObj, s.getFormattedText(), this.width / 2, i, 16777215);
            i += this.fontRendererObj.FONT_HEIGHT;


        }

        handleComponentHover(findChatComponent(mouseX, mouseY), mouseX, mouseY);

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    private IChatComponent findChatComponentLine(int mouseY)
    {
        int i = 50 - this.messageLengthTimesFontHeight / 2;

        for (IChatComponent s : this.message)
        {
            int yTop = i;
            int yBottom = i + this.fontRendererObj.FONT_HEIGHT;
            if (mouseY >= yTop && mouseY < yBottom) {
                return s;
            }
            i += this.fontRendererObj.FONT_HEIGHT;
        }

        return null;
    }

    private IChatComponent findChatComponent(int mouseX, int mouseY) {

        IChatComponent s = findChatComponentLine(mouseY);

        if (s == null || !(s instanceof ChatComponentText)) {
            return null;
        }

        int stringWidth = this.mc.fontRendererObj.getStringWidth(GuiUtilRenderComponents.func_178909_a(((ChatComponentText)s).getChatComponentText_TextValue(), false));
        int xLeft = this.width / 2 - stringWidth / 2;
        int xRight = this.width / 2 + stringWidth / 2;
        if (mouseX >= xLeft && mouseX < xRight) {
            return s;
        }

        return null;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (mouseButton == 0)
        {
            IChatComponent ichatcomponent = findChatComponent(mouseX, mouseY);

            if (this.handleComponentClick(ichatcomponent))
            {
                return;
            }
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }


    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 0) mc.shutdown();
        super.actionPerformed(button);
    }
}