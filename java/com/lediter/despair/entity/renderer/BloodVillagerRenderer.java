package com.lediter.despair.entity.renderer;

import com.lediter.despair.entity.BloodVillagerEntity;
import com.lediter.despair.entity.EntityTypeRegister;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.client.renderer.entity.MobRenderer;


@OnlyIn(Dist.CLIENT)
public class BloodVillagerRenderer {
	public static class ModelRegisterHandler {
		@SubscribeEvent
		@OnlyIn(Dist.CLIENT)
		public void registerModels(ModelRegistryEvent event) {
			RenderingRegistry.registerEntityRenderingHandler(EntityTypeRegister.BLOOD_VILLAGER_ENTITY.get(),
					renderManager -> new MobRenderer(renderManager, new VillagerModel(0), 0.5f) {

						@Override
						public ResourceLocation getEntityTexture(Entity entity) {
							return new ResourceLocation("despair:textures/entity/blood_villager.png");
						}
					});
		}
	}
}
