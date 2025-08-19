public class UsaConta {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria();

        Pessoa p1 = new Pessoa("Pessoa-1", conta);
        Pessoa p2 = new Pessoa("Pessoa-2", conta);

        try {
            p1.getThread().join();
            p2.getThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Saldo final da conta (esperado R$1000,00): R$ " + conta.getSaldo());
    }
}
