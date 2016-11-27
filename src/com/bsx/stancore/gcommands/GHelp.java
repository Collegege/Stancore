package com.bsx.stancore.gcommands;

import com.bsx.stancore.Stancore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GHelp implements CommandExecutor {
    private Stancore stancore;

    public GHelp(Stancore stancore) {
        this.stancore = stancore;
        stancore.getCommand("ghelp").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getLabel().equalsIgnoreCase("ghelp") && sender instanceof Player) {
            Player pl = (Player) sender;
            if (args.length == 0 && pl.hasPermission("global.help")) {
                pl.sendMessage(ChatColor.YELLOW + "=====Global Help=====");
                pl.sendMessage(ChatColor.GREEN + "/gdebug (all, none, practice, anticheat, cfactions, hcfactions) - Allows you to receive debug output from the selected modules.");
                pl.sendMessage(ChatColor.GREEN + "/g () -  .");

            }
        }
        return false;
    }
}
