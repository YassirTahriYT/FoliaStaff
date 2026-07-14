package me.Yassir.foliaStaff

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player


class FoliaStaffChat(private val plugin: FoliaStaff) : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(plugin.config.getString("messages.consoleNoAccess")?: ""))
            return true
        }
        if (!sender.hasPermission("foliastaff.chat")) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(plugin.config.getString("messages.noPermission")?: ""))
            return true
        }
        if (args.isEmpty()) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize("<red>Usage: /staffchat <message>"))
            return true
        }
        val staffMessage = args.joinToString(" ")
        for (player in plugin.server.onlinePlayers) {
            if (player.hasPermission("foliastaff.chat")) {
                player.sendMessage(MiniMessage.miniMessage().deserialize(plugin.config.getString("messages.staffChat")?.replace("%player%", sender.name)?.replace("%message%", staffMessage)?: ""))
            }
        }
        return true
    }
}