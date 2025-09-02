package me.kall.villagerextend.mixin;

import me.kall.villagerextend.api.ISimpleContainer;
import net.minecraft.core.NonNullList;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SimpleContainer.class)
public class SimpleContainerAccessor implements ISimpleContainer {
    @Shadow @Final private NonNullList<ItemStack> items;

    @Override
    public int villagerExtend$getFirstAvailable() {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).isEmpty()) continue;
            return i;
        }
        return -1;
    }
}
