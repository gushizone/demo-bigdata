package tk.gushizone.bigdata.spark.scala.streaming.basis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author gushizone
 * @date 2022/10/31 11:50
 */
public class JedisPoolUtil {

    /**
     * 声明为volatile防止指令重排序
     */
    private static volatile JedisPool jedisPool = null;

    private static final String HOST = "localhost";
    private static final int PORT = 6379;

    private static final String PASSWORD = "redis";


    /**
     * 双重检查锁实现懒汉式单例
     */
    public static Jedis getConnection() {
        if (jedisPool == null) {
            synchronized (JedisPoolUtil.class) {
                if (jedisPool == null) {
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxTotal(30);
                    config.setMaxIdle(10);
//                    jedisPool = new JedisPool(config, HOST, PORT);
                    jedisPool = new JedisPool(config, HOST, PORT, 1000, PASSWORD);
                }
            }
        }
        return jedisPool.getResource();
    }
}