package org.example.store;


import org.example.account.AccountManager;
import org.example.account.AccountManagerImpl;
import org.example.account.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//Cover AccountManager deposit, withdraw with tests
public class AccountManagerTest {

    @Test
    public void givenAccountAndPositiveAmount_whenDeposit_thenSuccess() {
//    arrange
        Customer customer = new Customer();
        customer.setBalance(500);
        AccountManagerImpl accountManager = new AccountManagerImpl();
//    act
        accountManager.deposit(customer,100);
//    assert
        Assertions.assertEquals(customer.getBalance(),600);

    }
//    ================================  WITHDRAW    =================================
    @Test
    public void givenAccountWithSufficientBalance_whenWithdraw_thenSuccess() {
//        Arrange
        Customer customer = new Customer();
        customer.setBalance(500);
        AccountManager accountManager = new AccountManagerImpl();
//        Act
        String message = accountManager.withdraw(customer, 100);
//        Assert
        Assertions.assertEquals(customer.getBalance(), 400);
        Assertions.assertEquals(message, "success");
    }
        @Test
        public void  givenCustomerWithNotSufficientBalanceAndNotCreditAllowed_whenWithdraw_thenReturnInsufficient(){

            //        Arrange
            Customer customer = new Customer();
            customer.setBalance(100);
            AccountManager accountManager = new AccountManagerImpl();
            //        Act
            String message = accountManager.withdraw(customer,200);
            //        Assert
            Assertions.assertEquals(customer.getBalance(),100);
            Assertions.assertEquals(message,"insufficient account balance");
        }
        @Test
        public void  givenCreditAllowedCustomerAndNotVIPToWithdrawAmountLessThanMAX_CREDIT_whenWithdraw_thenReturnSuccess(){

            //        Arrange
            Customer customer = new Customer();
            customer.setBalance(100);
            customer.setCreditAllowed(true);
            AccountManager accountManager = new AccountManagerImpl();
            //        Act
            String message = accountManager.withdraw(customer,200);
            //        Assert
            Assertions.assertEquals(customer.getBalance(),-100);
            Assertions.assertEquals(message,"success");
        }
        @Test
        public void  givenCreditAllowedCustomerAndVIP_whenWithdraw_thenReturnSuccess(){

            //        Arrange
            Customer customer = new Customer();
            customer.setBalance(100);
            customer.setCreditAllowed(true);
            customer.setVip(true);
            AccountManager accountManager = new AccountManagerImpl();
            //        Act
            String message = accountManager.withdraw(customer,2000);
            //        Assert
            Assertions.assertEquals(customer.getBalance(),-1900);
            Assertions.assertEquals(message,"success");
        }
        @Test
        public void  givenCreditAllowedCustomerAndNotVIPToWithdrawAmountMoreThanMAX_CREDIT_whenWithdraw_thenReturnSuccess(){

            //        Arrange
            Customer customer = new Customer();
            customer.setBalance(100);
            customer.setCreditAllowed(true);

            AccountManager accountManager = new AccountManagerImpl();
            //        Act
            String message = accountManager.withdraw(customer,2000);
            //        Assert
            Assertions.assertEquals(customer.getBalance(),100);
            Assertions.assertEquals(message,"maximum credit exceeded");
        }
}
