package com.tomlowmc.groupshout;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;



public class GSListener implements Listener {
    
    private final GroupShout plugin;
    private GSmanage manager;
    
    public GSListener(GroupShout instance, GSmanage manageinstance) {
        this.plugin = instance;
        manager = manageinstance;
    }
    
    @EventHandler(priority=EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String name = player.getName();
        String group = GroupShout.perms.getPrimaryGroup(player);
        
        if(player.hasPermission("groupshout.shout") && manager.isShouting(player)){
            
            if(this.plugin.isEssHooked() && this.plugin.isMuted(player)) {
                return;
            }
            
            event.setCancelled(true);
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "[" + ChatColor.RED + group + ChatColor.DARK_RED + "] " + ChatColor.DARK_RED + "[" + ChatColor.RED + name + ChatColor.DARK_RED + "] " + ChatColor.RED + event.getMessage());
        }
    
    }
    
   
 }
