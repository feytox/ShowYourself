package name.uwu.feytox.showmeurnick.client;

import eu.midnightdust.lib.config.MidnightConfig;

public class ShowMeUrNickConfig extends MidnightConfig {
    @Entry
    public static String format = "&6-= &7$NICK$ &6=-";

    @Entry(min=1.0f, max=32.0f)
    public static float minDistance = 3.0f;

    @Entry(min=1.0f, max=32.0f)
    public static float maxDistance = 16.0f;
}
