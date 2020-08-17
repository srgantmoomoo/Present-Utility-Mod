package me.present.player;

import org.lwjgl.input.Keyboard;

import me.present.modules.Module;
import me.present.modules.Module.Category;

public class DiscordRPC extends Module {
	
	public DiscordRPC() {
		super("DiscordRPC", Keyboard.KEY_NONE, Category.PLAYER);
		
	}
	
}
