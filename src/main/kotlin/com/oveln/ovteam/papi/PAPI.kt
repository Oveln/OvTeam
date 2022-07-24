package com.oveln.ovteam.papi

import com.oveln.ovteam.Main
import com.oveln.ovteam.Teams
import com.oveln.ovteam.utils.CU.colorful
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

    override fun onRequest(player: OfflinePlayer?, params: String): String {
        if (player == null || player !is Player) return "&c ERROR".colorful()
        Teams.whatTeam(player.name)?.let {
            when(params) {
                "name" -> {
                    return it.name
                }
                "active" -> {
                    return it.activePlayer().toString()
                }
                "total" -> {
                    return it.size.toString()
                }
                "color" -> {
                    return it.colorCode.toString()
                }
                else -> {
                    return "&c No This Params".colorful()
                }
            }
        }
        return "&c ERROR".colorful()
    }

}