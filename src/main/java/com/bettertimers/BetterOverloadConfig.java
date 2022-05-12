package com.bettertimers;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("beanLoad")
public interface BetterOverloadConfig extends Config
{

	@ConfigItem(
		keyName = "brewTick",
		name = "Show brew tick",
		description = "Configures whether or not the overload text shows up green on the tick that u can brew without losing stats."
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


}