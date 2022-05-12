package com.bettertimers;

import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;

@SuppressWarnings("checkstyle:RegexpSinglelineJava")
@Slf4j
@PluginDescriptor(
	name = "Better Overload",
	description = "Improved overload timer, to account for world lag.",
	tags = {"better", "overload", "ovl", "betterovl", "better overload"}
)
public class BetterOverloadPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private BetterOverloadConfig config;

	@Inject
	private InfoBoxManager infoBoxManager;

	boolean overloaded;
	private BetterOverloadInfoBox infoBox;
	int overloadInTicks = -1;

	@Override
	protected void startUp() throws Exception
	{
	}

	@Override
	protected void shutDown() throws Exception
	{
		overloaded = false;
		overloadInTicks = -1;
		if (infoBox != null)
		{
			infoBoxManager.removeInfoBox(infoBox);
			infoBox = null;
		}
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (!overloaded)
		{
			return;
		}
		if (overloadInTicks <= 0)
		{
			ovlRunOut();
		}
		overloadInTicks--;
	}

	public void drinkOvl()
	{
		overloaded = true;
		overloadInTicks = 500;
		if (infoBox == null)
		{
			infoBox = new BetterOverloadInfoBox(client, this, config);
			infoBoxManager.addInfoBox(infoBox);
		}
	}

	public void ovlRunOut()
	{
		overloaded = false;
		infoBoxManager.removeInfoBox(infoBox);
		infoBox = null;
	}

	@Subscribe
	public void onChatMessage(ChatMessage event)
	{
		final String message = event.getMessage();
		if (message.startsWith("You drink some of your") && message.contains("overload"))
		{
			drinkOvl();
		}
		if (message.equalsIgnoreCase("The effects of overload have worn off, and you feel normal again."))
		{
			ovlRunOut();
		}
	}

	@Provides
	BetterOverloadConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BetterOverloadConfig.class);
	}

	public static String to_mmss(int ticks)
	{
		int m = ticks / 100;
		int s = (ticks - m * 100) * 6 / 10;
		return m + (s < 10 ? ":0" : ":") + s;
	}

	public static String to_mmss_precise_short(int ticks)
	{
		int min = ticks / 100;
		int tmp = (ticks - min * 100) * 6;
		int sec = tmp / 10;
		int sec_tenth = tmp - sec * 10;
		return new StringBuilder().append(min).append(sec < 10 ? ":0" : ":").append(sec).append(".")
			.append(sec_tenth).toString();
	}
}