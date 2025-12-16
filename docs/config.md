CEZESSENTIALS CONFIGURATION

CONFIG FILE
File: plugins/CEZESSENTIALS/config.yml


MESSAGE PREFIX
Example:

messages:
  prefix: "<gray>[<gold>CEZ</gold>]</gray> "

The prefix is automatically added to all plugin messages.


MESSAGES
All messages are configurable and use MiniMessage formatting.

Example:
messages:
  home-set: "<green>Your home has been saved!</green>"

Changes can be applied with:
/cezreload

No server restart required.


DATA STORAGE
CEZESSENTIALS stores data in YAML files.

homes.yml
Purpose: Player homes

warps.yml
Purpose: Global warps

config.yml
Purpose: Settings and messages

Note:
Data files are NOT reloaded by /cezreload.
