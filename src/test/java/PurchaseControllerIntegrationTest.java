import de.Mustermanner.bs14.Purchase;
import de.Mustermanner.bs14.controller.PurchaseController;
import de.Mustermanner.bs14.service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = PurchaseController.class)
@AutoConfigureMockMvc
public class PurchaseControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PurchaseService purchaseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getPurchaseTest() throws Exception {
        // Creating sample data
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(new Purchase("123", "123", "TestName", 10, 10));
        purchases.add(new Purchase("456", "456", "TestName2", 20, 20));


        // Mocking the behavior of the purchaseService.getPurchases() method
        when(purchaseService.getPurchases()).thenReturn(purchases);

        mockMvc.perform(MockMvcRequestBuilders.get("/purchase")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("new-purchase"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("purchases"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("newPurchase"));
    }
}
