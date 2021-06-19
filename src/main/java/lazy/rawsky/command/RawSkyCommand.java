package lazy.rawsky.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import lazy.rawsky.RawSky;
import lazy.rawsky.module.ModuleConfigs;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;

public class RawSkyCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        LiteralArgumentBuilder<CommandSource> rawSkyCommand = Commands.literal("rawsky")
                .then(
                        Commands.literal("reloadConfigs")
                        .executes((context -> {
                            RawSky.LOGGER.info("Reloading module configs...");
                            ModuleConfigs.reload();
                            RawSky.LOGGER.info("Done.");
                            return 1;
                        }))
                );
        dispatcher.register(rawSkyCommand);
    }
}
