package net.runelite.client.plugins.bettertimers;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("beanLoad")
public interface BetterOverloadConfig extends Config
{

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
}