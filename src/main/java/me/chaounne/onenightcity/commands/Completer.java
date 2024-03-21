package me.chaounne.onenightcity.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Completer implements TabCompleter {

    private final List<String> argumentsCity = new ArrayList<>();

    private final List<String> argumentsJump = new ArrayList<>();

    private final List<String> argumentsPowder = new ArrayList<>();

    private final List<String> argumentsTeams = new ArrayList<>();

    private final List<String> argumentsColor = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (argumentsCity.isEmpty()) {
            argumentsCity.add("calc");
            argumentsCity.add("jump");
            argumentsCity.add("powder");
            argumentsCity.add("start");
            argumentsCity.add("stop");
            argumentsCity.add("team");
        }

        if (argumentsJump.isEmpty()) {
            argumentsJump.add("restart");
            argumentsJump.add("start");
            argumentsJump.add("stop");
        }

        if (argumentsPowder.isEmpty()) {
            argumentsPowder.add("give");
            argumentsPowder.add("remove");
        }

        if (argumentsTeams.isEmpty()) {
            argumentsTeams.add("color");
            argumentsTeams.add("create");
            argumentsTeams.add("disband");
            argumentsTeams.add("fire");
            argumentsTeams.add("hire");
            argumentsTeams.add("leave");
            argumentsTeams.add("members");
            argumentsTeams.add("marianne");
            argumentsTeams.add("purge");
            argumentsTeams.add("rename");
        }

        if (argumentsColor.isEmpty()) {
            argumentsColor.add("aqua");
            argumentsColor.add("black");
            argumentsColor.add("blue");
            argumentsColor.add("bold");
            argumentsColor.add("dark_aqua");
            argumentsColor.add("dark_blue");
            argumentsColor.add("dark_gray");
            argumentsColor.add("dark_green");
            argumentsColor.add("dark_purple");
            argumentsColor.add("dark_red");
            argumentsColor.add("gold");
            argumentsColor.add("gray");
            argumentsColor.add("green");
            argumentsColor.add("italic");
            argumentsColor.add("light_purple");
            argumentsColor.add("red");
            argumentsColor.add("underline");
            argumentsColor.add("white");
            argumentsColor.add("yellow");
        }

        List<String> result = new ArrayList<>();

        if (args.length == 1) {
            for (String arg : argumentsCity) {
                if (arg.startsWith(args[0].toLowerCase()))
                    result.add(arg);
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("calc"))
                result.add("<trade_price>");
            else if (args[0].equalsIgnoreCase("jump"))
                for (String arg : argumentsJump) {
                    if (arg.startsWith(args[1].toLowerCase()))
                        result.add(arg);
                }

            else if (args[0].equalsIgnoreCase("team")) {
                for (String arg : argumentsTeams) {
                    if (arg.startsWith(args[1].toLowerCase()))
                        result.add(arg);
                }
            } else if (args[0].equalsIgnoreCase("powder")) {
                for (String arg : argumentsPowder) {
                    if (arg.startsWith(args[1].toLowerCase()))
                        result.add(arg);
                }
            }
        } else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("calc"))
                result.add("<trade_amount>");
            else if (args[1].equalsIgnoreCase("color")) {
                for (String arg : argumentsColor) {
                    if (arg.startsWith(args[2].toLowerCase()))
                        result.add(arg);
                }
            }
        } else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("calc"))
                result.add("<item_stack>");
        }

        return result;
    }

}