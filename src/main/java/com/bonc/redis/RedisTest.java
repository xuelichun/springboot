package com.bonc.redis;

import com.bonc.test.Course;
import com.bonc.test.User;
import com.bonc.utils.SerializableHelper;
import com.wandoulabs.jodis.RoundRobinJedisPool;
import org.springframework.transaction.jta.WebSphereUowTransactionManager;
import redis.clients.jedis.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : xuelc
 * @Description:
 * @Date : Created in 11:56 2018/12/14.
 * @Modified By:
 */
public class RedisTest {

    public static Map<String, Object> getMap(){
        Map<String,Object> map=new HashMap<>();
        Jedis jedis = new Jedis("192.168.31.110",7000);
        String  data=jedis.get("string");
        System.out.println(data);
        return map;
    }
    /**
     *  获取返回对象
     * @param key
     * @return
     */
    public static User hget(String key, String page){
        User user=new User();
        Jedis jedis = new Jedis("192.168.31.110",7000);
        //Jedis jedis =jedisPool.getResource();

        user=(User) SerializableHelper.deserialize(jedis.hget(key.getBytes(),page.getBytes()));

        return user;
    }

    /**
     * 往reids中存数据
     * @param user
     * @param key
     * @param page
     * @return
     */

    public static void hset(String key,String page,User user){
        Jedis jedis = new Jedis("192.168.31.110",7000);
        jedis.hset(key.getBytes(),page.getBytes(),SerializableHelper.serialize(user));
        jedis.expire(key.getBytes(),300);
    }

    /**
     * 测试String 性能
     */

    public static void stringSet(){

        Jedis jedis = new Jedis("192.168.31.110",7000);
        List<Course> courseList=new ArrayList<>();
        for (int i = 0; i <10000 ; i++) {
            Course course=new Course();
            course.setcId(i);
            course.setcName("东方国信科技股份有限公司||东方国信科技股份有限公司||东方国信科技股份有限公司||" +
                    "东方国信科技股份有限公司||东方国信科技股份有限公司||东方国信科技股份有限公司||" +
                    "东方国信科技股份有限公司||东方国信科技股份有限公司||东方国信科技股份有限公司||" +
                    "东方国信科技股份有限公司||东方国信科技股份有限公司||东方国信科技股份有限公司||" +
                    "东方国信科技股份有限公司||东方国信科技股份有限公司||东方国信科技股份有限公司||" );
            course.setcScore(12.12);
            courseList.add(course);
        }
        for (int i = 0; i <1 ; i++) {
            User user=new User();
            user.setId(i);
            user.setCourseList(courseList);
            jedis.set(new Integer(i).toString().getBytes(),SerializableHelper.serialize(user));
            jedis.expire(new Integer(i).toString().getBytes(),60*5);
        }

    }


    public static void stringGet(){
         Jedis jedis = new Jedis("192.168.31.110",7000);
         User user=new User();
         jedis.get(new Integer(0).toString().getBytes());
         user=(User)SerializableHelper.deserialize(new Integer(0).toString().getBytes());
         System.out.println("返回的对象:"+user);
    }



    public static void main(String[] args) {
        //user.setId(156);
        //hset("15604364062","2",user);
        //System.out.println(hget("15604364062","1"));
        //getMap();

        long start=System.currentTimeMillis();
        stringSet();
        stringGet();
        long end=System.currentTimeMillis();
        System.out.println("方法用时毫秒:"+(end-start));
    }

}
