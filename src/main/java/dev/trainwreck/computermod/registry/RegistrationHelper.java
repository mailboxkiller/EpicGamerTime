package dev.trainwreck.computermod.registry;

import dev.trainwreck.computermod.Reference;
import dev.trainwreck.computermod.blocks.BlockBase;
import dev.trainwreck.computermod.blocks.BlockTileBase;
import dev.trainwreck.computermod.items.BlockItemBase;
import dev.trainwreck.computermod.items.ItemBase;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class RegistrationHelper {
    private static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Reference.MOD_ID);
    private static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Reference.MOD_ID);
    private static final DeferredRegister<TileEntityType<?>> TILES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Reference.MOD_ID);


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
    }
    public void RegisterTileBlock(BlockTileBase blockIn, BlockItemBase itemBase, TileEntityType<?> tileEntitys){
        BLOCKS.register(blockIn.getInternalID(), () -> blockIn);
        ITEMS.register(blockIn.getInternalID(), () -> itemBase);
        blockIn.setTileEntityType(tileEntitys);
        TILES.register(blockIn.getInternalID(), () -> tileEntitys);
    }
    public void RegisterItem(ItemBase itemIn){
        ITEMS.register(itemIn.getInternalName(), () -> itemIn);
    }


    public RegistrationHelper() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}
