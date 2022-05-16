package net.runelite.client.plugins.bettertimers;

import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ItemID;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
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

	@Inject
	private ItemManager itemManager;

	boolean overloaded;
	private BetterOverloadInfoBox infoBox;
	int overloadInTicks = -1;

	private int ovlId = ItemID.OVERLOAD_4_20996;
	private int varbOvl = 5418;

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
		if (client.getVarbitValue(varbOvl) > 0)
		{
			ovlAdd();
		}
		if (client.getVarbitValue(varbOvl) == 0)
		{
			ovlReset();
		}
		overloadInTicks--;
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		if (event.getIndex() == 1428)
		{
			overloadInTicks = client.getVarbitValue(varbOvl) * 25;
			ovlAdd(); //Makes infobox persist after log out
		}
	}

	@Provides
	BetterOverloadConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BetterOverloadConfig.class);
	}

	public void ovlAdd()
	{
		overloaded = true;
		if (infoBox == null)
		{
			infoBox = new BetterOverloadInfoBox(client, this, config);
			infoBox.setImage(itemManager.getImage(ovlId));
			infoBoxManager.addInfoBox(infoBox);
		}
	}

	public void ovlReset()
	{
		overloaded = false;
		infoBoxManager.removeInfoBox(infoBox);
		infoBox = null;
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
		return min + (sec < 10 ? ":0" : ":") + sec + "." +
			sec_tenth;
	}
}