package dev.trainwreck.computermod.tileentity;

import dev.trainwreck.computermod.Reference;
import dev.trainwreck.computermod.blocks.CmBlocks;
import net.minecraft.tileentity.ITickableTileEntity;

public class TileTest extends TileEntityBase implements ITickableTileEntity {

    public TileTest() {
        super(CmBlocks.TEST_BLOCK.getTileEntityType());
    }



    @Override
    public void tick() {
        Reference.LOGGER.debug("Annoying debug message");  //TODO: to be removed
    }
}
