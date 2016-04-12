package smallutils.totalvanish;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
	@EventHandler
	public void onPlayerJoinEvent (PlayerJoinEvent event){
		if (event.getPlayer().hasPermission("TotalVanish.Vanish")){
			String plid=event.getPlayer().getUniqueId().toString();
			main.ids.put(plid, main.defvan);
			if (main.defvan){
				event.getPlayer().sendMessage(ChatColor.AQUA + "[TotalVanish] You joined vanished from commands, use /tvd to unvanish.");
			}else{
				event.getPlayer().sendMessage(ChatColor.AQUA + "[TotalVanish] You joined unvanished from commands, use /tvd to vanish.");
			}
			Bukkit.getLogger().info("Tracked Player " + event.getPlayer().getDisplayName()+ " logged on. Tracking for commands.");
		}
	}
}
