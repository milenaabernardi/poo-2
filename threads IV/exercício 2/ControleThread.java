public class ControleThread {
    public static void main(String[] args) {
        MyThread t = new MyThread("Minha Thread");

        try {
            Thread.sleep(1000);
            t.pausar();
            System.out.println("\nThread pausada.");

            Thread.sleep(1000);
            t.retomar();
            System.out.println("Thread retomada.");

            Thread.sleep(1000);
            t.pausar();
            System.out.println("Thread pausada novamente.");

            Thread.sleep(1000);
            t.retomar();
            System.out.println("Thread retomada novamente.");

            Thread.sleep(1000);
            t.encerrar();
            System.out.println("Encerrando thread...");

            t.thread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread principal interrompida.");
        }

        System.out.println("Thread principal finalizando.");
    }
}
