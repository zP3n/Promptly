package dev.zp3n.promptly;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import dev.zp3n.promptly.config.PromptlyConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;

public class PromptlyClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		PromptlyConfig.load();
//		Minecraft.getInstance().options.toggleAttack().set(false);

		ClientSendMessageEvents.ALLOW_COMMAND.register((command) -> {

			PromptlyConfig.load();
			String original = "/" + command;
			if(command.equals("holdbreakclick") || command.equals("hbc")){
				Minecraft mc = Minecraft.getInstance();
				if(mc.options.toggleAttack().get()){
					mc.player.sendSystemMessage(Component.literal("§6[Promptly]§r Attack/Destroy :  §cHold§r"));
					mc.player.playSound(SoundEvents.ARROW_HIT_PLAYER, 0.5F, 0.8F);
					mc.options.toggleAttack().set(false);
				}else{
					mc.player.sendSystemMessage(Component.literal("§6[Promptly]§r Attack/Destroy : §aToggle§r"));
					mc.player.playSound(SoundEvents.ARROW_HIT_PLAYER, 0.5F, 1.2F);
					mc.options.toggleAttack().set(true);
				}
				mc.options.save();
				return false;
			}

			String rewritten = CommandRewriter.rewrite(original);

			if (!original.equals(rewritten)) {
				Minecraft.getInstance().player.connection.sendCommand(
						rewritten.substring(1)
				);
				return false;
			}

			return true;
		});
	}
}