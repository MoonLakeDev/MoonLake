package com.minecraft.moonlake.api.item;

import com.minecraft.moonlake.api.item.meta.MetaLibrary;
import com.minecraft.moonlake.api.item.skull.SkullLibrary;
import com.minecraft.moonlake.builder.ItemBuilderWrapped;
import com.minecraft.moonlake.item.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by MoonLake on 2016/9/13.
 */
public class ItemLibraryFactory {

    /**
     * 静态 ItemLibraryFactory 实例对象
     */
    private static ItemLibraryFactory instance;

    private ItemLibraryFactory() {

    }

    /**
     * 获取 ItemLibraryFactory 对象
     *
     * @return ItemLibraryFactory
     */
    public static ItemLibraryFactory get() {

        if(instance == null) {

            instance = new ItemLibraryFactory();
        }
        return instance;
    }

    /**
     * 获取 ItemLibrary 实例对象
     *
     * @return ItemLibrary
     */
    public ItemLibrary item() {

        return new ItemExpressionWrapped();
    }

    /**
     * 获取 CraftLibrary 实例对象
     *
     * @return CraftLibrary
     */
    public CraftLibrary craft() {

        return new CraftExpression();
    }

    /**
     * 获取 MetaLibrary 实例对象
     *
     * @return MetaLibrary
     */
    public MetaLibrary meta() {

        return new MetaExpression();
    }

    /**
     * 获取 SkullLibrary 实例对象
     *
     * @return SkullLibrary
     */
    public SkullLibrary skull() {

        return new SkullExpression();
    }

    /**
     * 获取 AttributeLibrary 实例对象
     *
     * @return AttributeLibrary
     */
    public AttributeLibrary attribute() {

        return new AttributeExpression();
    }

    /**
     * 获取 ItemBuilder 实例对象
     *
     * @param itemStack 物品栈对象
     * @return ItemBuilder
     * @throws IllegalArgumentException 如果物品栈对象为 {@code null} 则抛出异常
     */
    public ItemBuilder itemBuilder(ItemStack itemStack) {

        return new ItemBuilderWrapped(itemStack);
    }

    /**
     * 获取 ItemBuilder 实例对象
     *
     * @param material 物品栈类型
     * @return ItemBuilder
     * @throws IllegalArgumentException 如果物品栈类型对象为 {@code null} 则抛出异常
     */
    public ItemBuilder itemBuilder(Material material) {

        return new ItemBuilderWrapped(material);
    }

    /**
     * 获取 ItemBuilder 实例对象
     *
     * @param material 物品栈类型
     * @param data 物品栈数据
     * @return ItemBuilder
     * @throws IllegalArgumentException 如果物品栈类型对象为 {@code null} 则抛出异常
     */
    public ItemBuilder itemBuilder(Material material, int data) {

        return new ItemBuilderWrapped(material, data);
    }

    /**
     * 获取 ItemBuilder 实例对象
     *
     * @param material 物品栈类型
     * @param data 物品栈数据
     * @param amount 物品栈数量
     * @return ItemBuilder
     * @throws IllegalArgumentException 如果物品栈类型对象为 {@code null} 则抛出异常
     */
    public ItemBuilder itemBuilder(Material material, int data, int amount) {

        return new ItemBuilderWrapped(material, data, amount);
    }

    /**
     * 获取 ItemBuilder 实例对象
     *
     * @param material 物品栈类型
     * @param data 物品栈数据
     * @param amount 物品栈数量
     * @param displayName 物品栈显示名称
     * @return ItemBuilder
     * @throws IllegalArgumentException 如果物品栈类型对象为 {@code null} 则抛出异常
     * @throws IllegalArgumentException 如果显示名称对象为 {@code null} 则抛出异常
     */
    public ItemBuilder itemBuilder(Material material, int data, int amount, String displayName) {

        return new ItemBuilderWrapped(material, data, amount, displayName);
    }

    /**
     * 获取 ItemBuilder 实例对象
     *
     * @param material 物品栈类型
     * @param data 物品栈数据
     * @param amount 物品栈数量
     * @param displayName 物品栈显示名称
     * @param lore 物品栈标签信息
     * @return ItemBuilder
     * @throws IllegalArgumentException 如果物品栈类型对象为 {@code null} 则抛出异常
     * @throws IllegalArgumentException 如果显示名称对象为 {@code null} 则抛出异常
     * @throws IllegalArgumentException 如果标签信息对象为 {@code null} 则抛出异常
     */
    public ItemBuilder itemBuilder(Material material, int data, int amount, String displayName, String... lore) {

        return new ItemBuilderWrapped(material, data, amount, displayName, lore);
    }
}
