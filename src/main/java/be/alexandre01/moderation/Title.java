package be.alexandre01.moderation;

import org.bukkit.craftbukkit.v1_9_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_9_R2.IChatBaseComponent;
import net.minecraft.server.v1_9_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_9_R2.PacketPlayOutChat;
import net.minecraft.server.v1_9_R2.PacketPlayOutTitle;
import net.minecraft.server.v1_9_R2.PacketPlayOutTitle.EnumTitleAction;
import net.minecraft.server.v1_9_R2.PlayerConnection;

public class Title {
	private Title(){ }
	  @SuppressWarnings("deprecation")
	    public static void sTitle(Player player , String title , String subtitle)
	    {
	PacketPlayOutTitle Title = new PacketPlayOutTitle(EnumTitleAction.TITLE , 
			ChatSerializer.a("{\"text\":\" "+title+"\",\"color\":\"green\",\"bold\":true}") , 20 , 40 , 30) ;
	PacketPlayOutTitle SubTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE ,
			ChatSerializer.a("{\"text\":\" "+subtitle+"\",\"color\":\"green\",\"bold\":true}") , 20 , 40 , 30) ;
	PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
	connection.sendPacket(SubTitle);
	connection.sendPacket(Title);
	    }
	  public static void sItem(Player player, String message) {
		  PlayerConnection con = ((CraftPlayer) player).getHandle().playerConnection;
		  IChatBaseComponent chat = ChatSerializer.a("{\"text\": \"" + message + "\"}");
		  PacketPlayOutChat packet = new PacketPlayOutChat(chat, (byte) 2);
		  con.sendPacket(packet);
		  }
		  
}
