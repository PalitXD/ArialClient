package com.arialclient.gui;

import com.arialclient.ArialClient;
import com.arialclient.http.HttpFunctions;
import com.arialclient.utils.HWID;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GuiWhitelisted extends GuiScreen {

    private final IChatComponent[] message;
    private int messageLengthTimesFontHeight;
    private int secondsLeft = 0;

    private static final Timer timer = new Timer();

    private GuiButton refreshButton;

    public GuiWhitelisted()
    {

        message = new IChatComponent[] {
                new ChatComponentText("Hi, "),
                new ChatComponentText(""),
                new ChatComponentText("Thank you for using Arial Client!"),
                new ChatComponentText(""),
                new ChatComponentText("Currently the client is whitelisted for Beta Testing."),
                new ChatComponentText(""),
                new ChatComponentText("If you are asked for your HWID, it is displayed below:"),
                new ChatComponentText(""),
                new ChatComponentText(HWID.getHWID()).setChatStyle(
                        new ChatStyle().setColor(EnumChatFormatting.GOLD)),
                new ChatComponentText("")
        };

    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
    }

    @Override
    public void initGui()
    {
        this.buttonList.clear();

        this.messageLengthTimesFontHeight = this.message.length * this.fontRendererObj.FONT_HEIGHT;
        this.buttonList.add(new GuiButton(0, width / 2 - 100, height - 25, 98, 20, EnumChatFormatting.RED + "Close Game"));
        this.buttonList.add(refreshButton = new GuiButton(1, width / 2 + 2, height - 25, 98, 20, "Refresh"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException
    {
        if (button.id == 0)
        {
            mc.shutdown();
        }
        if (button.id == 1 && secondsLeft == 0)
        {

            boolean isWhitelisted;
            try {
                isWhitelisted = HttpFunctions.isWhitelisted();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            if(!isWhitelisted) {

                secondsLeft = 6;
                timer.schedule(new TimerTask() {

                    @Override
                    public void run() {
                        secondsLeft--;
                        if(secondsLeft == 0) {
                            refreshButton.displayString = "Refresh Again";
                            refreshButton.enabled = true;
                            cancel();
                            return;
                        }

                        refreshButton.enabled = false;
                        refreshButton.displayString = EnumChatFormatting.AQUA + "" + secondsLeft + "...";
                    }
                }, 0, 1000);
            }
            else {
                ArialClient.INSTANCE.isWhitelisted = true;
                mc.displayGuiScreen(null);
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();

        int i = this.height / 2 - this.messageLengthTimesFontHeight / 2;


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
        int i = this.height / 2 - this.messageLengthTimesFontHeight / 2;

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

}
