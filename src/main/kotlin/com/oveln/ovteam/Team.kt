package com.oveln.ovteam

import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.scoreboard.Scoreboard

class Team(teamFileData: ConfigurationSection, scoreboard: Scoreboard, playerdata: MutableMap<String, Int>, id: Int) {
    var name = teamFileData.getString("name")?:""
    private var colorCode = teamFileData.getString("color")?.get(0)
    private var players: MutableList<String> = teamFileData.getStringList("players")
    val size: Int
        get() = players.size

    private var lastTime = System.currentTimeMillis()
    private var activePlayerNum = 0
    private var team: org.bukkit.scoreboard.Team

    init{
        team = try {
            scoreboard.registerNewTeam(name)
        } catch (_: java.lang.Exception) {
            scoreboard.getTeam(name)!!
        }
        team.prefix = name
        teamFileData.getString("prefix")?.let {
            team.prefix = it.replace("%teamname%", name)
        }
        ChatColor.getByChar(colorCode?:Char(0))?.let {
            team.color = it
        }
        players.forEach() {
            Main.Instance.logger.info("$it 被加入了 $name")
            team.addEntry(it)
            playerdata[it] = id
        }
    }
    fun activePlayer():Int {
        System.currentTimeMillis().let{
            if (it-lastTime> 1) {
                activePlayerNum = 0
                Main.Instance.server.onlinePlayers.forEach() {
                    if (it.gameMode == GameMode.SURVIVAL && players.contains(it.name)) activePlayerNum++
                }
                lastTime = it
            }
        }
        return activePlayerNum
    }
    fun unregister() {
        team.unregister()
    }
}