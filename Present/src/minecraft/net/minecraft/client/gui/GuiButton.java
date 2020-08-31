package net.minecraft.client.gui;

import java.awt.Color;
import java.util.List;

import me.present.Present;
import me.present.modules.Module;
import me.present.modules.Module.Category;
import me.present.settings.BooleanSetting;
import me.present.ui.ClickGuiRainbow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;

public class GuiButton extends Gui {
	public BooleanSetting Rainbow = new BooleanSetting("Rainbow", false);
	
    protected static final ResourceLocation BUTTON_TEXTURES = new ResourceLocation("textures/gui/widgets.png");
    
    public int currentTab;

    /** Button width in pixels */
    protected int width;

    /** Button height in pixels */
    protected int height;

    /** The x position of this control. */
    public int xPosition;

    /** The y position of this control. */
    public int yPosition;

    /** The string displayed on this control. */
    public String displayString;
    public int id;

    /** True if this control is enabled, false to disable. */
    public boolean enabled;

    /** Hides the button completely if false. */
    public boolean visible;
    protected boolean hovered;

    public GuiButton(int buttonId, int x, int y, String buttonText)
    {
        this(buttonId, x, y, 200, 20, buttonText);
    }

    public GuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText)
    {
        this.width = 200;
        this.height = 20;
        this.enabled = true;
        this.visible = true;
        this.id = buttonId;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonText;
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean mouseOver)
    {
        int i = 1;

        if (!this.enabled)
        {
            i = 0;
        }
        else if (mouseOver)
        {
            i = 2;
        }

        return i;
    }

    public void func_191745_a(Minecraft p_191745_1_, int p_191745_2_, int p_191745_3_, float p_191745_4_)
    {
        if (this.visible)
        {
        	Category category = Module.Category.values()[currentTab];
			List<Module> modules = Present.getModulesByCategory(category);
        	float hue = (System.currentTimeMillis() % 2000) / 2000f;
			int color = Color.HSBtoRGB(hue, 1, 1);
					
			int primaryColor = color;
        	
			for(Module m : modules) {
            FontRenderer fontrenderer = p_191745_1_.fontRendererObj;
            p_191745_1_.getTextureManager().bindTexture(BUTTON_TEXTURES);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = p_191745_2_ >= this.xPosition && p_191745_3_ >= this.yPosition && p_191745_2_ < this.xPosition + this.width && p_191745_3_ < this.yPosition + this.height;
            int i = this.getHoverState(this.hovered);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            this.mouseDragged(p_191745_1_, p_191745_2_, p_191745_3_);
            if(Rainbow.isEnabled()) {
            drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, color); //0x30ffffff
            }else {
            	if(this.hovered) {
            		drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 0xff87C0E1);
            			}else {
            				drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, 0x80000000);
            	
            	}
            }
            
            int j = 14737632;

            if (!this.enabled)
            {
                j = 10526880;
            }
            else if (this.hovered)
            {
            	if(Rainbow.isEnabled()) {
                j = 0xc0c0c0;  //0x93d3d3
            	}else {
            		j = -1;
            	}
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);
			}
        }
    }
    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int mouseX, int mouseY)
    {
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        return this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver()
    {
        return this.hovered;
    }

    public void drawButtonForegroundLayer(int mouseX, int mouseY)
    {
    }

    public void playPressSound(SoundHandler soundHandlerIn)
    {
        soundHandlerIn.playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    public int getButtonWidth()
    {
        return this.width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }
}
