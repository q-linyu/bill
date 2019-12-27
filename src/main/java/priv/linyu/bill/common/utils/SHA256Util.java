package priv.linyu.bill.common.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @className: SHA256Util
 * @author: q-linyu
 * @description: 密码加密工具类
 * @date: 2019/12/16 21:04
 * @version: 1.0
 **/
public class SHA256Util {

    /**
     * 私有构造器
     */
    private SHA256Util(){

    }

    /**
     * 加密的算法
     */
    public final static String HASH_ALGORITHM_NAME = "SHA-256";

    /**
     * 加密的次数
     */
    public final static int HASH_ITERATIONS = 15;

    /**
     * 执行加密方法
     * @param password 密码
     * @param salt 盐值
     * @return
     */
    public static String sha256(String password,String salt){
        return new SimpleHash(HASH_ALGORITHM_NAME,password,salt,HASH_ITERATIONS).toString();
    }


    public static void main(String[] args) {
        String sha256Password = sha256("zhangsan","J.GAS36XLOFFMOQKJSG8");
        System.out.println(sha256Password);
    }

}
