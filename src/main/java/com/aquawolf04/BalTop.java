package com.aquawolf04;


import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class BalTop extends JavaPlugin implements CommandExecutor {

    private String PREFIX;

    private double balance;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        PREFIX = getConfig().getString("prefix");
        getServer().getConsoleSender().sendMessage("§7[§6BalTop§7] §fPlugin §2enabled");
        getCommand("baltop").setExecutor(this);

    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("baltop")){
            if (args == null || args.length == 0) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    balance = getServer().getServicesManager().getRegistration(Economy.class).getProvider().getBalance(player);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', PREFIX + " &fEgyenleged: &e$" + balance));

                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PREFIX + " &cCsak játékosok használhatják ezt a parancsot!"));
                }
            }else{
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission("baltop.reload")) {
                        saveConfig();
                        reloadConfig();
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PREFIX + " &fKonfig újratöltve!"));
                    } else {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PREFIX + " &cNincs jogod ehhez a parancshoz!"));
                    }
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', PREFIX + " &cIsmeretlen parancs!"));
                }
            }
        }
        return true;
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("§7[§6BalTop§7] §fPlugin §4disabled");
    }
}
