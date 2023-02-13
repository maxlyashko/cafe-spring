package ua.lyashko.cafe;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-category-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-category-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class CategoryTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getNewCategoryTest () throws Exception {
        this.mockMvc.perform ( get ( "/categories/new" ) )
                .andDo ( print ( ) )
                .andExpect ( status ( ).isOk ( ) );
    }

    @Test
    public void getCategoriesTest () throws Exception {
        this.mockMvc.perform ( get ( "/categories" ) )
                .andDo ( print ( ) )
                .andExpect ( status ( ).isOk ( ) );
    }

    @Test
    public void getCategoryEdit () throws Exception {
        this.mockMvc.perform ( get ( "/categories/edit/{id}", "1" ) )
                .andDo ( print ( ) )
                .andExpect ( status ( ).isOk ( ) );
    }

    @Test
    public void getDeleteCategory () throws Exception {
        this.mockMvc.perform ( get ( "/categories/{id}", "1" ) )
                .andDo ( print ( ) )
                .andExpect ( status ( ).is3xxRedirection ( ) );
    }
}
