package priv.linyu.bill.common.utils;

/**
 * @className: RedisKeyUtils
 * @author: q-linyu
 * @description: redis生成key工具类
 * @date: 2019/12/16 22:07
 * @version: 1.0
 **/
public class RedisKeyUtils {

    /**
     * 生成key
     * @param prefix
     * @param key
     * @return
     */
    public static String createKey(String prefix,String key){
        return prefix + "::" + key;
    }
}
