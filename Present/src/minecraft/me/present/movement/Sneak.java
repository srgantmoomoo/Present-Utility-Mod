package me.present.movement;

import org.lwjgl.input.Keyboard;

import me.present.events.Event;
import me.present.listeners.EventUpdate;
import me.present.modules.Module;

public class Sneak extends Module {
	
	public Sneak() {
		super("Sneak", Keyboard.KEY_N, Category.MOVEMENT);
	}
	
	public void onEnable() {

	}
	
	public void onDisable() {
		
	}
	
	public void onEvent(Event e){
		if(e instanceof EventUpdate){
			if(e.isPre()) {
				
				
					
				}
			}
		}
	}
