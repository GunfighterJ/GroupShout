package com.tomlowmc.groupshout;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.entity.Player;

public class GSmanage {
    
    private final GroupShout plugin;

    public GSmanage(GroupShout plugin) {
        this.plugin = plugin;
    }
    
    private final Map<String, Boolean> shouting = new HashMap<>();
    
    void toggleShouting(Player player) {
        String name = player.getName();
        if(shouting.get(name) != null) {
            shouting.put(name, !isShouting(player));
        } else {
            shouting.put(name, true);
        }
    }
    
    public boolean isShouting(Player player) {
        String name = player.getName();
        if(shouting.containsKey(name)) {
            return shouting.get(name);
        } else {
            return false;
        }
    }
}
