package com.github.telvarost.misctweaks.events.init;

import com.github.telvarost.misctweaks.Config;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()) {
            if (Config.config.enableDoubleStoneSlabCraftingRecipe) {
                CraftingRegistry.addShapedRecipe(new ItemStack(Block.DOUBLE_SLAB.asItem(), 1), "X", "X", 'X', Block.SLAB.asItem());
            }
        }

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())
        {
            if (Config.config.enableDoubleStoneSlabCraftingRecipe) {
                CraftingRegistry.addShapelessRecipe(new ItemStack(Block.SLAB.asItem(), 2), Block.DOUBLE_SLAB.asItem());
            }
        }
    }
}