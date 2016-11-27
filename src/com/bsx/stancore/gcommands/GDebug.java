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
           if (args.length == 0 || args.length >= 2) {
               p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[Stancore] Incorrect amount of arguments specified.");
               return false;
           } else if (args.length == 1) {
               String input = args[0];
               if (! input.equalsIgnoreCase("all") || ! input.equalsIgnoreCase("none")) {
                   boolean isActive = false;
                   for (Module m : stancore.getAPI().getModuleManager().getActiveModules()) {
                       if (m.getModuleName().equalsIgnoreCase(input)) {
                           isActive = true;
                       }
                   }
                   if (isActive) {
                       for (Module m : stancore.getAPI().getModuleManager().getActiveModules()) {
                           if (m.getModuleName().equalsIgnoreCase(input)) {
                               if (m.getDebugMap().containsKey(p)) {
                                   boolean current = m.getDebugMap().get(p);
                                   m.getDebugMap().remove(p);
                                   m.setDebugMode(p, !current);
                                   if (! current) {
                                       p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[Stancore] You will now receive debug output from the specified module.");
                                       return true;
                                   } else {
                                       p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[Stancore] You will now not receive debug output from the specified module.");
                                       return true;
                                   }
                               } else {
                                   m.setDebugMode(p, true);
                                   p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[Stancore] You will now receive debug output from the specified module.");
                                   return true;
                               }
                           }
                       }
                   } else {
                       p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[Stancore] The following module is not active. To activate this module, please perform the command /module activate " + input + ".");
                       return true;
                   }
               } else {
                   if (input.equalsIgnoreCase("all")) {
                       for (Module m : stancore.getAPI().getModuleManager().getActiveModules()) {
                           if (m.hasDebugModeEnabled(p)) {
                               continue;
                           } else {
                               m.setDebugMode(p, true);
                           }
                       }
                       p.sendMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "[Stancore] You will now receive debug output from every module.");
                       return true;
                   } else {
                       for (Module m : stancore.getAPI().getModuleManager().getActiveModules()) {
                           if (! m.hasDebugModeEnabled(p)) {
                               continue;
                           } else {
                               m.setDebugMode(p, false);
                           }
                       }
                       p.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "[Stancore] You will receive no debug output from any modules.");
                       return true;
                   }
               }
           }
        }
        return false;
    }
}
