package me.chaounne.onenightcity.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class CityCompleter implements TabCompleter {
    List<String> argumentsCity = new ArrayList<>();
    List<String> argumentsTeams = new ArrayList<>();
    List<String> argumentsPoudre = new ArrayList<>();

    List<String> argumentsEntity = new ArrayList<>();

    List<String> argumentsColor = new ArrayList<>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(argumentsCity.isEmpty()){
            argumentsCity.add("start");
            argumentsCity.add("stop");
            argumentsCity.add("team");
            argumentsCity.add("poudre");
            argumentsCity.add("entity");
            argumentsCity.add("chest");
        }
        if(argumentsTeams.isEmpty()){
            argumentsTeams.add("create");
            argumentsTeams.add("add");
            argumentsTeams.add("remove");
            argumentsTeams.add("disband");
            argumentsTeams.add("leave");
            argumentsTeams.add("list");
            argumentsTeams.add("color");
            argumentsTeams.add("name");

        }
        if(argumentsPoudre.isEmpty()){
            argumentsPoudre.add("give");
            argumentsPoudre.add("remove");
        }

        if(argumentsEntity.isEmpty()){
            argumentsEntity.add("spawn");

        }

        if (argumentsColor.isEmpty()) {
            argumentsColor.add("black");
            argumentsColor.add("dark_blue");
            argumentsColor.add("dark_green");
            argumentsColor.add("dark_aqua");
            argumentsColor.add("dark_red");
            argumentsColor.add("dark_purple");
            argumentsColor.add("gold");
            argumentsColor.add("gray");
            argumentsColor.add("dark_gray");
            argumentsColor.add("blue");
            argumentsColor.add("green");
            argumentsColor.add("aqua");
            argumentsColor.add("red");
            argumentsColor.add("light_purple");
            argumentsColor.add("yellow");
            argumentsColor.add("white");
            argumentsColor.add("bold");
            argumentsColor.add("underline");
            argumentsColor.add("italic");
        }

        List<String> result = new ArrayList<>();

        if(args.length == 1){
            for(String a : argumentsCity){
                if(a.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(a);
            }
            return result;
        }

        if(args.length == 2){
            if(args[0].equalsIgnoreCase("team")){
                for(String a : argumentsTeams){
                    if(a.toLowerCase().startsWith(args[1].toLowerCase()))
                        result.add(a);
                }
                return result;
            } else if(args[0].equalsIgnoreCase("poudre")){
                for(String a : argumentsPoudre){
                    if(a.toLowerCase().startsWith(args[1].toLowerCase()))
                        result.add(a);
                }
                return result;
            }else if(args[0].equalsIgnoreCase("entity")){
                for(String a : argumentsEntity){
                    if(a.toLowerCase().startsWith(args[1].toLowerCase()))
                        result.add(a);
                }
                return result;
            }
        }
        if(args.length == 3){
            if(args[1].equalsIgnoreCase("color")){
                for(String a : argumentsColor){
                    if(a.toLowerCase().startsWith(args[2].toLowerCase())) // Utilisez args[2] ici
                        result.add(a);
                }
                return result;
            }
        }

        return null;
    }
}
