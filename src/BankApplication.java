import exceprions.WrongAccountException;
import exceprions.WrongCurrencyException;
import exceprions.WrongOperationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankApplication {

    private final List<Account> accounts = new ArrayList<>() {{
        add(new Account("accountId001", 100, "USD"));
        add(new Account("accountId002", 500, "EUR"));
        add(new Account("accountId003", 250, "HRV"));
        add(new Account("accountId004", 1000, "USD"));
        add(new Account("accountId005", 750, "USD"));
        add(new Account("accountId006", 50, "USD"));
    }};


    private void processChecker(String accountId, int amount, String currency) throws Exception {

        accounts.stream().filter(account -> account.getId().equals(accountId))
                .findAny().orElseThrow(WrongAccountException::new);

        accounts.stream().filter(account -> account.getId().equals(accountId))
                .filter(account -> account.getCurrency().equals(currency))
                .findAny().orElseThrow(WrongCurrencyException::new);

        accounts.stream().filter(account -> account.getId().equals(accountId))
                .filter(account -> account.getCurrency().equals(currency))
                .filter(account -> account.getBalance() >= amount)
                .findAny().orElseThrow(WrongOperationException::new);


        Account desiredAccount = accounts.stream()
                .filter(account -> account.getId().equals(accountId))
                .filter(account -> account.getCurrency().equals(currency))
                .filter(account -> account.getBalance() >= amount).findAny()
                .orElseThrow();

       int randomInt = new Random().nextInt(2);

       if(randomInt == 1){
           throw new Exception();
       }

        desiredAccount.setBalance(desiredAccount.getBalance() - amount);
    }

    public void process (String accountId, int amount, String currency){
        System.out.println("--------------------------");
        try {
            processChecker(accountId, amount, currency);
        } catch (WrongAccountException wrongAccountException){
            System.out.println("This account doesn't exist");
        } catch (WrongCurrencyException wrongCurrencyException){
            System.out.println("This account have another currency");
        } catch (WrongOperationException wrongOperationException){
            System.out.println("Not enough money. Please check your balance");
        } catch (Exception exception){
            System.out.println("Something went wrong. Please try again.");
        } finally {
            System.out.println("Thank you for using our service! :)");
        }
    }
}
