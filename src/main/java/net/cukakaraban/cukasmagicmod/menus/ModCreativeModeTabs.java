package net.cukakaraban.cukasmagicmod.menus;

import net.cukakaraban.cukasmagicmod.CukasMagicMod;
import net.cukakaraban.cukasmagicmod.items.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModCreativeModeTabs {
    public static final ItemGroup MAGIC_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(CukasMagicMod.MOD_ID, "magictab"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.magictab"))
                    .icon(() ->new ItemStack(Items.NETHER_STAR)).entries((displayContext, entries) -> {
                        //Add blocks to the tab WIP
                entries.add(ModItems.TESTITEM);
                    })



                    .build());

    public static void registerItemGroups(){
        CukasMagicMod.LOGGER.info("Registering Mod Tabs for " + CukasMagicMod.MOD_ID);
    }

}
