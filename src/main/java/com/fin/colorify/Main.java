package com.fin.colorify;

import com.fin.colorify.config.Config;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
public class Main implements ClientModInitializer {

	public static Config config;

	@Override
	public void onInitializeClient() {

		AutoConfig.register(Config.class, GsonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(Config.class).getConfig();

	}
}
