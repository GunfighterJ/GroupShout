package org.tomlowmc.groupshout;

import com.earth2me.essentials.IEssentials;
import com.earth2me.essentials.User;
import java.util.logging.Logger;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class GroupShout extends JavaPlugin
{
    
  public static final Logger log = Logger.getLogger("Minecraft");
  
  public static GSmanage manager;
  public static Permission perms = null;
  private transient IEssentials ess = null;
  
  @Override
  public void onEnable() {
      
    GroupShout.manager = new GSmanage(this);
    this.getServer().getPluginManager().registerEvents(new GSListener(this, GroupShout.manager), this);
    getCommand("shout").setExecutor(new GScommand(this, GroupShout.manager));
    setupPermissions();
    
    getServer().getScheduler().scheduleSyncDelayedTask(this,
        new Runnable() {
            @Override
            public void run() {
                hookEss();
            }
        }
    );
  }
  
   private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
   
   public void hookEss() {
        final PluginManager pm = this.getServer().getPluginManager();
        final Plugin essPlugin = pm.getPlugin("Essentials");
        if (essPlugin == null || !essPlugin.isEnabled()) {
            this.setEnabled(false);
            log.warning("Couldn't hook ess.");
            log.warning("Will not be using essentials muting functions.");
            return;
        }
        ess = (IEssentials) essPlugin;
    }
   
   public boolean isEssHooked() {
       return (ess == null)? false : true;
   }
   
   public boolean isMuted(Player player) {
       User user = (User) player;
       return (user.getMuted())? true : false;
       
   }
   
   
}