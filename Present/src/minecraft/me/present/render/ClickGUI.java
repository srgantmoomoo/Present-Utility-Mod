package me.present.render;

import org.lwjgl.input.Keyboard;

import me.present.modules.Module;
import me.present.modules.Module.Category;
import me.present.ui.ClickGui;

public class ClickGUI extends Module {
	
	public ClickGUI() {
		super("ClickGui", Keyboard.KEY_RSHIFT, Category.RENDER);
	}
	
	public void onEnable() {
		mc.displayGuiScreen(new ClickGui());
		toggle();
	}
	
}
