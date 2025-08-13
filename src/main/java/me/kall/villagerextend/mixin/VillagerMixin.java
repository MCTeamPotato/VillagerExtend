package me.kall.villagerextend.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.gossip.GossipContainer;
import net.minecraft.world.entity.ai.gossip.GossipType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.UUID;

@Mixin(Villager.class)
public abstract class VillagerMixin extends AbstractVillager {
    public VillagerMixin(EntityType<? extends AbstractVillager> entityType, Level level) {
        super(entityType, level);
    }

    @WrapOperation(method = "onReputationEventFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/gossip/GossipContainer;add(Ljava/util/UUID;Lnet/minecraft/world/entity/ai/gossip/GossipType;I)V", ordinal = 3))
    private void onHurt(GossipContainer instance, UUID entityGossips, GossipType identifier, int gossipType, Operation<Void> original) {
        float percent = this.getHealth() / this.getMaxHealth();
        if (percent < 0.5) {
            original.call(instance, entityGossips, GossipType.MAJOR_POSITIVE, 50 - (int) (percent * 100F));
        } else {
            original.call(instance, entityGossips, identifier, gossipType);
        }
    }

    @ModifyArg(method = "onReputationEventFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/gossip/GossipContainer;add(Ljava/util/UUID;Lnet/minecraft/world/entity/ai/gossip/GossipType;I)V", ordinal = 4))
    private GossipType onDie(GossipType gossipType) {
        return GossipType.MAJOR_POSITIVE;
    }

    @WrapOperation(method = "handleEntityEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/npc/Villager;addParticlesAroundSelf(Lnet/minecraft/core/particles/ParticleOptions;)V", ordinal = 1))
    private void onParticle(Villager instance, ParticleOptions particleOptions, Operation<Void> original) {
        if ((this.getHealth() / this.getMaxHealth()) < 0.5) return;
        original.call(instance, particleOptions);
    }
}
