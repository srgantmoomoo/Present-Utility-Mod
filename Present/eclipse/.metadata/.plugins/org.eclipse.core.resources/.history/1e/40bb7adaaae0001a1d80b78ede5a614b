package me.present.ui;

import java.io.IOException;

import me.present.Present;
import me.present.modules.Module;
import me.present.modules.Module.Category;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class ClickGui extends GuiScreen {
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		drawRect(135, 2, 220, 14, 0x80000000);
		mc.fontRendererObj.drawString("Combat", 161, 4, 0xffffffff);
		drawRect(235, 2, 320, 14, 0x80000000);
		mc.fontRendererObj.drawString("Movement", 255, 4, 0xffffffff);
		drawRect(335, 2, 420, 14, 0x80000000);
		mc.fontRendererObj.drawString("Render", 359, 4, 0xffffffff);
		drawRect(435, 2, 520, 14, 0x80000000);
		mc.fontRendererObj.drawString("Player", 460, 4, 0xffffffff);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	public int placeForHackY(Module m) {
		
		if(m.category.equals(Category.COMBAT)) return Category.placeInListCombat(m) * 14;
		if(m.category.equals(Category.MOVEMENT)) return Category.placeInListMovement(m) * 14;
		if(m.category.equals(Category.RENDER)) return Category.placeInListRender(m) * 14 - 14;
		if(m.category.equals(Category.PLAYER)) return Category.placeInListPlayer(m) * 14;
		
		return 0;
	}
	
	public int placeForHackX(Module m) {
		
		if(m.category.equals(Category.COMBAT)) return 135;
		if(m.category.equals(Category.MOVEMENT)) return 235;
		if(m.category.equals(Category.RENDER)) return 335;
		if(m.category.equals(Category.PLAYER)) return 435;
		
		return 0;
	}
	
	@Override
	public void initGui() {
		for(int i = 1; i < Present.modules.size(); i++) {
			Module m = Present.modules.get(i);
			buttonList.add(new GuiButton(i, placeForHackX(m), placeForHackY(m), 85, 14, m.name));
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		for(int i = 1; i < Present.modules.size(); i++) {
			if(button.id == i) {
				Present.modules.get(i).toggle();
			}
		}
	}
	
}
