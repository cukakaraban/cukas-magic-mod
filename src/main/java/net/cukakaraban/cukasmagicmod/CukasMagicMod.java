package net.cukakaraban.cukasmagicmod;

import net.cukakaraban.cukasmagicmod.items.ModItems;
import net.cukakaraban.cukasmagicmod.menus.ModCreativeModeTabs;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CukasMagicMod implements ModInitializer {
	public static final String MOD_ID = "cukas-magic-mod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModCreativeModeTabs.registerItemGroups();
		LOGGER.info("Hello Fabric world!");
	}
}