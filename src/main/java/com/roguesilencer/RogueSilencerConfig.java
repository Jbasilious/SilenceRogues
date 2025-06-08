package com.roguesilencer;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(RogueSilencerConfig.GROUP)
public interface RogueSilencerConfig extends Config {
    String GROUP = "rogueSilencer";

    @ConfigItem(
            position = 1,
            keyName = "hideText",
            name = "Hide Rogues Text",
            description = "Configures whether or not Rogues Text is hidden"
    )
    default boolean hideText() {
        return true;
    }


    @ConfigItem(
            position = 2,
            keyName = "hideRogues",
            name = "Hide Rogues",
            description = "Configures whether or not Rogues are hidden"
    )
    default boolean hideRogues() {
        return false;
    }
}