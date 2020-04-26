package dev.trainwreck.computermod.tileentity;

import dev.trainwreck.computermod.blocks.CmBlocks;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Supplier;

public enum CmTileEntitys {
    COMPUTER_TE(TileComputer::new);

    private Supplier<? extends TileEntity> factoryIn;

    CmTileEntitys(Supplier<? extends TileEntity> factoryIn) {
        System.out.println("test");
        this.factoryIn = factoryIn;
    }

    public TileEntityType<?> getTileType(Block block) {
        return TileEntityType.Builder.create(factoryIn,block).build(null);
    }
}
