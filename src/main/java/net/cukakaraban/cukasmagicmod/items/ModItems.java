package net.cukakaraban.cukasmagicmod.items;

import net.cukakaraban.cukasmagicmod.CukasMagicMod;
import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.component.Component;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItems {
    public static final Item TESTITEM = registerItem("testitem", new Item(new Item.Settings()){
        @Override
        public Text getName(ItemStack stack) {
            return Text.literal("Cuka is the best").setStyle(Style.EMPTY.withFont(Identifier.of("minecraft", "illageralt")));
        }
    });


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(CukasMagicMod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        CukasMagicMod.LOGGER.info("Registering Mod Items for " + CukasMagicMod.MOD_ID);
    }
}
