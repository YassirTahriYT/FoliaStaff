package me.Yassir.foliaStaff

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.command.TabExecutor

class FoliaStaffMainCommand(private val plugin: FoliaStaff) : TabExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage(MiniMessage.miniMessage().deserialize(plugin.config.getString("messages.version")?.replace("%version%", plugin.description.version)?: ""))
            return true
        }
        when (args[0].lowercase()) {
            "reload" -> {
                if (!sender.hasPermission("foliastaff.op")) {
                    sender.sendMessage(MiniMessage.miniMessage().deserialize(plugin.config.getString("messages.noPermission")?: ""))
                    return true
                }
                plugin.reloadConfig()
                sender.sendMessage(MiniMessage.miniMessage().deserialize(plugin.config.getString("messages.reload")?: ""))
            }
            "version" -> {
                sender.sendMessage(MiniMessage.miniMessage().deserialize(plugin.config.getString("messages.version")?.replace("%version%", plugin.description.version)?: ""))
            }
            else -> {
                sender.sendMessage(MiniMessage.miniMessage().deserialize(plugin.config.getString("messages.version")?.replace("%version%", plugin.description.version)?: ""))
            }
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String?>? {
        if (args.size == 1) {
            val tabCompletions = mutableListOf("reload", "version")
            return tabCompletions.filter({it.startsWith(args[0], ignoreCase = true)}).toMutableList()
        }
        return mutableListOf()
    }
}

