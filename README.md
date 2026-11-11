# Minecraft Smart Villager Plugin

Smart Villager is a Paper Minecraft plugin written in Java that adds a marked custom villager with natural AI movement, friendly default behavior, self-defense against attackers, a librarian profession, a taiga villager type, and a reusable Smart Villager spawn egg.

## Install

1. Build the plugin with Java 21 and Maven:
   ```bash
   mvn package
   ```
2. Copy `target/plugin.jar` to the server's `plugins` directory.
3. Start or restart a Paper 1.21.x server.
4. Ensure the player has the `smartvillager.use` permission. This permission defaults to server operators.

## Use

Give a player a reusable spawn egg:
```text
/smartvillager egg
```

The command can also be used without an argument:
```text
/smartvillager
```

Use the egg on a block to spawn a Smart Villager. The egg is identified by custom item data, and using it does not consume the item.

Spawn a Smart Villager directly at the player's location:
```text
/smartvillager spawn
```

Smart Villagers retain normal villager AI and movement. They are marked internally so the plugin can identify them, display the name “Smart Villager,” assign the librarian profession and taiga variant, and make them persistent. When a Smart Villager is damaged by a living entity, it targets that attacker for self-defense.

---

More Minecraft plugin projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/8c63aefd-6464-4036-9cac-80656f74c587?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=8c63aefd-6464-4036-9cac-80656f74c587
