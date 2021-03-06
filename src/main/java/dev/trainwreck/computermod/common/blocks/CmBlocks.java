package dev.trainwreck.computermod.common.blocks;

import dev.trainwreck.computermod.common.items.BlockItemBase;
import dev.trainwreck.computermod.common.registry.RegistrationHelper;
import dev.trainwreck.computermod.common.tileentity.TileComputer;
import dev.trainwreck.computermod.common.tileentity.TileTest;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.EnumSet;
import java.util.function.Supplier;

public enum CmBlocks {
    COMPUTER_BLOCK(new ComputerBlock(), null, TileComputer::new),
    TEST_BLOCK(new BlockTileBase(Block.Properties.create(Material.PISTON),"test"),null, TileTest::new); //TODO: Remove block

    private BlockBase block;
    private BlockItemBase blockitem;
    private TileEntityType<?> tileEntityType;

    CmBlocks(BlockBase block, BlockItemBase blockitem, Supplier<? extends TileEntity> factoryIn) {
        this.block = block;
        this.blockitem = blockitem;
        this.tileEntityType = TileEntityType.Builder.create(factoryIn ,block.getBlock()).build(null);
    }

    public static void RegisterBlocks(RegistrationHelper registrationHelper){
        EnumSet.allOf(CmBlocks.class).forEach(block -> {
            if(block.block instanceof BlockTileBase){
                if (block.blockitem == null) {
                    registrationHelper.RegisterTileBlock((BlockTileBase) block.block, block.tileEntityType);

                } else {
                    registrationHelper.RegisterTileBlock((BlockTileBase) block.block, block.blockitem, block.tileEntityType);
                }
            }else {
                if (block.blockitem == null) {
                    registrationHelper.RegisterBlock(block.block);
                } else {
                    registrationHelper.RegisterBlock(block.block, block.blockitem);
                }
            }

        });

    }

    public BlockBase getBlock() {
        return block;
    }

    public TileEntityType<?> getTileEntityType() {
        return tileEntityType;
    }
}
