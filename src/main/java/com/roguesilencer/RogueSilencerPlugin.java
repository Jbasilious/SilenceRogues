package com.roguesilencer;

import com.google.common.annotations.VisibleForTesting;
import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Renderable;
import net.runelite.client.callback.Hooks;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.party.PartyService;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
        name = "Rogue Silencer"
)
public class RogueSilencerPlugin extends Plugin {
    @Inject
    private Client client;

    @Inject
    private RogueSilencerConfig config;

    @Inject
    private Hooks hooks;

    @Inject
    private PartyService partyService;

    private boolean hideText;
    private boolean hideRogues;

    private final Hooks.RenderableDrawListener drawListener = this::shouldDraw;

    @VisibleForTesting
    boolean shouldDraw(Renderable renderable, boolean drawingUI) {
        if (renderable instanceof NPC) {
            NPC npc = (NPC) renderable;
            if (npc.getId() == 6603) {
                return !(drawingUI ? hideText : hideRogues);
            }
        }
        return true;
    }

    @Override
    protected void startUp() throws Exception {
        updateConfig();
        hooks.registerRenderableDrawListener(drawListener);
    }

    @Override
    protected void shutDown() {
        hooks.unregisterRenderableDrawListener(drawListener);
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged e) {
        if (e.getGroup().equals(RogueSilencerConfig.GROUP)) {
            updateConfig();
        }
    }

    private void updateConfig() {
        hideText = config.hideText();
        hideRogues = config.hideRogues();
    }

    @Provides
    RogueSilencerConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(RogueSilencerConfig.class);
    }
}
