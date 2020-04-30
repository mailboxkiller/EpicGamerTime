package dev.trainwreck.computermod.api.redstone;

import dev.trainwreck.computermod.api.javascript.IJavaScriptAPI;
import dev.trainwreck.computermod.computer.Computer;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class RedstoneAPI implements IJavaScriptAPI {

    private Computer computer;

    public int getSide(String side){
        return Arrays.asList(Computer.sideNames).indexOf(side);
    }

    public void setOutput(String side, boolean state){
        computer.setRedstoneOutput(getSide(side),state ? 15 : 0);
    }

    public int getOutput(int side){
        return computer.getRedstoneOutput(side);
    }
    public int getOutput(String side){
        return getOutput(getSide(side));
    }

    @Override
    public String[] getMethods() {
        Method[] methods =  RedstoneAPI.class.getMethods();
        ArrayList<String> strings = new ArrayList<>();
        for (Method method : methods) {
            strings.add(method.getName());
        }
        strings.remove("getComputer");
        strings.remove("setComputer");
        strings.remove("getAPIName");
        strings.remove("getMethods");

        return (String[]) strings.toArray();
    }

    @Override
    public Computer getComputer() {
        return computer;
    }

    @Override
    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    @Override
    public String getAPIName() {
        return "RedstoneAPI";
    }
}
