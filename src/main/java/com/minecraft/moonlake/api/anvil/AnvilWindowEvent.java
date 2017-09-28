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
 
 
package com.minecraft.moonlake.api.anvil;

import org.bukkit.entity.Player;

/**
 * <h1>AnvilWindowEvent</h1>
 * 铁砧窗口事件接口类（详细doc待补充...）
 *
 * @version 1.0
 * @author Month_Light
 */
public interface AnvilWindowEvent {

    /**
     * 获取此铁砧窗口事件的玩家
     *
     * @return 玩家
     */
    Player getPlayer();

    /**
     * 获取此铁砧窗口事件的对象
     *
     * @return AnvilWindow
     */
    AnvilWindow getAnvilWindow();
}
