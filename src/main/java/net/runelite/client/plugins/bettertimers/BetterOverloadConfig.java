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
		keyName = "vengeMode",
		name = "Display mode",
		description = "Configures how the vengeance timer is displayed.",
		position = 2
	)
	default BetterOverloadMode vengeMode()
	{
		return BetterOverloadMode.SECONDS;
	}

}