package net.runelite.client.plugins.bettertimers;

import java.awt.Color;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("beanLoad")
public interface BetterOverloadConfig extends Config
{
	final Color BREW_TICK_COLOR = new Color(26, 204, 6);
 
	@ConfigItem(
		keyName = "brewTick",
		name = "Show brew tick",
		description = "Shows the timer in green on overload restore tick"
	)
	default boolean brewTick()
	{
		return true;
	}

	@ConfigItem(
		keyName = "overloadMode",
		name = "Display mode",
		description = "Configures how the overload timer is displayed.",
		position = 2
	)
	default BetterOverloadMode overloadMode()
	{
		return BetterOverloadMode.SECONDS;
	}

	@ConfigItem(
			keyName = "brewWarningTicks",
			name = "Brew Warning Ticks",
			description = "Shows the timer in yellow this many ticks ahead of the overload restore tick. Set to 0 to disable.",
			position = 3
	)
	default int brewWarningTicks()
	{
		return 0;
	}

	@ConfigItem(
		keyName = "enableSalt",
		name = "Enable salt timer",
		description = "Enables the smelly salt timer, with the overload settings",
		position = 4
	)
	default boolean enableSalt()
	{
		return true;
	}

	@ConfigItem(
			keyName = "enableMenaphiteRemedy",
			name = "Enable menaphite remedy timer",
			description = "Enables the menaphite remedy timer, with the overload settings",
			position = 5
	)
	default boolean enableMenaphiteRemedy()
	{
		return true;
	}

	default Color getTextColor(int ticks)
	{
		if (ticks % 25 == 0 && this.brewTick())
		{
			return BREW_TICK_COLOR;
		}
		else if (ticks < 25)
		{
			return Color.RED;
		}
		else if (ticks % 25 < this.brewWarningTicks()) {
			return Color.YELLOW;
		}
		return Color.WHITE;
	}
}