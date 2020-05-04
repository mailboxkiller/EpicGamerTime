package dev.trainwreck.computermod.client.gui;

import dev.trainwreck.computermod.common.contaner.ComputerContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ComputerGui extends ContainerScreen<ComputerContainer> {


    public ComputerGui(ComputerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        super.render(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        this.font.drawString(this.title.getFormattedText(), 8.0f, 6.0f, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0f, 90.0f, 4210752);
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }
}
