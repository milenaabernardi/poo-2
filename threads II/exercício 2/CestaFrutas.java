import java.util.ArrayList;

public class CestaFrutas implements Runnable {
    @Override
    public void run() {
        ArrayList<String> frutas = new ArrayList<>();
        frutas.add("Maçã");
        frutas.add("Banana");
        frutas.add("Laranja");
        frutas.add("Manga");
        frutas.add("Uva");

        for (String fruta : frutas) {
            System.out.println("Fruta: " + fruta);
            try {
                Thread.sleep(1000); // pausa de 1 segundo
            } catch (InterruptedException e) {
                System.out.println("Thread interrompida.");
            }
        }
    }
}
