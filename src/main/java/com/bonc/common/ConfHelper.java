package com.bonc.common;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * <p>Title: BONC - ConfHelper </p>
 * 
 * <p>Description: 配置辅助类 </p>
 * 
 * <p>Copyright: Copyright BONC(c) 2013 - 2025 </p>
 * 
 * <p>Company: 北京东方国信科技股份有限公司 </p>
 * 
 * @author yangdx
 * @version 1.0.0
 */
public class ConfHelper {

    /**
     * 配置文件对象
     */
    private static Properties CONF = new Properties();

    /**
     * 加载配置
     */
    static {
        // 加载配置信息
        try {
            CONF.load(ConfHelper.class.getResourceAsStream("/application.properties"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取对应的value
     *
     * @param key 对应的key
     * @return 对应的值
     */
    public static String getValue(String key) {

        return CONF.getProperty(key);
    }
}
