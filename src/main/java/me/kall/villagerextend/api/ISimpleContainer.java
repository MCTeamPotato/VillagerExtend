package me.kall.villagerextend.api;

import net.minecraft.world.SimpleContainer;

public interface ISimpleContainer {
    int villagerExtend$getFirstAvailable();

    static ISimpleContainer cast(SimpleContainer container) {
        return (ISimpleContainer) container;
    }
}
