CEZESSENTIALS DEVELOPMENT GUIDE

ARCHITECTURE RULES
- No static managers
- No god classes
- Constructor-based dependency injection
- One feature per package
- Commands must not access config directly


ADDING A NEW FEATURE
1. Create a new package under features/
2. Add a manager class for logic and storage
3. Add command classes under commands/
4. Inject dependencies via CommandRegistrar
5. Add messages to config.yml
6. Register commands in plugin.yml


MESSAGE SYSTEM RULES
- All messages go through MessageService
- Prefix is never cached
- MessageService is not recreated on reload
- Reload updates config reference in-place


RELOAD SAFETY
/cezreload reloads:
- config.yml
- messages
- prefix

/cezreload does NOT:
- re-register commands
- reload stored data
