package dev.trainwreck.computermod.blocks;

import dev.trainwreck.computermod.items.BlockItemBase;
import dev.trainwreck.computermod.registry.RegistrationHelper;
import dev.trainwreck.computermod.tileentity.CmTileEntitys;

import java.util.EnumSet;

public enum CmBlocks {
    COMPUTER_BLOCK(new ComputerBlock(), null, CmTileEntitys.COMPUTER_TE);

    private BlockBase block;
    private BlockItemBase blockitem;
    private CmTileEntitys tileEntitys;

    CmBlocks(BlockBase block, BlockItemBase blockitem, CmTileEntitys tileEntitys) {
        this.block = block;
        this.blockitem = blockitem;
        this.tileEntitys = tileEntitys;
    }

    public BlockBase getBlock() {
        return block;
    }

    public static void RegisterBlocks(RegistrationHelper registrationHelper){
        EnumSet.allOf(CmBlocks.class).forEach(block -> {
            if(block.block instanceof BlockTileBase){
                if (block.blockitem == null) {
                    registrationHelper.RegisterTileBlock((BlockTileBase) block.block, block.tileEntitys);
                } else {
                    registrationHelper.RegisterTileBlock((BlockTileBase) block.block, block.blockitem, block.tileEntitys);
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
}
