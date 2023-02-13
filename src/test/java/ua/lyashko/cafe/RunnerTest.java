package ua.lyashko.cafe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.lyashko.cafe.controllers.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RunnerTest {

    @Autowired
    private DashboardController dashboardController;

    @Autowired
    private BillController billController;

    @Autowired
    private CategoryController categoryController;

    @Autowired
    private ProductController productController;

    @Autowired
    private LoggingController loggingController;

    @Test
    public void runTest () throws Exception {
    }

    @Test
    public void dashboardTest () throws Exception {
        assertThat ( dashboardController ).isNotNull ( );
    }

    @Test
    public void billTest () throws Exception {
        assertThat ( billController ).isNotNull ( );
    }

    @Test
    public void categoryTest () throws Exception {
        assertThat ( categoryController ).isNotNull ( );
    }

    @Test
    public void productTest () throws Exception {
        assertThat ( productController ).isNotNull ( );
    }

    @Test
    public void loggingTest () throws Exception {
        assertThat ( loggingController ).isNotNull ( );
    }
}
