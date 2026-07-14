package me.Yassir.foliaStaff

import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.util.UUID

class FoliaStaff : JavaPlugin() {
    val mainCommand = FoliaStaffMainCommand(this)
    val staffChatToggled = mutableSetOf<UUID>()
    override fun onEnable() {
        saveDefaultConfig()
        logger.info("FoliaStaff Enabled")
        getCommand("foliastaff")?.apply {
            setExecutor(mainCommand)
            tabCompleter = mainCommand
        }
        getCommand("staffchat")?.setExecutor(FoliaStaffChat(this))
        getCommand("togglestaffchat")?.setExecutor(FoliaStaffChatToggle(this))
        server.pluginManager.registerEvents(FoliaStaffChatListener(this), this)
        logger.info("Listeners Registered")
    }

    override fun onDisable() {
        logger.info("FoliaStaff Disabled")
    }

}
