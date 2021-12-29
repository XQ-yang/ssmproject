import com.ssm.entity.Mood;
import com.ssm.entity.User;
import com.ssm.entity.UserMoodPraiseRel;
import com.ssm.service.impl.MoodServiceImpl;
import com.ssm.service.impl.UserMoodPraiseRelServiceImpl;
import com.ssm.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author: 小强
 * @date: 2021/8/21 0021
 * @tool: IntelliJ IDEA
 * @words: Be more professional every day!
 */
public class MyTest {
    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserServiceImpl booksServiceImpl = (UserServiceImpl) context.getBean("UserServiceImpl");

        List<User> users = booksServiceImpl.queryAll();

        System.out.println(users);

    }

    @Test
    public void test1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        MoodServiceImpl moodServiceImpl = (MoodServiceImpl) context.getBean("moodServiceImpl");

        List<Mood> moods = moodServiceImpl.queryAll();

        System.out.println(moods);

    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        UserMoodPraiseRelServiceImpl userMoodPraiseRelServiceImpl = (UserMoodPraiseRelServiceImpl) context.getBean("userMoodPraiseRelServiceImpl");

        List<UserMoodPraiseRel> list = userMoodPraiseRelServiceImpl.queryAllByLimit(4,2);

        System.out.println(list);

    }
}
