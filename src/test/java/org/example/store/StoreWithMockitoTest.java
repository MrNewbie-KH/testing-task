package org.example.store;

import org.example.account.AccountManager;
import org.example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;


//Cover Store buy with tests
public class StoreWithMockitoTest {

    @Test
    public void givenProductWithPositiveQuantityAndCustomerWithSufficientBalance_whenBuy_thenSuccess() {

        // Arrange
        Product product = new Product();
        product.setPrice(100);
        product.setQuantity(4);
        Customer customer = new Customer();
        AccountManager accountManager = mock(AccountManager.class);
        when(accountManager.withdraw(customer, 80))
                .thenReturn("success");
        when(accountManager.withdraw(customer, 100))
                .thenReturn("success");
        Store store = new StoreImpl(accountManager);

        // Act
        store.buy(product, customer);

        // Assert
        Assertions.assertEquals(3, product.getQuantity());
        verify(accountManager).withdraw(customer, 100);
    }
    @Test
    public void givenProductWithZeroQuantityAndCustomerWithSufficientBalance_whenBuy_thenNotEnoughStock() {

        // Arrange
        Product product = new Product();
        product.setQuantity(0);
        Customer customer = new Customer();
        AccountManager accountManager = mock(AccountManager.class); // not needed just mocked
        Store store = new StoreImpl(accountManager);

        // Act

        // Assert
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> store.buy(product, customer));
        Assertions.assertEquals("Product out of stock", exception.getMessage());
    }
    @Test
    public void givenProductWithPositiveQuantityAndCustomerWithNotSufficientBalance_whenBuy_thenFail(){
//        Arrange
        Product product = new Product();
        product.setPrice(100);
        product.setQuantity(4);
        Customer customer = new Customer();
        AccountManager accountManager = mock(AccountManager.class);
        Store store = new StoreImpl(accountManager);
//        act
        when(accountManager.withdraw(customer, 80)).thenReturn("insufficient account balance");

//        assert
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class,()->store.buy(product,customer));
        Assertions.assertEquals("Payment failure: insufficient account balance", ex.getMessage());

    }
}
