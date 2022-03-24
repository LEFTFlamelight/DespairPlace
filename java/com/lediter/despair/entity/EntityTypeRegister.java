package com.lediter.despair.entity;

import com.lediter.despair.DespairMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EntityTypeRegister {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, DespairMod.MOD_ID);
    public static final RegistryObject<EntityType<KiriaEntity>> KIRIA_ENTITY = ENTITY_TYPES.register("kiria_entity", () -> EntityType.Builder.create(KiriaEntity::new, EntityClassification.MONSTER).size(1, 2F).build("kiria_entity"));
}