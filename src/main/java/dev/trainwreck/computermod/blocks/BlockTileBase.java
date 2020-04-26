package dev.trainwreck.computermod.blocks;

import dev.trainwreck.computermod.registry.RegistrationHelper;
import dev.trainwreck.computermod.tileentity.CmTileEntitys;
import dev.trainwreck.computermod.tileentity.TileComputer;
import dev.trainwreck.computermod.tileentity.TileEntityBase;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public class BlockTileBase extends BlockBase {
    private TileEntityType<?> tileEntityType;

    public BlockTileBase(Properties properties, String internalID) {
        super(properties, internalID);
    }

    public void setTileEntityType(TileEntityType<?> tileEntityType) {
        this.tileEntityType = tileEntityType;
    }

    public TileEntityType<?> getTileEntityType() {
        return tileEntityType;
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return tileEntityType.create();
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }
}
