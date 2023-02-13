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
@Sql(value = {"/create-product-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/create-product-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(value = {"/create-category-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ProductTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getNewProductTest () throws Exception {
        this.mockMvc.perform ( get ( "/products/new" ) )
                .andDo ( print ( ) )
                .andExpect ( status ( ).isOk ( ) );
    }

    @Test
    public void getProductTest () throws Exception {
        this.mockMvc.perform ( get ( "/products" ) )
                .andDo ( print ( ) )
                .andExpect ( status ( ).isOk ( ) );
    }

    @Test
    public void getProductEdit () throws Exception {
        this.mockMvc.perform ( get ( "/products/edit/{id}", "1" ) )
                .andDo ( print ( ) )
                .andExpect ( status ( ).isOk ( ) );
    }

    @Test
    public void getDeleteProduct () throws Exception {
        this.mockMvc.perform ( get ( "/products/{id}", "1" ) )
                .andDo ( print ( ) )
                .andExpect ( status ( ).is3xxRedirection ( ) );
    }
}
