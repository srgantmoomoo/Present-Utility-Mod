package me.present.render;

import org.lwjgl.input.Keyboard;

import me.present.modules.Module;
import me.present.modules.Module.Category;

public class GuiTakeUp extends Module {
	
	public GuiTakeUp() {
		super("GuiPlaceHold", Keyboard.KEY_NONE, Category.RENDER);
	}
}
