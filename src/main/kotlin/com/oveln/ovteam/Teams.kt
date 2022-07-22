package com.oveln.ovteam

import org.bukkit.configuration.ConfigurationSection

object Teams {
    var teams: MutableList<Team> = mutableListOf()
    private val playerData: MutableMap<String, Int> = mutableMapOf()
    private var scoreboard = Main.Instance.server.scoreboardManager!!.mainScoreboard
    fun load(file: ConfigurationSection) {
        val num = file.getInt("teamNum")
        for (i in (1..num)) {
            file.getConfigurationSection("teams")?.getConfigurationSection(i.toString())?.let {
                teams.add(Team(it, scoreboard, playerData, i-1))
            }
        }
        Main.Instance.logger.info("读取了${num}个队伍数据")
    }
    fun whatTeam(playerName: String): Team? {
        playerData[playerName]?.let {
            return teams[it]
        }
        return null
    }
    fun unload() {
        teams.forEach() {it.unregister()}
        teams.clear()
    }
}