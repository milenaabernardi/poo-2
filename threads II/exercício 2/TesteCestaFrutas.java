public class TesteCestaFrutas {
    public static void main(String[] args) {
        CestaFrutas cesta = new CestaFrutas();
        Thread thread = new Thread(cesta);
        thread.start();
    }
}
