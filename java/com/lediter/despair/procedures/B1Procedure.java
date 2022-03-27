package com.lediter.despair.procedures;

import com.lediter.despair.DespairMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;


public class B1Procedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DespairMod.LOGGER.warn("Failed to load dependency entity for procedure B1!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (((entity instanceof PlayerEntity) ? ((PlayerEntity) entity).experienceLevel : 0) >= 1) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 60, 5));
			if (entity instanceof PlayerEntity) {
				((PlayerEntity) entity).giveExperiencePoints(-1);
			}
		}
	}
}