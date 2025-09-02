package me.kall.villagerextend.mixin;

import me.kall.villagerextend.api.Pickable;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemStack.class)
public class ItemStackMixin implements Pickable {
    @Unique private boolean villagerExtend$pickable = true;

    @Override
    public boolean villagerExtend$canPickup() {
        return this.villagerExtend$pickable;
    }

    @Override
    public void villagerExtend$setPickable(boolean pickable) {
        this.villagerExtend$pickable = pickable;
    }
}
