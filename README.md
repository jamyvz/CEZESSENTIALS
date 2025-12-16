![Java](https://img.shields.io/badge/Java-21+-orange)
![Paper](https://img.shields.io/badge/Paper-1.21.x-blue)
![License](https://img.shields.io/github/license/jamyvz/CEZESSENTIALS)
![GitHub Release](https://img.shields.io/github/v/release/jamyvz/CEZESSENTIALS)

[![Hangar](https://img.shields.io/badge/Hangar-CEZESSENTIALS-blue)](https://hangar.papermc.io/clippyclip/CEZESSENTIALS)
[![Modrinth](https://img.shields.io/badge/Modrinth-CEZESSENTIALS-green)](https://modrinth.com/plugin/cezessentials)

CEZESSENTIALS

CEZESSENTIALS is a clean, modular Essentials-style plugin for Paper servers.
It is built with modern Java practices and designed to be easy to extend.

Minecraft: Paper 1.21.x
Java: 21
Author: clipson
Version: 1.0.0

FEATURES
- Homes system
- Warps system
- Spawn system
- Teleport requests (TPA)
- Reloadable config and messages
- Dynamic help command
- Adventure + MiniMessage support
- Modular architecture (no static managers)

INSTALLATION
1. Download CEZESSENTIALS-1.0.0.jar
2. Place it in the server plugins folder
3. Start the server
4. Stop the server to edit config files
5. Start the server again

RELOADING
You can reload CEZESSENTIALS safely without restarting the server.

Command:
/cezreload

Reloads:
- config.yml
- message prefix
- all message strings

Does NOT reload:
- homes
- warps
- stored data

REQUIREMENTS
- Paper or Paper-based fork
- Java 21+
- Minecraft 1.21.x

LICENSE
Private use. You may modify this plugin for your own server.
