package me.ritomg.raptor.client.misc;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import me.ritomg.raptor.RaptorClient;

public class DiscordService {

    private static String discordID = "833714199124246588";
    private static DiscordRichPresence richPresence = new DiscordRichPresence();
    private static DiscordRPC discordRPC = DiscordRPC.INSTANCE;

    public static void startRPC() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        discordRPC.Discord_Initialize(discordID, handlers, true, null);
        richPresence.startTimestamp = System.currentTimeMillis() / 1000L;
        richPresence.details = "discord.gg/3Sq3zCy3aN";
        richPresence.largeImageKey = "big";
        richPresence.largeImageText = RaptorClient.NAME + " v: " + RaptorClient.VERSION;
        richPresence.smallImageKey = "me";
        richPresence.smallImageText = "RitomG";
        richPresence.state = null;
        discordRPC.Discord_UpdatePresence(richPresence);
    }

    public static void stopRPC() {
        discordRPC.Discord_Shutdown();
        discordRPC.Discord_ClearPresence();
    }
}
