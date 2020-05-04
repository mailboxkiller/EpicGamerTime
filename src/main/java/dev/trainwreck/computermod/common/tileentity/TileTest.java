package dev.trainwreck.computermod.common.tileentity;

import dev.trainwreck.computermod.Reference;
import dev.trainwreck.computermod.common.blocks.CmBlocks;
import net.minecraft.tileentity.ITickableTileEntity;

public class TileTest extends TileEntityBase implements ITickableTileEntity {

    public TileTest() {
        super(CmBlocks.TEST_BLOCK.getTileEntityType());
    }



    @Override
    public void tick() {
        Reference.LOGGER.debug("Annoying debug message " + getPos());  //TODO: to be removed
    }
}
