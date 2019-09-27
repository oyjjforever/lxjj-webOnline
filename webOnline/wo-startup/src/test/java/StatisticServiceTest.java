import com.lxjj.wo.Startup;
import com.lxjj.wo.service.StatisticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * @ClassName:MyPapersTest.java
 * @author 欧阳俊杰
 * @description:
 * @Date:2019年4月25日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Startup.class)
public class StatisticServiceTest {
    @Autowired
    StatisticService statisticService;

    @Test
    public void setReportOfMonth() {
        statisticService.setReportOfMonth();
    }

}
