/*
 * Copyright (C) 2016 The MoonLake Authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.minecraft.moonlake.enums;

public enum Sound {

    // 两个 String 说明有老版本也有新版本
    // 一个 int 和一个 String 说明版本有不同
    // 如果 int 为负数代表只支持那个版本的绝对值
    // 如果 int 为整数代表支持那个版本以及以上
    ///

    环境_洞穴("ambience_cave", "ambient_cave"),
    环境_雨(-8, "ambience_rain"),
    环境_雷(-8, "ambience_thunder"),

    方块_铁砧_摧毁(9, "block_anvil_destroy"),
    方块_铁砧_着地("anvil_land", "block_anvil_land"),
    方块_铁砧_使用("anvil_use", "block_anvil_use"),
    方块_铁砧_破坏("anvil_break", "block_anvil_break"),
    方块_铁砧_落下(9, "block_anvil_fall"),
    方块_铁砧_撞击(9, "block_anvil_hit"),
    方块_铁砧_放置(9, "block_anvil_place"),
    方块_铁砧_步调(9, "block_anvil_step"),

    方块_酿造台_冒泡(9, "block_brewing_stand_brew"),
    方块_箱子_关闭("chest_close", "block_chest_close"),
    方块_箱子_打开("chest_open", "block_chest_open"),
    方块_箱子_锁上(9, "block_chest_locked"),

    方块_紫影花_死亡(9, "block_chorus_flower_death"),
    方块_紫影花_生长(9, "block_chorus_flower_grow"),

    方块_比较器_点击(9, "block_comparator_click"),
    方块_发射器_发射物品(9, "block_dispenser_dispense"),
    方块_发射器_发射失败(9, "block_dispenser_fail"),
    方块_发射器_发射弹丸(9, "block_dispenser_launch"),

    方块_末影箱_关闭(9, "block_enderchest_close"),
    方块_末影箱_打开(9, "block_enderchest_open"),
    方块_末地折跃门_生成(9, "block_end_gateway_spawn"),

    方块_栅栏门_关闭(9, "block_fence_gate_close"),
    方块_栅栏门_打开(9, "block_fence_gate_open"),

    方块_火_燃烧("fire", "block_fire_ambient"),
    方块_火_熄灭(9, "block_fire_extinguish"),

    方块_熔炉_火裂纹(9, "block_furnace_fire_crackle"),

    方块_布类_破坏("dig_wool", "block_cloth_break"),
    方块_布类_落下(9, "block_cloth_fall"),
    方块_布类_撞击(9, "block_cloth_hit"),
    方块_布类_放置(9, "block_cloth_place"),
    方块_布类_步调("step_wool", "block_cloth_step"),

    方块_玻璃类_破坏("glass", "block_glass_break"),
    方块_玻璃类_落下(9, "block_glass_fall"),
    方块_玻璃类_撞击(9, "block_glass_hit"),
    方块_玻璃类_放置(9, "block_glass_place"),
    方块_玻璃类_步调(9, "block_glass_step"),

    方块_草类_破坏("dig_grass", "block_grass_break"),
    方块_草类_落下(9, "block_grass_fall"),
    方块_草类_撞击(9, "block_grass_hit"),
    方块_草类_放置(9, "block_grass_place"),
    方块_草类_步调("step_grass", "block_grass_step"),

    方块_砂砾类_破坏("dig_gravel", "block_gravel_break"),
    方块_砂砾类_落下(9, "block_gravel_fall"),
    方块_砂砾类_撞击(9, "block_gravel_hit"),
    方块_砂砾类_放置(9, "block_gravel_place"),
    方块_砂砾类_步调("step_gravel", "block_gravel_step"),

    方块_铁质门_关闭(9, "block_iron_door_close"),
    方块_铁质门_打开(9, "block_iron_door_open"),

    方块_铁质活板门_关闭(9, "block_iron_trapdoor_close"),
    方块_铁质活板门_打开(9, "block_iron_trapdoor_open"),

    方块_梯子_破坏(9, "block_ladder_break"),
    方块_梯子_落下(9, "block_ladder_fall"),
    方块_梯子_撞击(9, "block_ladder_hit"),
    方块_梯子_放置(9, "block_ladder_place"),
    方块_梯子_步调("step_ladder", "block_ladder_step"),

    方块_岩浆_迸裂("lava", "block_lava_ambient"),
    方块_岩浆_熄灭(9, "block_lava_extinguish"),
    方块_岩浆_流动("lava_pop", "block_lava_pop"),

    方块_拉杆_拉动(9, "block_lever_click"),

    方块_金属_破坏(9, "block_metal_break"),
    方块_金属_落下(9, "block_metal_fall"),
    方块_金属_撞击(9, "block_metal_hit"),
    方块_金属_放置(9, "block_metal_place"),
    方块_金属_步调(9, "block_metal_step"),
    方块_金属_压力板_关闭(9, "block_metal_pressureplate_click_off"),
    方块_金属_压力板_触发(9, "block_metal_pressureplate_click_on"),

    方块_音符盒_低音("note_bass", "block_note_bass"),
    方块_音符盒_低音鼓("note_bass_drum", "block_note_basedrum"),
    方块_音符盒_钢琴("note_piano", "block_note_harp"),
    方块_音符盒_棍棒("note_sticks", "block_note_hat"),
    方块_音符盒_小鼓("note_snare_drum", "block_note_snare"),
    方块_音符盒_叮("note_pling", "block_note_pling"),

    方块_活塞_缩回("piston_retract", "block_piston_contract"),
    方块_活塞_伸展("piston_extend", "block_piston_extend"),

    方块_传送门_呼啸("portal", "block_portal_ambient"),
    方块_传送门_进入("portal_travel", "block_portal_travel"),
    方块_传送门_触发("portal_trigger", "block_portal_trigger"),

    方块_红石火把_熄灭(9, "block_redstone_torch_burnout"),

    方块_沙类_破坏("dig_sand", "block_sand_break"),
    方块_沙类_落下(9, "block_sand_fall"),
    方块_沙类_撞击(9, "block_sand_hit"),
    方块_沙类_放置(9, "block_sand_place"),
    方块_沙类_步调("step_sand", "block_sand_step"),

    方块_史莱姆_破坏(9, "block_slime_break"),
    方块_史莱姆_落下(9, "block_slime_fall"),
    方块_史莱姆_撞击(9, "block_slime_hit"),
    方块_史莱姆_放置(9, "block_slime_place"),
    方块_史莱姆_步调(9, "block_slime_step"),

    方块_雪类_破坏("dig_snow", "block_snow_break"),
    方块_雪类_落下(9, "block_snow_fall"),
    方块_雪类_撞击(9, "block_snow_hit"),
    方块_雪类_放置(9, "block_snow_place"),
    方块_雪类_步调("step_snow", "block_snow_step"),

    方块_石类_破坏("dig_stone", "block_stone_break"),
    方块_石类_落下(9, "block_stone_fall"),
    方块_石类_撞击(9, "block_stone_hit"),
    方块_石类_放置(9, "block_stone_place"),
    方块_石类_步调("step_stone", "block_stone_step"),
    方块_石质_压力板_关闭(9, "block_stone_pressureplate_click_off"),
    方块_石质_压力板_触发(9, "block_stone_pressureplate_click_on"),

    方块_拌线钩_连接(9, "block_tripwire_attach"),
    方块_拌线钩_关闭(9, "block_tripwire_click_off"),
    方块_拌线钩_触发(9, "block_tripwire_click_on"),
    方块_拌线钩_分离(9, "block_tripwire_detach"),

    方块_睡莲_放置(9, "block_waterlily_place"),
    方块_水_流动("water", "block_water_ambient"),

    方块_木质门_关闭(9, "block_wooden_door_close"),
    方块_木质门_打开(9, "block_wooden_door_open"),

    方块_木质活板门_关闭(9, "block_wooden_trapdoor_close"),
    方块_木质活板门_打开(9, "block_wooden_trapdoor_open"),

    方块_木类_破坏("dig_wood", "block_wood_break"),
    方块_木类_落下(9, "block_wood_fall"),
    方块_木类_撞击(9, "block_wood_hit"),
    方块_木类_放置(9, "block_wood_place"),
    方块_木类_步调("step_wood", "block_wood_step"),
    方块_木质_压力板_关闭(9, "block_wood_pressureplate_click_off"),
    方块_木质_压力板_触发(9, "block_wood_pressureplate_click_on"),

    附魔_荆棘_撞击(9, "enchant_thorns_hit"),

    实体_盔甲架_破坏(9, "entity_armorstand_break"),
    实体_盔甲架_落下(9, "entity_armorstand_fall"),
    实体_盔甲架_撞击(9, "entity_armorstand_hit"),
    实体_盔甲架_放置(9, "entity_armorstand_place"),

    实体_弓箭_撞击("arrow_hit", "entity_arrow_hit"),
    实体_弓箭_撞击玩家("successful_hit", "entity_arrow_hit_player"),
    实体_弓箭_射出("shoot_arrow", "entity_arrow_shoot"),

    ;

    private final int version;
    private final String oldSound;
    private final String latestSound;

    Sound(int version, String value) {

        this.version = version;
        this.oldSound = version < 0 ? value : null;
        this.latestSound = version > 0 ? value : null;
    }

    Sound(String old, String latest) {

        this(0, old, latest);
    }

    Sound(int version, String old, String latest) {

        this.version = version;
        this.oldSound = old;
        this.latestSound = latest;
    }
}
