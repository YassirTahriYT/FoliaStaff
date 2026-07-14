package me.Yassir.foliaStaff

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FoliaStaffChatToggle(private val plugin: FoliaStaff) : CommandExecutor{
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
        if (sender.uniqueId in plugin.staffChatToggled) {
            plugin.staffChatToggled.remove(sender.uniqueId)
            sender.sendMessage(MiniMessage.miniMessage().deserialize(plugin.config.getString("messages.staffChatToggledOff")?: ""))
        } else {
            plugin.staffChatToggled.add(sender.uniqueId)
            sender.sendMessage(MiniMessage.miniMessage().deserialize(plugin.config.getString("messages.staffChatToggledOn")?: ""))
        }
        return true
    }
}