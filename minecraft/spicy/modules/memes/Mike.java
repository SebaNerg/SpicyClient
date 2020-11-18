package spicy.modules.memes;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import spicy.events.Event;
import spicy.events.listeners.EventRenderGUI;
import spicy.events.listeners.EventUpdate;
import spicy.modules.Module;

public class Mike extends Module {

	public Mike() {
		super("Mike", Keyboard.KEY_NONE, Category.MEMES);
	}
	
	public static transient ResourceLocation mike1 = new ResourceLocation("spicy/mike1.png");
	
	@Override
	public void onEvent(Event e) {
		
		if (e instanceof EventRenderGUI && e.isPre()) {
			
			GlStateManager.enableBlend();
			GlStateManager.color(1, 1, 1);
			mc.getTextureManager().bindTexture(mike1);
			int imageWidth = new ScaledResolution(mc).getScaledWidth(), imageHeight = new ScaledResolution(mc).getScaledHeight();
			Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
			
		}
	}
	
}
