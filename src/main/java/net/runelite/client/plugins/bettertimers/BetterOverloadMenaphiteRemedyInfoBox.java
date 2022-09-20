package net.runelite.client.plugins.bettertimers;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.infobox.InfoBox;

import javax.inject.Inject;
import java.awt.Color;
import net.runelite.client.ui.overlay.infobox.InfoBoxPriority;

public class BetterOverloadMenaphiteRemedyInfoBox extends InfoBox
{
    private final BetterOverloadPlugin plugin;
    private final BetterOverloadConfig config;

    @Inject
    public BetterOverloadMenaphiteRemedyInfoBox(Client client, BetterOverloadPlugin plugin, BetterOverloadConfig config)
    {
        super(null, plugin);
        this.plugin = plugin;
        this.config = config;
        setPriority(InfoBoxPriority.MED);
    }

    @Override
    public String getText()
    {
        String str;
        if (config.overloadMode() == BetterOverloadMode.TICKS)
        {
            str = String.valueOf(plugin.menaphiteRemedyInTicks);
        }
        else if (config.overloadMode() == BetterOverloadMode.DECIMALS)
        {
            str = BetterOverloadPlugin.to_mmss_precise_short(plugin.menaphiteRemedyInTicks);
        }
        else
        {
            str = BetterOverloadPlugin.to_mmss(plugin.menaphiteRemedyInTicks);
        }
        return str;
    }

    @Override
    public Color getTextColor()
    {
        if (plugin.menaphiteRemedyInTicks % 25 == 0 && config.brewTick())
        {
            return new Color(26, 204, 6);
        }
        else if (plugin.menaphiteRemedyInTicks < 25)
        {
            return Color.RED;
        }
        else if (plugin.menaphiteRemedyInTicks % 25 < config.brewWarningTicks()) {
            return Color.YELLOW;
        }
        return Color.WHITE;
    }

    @Override
    public String getTooltip()
    {
        return "Menaphite Remedy";
    }
}
