package ru.feytox.showmeurnick;

import net.fabricmc.api.ModInitializer;
import ru.feytox.showmeurnick.client.ShowMeUrNickConfig;

public class ShowMeUrNick implements ModInitializer {
    @Override
    public void onInitialize() {
        ShowMeUrNickConfig.init("showyourself", ShowMeUrNickConfig.class);
    }
}
