package com.github.telvarost.misctweaks.events.init;

import com.github.telvarost.misctweaks.Config;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeRegistry;
import net.minecraft.recipe.ShapedRecipe;
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
                CraftingRegistry.addShapedRecipe(new ItemInstance(BlockBase.DOUBLE_STONE_SLAB.asItem(), 1), "X", "X", 'X', BlockBase.STONE_SLAB.asItem());
            }
        }

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS.type())
        {
            if (Config.config.enableDoubleStoneSlabCraftingRecipe) {
                CraftingRegistry.addShapelessRecipe(new ItemInstance(BlockBase.STONE_SLAB.asItem(), 2), BlockBase.DOUBLE_STONE_SLAB.asItem());
            }
        }
    }
}