package com.example;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.CommandExecuted;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@PluginDescriptor(
	name = "8ball"
)
public class eightBallPlugin extends Plugin
{
	List<String> answerList = Arrays.asList("Definitely.", "Nope.", "Yes.", "Nah.", "Ask me later.", "All I can say is yes.", "Pretty sure it's a yes.", "Pretty sure it's a no.", "I can't answer that.", "That is never going to happen.", "You are legit asking this to an rng plugin?", "Maybe.", "This will 100% happen.", "Surely.", "The rng Gods will smile to you.", "Bruh, of course not lmaoooo");
	List<String> skillList = Arrays.asList("Attack", "Strength", "Defense", "Ranged", "Prayer", "Magic", "Runecraft, let me see you   s u f f e r", "Hitpoints", "Crafting", "Mining", "Smithing", "Fishing", "Cooking", "Firemaking", "Woodcutting");
	@Inject
	private Client client;

	@Inject
	private eightBallConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
//	public void onGameStateChanged(GameStateChanged gameStateChanged)
//	{
//		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
//		{
//			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
//		}
//	}

	public void onCommandExecuted(CommandExecuted commandExecuted){
		if (commandExecuted.getCommand().equals("8ball")) {
			Random rand = new Random();
			if (commandExecuted.getArguments().length != 0 && commandExecuted.getArguments()[0].equals("skill"))
			{
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "The 8ball says: " + skillList.get(rand.nextInt(skillList.size())), null);
			}
			else if (WorldPoint.fromLocalInstance(client, client.getLocalPlayer().getLocalLocation()).getRegionID() == 0)
			{
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "The 8ball says: Wtf dude how are you even here-", null);
			}
			else if (WorldPoint.fromLocalInstance(client, client.getLocalPlayer().getLocalLocation()).getRegionID() == 12597)
			{
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "The 8ball says: Drink the forbidden berry fruit punch, I double dare ya.", null);
			}
			else
			{
				client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "The 8ball says: " + answerList.get(rand.nextInt(answerList.size())), null);
			}
		}
	}


	@Provides
	eightBallConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(eightBallConfig.class);
	}
}