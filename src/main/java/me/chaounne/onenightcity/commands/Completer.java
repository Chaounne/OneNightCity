package me.chaounne.onenightcity.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class Completer implements TabCompleter {

    private final List<String> argumentsCity = new ArrayList<>();

    private final List<String> argumentsEntity = new ArrayList<>();

    private final List<String> argumentsPouder = new ArrayList<>();

    private final List<String> argumentsTeams = new ArrayList<>();

    private final List<String> argumentsColor = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (argumentsCity.isEmpty()) {
            argumentsCity.add("chest");
            argumentsCity.add("entity");
            argumentsCity.add("pouder");
            argumentsCity.add("start");
            argumentsCity.add("stop");
            argumentsCity.add("team");
        }

        if (argumentsEntity.isEmpty()) {
            argumentsEntity.add("spawn");
        }

        if (argumentsPouder.isEmpty()) {
            argumentsPouder.add("give");
            argumentsPouder.add("remove");
        }

        if (argumentsTeams.isEmpty()) {
            argumentsTeams.add("color");
            argumentsTeams.add("clear");
            argumentsTeams.add("create");
            argumentsTeams.add("disband");
            argumentsTeams.add("fire");
            argumentsTeams.add("hire");
            argumentsTeams.add("leave");
            argumentsTeams.add("members");
            argumentsTeams.add("marianne");
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
            for (String a : argumentsCity) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("team")) {
                for (String a : argumentsTeams) {
                    if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                        result.add(a);
                }
                return result;
            } else if (args[0].equalsIgnoreCase("pouder")) {
                for (String a : argumentsPouder) {
                    if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                        result.add(a);
                }
                return result;
            } else if (args[0].equalsIgnoreCase("entity")) {
                for (String a : argumentsEntity) {
                    if (a.toLowerCase().startsWith(args[1].toLowerCase()))
                        result.add(a);
                }
                return result;
            }
        } else if (args.length == 3) {
            if (args[1].equalsIgnoreCase("color")) {
                for (String a : argumentsColor) {
                    if (a.toLowerCase().startsWith(args[2].toLowerCase())) // Utilisez args[2] ici
                        result.add(a);
                }
                return result;
            }
        }

        return null;
    }

}