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


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(argumentsCity.isEmpty()){
            argumentsCity.add("start");
            argumentsCity.add("stop");
            argumentsCity.add("team");
            argumentsCity.add("poudre");
            argumentsCity.add("entity");
        }
        if(argumentsTeams.isEmpty()){
            argumentsTeams.add("create");
            argumentsTeams.add("add");
            argumentsTeams.add("remove");
            argumentsTeams.add("list");
            argumentsTeams.add("disband");
        }
        if(argumentsPoudre.isEmpty()){
            argumentsPoudre.add("give");
            argumentsPoudre.add("remove");
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
            }
        }

        return null;
    }
}
