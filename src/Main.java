public class Main {

    public static void main(String[] args){
        BankApplication bankApplication = new BankApplication();
        bankApplication.process("accountId000", 50, "USD");
        bankApplication.process("accountId003", 250, "HRV");
        bankApplication.process("accountId001", 50, "EUR");
        bankApplication.process("accountId001", 50, "USD");
        bankApplication.process("accountId001", 50, "USD");
    }
}
