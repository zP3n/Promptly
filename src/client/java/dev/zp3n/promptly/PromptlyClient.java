package dev.zp3n.promptly;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import dev.zp3n.promptly.config.PromptlyConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;

public class PromptlyClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		PromptlyConfig.load();
//		Minecraft.getInstance().options.toggleAttack().set(false);
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
			dispatcher.register(ClientCommands.literal("hbc").executes(context -> {
				Minecraft mc = Minecraft.getInstance();
				if (mc.options.toggleAttack().get()) {
					mc.player.sendSystemMessage(Component.literal("§6[Promptly]§r Attack/Destroy : §cHold§r"));
					mc.player.playSound(SoundEvents.ARROW_HIT_PLAYER, 0.5F, 0.8F);
					mc.options.toggleAttack().set(false);
				} else {
					mc.player.sendSystemMessage(Component.literal("§6[Promptly]§r Attack/Destroy : §aToggle§r"));
					mc.player.playSound(SoundEvents.ARROW_HIT_PLAYER, 0.5F, 1.2F);
					mc.options.toggleAttack().set(true);
				}
				mc.options.save();
				return 1;
			}));
			dispatcher.register(ClientCommands.literal("promptlyReload").executes(context -> {
				PromptlyConfig.load();
				for (var entry : PromptlyConfig.commands.entrySet()) {
					String key = entry.getKey();
					String value = entry.getValue();
					PromptlyCmdRgstr.regist(key,value);
				}
				Minecraft mc = Minecraft.getInstance();
				mc.player.sendSystemMessage(Component.literal("§6[Promptly]§r Config Reloaded!"));
				mc.player.playSound(SoundEvents.ARROW_HIT_PLAYER, 0.5F, 1.6F);
				return 1;
			}));
		});
		PromptlyConfig.load();
		for (var entry : PromptlyConfig.commands.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			PromptlyCmdRgstr.regist(key,value);
		}
	}
}