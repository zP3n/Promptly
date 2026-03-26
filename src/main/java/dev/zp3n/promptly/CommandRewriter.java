package dev.zp3n.promptly;

import java.util.HashMap;
import java.util.Map;

public class CommandRewriter {

    private static final Map<String, String> commandMap = new HashMap<>();

    static {
        // ここに変換ルールを書く
        commandMap.put("/da", "/warp da");
        commandMap.put("/wiz", "/warp wizard_tower");
        commandMap.put("/des", "/warp desert");
        commandMap.put("/tra", "/warp trapper");
        commandMap.put("/gal", "/warp galatea");
        commandMap.put("/mw", "/warp murkwater");
        commandMap.put("/dw", "/warp dwarves");
        commandMap.put("/for", "/warp forge");
        commandMap.put("/crs", "/warp crystals");
        commandMap.put("/nuc", "/warp nucleus");
        commandMap.put("/ara", "/warp arachne");
        commandMap.put("/end", "/warp end");
        commandMap.put("/dra", "/warp drag");
        commandMap.put("/ku", "/warp kuudra");
        commandMap.put("/bay", "/warp bayou");

        commandMap.put("/sbc", "/skyblocker config");

        commandMap.put("/acc", "/accessorybag");
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