class MinhaThread implements Runnable {
    private String nome;

    public MinhaThread(String nome) {
        this.nome = nome;
    }

    public void run() {
        System.out.println(nome + " iniciando.");

        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("Em " + nome + ", contagem é " + i);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println(nome + " interrompida.");
        }

        System.out.println(nome + " terminando.");
    }
}

public class TesteThreads {
    public static void main(String[] args) {
        System.out.println("Thread principal iniciando.");

        MinhaThread t1 = new MinhaThread("Filha #1");
        MinhaThread t2 = new MinhaThread("Filha #2");
        MinhaThread t3 = new MinhaThread("Filha #3");

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);
        Thread thread3 = new Thread(t3);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.out.println("Thread principal interrompida.");
        }

        System.out.println("Thread principal finalizando.");
    }
}
