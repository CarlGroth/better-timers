package net.runelite.client.plugins.bettertimers;

public enum BetterOverloadMode {
	TICKS,
	SECONDS,
	DECIMALS,
	REPEATING_TICKS;

	public String format(int ticks) {
		switch (this) {
			case TICKS:
				return String.valueOf(ticks);
			case SECONDS:
				return BetterOverloadPlugin.to_mmss(ticks);
			case DECIMALS:
				return BetterOverloadPlugin.to_mmss_precise_short(ticks);
			case REPEATING_TICKS:
				return String.valueOf(ticks % 25);
			default:
				return BetterOverloadPlugin.to_mmss(ticks);
		}
	}
}
