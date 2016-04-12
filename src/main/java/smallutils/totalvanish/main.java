package smallutils.totalvanish;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {
	static HashMap<String,Object> settings=null;
	static HashMap<String,Boolean> ids = new HashMap<String,Boolean>();
	static ArrayList<String> cmds = null;
	static Map<String, Object> messages = null;
	public static Boolean defvan;
	@Override
	public void onEnable(){
		//enabling logic here
		getServer().getPluginManager().registerEvents(new CommandListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerLeaveListener(), this);
		File f = getDataFolder();
		if (!f.exists()){
			f.mkdir();
			saveResource("config.yml", false);
			saveResource("plugin.yml", false);
		}
		settings=(HashMap<String, Object>) getConfig().getValues(true);
		cmds = (ArrayList<String>) settings.get("Commands");
		messages = getConfig().getConfigurationSection("Disguises").getValues(false);
		defvan = (settings.get("Vanish_By_Default").toString().toLowerCase() == "true");
		}
	@Override
	public void onDisable(){
		ids=null;
		cmds=null;
		messages=null;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if (cmd.getName().equalsIgnoreCase("tv d")){
			Player pl = (Player) sender;
			ids.put(pl.getUniqueId().toString(), !ids.get(pl.getUniqueId().toString()));
			sender.sendMessage(ChatColor.AQUA + "[TotalVanish] You toggled disguise mode");
			return true;
		}
		else if (cmd.getName().equalsIgnoreCase("tv s")){
			String message = "";
			Player pl = (Player) sender;
			if (ids.get(pl.getUniqueId().toString())){
				message = "Disguise Mode: Active";
			}else{
				message = "Disguise Mode: Disabled";
			}
			sender.sendMessage(ChatColor.AQUA + "[TotalVanish] " + message);
			return true;
		}
		return false;
	}
	public static void Handle(Player player, String cmd) {
		Collection<? extends Player> notifications = Bukkit.getServer().getOnlinePlayers();
		for (Player p: notifications){
			if (p.hasPermission("TotalVanish.Notify")){
				UUID pID=p.getUniqueId();
				Bukkit.getServer().getPlayer(pID).sendMessage(ChatColor.AQUA + "[TotalVanish] Player " +ChatColor.GOLD + player.getName() + ChatColor.AQUA + " used the command " + ChatColor.RED + cmd );
			}
		}
	}
	public static String getMessage(String cmd) {
		System.out.println("Command string is: " + cmd);
		String msg = (String) messages.get(cmd.toLowerCase());
		return msg;
	}
	public static String Convert(String msg, String Playername) {
		String newline = msg;
		newline = newline.replaceAll("&0", ChatColor.BLACK + "");
        newline = newline.replaceAll("&1", ChatColor.DARK_BLUE + "");
        newline = newline.replaceAll("&2", ChatColor.DARK_GREEN + "");
        newline = newline.replaceAll("&3", ChatColor.DARK_AQUA + "");
        newline = newline.replaceAll("&4", ChatColor.DARK_RED + "");
        newline = newline.replaceAll("&5", ChatColor.DARK_PURPLE + "");
        newline = newline.replaceAll("&6", ChatColor.GOLD + "");
        newline = newline.replaceAll("&7", ChatColor.GRAY + "");
        newline = newline.replaceAll("&8", ChatColor.DARK_GRAY+ "");
        newline = newline.replaceAll("&9", ChatColor.BLUE + "");
        newline = newline.replaceAll("&a", ChatColor.GREEN + "");
        newline = newline.replaceAll("&b", ChatColor.AQUA + "");
        newline = newline.replaceAll("&c", ChatColor.RED + "");
        newline = newline.replaceAll("&d", ChatColor.LIGHT_PURPLE + "");
        newline = newline.replaceAll("&e", ChatColor.YELLOW + "");
        newline = newline.replaceAll("&f", ChatColor.WHITE + "");
        newline = newline.replaceAll("&k", ChatColor.MAGIC + "");
        newline = newline.replaceAll("&l", ChatColor.BOLD + "");
        newline = newline.replaceAll("&o", ChatColor.ITALIC + "");
        newline = newline.replaceAll("&n", ChatColor.UNDERLINE + "");
        newline = newline.replaceAll("&m", ChatColor.STRIKETHROUGH + "");
        newline = newline.replaceAll("&r", ChatColor.RESET + "");
        newline = newline.replaceAll("--Player--", Playername);
		return newline;
	}
}
