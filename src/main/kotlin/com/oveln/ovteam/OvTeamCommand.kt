package com.oveln.ovteam

import com.oveln.ovteam.utils.CU.colorful
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class OvTeamCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<out String>?): Boolean {
        args?.let { it ->
            if (it.size == 1) {
                if (it[0] == "reload") {
                    Teams.unload()
                    Main.Instance.reloadConfig()
                    Teams.load(Main.Instance.config)
                    sender.sendMessage("&2 OvTeam重载成功".colorful())
                    return true
                }
            }
        }
        return false
    }
}