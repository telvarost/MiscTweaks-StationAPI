package com.github.telvarost.misctweaks.events.init;

import com.github.telvarost.misctweaks.Config;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.CraftingRecipeManager;
import net.minecraft.recipe.ShapedRecipe;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()) {
            List<CraftingRecipe> recipes = CraftingRecipeManager.getInstance().getRecipes();

            for (int i = 0; i < recipes.size(); i++) {
                CraftingRecipe recipe = recipes.get(i);
                int recipeItemId = recipe.getOutput().itemId;

                if (recipeItemId == Block.DISPENSER.asItem().id) {
                    if (Config.config.EQUIPMENT_CONFIG.bowsHaveDurability) {
                        ItemStack[] inputArray = new ItemStack[9];
                        inputArray[0] = new ItemStack(Block.COBBLESTONE, 1);
                        inputArray[1] = new ItemStack(Block.COBBLESTONE, 1);
                        inputArray[2] = new ItemStack(Block.COBBLESTONE, 1);
                        inputArray[3] = new ItemStack(Block.COBBLESTONE, 1);
                        inputArray[4] = new ItemStack(Item.BOW, 1, -1);
                        inputArray[5] = new ItemStack(Block.COBBLESTONE, 1);
                        inputArray[6] = new ItemStack(Block.COBBLESTONE, 1);
                        inputArray[7] = new ItemStack(Item.REDSTONE, 1);
                        inputArray[8] = new ItemStack(Block.COBBLESTONE, 1);
                        recipes.set(i, new ShapedRecipe(3, 3, inputArray, new ItemStack(Block.DISPENSER.asItem(), 1)));
                    }
                }
            }
        }
    }
}