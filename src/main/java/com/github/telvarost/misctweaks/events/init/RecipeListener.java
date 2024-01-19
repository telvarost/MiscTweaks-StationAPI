package com.github.telvarost.misctweaks.events.init;

import blue.endless.jankson.JsonObject;
import blue.endless.jankson.JsonPrimitive;
import com.github.telvarost.misctweaks.Config;
import net.glasslauncher.mods.api.gcapi.api.GCAPI;
import net.glasslauncher.mods.api.gcapi.impl.ConfigFactories;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.BlockBase;
import net.minecraft.item.ItemInstance;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.RecipeRegistry;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.util.Identifier;

import java.lang.reflect.Field;
import java.util.List;

import static com.github.telvarost.misctweaks.Config.config;

public class RecipeListener {

    @EventListener
    public void registerRecipes(RecipeRegisterEvent event) {
        Identifier type = event.recipeId;

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED.type()) {
            List<Recipe> recipes = RecipeRegistry.getInstance().getRecipes();

            /** - Ensure stairs crafting recipe output is an integer value between 1 and 16 */
            if (16 < Config.ConfigFields.stairsOutput)
            {
                Config.ConfigFields.stairsOutput = 16;
                try {
                    JsonObject jsonObject = new JsonObject();
                    for (Field field : com.github.telvarost.misctweaks.Config.ConfigFields.class.getDeclaredFields()) {
                        jsonObject.put(field.getName(), ConfigFactories.saveFactories.get(field.getType()).apply(field.get(config)));
                    }
                    jsonObject.put("stairsOutput", new JsonPrimitive(16));
                    GCAPI.reloadConfig(Identifier.of("misctweaks:config"), jsonObject.toJson());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            else if (1 > Config.ConfigFields.stairsOutput)
            {
                Config.ConfigFields.stairsOutput = 1;
                try {
                    JsonObject jsonObject = new JsonObject();
                    for (Field field : com.github.telvarost.misctweaks.Config.ConfigFields.class.getDeclaredFields()) {
                        jsonObject.put(field.getName(), ConfigFactories.saveFactories.get(field.getType()).apply(field.get(config)));
                    }
                    jsonObject.put("stairsOutput", new JsonPrimitive(1));
                    GCAPI.reloadConfig(Identifier.of("misctweaks:config"), jsonObject.toJson());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < recipes.size(); i++) {
                Recipe recipe = recipes.get(i);

//            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockBase.WOOD_STAIRS.asItem(), 6), "X  ", "XX ", "XXX", 'X', BlockBase.WOOD);
//            CraftingRegistry.addShapedRecipe(new ItemInstance(BlockBase.COBBLESTONE_STAIRS.asItem(), 6), "X  ", "XX ", "XXX", 'X', BlockBase.COBBLESTONE);
                if (recipe.getOutput().itemId == BlockBase.WOOD_STAIRS.asItem().id) {
                    ItemInstance[] inputArray = new ItemInstance[9];
                    inputArray[0] = new ItemInstance(BlockBase.WOOD.asItem(), 1);
                    inputArray[3] = new ItemInstance(BlockBase.WOOD.asItem(), 1);
                    inputArray[4] = new ItemInstance(BlockBase.WOOD.asItem(), 1);
                    inputArray[6] = new ItemInstance(BlockBase.WOOD.asItem(), 1);
                    inputArray[7] = new ItemInstance(BlockBase.WOOD.asItem(), 1);
                    inputArray[8] = new ItemInstance(BlockBase.WOOD.asItem(), 1);
                    recipes.set(i, new ShapedRecipe(3, 3, inputArray, new ItemInstance(BlockBase.WOOD_STAIRS.asItem(), Config.ConfigFields.stairsOutput)));
                }

                if (recipe.getOutput().itemId == BlockBase.COBBLESTONE_STAIRS.asItem().id) {
                    ItemInstance[] inputArray = new ItemInstance[9];
                    inputArray[0] = new ItemInstance(BlockBase.COBBLESTONE.asItem(), 1);
                    inputArray[3] = new ItemInstance(BlockBase.COBBLESTONE.asItem(), 1);
                    inputArray[4] = new ItemInstance(BlockBase.COBBLESTONE.asItem(), 1);
                    inputArray[6] = new ItemInstance(BlockBase.COBBLESTONE.asItem(), 1);
                    inputArray[7] = new ItemInstance(BlockBase.COBBLESTONE.asItem(), 1);
                    inputArray[8] = new ItemInstance(BlockBase.COBBLESTONE.asItem(), 1);
                    recipes.set(i, new ShapedRecipe(3, 3, inputArray, new ItemInstance(BlockBase.COBBLESTONE_STAIRS.asItem(), Config.ConfigFields.stairsOutput)));
                }
            }
        }
    }
}