package me.present.render;

import java.awt.Color;
import java.util.List;

import org.lwjgl.input.Keyboard;

import me.present.Present;
import me.present.events.Event;
import me.present.listeners.EventKey;
import me.present.listeners.EventRenderGUI;
import me.present.listeners.EventUpdate;
import me.present.modules.Module;
import me.present.settings.BooleanSetting;
import me.present.ui.ClickGuiRainbow;
import me.present.ui.UIRenderer;
import me.present.ui.UIRendererRainbow;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class TabGUI extends Module {
	
	public BooleanSetting Rainbow = new BooleanSetting("Rainbow", false);
	
	public int currentTab;
	public boolean expanded;
	public boolean shaders;
	
	public TabGUI() {
		super("TabGUI", Keyboard.KEY_NONE, Category.RENDER);
		toggled = true;
	}
	
	public void onEvent(Event e) {
		
		
		if(e instanceof EventRenderGUI) {
			FontRenderer fr = mc.fontRendererObj;
			
			float hue = (System.currentTimeMillis() % 2000) / 2000f;
			int color = Color.HSBtoRGB(hue, 1, 1);
					
			int primaryColor = color, secondaryColor = 0xff0070aa;
			
			if(Rainbow.isEnabled()) {
			Gui.drawRect(2, 12, 53, 71, 0x80000000);
			Gui.drawRect(3, 14 + currentTab * 15 - 1, 52, 14 + currentTab * 15 + 11, primaryColor); //0x9993d3d3
			}else {
				Gui.drawRect(2, 26, 53, 82, 0x80000000);
				Gui.drawRect(2, 12, 53, 26, 0x80000000);
				Gui.drawRect(2, 12, 53, 26, 0x80000000);
				Gui.drawRect(3, 14 + currentTab * 14 - 1, 52, 14 + currentTab * 14 + 11, 0xff87C0E1);
				Gui.drawRect(2.5, 12, 2, 26, 0xffd3d3d3);
				Gui.drawRect(53, 12, 52.5, 26, 0xffd3d3d3);
				Gui.drawRect(2, 12.5, 53, 12, 0xffd3d3d3);
				Gui.drawRect(2, 25.5, 53, 26, 0xffd3d3d3);
				
			}
			int count = 0;
			for(Category c : Module.Category.values()) {
				if(c.name.equals("Shaders")) {
					fr.drawStringWithShadow("Modules", 5, 15 + count * 15, -1);
					count++;
				}else {
				fr.drawStringWithShadow(c.name, 5, 15 + count * 14, -1);
			
				count++;
				}
				
			}
			
			if(expanded) {
				Category category = Module.Category.values()[currentTab];
				List<Module> modules = Present.getModulesByCategory(category);
				
				if (modules.size() == 0)
					return;
				if(Rainbow.isEnabled()) {
				Gui.drawRect(54.5, 12, 130, 11 + modules.size() * 15, 0x80000000);
				Gui.drawRect(55.5, 14 + category.moduleIndex * 15 - 1, 129, 14 + category.moduleIndex * 15 + 11, primaryColor);
				}else {
					Gui.drawRect(54.5, 12, 130, 12 + modules.size() * 14, 0x80000000);
					Gui.drawRect(55.5, 14 + category.moduleIndex * 14 - 1, 129, 14 + category.moduleIndex * 14 + 11, 0xff87C0E1);
					
				}
				
				count = 0;
				for(Module m : modules) {
					fr.drawStringWithShadow(m.name, 5 + 53, 15 + count * 14, -1);
				
					count++;
				}
				}
		}
		
			if(shaders) {
				Category category = Module.Category.values()[currentTab];
				List<Module> modules = Present.getModulesByCategory(category );
				FontRenderer fr = mc.fontRendererObj;
				
				Gui.drawRect(2, 26, 53, 82, 0xff000000);
				Gui.drawRect(2, 12, 53, 26, 0xff000000);
				Gui.drawRect(2, 12, 53, 26, 0xff000000);
				Gui.drawRect(3, 14 + currentTab * 14 - 1, 52, 14 + currentTab * 14 + 11, 0xff87C0E1);
				Gui.drawRect(2.5, 12, 2, 26, 0xffd3d3d3);
				Gui.drawRect(53, 12, 52.5, 26, 0xffd3d3d3);
				Gui.drawRect(2, 12.5, 53, 12, 0xffd3d3d3);
				Gui.drawRect(2, 25.5, 53, 26, 0xffd3d3d3);
				
				int count = 0;
				for(Category c : Module.Category.values()) {
					if(c.name.equals("Shaders")) 
					fr.drawStringWithShadow(c.name, 5, 15 + count * 14, -1);
					count++;
					fr.drawStringWithShadow("Sildurs", 5, 29, -1);
					fr.drawStringWithShadow("BSL", 5, 43, -1);
					fr.drawStringWithShadow("Seus", 5, 57, -1);
					fr.drawStringWithShadow("Oceano", 5, 71, -1);
				}	
				}
		if(e instanceof EventKey) {
			int code = ((EventKey)e).code;

			Category category = Module.Category.values()[currentTab];
			List<Module> modules = Present.getModulesByCategory(category);
			
			if(code == Keyboard.KEY_UP) {
				if(expanded) {
					if(category.moduleIndex <= 0) {
						category.moduleIndex = modules.size() - 1;
					}else
						category.moduleIndex--;
				}else {
					if(currentTab <= 0) {
						currentTab = Module.Category.values().length - 1;
					}else
						currentTab--;
				}
			}
			
			if(code == Keyboard.KEY_DOWN) {
				if (expanded) {
					if(category.moduleIndex >= modules.size() - 1) {
						category.moduleIndex = 0;
						}else
							category.moduleIndex++;
				}else {
					if(currentTab >= Module.Category.values().length - 1) {
						currentTab = 0;
						}else
							currentTab++;
				}
			}
			
			if(code == Keyboard.KEY_RIGHT) {
				if(category.name.equals("Shaders")) {
					shaders = true;
				}else {
				
				if(expanded && modules.size() !=0) {
					Module module = modules.get(category.moduleIndex);
					if(!module.name.equals("TabGUI"))
						module.toggle();
				}else {
					expanded = true;
				}
			}
			}
			
			if(code == Keyboard.KEY_LEFT) {
				expanded = false;
				shaders = false;
			
			}
		}
	}
	
}





