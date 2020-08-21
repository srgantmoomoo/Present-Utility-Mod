package me.present.ui;

import java.awt.Color;
import java.util.Collections;
import java.util.Comparator;

import me.present.Present;
import me.present.listeners.EventRenderGUI;
import me.present.modules.Module;
import me.present.settings.BooleanSetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class UIRenderer extends GuiScreen {
	
	public BooleanSetting Rainbow = new BooleanSetting("Rainbow", false);
	
	private Minecraft mc = Minecraft.getMinecraft();
	
	public static class ModuleComparator implements Comparator<Module> {

		@Override
		public int compare(Module arg0, Module arg1) {
			if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg0.name) > Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg1.name)) {
				return -1;
			}
			if(Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg0.name) > Minecraft.getMinecraft().fontRendererObj.getStringWidth(arg1.name)) {
				return 1;
			}
			return 1;
		}
		
	}

	public void draw() {
		ScaledResolution sr = new ScaledResolution(mc);
		FontRenderer fr = mc.fontRendererObj;
		
		float hue = (System.currentTimeMillis() % 2000) / 2000f;
		int color = Color.HSBtoRGB(hue, 1, 1);
				
		int primaryColor = color;
	
		
		Collections.sort(Present.modules, new ModuleComparator());
		
		fr.drawStringWithShadow(Present.clientName + " " + Present.clientVersion, 1, 2, 0xffffff); //0xffffff
		
		int count = 0;
		for(Module module : Present.modules) {
			if(!module.isEnabled() || module.name.equals("TabGUI"))
				continue;
			
			double offset1 = count*(fr.FONT_HEIGHT + 1);
			double offset2 = count*(fr.FONT_HEIGHT + 1);
			
			if(Rainbow.isEnabled()) {
			Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(module.name) - 5, offset1, sr.getScaledWidth() - fr.getStringWidth(module.name) -3, 1 + fr.FONT_HEIGHT + offset2, color);
			Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(module.name) - 3, offset1, sr.getScaledWidth(), 1 + fr.FONT_HEIGHT + offset2, 0x90000000);
			fr.drawStringWithShadow(module.name, sr.getScaledWidth() - fr.getStringWidth(module.name) - 1, 1 + offset2, color);
			}else {
				Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(module.name) - 5, offset1, sr.getScaledWidth() - fr.getStringWidth(module.name) -3, 1 + fr.FONT_HEIGHT + offset2, 0xff87C0E1); 
				Gui.drawRect(sr.getScaledWidth() - fr.getStringWidth(module.name) - 3, offset1, sr.getScaledWidth(), 1 + fr.FONT_HEIGHT + offset2, 0x90000000);
				fr.drawStringWithShadow(module.name, sr.getScaledWidth() - fr.getStringWidth(module.name) - 1, 1 + offset2, 0xffffff);
			}
			count++;
			
		}
		
		Present.onEvent(new EventRenderGUI());
	}
	
}
