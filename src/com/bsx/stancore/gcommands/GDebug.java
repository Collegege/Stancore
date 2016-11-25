package com.bsx.stancore.gcommands;

import com.bsx.stancore.Stancore;
import com.bsx.stancore.objects.Module;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GDebug implements CommandExecutor {

    private Stancore stancore;

    public GDebug(Stancore stancore) {
        this.stancore = stancore;
        stancore.getCommand("gdebug").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equals("gdebug") && sender instanceof Player && sender.hasPermission("stancore.debug")) {
           Player p = (Player)sender;
           int i = args.length;
           if (args.length == 0 || args.length >= 2) {
               p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[Stancore] Incorrect amount of arguments specified.");
               return false;
           } else if (args.length == 1) {
               String input = args[0];
               if (input.equalsIgnoreCase("anticheat")) {
                   boolean isActive = false;
                   for (Module m : stancore.getAPI().getModuleManager().getActiveModules()) {
                       if (m.getModuleName().equals("anticheat")) {
                           isActive = true;
                       }
                   }
                   if (isActive) {
                       for (Module m : stancore.getAPI().getModuleManager().getActiveModules()) {
                           if (m.getModuleName().equals("anticheat")) {
                               if (m.getDebugMap().containsKey(p)) {
                                   boolean current = m.getDebugMap().get(p);
                                   m.getDebugMap().remove(p);
                                   m.setDebugMode(p, !current);
                                   if (! current) {
                                       p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[Stancore] You will now receive debug output from the anticheat module.");
                                   } else {
                                       p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[Stancore] You will now not receive debug output from the anticheat module.");
                                   }
                               } else {
                                   m.setDebugMode(p, true);
                                   p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[Stancore] You will now receive debug output from the anticheat module.");
                               }
                           }
                       }
                   } else {
                       p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[Stancore] The following module is not active. To activate this module, please perform the command /module activate Anticheat.");
                   }
               }
           }
        }
        return false;
    }
}
