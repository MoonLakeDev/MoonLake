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


package com.minecraft.moonlake.api.packet.wrapper;

import com.minecraft.moonlake.property.ObjectProperty;
import com.minecraft.moonlake.property.SimpleObjectProperty;
import com.minecraft.moonlake.property.SimpleStringProperty;
import com.minecraft.moonlake.property.StringProperty;

import java.io.IOException;

/**
 * <h1>PacketPlayOutBungeeForwardToPlayer</h1>
 * 数据包输出蹦极数据服务器到目标
 *
 * @version 1.0
 * @author Month_Light
 */
public class PacketPlayOutBungeeForwardToPlayer extends PacketPlayOutBungeeAbstractTarget {

    private StringProperty channel;
    private ObjectProperty<byte[]> data;

    /**
     * 数据包输出蹦极数据服务器到目标构造函数
     *
     * @param target 目标玩家名
     * @param channel 通道名
     * @param data 数据
     */
    public PacketPlayOutBungeeForwardToPlayer(String target, String channel, byte[] data) {

        super(target);

        this.channel = new SimpleStringProperty(channel);
        this.data = new SimpleObjectProperty<>(data);
    }

    /**
     * 获取此数据包输出蹦极数据服务器到目标的通道名
     *
     * @return 通道名
     */
    public StringProperty getChannel() {

        return channel;
    }

    /**
     * 获取此数据包输出蹦极数据服务器到目标的数据
     *
     * @return 数据
     */
    public ObjectProperty<byte[]> getData() {

        return data;
    }

    @Override
    protected void write() throws IOException {

        super.dataOut.writeUTF("Forward");
        super.dataOut.writeUTF(super.target.get());
        super.dataOut.writeUTF(channel.get());
        super.dataOut.writeShort(data.get().length);
        super.dataOut.write(data.get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PacketPlayOutBungeeForwardToPlayer that = (PacketPlayOutBungeeForwardToPlayer) o;

        if (!channel.equals(that.channel)) return false;
        return data.equals(that.data);
    }

    @Override
    public int hashCode() {
        int result = channel.hashCode();
        result = 31 * result + data.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "PacketPlayOutBungeeForwardToPlayer{" +
                "target=" + target +
                ", channel=" + channel +
                ", data=" + data +
                ", dataOut=" + dataOut +
                '}';
    }
}
