package com.lediter.despair.tools;

import com.lediter.despair.item.ItemRegistry;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;
public enum BloodTools implements IItemTier {
    BLOOD(5000,12.0F,12.0F,9,8,()->{
        return Ingredient.fromItems(ItemRegistry.BLOOD_INGOT.get());
    });
    private int maxUses;
    private float efficiency;
    private float attackDamage;
    private int havestLevel;
    private int enchatability;
    private LazyValue<Ingredient> repairMaterial;

    BloodTools(int maxUses, float efficiency, float attackDamage, int havestLevel, int enchatability, Supplier<Ingredient> repairMaterial){
        this.maxUses=maxUses;
        this.efficiency=efficiency;
        this.attackDamage=attackDamage;
        this.havestLevel=havestLevel;
        this.enchatability=enchatability;
        this.repairMaterial=new LazyValue<>(repairMaterial);
    }
    @Override
    public int getMaxUses() {
        return this.maxUses;
    }

    @Override
    public float getEfficiency() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getHarvestLevel() {
        return this.havestLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchatability;
    }

    @Override
    public Ingredient getRepairMaterial() {
        return this.repairMaterial.getValue();
    }
}
