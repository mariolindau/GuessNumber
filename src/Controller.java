/**
 * Käivitab mängu
 */
public class Controller {
    private final Model model;
    private final View view;

    /**
     * Kontrolleri konstruktor
     * @param model App failis loodud mudel
     * @param view App failisi loodud view
     */
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Käivitab mängu loogika
     */
    public void start() {
        boolean running = true;
        while (running) {
            view.showMenu(); // Näita menüüd
            int choice = view.getMenuChoice(); // Küsi kasutajale menüü valikut (1, 2 või 3)
            switch (choice) {
                case 1: // Uus mäng
                    model.initGame(); // Loo uus mäng
                    view.showMessage(String.valueOf(model.getPc_number())); // TEST, mida arvuti mõtles
                    Stopwatch stopwatch = new Stopwatch(); // Loome stopperi
                    stopwatch.start(); // Käivitame stopperi
                    while (!model.isGame_over()) { // Kui mäng pole läbi
                        int guess = view.askGuess(); // Küsi kasutajalt numbrit
                        view.showMessage(model.checkGuess(guess)); // Kontrolli ja väljasta tulemus
                    }
                    stopwatch.stop(); // Peata stopper
                    // Näita aega järgnevalt: "Mängu aeg 00:00:00 (0000)"
                    view.showMessage("Mängu aeg: " + stopwatch.getElapsedTime() + " ("+ stopwatch.getElapsedMillis() +")");
                    String name = view.askName(); // Küsi nime
                    model.saveScore(name);
                    break;
                case 2: // Edetabel
                    // Näita edetabelit
                    view.showScoreBoard(model.loadScores());
                    break;
                case 3: // Väljumine mängust
                    running = false;
                    view.showMessage("Head aega");
                    break;
                default:
                    view.showMessage("Vigane valik");
            }
        }
    }
}
