package site.beanyon.apt.common.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import site.beanyon.apt.common.entity.back.BackUser;
import site.beanyon.apt.common.util.RedisUtils;

/**
 * RedisUtil的测试类
 *
 * @author BeanYon
 * 2019.08.13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisUtilTest {
    @Autowired
    private RedisUtils redisUtil;

    @Test
    public void testSetObj() {
        BackUser backUser = new BackUser();
        backUser.setName("BeanYon");
        redisUtil.set("user", backUser);
    }

    @Test
    public void testGetObj() {
        BackUser backUser = (BackUser) redisUtil.get("user");
        System.out.println(backUser.getName());
    }

    @Test
    public void testSetHash() {
        BackUser backUser = new BackUser();
        backUser.setName("BeanYon");
        redisUtil.hset("backuser", "13007215142", backUser);
    }

    @Test
    public void testGetHash() {
        BackUser backUser = (BackUser) redisUtil.hget("backuser", "13007215142");
        System.out.println(backUser.getName());
    }
}
