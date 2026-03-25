package dev.zp3n.promptly;

import java.util.HashMap;
import java.util.Map;

public class CommandRewriter {

    private static final Map<String, String> commandMap = new HashMap<>();

    static {
        // ここに変換ルールを書く
        commandMap.put("/end", "/warp end");
        commandMap.put("/spawn", "/warp spawn");

        // 必要ならどんどん追加
    }

    public static String rewrite(String input) {
        // 完全一致の場合
        if (commandMap.containsKey(input)) {
            return commandMap.get(input);
        }

        // 引数付き対応（例: /home 1 → /warp home 1）
        for (String key : commandMap.keySet()) {
            if (input.startsWith(key + " ")) {
                return commandMap.get(key) + input.substring(key.length());
            }
        }

        return input; // 変換なし
    }
}