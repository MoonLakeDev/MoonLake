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


package com.minecraft.moonlake.api.chat.adapter;

import com.minecraft.moonlake.api.chat.ChatComponent;
import com.minecraft.moonlake.api.chat.ChatSerializer;
import com.minecraft.moonlake.validate.Validate;

/**
 * <h1>ChatJsonAdapter</h1>
 * 聊天组件 Json 适配器类
 *
 * @version 1.0
 * @author Month_Light
 */
public class ChatJsonAdapter implements ChatAdapter {

    private final String json;

    /**
     * 聊天组件 Json 适配器类构造函数
     *
     * @param json Json
     */
    public ChatJsonAdapter(String json) {
        this.json = Validate.checkNotNull(json);
    }

    @Override
    public ChatComponent toChatComponent() {
        return ChatSerializer.fromJson(json);
    }

    @Override
    public Object toIChatBaseComponent() {
        return ChatSerializer.iCBCFromJson(json);
    }
}
