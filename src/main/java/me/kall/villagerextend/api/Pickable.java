package me.kall.villagerextend.api;

import net.minecraft.world.item.ItemStack;

public interface Pickable {
    boolean villagerExtend$canPickup();
    void villagerExtend$setPickable(boolean pickable);

    static Pickable cast(ItemStack stack) {
        return (Pickable) (Object) stack;
    }
}
