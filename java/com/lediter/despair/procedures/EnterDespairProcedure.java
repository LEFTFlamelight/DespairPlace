package com.lediter.despair.procedures;

import com.lediter.despair.DespairMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

public class EnterDespairProcedure {

    @Mod.EventBusSubscriber
    private static class GlobalTrigger {
        @SubscribeEvent
        public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
            Entity entity = event.getPlayer();
            Map<String, Object> dependencies = new HashMap<>();
            dependencies.put("x", entity.getPosX());
            dependencies.put("y", entity.getPosY());
            dependencies.put("z", entity.getPosZ());
            dependencies.put("world", entity.world);
            dependencies.put("entity", entity);
            dependencies.put("endconquered", event.isEndConquered());
            dependencies.put("event", event);
            executeProcedure(dependencies);
        }
    }

    public static void executeProcedure(Map<String, Object> dependencies) {
        if (dependencies.get("entity") == null) {
            if (!dependencies.containsKey("entity"))
                DespairMod.LOGGER.warn("Failed to load dependency entity for procedure EnterDespair!");
            return;
        }

        Entity entity = (Entity) dependencies.get("entity");

        {
            Entity _ent = entity;
            if (_ent instanceof PlayerEntity) {
                Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
                ScoreObjective _so = _sc.getObjective("custom_score");
                if (_so == null) {
                    _so = _sc.addObjective("custom_score", ScoreCriteria.DUMMY, new StringTextComponent("custom_score"),
                            ScoreCriteria.RenderType.INTEGER);
                }
                Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
                _scr.setScorePoints((int) 0);
            }
        }
        {
            Entity _ent = entity;
            if (_ent instanceof PlayerEntity) {
                Scoreboard _sc = ((PlayerEntity) _ent).getWorldScoreboard();
                ScoreObjective _so = _sc.getObjective("custom_score");
                if (_so == null) {
                    _so = _sc.addObjective("custom_score", ScoreCriteria.DUMMY, new StringTextComponent("custom_score"),
                            ScoreCriteria.RenderType.INTEGER);
                }
                Score _scr = _sc.getOrCreateScore(((PlayerEntity) _ent).getScoreboardName(), _so);
                _scr.setScorePoints((int) (new Object() {
                    public int getScore(String score) {
                        if (entity instanceof PlayerEntity) {
                            Scoreboard _sc = ((PlayerEntity) entity).getWorldScoreboard();
                            ScoreObjective _so = _sc.getObjective(score);
                            if (_so != null) {
                                Score _scr = _sc.getOrCreateScore(((PlayerEntity) entity).getScoreboardName(), _so);
                                return _scr.getScorePoints();
                            }
                        }
                        return 0;
                    }
                }.getScore("custom_score") + 1));
            }
        }
    }

}