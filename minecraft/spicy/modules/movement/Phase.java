package spicy.modules.movement;

import org.lwjgl.input.Keyboard;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockHopper;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import spicy.events.Event;
import spicy.events.listeners.EventGetBlockHitbox;
import spicy.events.listeners.EventMotion;
import spicy.events.listeners.EventPlayerUseItem;
import spicy.events.listeners.EventUpdate;
import spicy.modules.Module;
import spicy.modules.Module.Category;

public class Phase extends Module {
	
	public Phase() {
		super("Phase", Keyboard.KEY_NONE, Category.MOVEMENT);
	}

	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		mc.thePlayer.motionY += 0.1;
	}
	
	private transient int hypixelStage = 0;
	
	public void onEvent(Event e) {
		
		if (e instanceof EventGetBlockHitbox) {
			
			if (e.isPre()) {
				
				EventGetBlockHitbox event = (EventGetBlockHitbox) e;
				event.setCanceled(true);
				mc.thePlayer.motionY = 0;
				mc.thePlayer.onGround = true;
				
				double y, y1;
				mc.thePlayer.motionY = 0;
				
				//if (mc.thePlayer.ticksExisted % 3 ==0) {
					
					//y = mc.thePlayer.posY - 1.0E-10D;
					//mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, y, mc.thePlayer.posZ, true));
					
				//}
				
				//y1 = mc.thePlayer.posY + 1.0E-10D;
				//mc.thePlayer.setPosition(mc.thePlayer.posX, y1, mc.thePlayer.posZ);
				
				switch (hypixelStage) {
				case 0:
					mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ);
					hypixelStage++;
					break;
				case 1:
					// mc.thePlayer.posY = mc.thePlayer.posY + 9.947598300641403E-14;
					// mc.thePlayer.posY = mc.thePlayer.lastTickPosY + 0.0002000000000066393;
					mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.0002000000000066393,
							mc.thePlayer.posZ);
					hypixelStage++;
					break;
				case 2:
					// mc.thePlayer.posY = mc.thePlayer.posY + -9.947598300641403E-14;
					// mc.thePlayer.posY = mc.thePlayer.lastTickPosY -0.0002000000000066393;
					mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY + -0.0002000000000066393,
							mc.thePlayer.posZ);
					hypixelStage = 0;
					break;
				}
				
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
				
			}
			
		}
		
	}
	
}