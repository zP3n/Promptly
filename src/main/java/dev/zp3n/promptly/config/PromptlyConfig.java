package dev.zp3n.promptly.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class PromptlyConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH =
            FabricLoader.getInstance().getConfigDir().resolve("promptly.json");

    public static Map<String, String> commands = new HashMap<>();

    public static void load() {
        try {
            if (!Files.exists(PATH)) {
                createDefault();
            }

            try (Reader reader = Files.newBufferedReader(PATH)) {
                Type type = new TypeToken<Map<String, String>>(){}.getType();
                commands = GSON.fromJson(reader, type);

                if (commands == null) {
                    commands = new HashMap<>();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try (Writer writer = Files.newBufferedWriter(PATH)) {
            GSON.toJson(commands, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDefault() {
        commands.put("end", "warp end");
        commands.put("dw", "warp dwarves");
        save();
    }
}