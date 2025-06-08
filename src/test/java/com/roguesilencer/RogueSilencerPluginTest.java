package com.roguesilencer;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class RogueSilencerPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(RogueSilencerPlugin.class);
		RuneLite.main(args);
	}
}