package com.tomlowmc.groupshout;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GScommand implements CommandExecutor {
    
    private final GroupShout plugin;
    private GSmanage manager;

    public GScommand(GroupShout instance, GSmanage manageinstance) {
        this.plugin = instance;
        manager = manageinstance;
    }
 
    private final Map<String, Boolean> shouting = new HashMap<>();
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if(args.length == 0) {
                manager.toggleShouting(player);
                
                if(manager.isShouting(player)){
                    sender.sendMessage(ChatColor.GREEN + "You are now shouting.");
                } else {
                    sender.sendMessage(ChatColor.RED + "You are no longer shouting.");
                }
                
            } else {
                sender.sendMessage("This command takes no arguments as of yet.");
            }
            
            return true;
            
        } else {
            sender.sendMessage("You must be a player to execute this command.");
            return false;
        }
    }
	
}
