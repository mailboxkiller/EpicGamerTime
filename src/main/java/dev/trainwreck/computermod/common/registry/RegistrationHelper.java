package dev.trainwreck.computermod.common.registry;

import dev.trainwreck.computermod.Reference;
import dev.trainwreck.computermod.common.blocks.BlockBase;
import dev.trainwreck.computermod.common.blocks.BlockTileBase;
import dev.trainwreck.computermod.common.contaner.ComputerContainer;
import dev.trainwreck.computermod.common.items.BlockItemBase;
import dev.trainwreck.computermod.common.items.ItemBase;
import dev.trainwreck.computermod.common.tileentity.TileEntityUIBase;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class RegistrationHelper {
    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MOD_ID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Reference.MOD_ID);
    private static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = new DeferredRegister<>(ForgeRegistries.CONTAINERS, Reference.MOD_ID);

    private static RegistryObject<ContainerType<?>> containerTypes = null;

    public void RegisterBlock(BlockBase blockIn){
         BLOCKS.register(blockIn.getInternalID(), () -> blockIn);
         ITEMS.register(blockIn.getInternalID(), () -> new BlockItem(blockIn, new Item.Properties().group(blockIn.getItemGroup())));
    }
    public void RegisterBlock(BlockBase blockIn, BlockItemBase itemBase){
        BLOCKS.register(blockIn.getInternalID(), () -> blockIn);
        ITEMS.register(blockIn.getInternalID(), () -> itemBase);
    }
    public void RegisterTileBlock(BlockTileBase blockIn, TileEntityType<?> tileEntitys){
        BLOCKS.register(blockIn.getInternalID(), () -> blockIn);
        ITEMS.register(blockIn.getInternalID(), () -> new BlockItem(blockIn, new Item.Properties().group(blockIn.getItemGroup())));
        blockIn.setTileEntityType(tileEntitys);
        TILES.register(blockIn.getInternalID(), () -> tileEntitys);

        if(tileEntitys.create() instanceof TileEntityUIBase){
            TileEntityUIBase tile = (TileEntityUIBase) tileEntitys.create();
            if(tile.getContainerType() == null)return;
             containerTypes = CONTAINER_TYPES.register(blockIn.getInternalID(), ()->tile.getContainerType());
        }
    }
    public void RegisterTileBlock(BlockTileBase blockIn, BlockItemBase itemBase, TileEntityType<?> tileEntitys){
        BLOCKS.register(blockIn.getInternalID(), () -> blockIn);
        ITEMS.register(blockIn.getInternalID(), () -> itemBase);
        blockIn.setTileEntityType(tileEntitys);
        TILES.register(blockIn.getInternalID(), () -> tileEntitys);

        if(tileEntitys.create() instanceof TileEntityUIBase){
            TileEntityUIBase tile = (TileEntityUIBase) tileEntitys.create();
            if(tile.getContainerType() == null)return;
            CONTAINER_TYPES.register(blockIn.getInternalID(), ()->tile.getContainerType());
        }
    }
    public void RegisterItem(ItemBase itemIn){
        ITEMS.register(itemIn.getInternalName(), () -> itemIn);
    }

    public static ContainerType<ComputerContainer> getContainerType() {
        return (ContainerType<ComputerContainer>) containerTypes.get();
    }

    public RegistrationHelper() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
