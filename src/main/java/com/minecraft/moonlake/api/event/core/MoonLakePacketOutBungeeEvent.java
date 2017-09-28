/*
 * Copyright (C) 2017 The MoonLake Authors
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


package com.minecraft.moonlake.api.event.core;

import com.minecraft.moonlake.api.packet.PacketPlayOutBungee;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * <h1>MoonLakePacketOutBungeeEvent</h1>
 * 月色之湖数据包输出蹦极事件类（详细doc待补充...）
 *
 * @version 1.0
 * @author Month_Light
 * @see MoonLakePacketOutEvent
 * @see PacketPlayOutBungee
 */
public class MoonLakePacketOutBungeeEvent extends MoonLakePacketOutEvent {

    private final static HandlerList handlerList = new HandlerList();

    /**
     * 月色之湖数据包输出蹦极事件类构造函数
     *
     * @param packet 数据包输出蹦极
     * @param players 玩家
     * @throws IllegalArgumentException 如果数据包对象为 {@code null} 则抛出异常
     */
    public MoonLakePacketOutBungeeEvent(PacketPlayOutBungee packet, Player... players) throws IllegalArgumentException {

        super(packet, players);
    }

    @Override
    public PacketPlayOutBungee getPacket() {

        return (PacketPlayOutBungee) super.getPacket();
    }

    @Override
    public HandlerList getHandlers() {

        return handlerList;
    }

    public static HandlerList getHandlerList() {

        return handlerList;
    }
}
