package smallutils.totalvanish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public final class CommandListener implements Listener{
	@EventHandler
	public void onCommandEvent(PlayerCommandPreprocessEvent event){
		try{
			String cmd = event.getMessage();
			HashMap<String,Boolean> ids = main.ids;
			ArrayList<String> list = main.cmds;
			String[] lst = cmd.split(" ");
			if (lst.length > 1){
				for (String item:list){
					if (lst[0].contains(item)){
						for (String id:ids.keySet()){
							UUID pID = UUID.fromString(id);
							try{
								String name = Bukkit.getServer().getPlayer(pID).getName();
								String tocomp = name.substring(0, lst[1].length());
								if (lst[1].toLowerCase().contains(tocomp.toLowerCase())){
									main.Handle((Player) event.getPlayer(), cmd);
									if (ids.get(id)){
										event.setCancelled(true);
										String msg = main.getMessage(lst[0].substring(1, lst[0].length()));
										msg=main.Convert(msg, lst[1]);
										event.getPlayer().sendMessage(msg);
									}
									break;
								}
							}catch (NullPointerException e){
								//player is offline
								continue;
							}
						}
					}
				}
			}
		} catch (NullPointerException e){
			e.printStackTrace();
		}
	}
}
