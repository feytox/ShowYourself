package ru.feytox.showmeurnick.client;

import eu.midnightdust.lib.config.MidnightConfig;

public class ShowMeUrNickConfig extends MidnightConfig {
    @Entry
    public static String format = "&6-= &f$NICK$ &6=-";

    @Entry(min=1.0f, max=64.0f)
    public static float minDistance = 3.0f;

    @Entry(min=1.0f, max=64.0f)
    public static float maxDistance = 32.0f;
}
