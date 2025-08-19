public class Pessoa implements Runnable {
    private Thread thread;
    private ContaBancaria conta;

    public Pessoa(String nome, ContaBancaria conta) {
        this.conta = conta;
        this.thread = new Thread(this, nome);
        this.thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            conta.sacar(200);
            conta.depositar(200);
        }
        System.out.println("Saldo final visto por " + thread.getName() + ": R$ " + conta.getSaldo());
    }

    public Thread getThread() {
        return thread;
    }
}
