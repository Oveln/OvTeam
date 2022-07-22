package com.oveln.ovteam

import com.oveln.ovteam.papi.PAPI
import com.oveln.ovteam.utils.CU.colorful
import org.bukkit.plugin.java.JavaPlugin


class Main: JavaPlugin() {
    companion object {
        lateinit var Instance : Main
    }
    override fun onEnable() {
        saveDefaultConfig()
        Instance = this
        Teams.load(config)
        PAPI.register()
        logger.info("${description.name} ${description.version} 启动成功   作者${description.authors}".colorful())
    }

    override fun onDisable() {
        Teams.unload()
        logger.info("${description.name} ${description.version} 关闭成功   作者${description.authors}".colorful())
    }
}