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


package com.minecraft.moonlake.api.player.depend;

import com.minecraft.moonlake.api.VectorConvertible;
import com.minecraft.moonlake.api.region.RegionBlockVector;
import com.minecraft.moonlake.api.region.RegionVector;
import com.minecraft.moonlake.util.StringUtil;
import com.minecraft.moonlake.validate.Validate;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.util.Vector;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <h1>WorldEditVector</h1>
 * 创世神矢量（详细doc待补充...）
 *
 * @version 1.0
 * @author Month_Light
 */
public class WorldEditVector implements VectorConvertible, ConfigurationSerializable, Comparable<WorldEditVector> {

    /**
     * 创世神矢量 ZERO 值
     */
    public final static WorldEditVector ZERO = new WorldEditVector(0d, 0d, 0d);

    static {

        ConfigurationSerialization.registerClass(WorldEditVector.class);
    }

    protected final double x;
    protected final double y;
    protected final double z;

    /**
     * 创世神矢量构造函数
     *
     * @param x X
     * @param y Y
     * @param z Z
     */
    public WorldEditVector(double x, double y, double z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * 创世神矢量构造函数
     *
     * @param x X
     * @param y Y
     * @param z Z
     */
    public WorldEditVector(float x, float y, float z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * 创世神矢量构造函数
     *
     * @param x X
     * @param y Y
     * @param z Z
     */
    public WorldEditVector(int x, int y, int z) {

        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * 创世神矢量构造函数
     *
     * @param other 创世神矢量
     * @throws IllegalArgumentException 如果创世神矢量对象为 {@code null} 则抛出异常
     */
    public WorldEditVector(WorldEditVector other) {

        Validate.notNull(other, "The other vector object is null.");

        this.x = other.x;
        this.y = other.y;
        this.z = other.z;
    }

    /**
     * 创世神矢量构造函数
     */
    public WorldEditVector() {

        this.x = 0d;
        this.y = 0d;
        this.z = 0d;
    }

    /**
     * 获取此创世神矢量的 X
     *
     * @return X
     */
    public double getX() {

        return x;
    }

    /**
     * 获取此创世神矢量的 Y
     *
     * @return Y
     */
    public double getY() {

        return y;
    }

    /**
     * 获取此创世神矢量的 Z
     *
     * @return Z
     */
    public double getZ() {

        return z;
    }

    /**
     * 获取此创世神矢量的方块 X
     *
     * @return 方块 X
     */
    public int getBlockX() {

        return (int) Math.round(x);
    }

    /**
     * 获取此创世神矢量的方块 Y
     *
     * @return 方块 Y
     */
    public int getBlockY() {

        return (int) Math.round(y);
    }

    /**
     * 获取此创世神矢量的方块 Z
     *
     * @return 方块 Z
     */
    public int getBlockZ() {

        return (int) Math.round(z);
    }

    /**
     * 设置此创世神矢量的 X
     *
     * @param x X
     */
    public WorldEditVector setX(double x) {

        return new WorldEditVector(x, y, z);
    }

    /**
     * 设置此创世神矢量的 Y
     *
     * @param y Y
     */
    public WorldEditVector setY(double y) {

        return new WorldEditVector(x, y, z);
    }

    /**
     * 设置此创世神矢量的 Z
     *
     * @param z Z
     */
    public WorldEditVector setZ(double z) {

        return new WorldEditVector(x, y, z);
    }

    /**
     * 设置此创世神矢量的 X
     *
     * @param x X
     */
    public WorldEditVector setX(int x) {

        return new WorldEditVector(x, y, z);
    }

    /**
     * 设置此创世神矢量的 Y
     *
     * @param y Y
     */
    public WorldEditVector setY(int y) {

        return new WorldEditVector(x, y, z);
    }

    /**
     * 设置此创世神矢量的 Z
     *
     * @param z Z
     */
    public WorldEditVector setZ(int z) {

        return new WorldEditVector(x, y, z);
    }

    /**
     * 将指定创世神矢量和此创世神矢量相加
     *
     * @param vector 创世神矢量
     */
    public WorldEditVector add(WorldEditVector vector) {

        return new WorldEditVector(x + vector.x, y + vector.y, z + vector.z);
    }

    /**
     * 将指定 X Y Z 和此创世神矢量相加
     *
     * @param x X
     * @param y Y
     * @param z Z
     */
    public WorldEditVector add(double x, double y, double z) {

        return new WorldEditVector(this.x + x, this.y + y, this.z + z);
    }

    /**
     * 将指定 X Y Z 和此创世神矢量相加
     *
     * @param x X
     * @param y Y
     * @param z Z
     */
    public WorldEditVector add(int x, int y, int z) {

        return new WorldEditVector(this.x + x, this.y + y, this.z + z);
    }

    /**
     * 将指定创世神矢量和此创世神矢量相减
     *
     * @param vector 创世神矢量
     */
    public WorldEditVector subtract(WorldEditVector vector) {

        return new WorldEditVector(x - vector.x, y - vector.y, z - vector.z);
    }

    /**
     * 将指定 X Y Z 和此创世神矢量相减
     *
     * @param x X
     * @param y Y
     * @param z Z
     */
    public WorldEditVector subtract(double x, double y, double z) {

        return new WorldEditVector(this.x - x, this.y - y, this.z - z);
    }

    /**
     * 将指定 X Y Z 和此创世神矢量相减
     *
     * @param x X
     * @param y Y
     * @param z Z
     */
    public WorldEditVector subtract(int x, int y, int z) {

        return new WorldEditVector(this.x - x, this.y - y, this.z - z);
    }
    /**
     * 将指定创世神矢量和此创世神矢量相乘
     *
     * @param vector 创世神矢量
     */

    public WorldEditVector multiply(WorldEditVector vector) {

        return new WorldEditVector(x * vector.x, y * vector.y, z * vector.z);
    }

    /**
     * 将指定 X Y Z 和此创世神矢量相乘
     *
     * @param x X
     * @param y Y
     * @param z Z
     */
    public WorldEditVector multiply(double x, double y, double z) {

        return new WorldEditVector(this.x * x, this.y * y, this.z * z);
    }

    /**
     * 将指定 X Y Z 和此创世神矢量相乘
     *
     * @param x X
     * @param y Y
     * @param z Z
     */
    public WorldEditVector multiply(int x, int y, int z) {

        return new WorldEditVector(this.x * x, this.y * y, this.z * z);
    }

    /**
     * 将指定 N 和此创世神矢量相乘
     *
     * @param n N
     */
    public WorldEditVector multiply(double n) {

        return new WorldEditVector(this.x * n, this.y * n, this.z * n);
    }

    /**
     * 将指定 N 和此创世神矢量相乘
     *
     * @param n N
     */
    public WorldEditVector multiply(float n) {

        return new WorldEditVector(this.x * n, this.y * n, this.z * n);
    }

    /**
     * 将指定 N 和此创世神矢量相乘
     *
     * @param n N
     */
    public WorldEditVector multiply(int n) {

        return new WorldEditVector(this.x * n, this.y * n, this.z * n);
    }

    /**
     * 将指定创世神矢量和此创世神矢量相除
     *
     * @param vector 创世神矢量
     */
    public WorldEditVector divide(WorldEditVector vector) {

        return new WorldEditVector(x / vector.x, y / vector.y, z / vector.z);
    }

    /**
     * 将指定 X Y Z 和此创世神矢量相除
     *
     * @param x X
     * @param y Y
     * @param z Z
     */
    public WorldEditVector divide(double x, double y, double z) {

        return new WorldEditVector(this.x / x, this.y / y, this.z / z);
    }

    /**
     * 将指定 X Y Z 和此创世神矢量相除
     *
     * @param x X
     * @param y Y
     * @param z Z
     */
    public WorldEditVector divide(int x, int y, int z) {

        return new WorldEditVector(this.x / x, this.y / y, this.z / z);
    }

    /**
     * 将指定 N 和此创世神矢量相除
     *
     * @param n N
     */
    public WorldEditVector divide(double n) {

        return new WorldEditVector(this.x / n, this.y / n, this.z / n);
    }

    /**
     * 将指定 N 和此创世神矢量相除
     *
     * @param n N
     */
    public WorldEditVector divide(float n) {

        return new WorldEditVector(this.x / n, this.y / n, this.z / n);
    }

    /**
     * 将指定 N 和此创世神矢量相除
     *
     * @param n N
     */
    public WorldEditVector divide(int n) {

        return new WorldEditVector(this.x / n, this.y / n, this.z / n);
    }

    /**
     * 获取此创世神矢量的长度
     *
     * @return 长度
     */
    public double length() {

        return Math.sqrt(lengthSq());
    }

    /**
     * 获取此创世神矢量的长度平方
     *
     * @return 长度平方
     */
    public double lengthSq() {

        return x * x + y * y + z * z;
    }

    /**
     * 获取指定创世神矢量和此创世神矢量的距离
     *
     * @param vector 创世神矢量
     * @return 距离
     */
    public double distance(WorldEditVector vector) {

        return Math.sqrt(distanceSq(vector));
    }

    /**
     * 获取指定创世神矢量和此创世神矢量的距离平方
     *
     * @param vector 创世神矢量
     * @return 距离平方
     */
    public double distanceSq(WorldEditVector vector) {

        return Math.pow(x - vector.x, 2d) + Math.pow(y - vector.y, 2d) + Math.pow(z - vector.z, 2d);
    }

    /**
     * 获取此创世神矢量的归一化
     *
     * @return 使归一化的创世神矢量
     */
    public WorldEditVector normalize() {

        return divide(length());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WorldEditVector that = (WorldEditVector) o;

        if (Double.compare(that.x, x) != 0) return false;
        if (Double.compare(that.y, y) != 0) return false;
        return Double.compare(that.z, z) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(z);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "WorldEditVector{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public int compareTo(WorldEditVector o) {

        if(x != o.x)
            return Double.compare(x, o.x);
        if(y != o.y)
            return Double.compare(y, o.y);
        if(z != o.z)
            return Double.compare(z, o.z);
        return 0;
    }

    @Override
    public Map<String, Object> serialize() {

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("x", x);
        result.put("y", y);
        result.put("z", z);
        return result;
    }

    public static WorldEditVector deserialize(Map<String, Object> args) {

        double x = StringUtil.parseDouble(args.get("x"));
        double y = StringUtil.parseDouble(args.get("y"));
        double z = StringUtil.parseDouble(args.get("z"));
        return new WorldEditVector(x, y, z);
    }

    @Override
    public Vector asBukkitVector() {

        return new Vector(x, y, z);
    }

    @Override
    public WorldEditVector asWorldEditVector() {

        return new WorldEditVector(this);
    }

    @Override
    public RegionVector asRegionVector() {

        return new RegionVector(x, y, z);
    }

    @Override
    public RegionBlockVector asRegionBlockVector() {

        return new RegionBlockVector(x, y, z);
    }
}
