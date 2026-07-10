package dev.zp3n.promptly;

import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.Minecraft;

public class PromptlyCmdRgstr {
    public static void regist(String input, String output) {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(ClientCommands.literal(input).executes(context -> {
                Minecraft mc = Minecraft.getInstance();
                mc.player.connection.sendCommand(output);
                return 1;
            }));
        });
    }
}