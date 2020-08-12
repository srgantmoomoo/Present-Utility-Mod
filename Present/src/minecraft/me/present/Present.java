package me.present;

import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.opengl.Display;

import me.present.events.Event;
import me.present.modules.Module;
import me.present.movement.Sneak;
import me.present.movement.Sprint;
import me.present.ui.UIRenderer;

public class Present {
	
	public static String clientName = "Present", clientVersion = "0.2";
	public static CopyOnWriteArrayList<Module> modules = new CopyOnWriteArrayList<Module>();
	public static UIRenderer uiRenderer;
	
	public static void startup() {
		uiRenderer = new UIRenderer();
		Display.setTitle(clientName + " " + clientVersion);
		
		modules.add(new Sprint());
		modules.add(new Sneak());
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