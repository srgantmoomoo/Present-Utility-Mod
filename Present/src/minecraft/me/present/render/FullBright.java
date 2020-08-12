package me.present.render;

import org.lwjgl.input.Keyboard;

import me.present.events.Event;
import me.present.listeners.EventUpdate;
import me.present.modules.Module;

public class FullBright extends Module {
	
	public FullBright() {
		super("FullBright", Keyboard.KEY_O, Category.RENDER);
	}
	
	public void onEnable() {
		mc.gameSettings.gammaSetting = 100;
	}

	public void onDisable() {
		mc.gameSettings.gammaSetting = 1; 
	}

	}
	


