import de.Mustermanner.bs14.Purchase;
import de.Mustermanner.bs14.controller.PurchaseController;
import de.Mustermanner.bs14.service.PurchaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PurchaseControllerTest {

    @Mock
    private PurchaseService purchaseService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    private PurchaseController purchaseController;

    @BeforeEach
    public void setup() {
        //Setup Mockito and Mocked Controller Class
        MockitoAnnotations.openMocks(this);
        purchaseController = new PurchaseController(purchaseService);
    }

    @Test
    public void getPurchasesTest() {
        // Creating sample data
        List<Purchase> purchases = List.of(new Purchase(), new Purchase());

        // Mocking the behavior of the method
        when(purchaseService.getPurchases()).thenReturn(purchases);

        // Invoking the getPurchase method
        String result = purchaseController.getPurchase(model);

        // Verifying the interactions
        verify(model).addAttribute("purchases", purchases);

        // Verifying the return value
        assertEquals("new-purchase", result);
    }

    @Test
    public void createPurchaseTest() {
        // Creating a sample Data
        Purchase purchase =
                new Purchase("123", "1234567890", "TestName", 10, 10);

        // Invoking the method
        String result = purchaseController.createPurchase(purchase, bindingResult, model);

        // Verifying the interactions
        verify(purchaseService).createPurchase(purchase);

        // Verifying the return value
        assertEquals("redirect:/purchase", result);
    }

    @Test
    public void deletePurchaseTest() {
        // Mocking the request parameter and creating sample Data
        String purchaseId = "123";
        purchaseController
                .createPurchase(new Purchase(purchaseId, "1234567890", "TestName", 10, 10), bindingResult, model);

        // Invoking the deletePurchase method
        String result = purchaseController.deletePurchase(purchaseId);

        // Verifying the interactions
        verify(purchaseService).deletePurchase(purchaseId);

        // Verifying the return value
        assertEquals("redirect:/purchase", result);
    }

    @Test
    public void testGetPurchasesFromBuyer() {
        // Mocking the request parameter
        String buyerId = "123";

        // Creating sample data
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(new Purchase("123", "123", "TestName", 10, 10));
        purchases.add(new Purchase("456", "456", "TestName", 10, 10));

        // Mocking the behavior of the purchaseService.getPurchases() method
        when(purchaseService.getPurchases()).thenReturn(purchases);

        // Invoking the getPurchasesFromBuyer method
        String result = purchaseController.getPurchasesFromBuyer(buyerId, model);

        // Verifying the interactions
        verify(model).addAttribute("purchases", purchases.subList(0, 1));

        // Verifying the return value
        assertEquals("specific-purchase", result);
    }

    @Test
    public void testGetPurchasesFromShareName() {
        // Mocking the request parameter
        String shareName = "ABC";

        // Creating sample data
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(new Purchase("123", "1234567890", "ABC", 10, 10));
        purchases.add(new Purchase("123", "1234567890", "DEF", 10, 10));
        purchases.add(new Purchase("123", "1234567890", "ABC", 10, 10));

        // Mocking the behavior of the purchaseService.getPurchases() method
        when(purchaseService.getPurchases()).thenReturn(purchases);

        // Invoking the getPurchasesFromShareName method
        String result = purchaseController.getPurchasesFromShareName(shareName, model);

        // Verifying the interactions
        List<Purchase> expectedPurchases = new ArrayList<>();
        expectedPurchases.add(purchases.get(0));
        expectedPurchases.add(purchases.get(2));
        verify(model).addAttribute("purchases", expectedPurchases);

        // Verifying the return value
        assertEquals("specific-purchase", result);
    }

    @Test
    public void testGetPurchasesFromTransactionsId() {
        // Mocking the request parameter
        String transactionId = "123";

        // Creating sample data
        List<Purchase> purchases = new ArrayList<>();
        purchases.add(new Purchase("123", "1234567890", "ABC", 10, 10));
        purchases.add(new Purchase("456", "1234567890", "ABC", 10, 10));
        purchases.add(new Purchase("123", "1234567890", "ABC", 10, 10));

        // Mocking the behavior of the purchaseService.getPurchases() method
        when(purchaseService.getPurchases()).thenReturn(purchases);

        // Invoking the getPurchasesFromTransactionsId method
        String result = purchaseController.getPurchasesFromTransactionsId(transactionId, model);

        // Verifying the interactions
        List<Purchase> expectedPurchases = new ArrayList<>();
        expectedPurchases.add(purchases.get(0));
        expectedPurchases.add(purchases.get(2));
        verify(model).addAttribute("purchases", expectedPurchases);

        // Verifying the return value
        assertEquals("specific-purchase", result);
    }

}
