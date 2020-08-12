package me.present.modules;

import me.present.events.Event;
import net.minecraft.client.Minecraft;

public class Module {
	
	public String name;
	public boolean toggled;
	private int keyCode;
	public Category category;
	public Minecraft mc = Minecraft.getMinecraft();
	
	public Module(String name, int key, Category c) {
		this.name = name;
		this.keyCode = key;
		this.category = c;
	}
	
	public boolean isEnabled() {
		return toggled;
	}

	public int getKey() {
		return keyCode;
	}
	
	public void onEvent(Event e){
		
	}
	
	public void toggle() {
		toggled = !toggled;
		if(toggled){
			onEnable();
		}else{
			onDisable();
		}
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public enum Category {
		COMBAT,
		MOVEMENT,
		RENDER,
		PLAYER;
	}
	
}
