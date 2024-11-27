# Simple PVP

## Translations
- [Russian](README.md)
- [English](README.en.md)

A plugin that allows configuring PVP logic on the server.

## **Features**
- **No unnecessary**: The plugin is designed to be understandable for any player. It does not include burdensome features.
- **PVP Timer**: Sets a timer for PVP mode during which players cannot exit the game or use certain commands.
- **PVP by gamemodes**: Configure permissions to disable PVP in specific gamemodes.
- **Easy to configure**: The configuration does not contain excessive information and is easy to set up.

## **Permissions**
- Allows PVP in a specific game mode: `simplepvp.gamemode.<gamemode>`
- Disables the PVP timer, along with death upon exiting and blocking commands: `simplepvp.timer.bypass`

## config.yml
```yaml
# PVP is regulated by the permission `simplepvp.gamemode.<gamemode>`
# For example, players with the permission `simplepvp.gamemode.creative` can hit players while in creative mode.

# Start the PVP timer when attacking another player (not enabled for players with the permission `simplepvp.timer.bypass`).
pvp-timer: true

# Duration of the PVP mode
pvp-time: 10

# Kill the player when exiting the game during PVP mode
exit-pvp-kill: true

# These commands cannot be used during PVP mode
blocked-cmds:
  - '/spawn'
  - '/tp'
  - '/tpo'

# -=-=-=-=-=- MESSAGES -=-=-=-=-=-
# For color, use the symbol '§'
# If you want to disable any message, make it empty.

# Displayed in ActionBar during PVP mode
on-pvp-msg: '§cYou are in §lPVP§r§c. §e{time}§c seconds remaining until exit from §lPVP'

# Displayed when entering a blocked command during PVP mode
command-on-pvp-msg: '§f[§4§lPVP§r§f] §cYou cannot execute this command during PVP'

# Broadcast to all players on the server if a player exits during PVP
exit-on-pvp-msg: '§f[§4§lPVP§r§f] §e{player} exited during §cPVP§e and was punished!'

# Displayed in ActionBar when the PVP mode ends
end-pvp-msg: '§aYou exited from §lPVP§r§a mode'

# Displayed when a player does not have permission for PVP in their current gamemode
no-permission-msg: '§cYou do not have permission for PVP in this gamemode.'
```
