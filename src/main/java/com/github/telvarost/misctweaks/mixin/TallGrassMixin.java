package com.github.telvarost.misctweaks.mixin;

import com.github.telvarost.misctweaks.Config;
import com.github.telvarost.misctweaks.ModHelperStationAPI;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(TallPlantBlock.class)
class TallGrassMixin extends PlantBlock {

    public TallGrassMixin(int i, int j) {
        super(i, j);
    }

    @Override
    public void onPlaced(World arg, int i, int j, int k) {
        arg.setBlockMeta(i, j, k, 1);
    }

    @Override
    public void afterBreak(World world, PlayerEntity playerEntity, int x, int y, int z, int meta) {
        int dropId = 0;

        if (  (Config.config.FLORA_CONFIG.enableShearsCollectTallGrass)
           || (Config.config.FLORA_CONFIG.enableShearsCollectFern)
        ) {
            if (  (null != playerEntity)
               && (null != playerEntity.inventory)
               && (null != playerEntity.inventory.getSelectedItem())
               && (Item.SHEARS.id == playerEntity.inventory.getSelectedItem().itemId)
            ) {
                if (  (Config.config.FLORA_CONFIG.enableShearsCollectFern)
                   && (FabricLoader.getInstance().isModLoaded("bhcreative"))
                   && (meta == 2)
                ) {
                    playerEntity.inventory.getSelectedItem().damage(1, playerEntity);
                    dropId = ModHelperStationAPI.identifierToItemId("bhcreative:fern");
                } else if (Config.config.FLORA_CONFIG.enableShearsCollectTallGrass) {
                    playerEntity.inventory.getSelectedItem().damage(1, playerEntity);
                    dropId = id;
                }
            }
        }

        playerEntity.increaseStat(Stats.MINE_BLOCK[this.id], 1);

        if (0 != dropId) {
            this.dropStack(world, x, y, z, new ItemStack(dropId, 1, 0));
        } else if (FabricLoader.getInstance().isModLoaded("wolves")) {
            this.dropWithChance(world, x, y, z, world.getBlockState(x, y, z), meta, 1.0F);
        } else {
            this.dropStacks(world, x, y, z, meta);
        }
    }
}

