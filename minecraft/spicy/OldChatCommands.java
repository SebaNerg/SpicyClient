package spicy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.network.play.server.S02PacketChat;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import spicy.events.listeners.EventChatmessage;
import spicy.files.FileManager;
import spicy.modules.Module;

public class OldChatCommands {
	
	public static String prefix = ".";
	
	public void onEvent(EventChatmessage e) throws Exception{
		
		if (e.getMessage().toLowerCase().startsWith(prefix)) {
			
			if (e.getMessage().toLowerCase().startsWith(prefix + "toggle")) {
				
				String[] temp = e.getMessage().split("toggle");
				String module = temp[1];
				System.out.println(module);
				
				for (Module m : SpicyClient.modules) {
					
					if ((" " + m.name.toLowerCase()).equals(module.toLowerCase())) {
						System.out.println("Toggling the " + m.name + " module...");
						m.toggle();
					}
					
				}
				
			}
			
			if (e.getMessage().toLowerCase().startsWith(prefix + "config")) {
				
				String[] temp = e.getMessage().split(" ");
				System.out.println(temp);
				String saveOrLoad = temp[1];
				String name = temp[2];
				if (saveOrLoad.equalsIgnoreCase("save")) {
					FileManager.save_config(name);
				}
				else if (saveOrLoad.equalsIgnoreCase("load")) {
					FileManager.load_config(name);
				}else {
					System.out.println("Please use .config [save/load] [config name]");
				}
				
			}
			
			if (e.getMessage().toLowerCase().startsWith(prefix + "say")) {
				
				String[] temp = e.getMessage().split(" ");
				System.out.println(temp);
				String message = null;
				for (int i = 0; i < temp.length; i++) {
					if (i != 0) {
						if (i == 1) {
							message = temp[i];
						}
						else if (i != temp.length) {
							message = message + " " + temp[i];
						}else {
							message = message + temp[i];
						}
					}
				}
				
				if (message == null) {
					
				}else {
					Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C01PacketChatMessage(message));
				}
				
			}
			
			if (e.getMessage().toLowerCase().startsWith(prefix + "panic") || e.getMessage().toLowerCase().startsWith(prefix + "p")) {
				
				for (Module m : SpicyClient.modules) {
					
					if (m.isEnabled()) {
						m.toggle();
					}
					
				}
				
			}
			
			e.setMessage("none");
			e.setCanceled(true);
			
		}
		
	}
	
}