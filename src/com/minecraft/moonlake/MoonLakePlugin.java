package com.minecraft.moonlake;

import com.minecraft.moonlake.api.MoonLake;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by MoonLake on 2016/4/26.
 * @version 1.0
 * @author Month_Light
 */
public class MoonLakePlugin extends JavaPlugin implements MoonLake {

    private MoonLake instance;
    private PluginDescriptionFile pdf;

    static { }

    public MoonLakePlugin() {
        // 构造函数
        instance = this;
        pdf = this.getDescription();
    }

    /**
     * 获取 MoonLake 实例对象
     *
     * @return MoonLake
     */
    public MoonLake getInstance() {
        return instance;
    }

    /**
     * 获取插件的称号
     *
     * @return Prefix
     */
    @Override
    public String getPluginPrefix() {
        return pdf.getPrefix();
    }

    /**
     * 获取插件的名字
     *
     * @return Name
     */
    @Override
    public String getPluginName() {
        return pdf.getName();
    }

    /**
     * 获取插件的主类
     *
     * @return MainClass
     */
    @Override
    public String getPluginMain() {
        return pdf.getMain();
    }

    /**
     * 获取插件的版本
     *
     * @return Version
     */
    @Override
    public String getPluginVersion() {
        return pdf.getVersion();
    }

    /**
     * 获取插件的网站
     *
     * @return Website
     */
    @Override
    public String getPluginWebsite() {
        return pdf.getWebsite();
    }

    /**
     * 获取插件的简介
     *
     * @return Description
     */
    @Override
    public String getPluginDescription() {
        return pdf.getDescription();
    }

    /**
     * 获取插件的作者
     *
     * @return Auther
     */
    @Override
    public Set<String> getPluginAuthers() {
        return new HashSet<String>(pdf.getAuthors());
    }

    /**
     * 获取插件的依赖
     *
     * @return Depend
     */
    @Override
    public Set<String> getPluginDepends() {
        return new HashSet<String>(pdf.getDepend());
    }

    /**
     * 获取插件的软依赖
     *
     * @return SoftDepend
     */
    @Override
    public Set<String> getPluginSoftDepends() {
        return new HashSet<String>(pdf.getSoftDepend());
    }
}