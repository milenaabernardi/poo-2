public class ContaBancaria {
    private double saldo = 1000;

    public synchronized void sacar(double valor) {
        if (saldo >= valor) {
            saldo -= valor;
            System.out.println(Thread.currentThread().getName() + " sacou R$ " + valor + " | Saldo atual: R$ " + saldo);
        } else {
            System.out.println(Thread.currentThread().getName() + " tentou sacar, mas não há saldo suficiente.");
        }
    }

    public synchronized void depositar(double valor) {
        saldo += valor;
        System.out.println(Thread.currentThread().getName() + " depositou R$ " + valor + " | Saldo atual: R$ " + saldo);
    }

    public synchronized double getSaldo() {
        return saldo;
    }
}
