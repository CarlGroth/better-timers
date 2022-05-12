package com.bettertimers;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.infobox.InfoBox;

import javax.inject.Inject;
import java.awt.Color;

public class BetterOverloadInfoBox extends InfoBox
{

	private final BetterOverloadPlugin plugin;
	private Client client;
	private final BetterOverloadConfig config;

	@Inject
	public BetterOverloadInfoBox(Client client, BetterOverloadPlugin plugin, BetterOverloadConfig config)
	{
		super(null, plugin);
		this.plugin = plugin;
		this.client = client;
		this.config = config;
	}

	@Override
	public String getText()
	{
		String str;
		if (config.overloadMode() == BetterOverloadMode.TICKS)
		{
			str = String.valueOf(plugin.overloadInTicks);
		}
		else if (config.overloadMode() == BetterOverloadMode.DECIMALS)
		{
			str = BetterOverloadPlugin.to_mmss_precise_short(plugin.overloadInTicks);
		}
		else
		{
			str = BetterOverloadPlugin.to_mmss(plugin.overloadInTicks);
		}
		return str;
	}

	@Override
	public Color getTextColor()
	{
		if (plugin.overloadInTicks % 25 == 0 && config.brewTick())
		{
			return new Color(26, 204, 6);
		}
		else if (plugin.overloadInTicks < 25)
		{
			return Color.RED;
		}
		return Color.WHITE;
	}

	@Override
	public String getTooltip()
	{
		return "Overload";
	}
}
