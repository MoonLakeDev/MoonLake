package com.minecraft.moonlake.api.mysql.query;

import com.minecraft.moonlake.api.mysql.MySQLConnection;
import com.minecraft.moonlake.api.mysql.MySQLTable;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MoonLake on 2016/7/28.
 */
public class MySQLQueryDelete extends MySQLQueryAbstract {

    private boolean and;
    private List<Object> values;
    private final MySQLTable table;

    public MySQLQueryDelete(MySQLConnection connection, MySQLTable table) {

        super(connection, "delete from " + table.getName());

        this.table = table;
        this.values = new ArrayList<>();
    }

    /**
     * 执行 MySQL 的更新表的判断指定键的值
     *
     * @param key 键
     * @param value 值
     * @return 实例
     */
    public MySQLQueryDelete where(String key, Object value) {

        if(!and) {

            sql += " where";
        }
        else {

            sql += " and";
        }
        sql = (sql + " " + key + "=");

        values.add(value);

        sql += "?";

        and = true;

        return this;
    }

    /**
     * 执行 MySQL 的任务
     *
     * @return 对象
     */
    @Override
    public Object execute() {

        try {

            super.execute();

            PreparedStatement pst = connection.getConnection().prepareStatement(sql);

            int i = 1;

            for(Object object : values) {

                pst.setObject(i, object);

                i++;
            }
            pst.executeUpdate();
            pst.close();
        }
        catch (Exception e) {

            getInstance().getMLogger().warn("执行数据库的声明对象的选择语句时异常: " + e.getMessage());
        }
        return null;
    }
}
