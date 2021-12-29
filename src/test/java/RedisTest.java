import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author: 小强
 * @date: 2021/8/21 0021
 * @tool: IntelliJ IDEA
 * @words: Be more professional every day!
 */
public class RedisTest extends BaseJunit4Test{
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis(){
        redisTemplate.opsForValue().set("name","yangxiaoqiang");
        String name = (String) redisTemplate.opsForValue().get("name");
        System.out.println("value of name is "+name);
    }

}
