package com.lediter.despair.procedures;

import com.lediter.despair.DespairMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

import java.util.Map;

public class K3Procedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DespairMod.LOGGER.warn("Failed to load dependency entity for procedure K3!");
			return;
		}

		Entity entity = (Entity) dependencies.get("entity");

		if (entity instanceof LivingEntity) {
			((LivingEntity) entity).attackEntityFrom(new DamageSource("\u6253\u5F00\u4E86\u6B7B\u4EA1\u4E4B\u76D2\uFF01").setDamageBypassesArmor(),
					(float) 100000000);
		}

	}
}
