package com.oveln.ovteam.papi

import com.oveln.ovteam.Main
import com.oveln.ovteam.Teams
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.OfflinePlayer
import org.bukkit.entity.Player

object PAPI: PlaceholderExpansion() {
    override fun canRegister(): Boolean {
        return true
    }

    override fun getIdentifier(): String {
        return "ovteam"
    }

    override fun getAuthor(): String {
        return "Oveln"
    }

    override fun getVersion(): String {
        return Main.Instance.description.version
    }

    override fun onRequest(player: OfflinePlayer?, params: String): String? {
        if (player == null || player !is Player) return "ERROR"
        when(params) {
            "name" -> {
                return Teams.whatTeam(player.name)?.name
            }
            "active" -> {
                return Teams.whatTeam(player.name)?.activePlayer().toString()
            }
            "total" -> {
                return Teams.whatTeam(player.name)?.size.toString()
            }
        }
        return "ERROR"
    }

}