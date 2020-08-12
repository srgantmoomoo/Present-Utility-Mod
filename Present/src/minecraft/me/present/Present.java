package me.present;

import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import me.present.events.Event;
import me.present.modules.Module;
import me.present.movement.Sprint;

public class Present {
	
	public static String clientName = "Present", clientVersion = "0.2";
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	
	public static void startup() {
		Display.setTitle(clientName + " " + clientVersion);
		
		modules.add(new Sprint());
	}
	
	public static void onEvent(Event e) {
		for(Module m : modules){
			if(!m.toggled)
				continue;
			
			m.onEvent(e);;
		}
	}
	
	public static void keyPress(int key) {
		for(Module m : modules){
			if(m.getKey() == key){
				m.toggle();
			}
		}
		
	}

}