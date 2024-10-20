package net.runelite.client.plugins.bettertimers;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.infobox.InfoBox;

import javax.inject.Inject;
import java.awt.Color;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;

public class BetterOverloadSaltInfoBox extends InfoBox
{
	private final BetterOverloadPlugin plugin;
	private final BetterOverloadConfig config;

	@Inject
	public BetterOverloadSaltInfoBox(Client client, BetterOverloadPlugin plugin, BetterOverloadConfig config)
	{
		super(null, plugin);
		this.plugin = plugin;
		this.config = config;
		setPriority(InfoBoxPriority.MED);
	}

	@Override
	public String getText()
	{
		return config.overloadMode().format(plugin.saltInTicks);
	}

	@Override
	public Color getTextColor()
	{
		return config.getTextColor(plugin.saltInTicks);
	}

	@Override
	public String getTooltip()
	{
		return "Smelling salt";
	}
}
