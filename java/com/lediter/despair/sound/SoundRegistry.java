package com.lediter.despair.sound;

import com.lediter.despair.DespairMod;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, DespairMod.MOD_ID);
    public static final RegistryObject<SoundEvent> SOUND1;








    static{
        SOUND1 = onSoundRegistry("sound_1");
    }

    private static RegistryObject<SoundEvent> onSoundRegistry(String key){
        return SOUNDS.register(key,()->new SoundEvent(new ResourceLocation(DespairMod.MOD_ID,key)));
    }
}
