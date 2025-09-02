package me.kall.villagerextend;

import me.kall.villagerextend.api.ISimpleContainer;
import me.kall.villagerextend.api.Pickable;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

@Mod(VillagerExtend.MOD_ID)
public final class VillagerExtend {
    public static final String MOD_ID = "villagerextend";

    public VillagerExtend() {
        MinecraftForge.EVENT_BUS.addListener(this::onInteract);
    }

    public void onInteract(PlayerInteractEvent.@NotNull EntityInteract event) {
        if (event.getTarget() instanceof AbstractVillager villager && villager.level() instanceof ServerLevel level && isLocalBully(event.getEntity())) {
            int slot = ISimpleContainer.cast(villager.getInventory()).villagerExtend$getFirstAvailable();
            if (slot == -1) return;
            ItemStack stack = villager.getInventory().removeItem(slot, 1);
            Pickable.cast(stack).villagerExtend$setPickable(false);
            level.addFreshEntity(new ItemEntity(level, villager.getX(), villager.getY(), villager.getZ(), stack));
        }
    }

    public static boolean isLocalBully(@NotNull Player player) {
        ItemStack item = player.getMainHandItem();
        return item.getAttributeModifiers(EquipmentSlot.MAINHAND).containsKey(Attributes.ATTACK_DAMAGE);
    }
}
