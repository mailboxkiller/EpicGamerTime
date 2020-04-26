package dev.trainwreck.computermod.blocks;

import net.minecraft.util.IStringSerializable;

public enum ComputerState implements IStringSerializable {
    Off("off"),
    Starting("starting"),
    Running("running"),
    Stopping("stopping");

    private final String name;

    ComputerState(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    @Override
    public String getName() {
        return null;
    }
}
