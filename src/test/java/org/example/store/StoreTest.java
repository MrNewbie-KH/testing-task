package org.example.store;

import org.example.account.AccountManager;
import org.example.account.AccountManagerImpl;
import org.example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StoreTest {

    @Test
    public void givenProductWithPositiveQuantityAndCustomerWithSufficientBalance_whenBuy_thenSuccess() {

        // Arrange

        Product product = new Product();
        product.setQuantity(4);
        Customer customer = new Customer();
        Store store = new StoreImpl(new AlwaysSuccessAccountManager());
        // Store store = new StoreImpl(new AccountManagerImpl())
        // Act
        store.buy(product, customer);

        // Assert
        Assertions.assertEquals(3, product.getQuantity());
    }


//    work around -> Stub
    static class AlwaysSuccessAccountManager implements AccountManager {
        @Override
        public void deposit(Customer customer, int amount) {

        }
        @Override
        public String withdraw(Customer customer, int amount) {
            return "success";
        }
    }

}
