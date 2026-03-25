package dev.zp3n.promptly;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.client.MinecraftClient;

public class PromptlyClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {

		ClientSendMessageEvents.ALLOW_COMMAND.register((command) -> {

			String original = "/" + command;
			String rewritten = CommandRewriter.rewrite(original);

			if (!original.equals(rewritten)) {
				// 変換された場合のみ送信
				MinecraftClient.getInstance().player.networkHandler.sendCommand(
						rewritten.substring(1) // "/"を削除
				);
				return false; // 元コマンドは送らない
			}

			return true; // そのまま送る
		});
	}
}