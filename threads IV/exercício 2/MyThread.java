public class MyThread implements Runnable {
    Thread thread;
    private boolean pausada = false;
    private boolean encerrada = false;

    public MyThread(String nome) {
        thread = new Thread(this, nome);
        thread.start();
    }

    public void run() {
        System.out.println(thread.getName() + " iniciando.");

        try {
            for (int i = 1; i <= 100; i++) {
                synchronized (this) {
                    while (pausada) {
                        wait();
                    }
                    if (encerrada) break;
                }

                System.out.print(i + " ");
                if (i % 10 == 0) {
                    System.out.println();
                    Thread.sleep(250);
                }
            }
        } catch (InterruptedException e) {
            System.out.println(thread.getName() + " interrompida.");
        }

        System.out.println("\n" + thread.getName() + " finalizando.");
    }

    public synchronized void pausar() {
        pausada = true;
    }

    public synchronized void retomar() {
        pausada = false;
        notify();
    }

    public synchronized void encerrar() {
        encerrada = true;
        pausada = false;
        notify();
    }
}
