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

import java.io.IOException;

/**
 * <h1>PacketPlayOutBungeeConnect</h1>
 * 数据包输出蹦极服务器连接
 *
 * @version 1.0
 * @author Month_Light
 */
public class PacketPlayOutBungeeConnect extends PacketPlayOutBungeeAbstractServer {

    /**
     * 数据包输出蹦极服务器连接构造函数
     *
     * @param targetServer 目标服务器
     */
    public PacketPlayOutBungeeConnect(String targetServer) {

        super(targetServer);
    }

    @Override
    protected void write() throws IOException {

        super.dataOut.writeUTF("Connect");
        super.dataOut.writeUTF(super.targetServer.get());
    }

    @Override
    public String toString() {
        return "PacketPlayOutBungeeConnect{" +
                "targetServer=" + targetServer +
                ", dataOut=" + dataOut +
                '}';
    }
}
