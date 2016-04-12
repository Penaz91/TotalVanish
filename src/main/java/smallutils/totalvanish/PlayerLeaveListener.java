package smallutils.totalvanish;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event){
		if (event.getPlayer().hasPermission("TotalVanish.Vanish")){
			main.ids.remove(event.getPlayer().getUniqueId().toString());
			Bukkit.getLogger().info("Tracked Player " + event.getPlayer().getDisplayName()+ " logged off. Untracking");
		}
	}
}
