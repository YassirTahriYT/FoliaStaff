package me.Yassir.foliaStaff

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class FoliaStaffChatListener(private val plugin: FoliaStaff) : Listener {
    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        val player = event.player
        if (player.uniqueId !in plugin.staffChatToggled) {
            return
        }
        event.isCancelled = true
        for (online in plugin.server.onlinePlayers) {
            if (online.hasPermission("foliastaff.chat")) {
                online.sendMessage(MiniMessage.miniMessage().deserialize(plugin.config.getString("messages.staffChat")?.replace("%player%", player.name)?.replace("%message%", event.message)?: ""))
            }
        }
    }
}