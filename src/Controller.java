/**
 * Käivitab mängu
 */
public class Controller {
    private final Model model;
    private final View view;

    /**
     * Kontrolleri konstruktor
     *
     * @param model App failis loodud mudel
     * @param view  App failisi loodud view
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
                    // view.showMessage(String.valueOf(model.getPc_number())); // TEST, mida arvuti mõtles
                    Stopwatch stopwatch = new Stopwatch(); // Loome stopperi
                    stopwatch.start(); // Käivitame stopperi
                    while (!model.isGame_over()) { // Kui mäng pole läbi
                        int guess = view.askGuess(); // Küsi kasutajalt numbrit
                        if (guess == 1000) {
                            view.showMessage("Õige vastus oli: " + model.getPc_number());
                        }
                        view.showMessage(model.checkGuess(guess));
                    }
                    stopwatch.stop(); // Peata stopper
                    // Kuvame vormindatud aja
                    view.showMessage("Mängu aeg: " + stopwatch.getElapsedTimeFormatted() + "(" + stopwatch.getElapsedMillis() + " ms)");
                    if (model.getSteps() == 1000) {
                        view.showMessage("Tagauks on avatud - Tulemust ei salvestata.");
                    } else {
                        String name = view.askName(); // Küsi nime juhul kui pole tagauks
                        model.saveScore(name, stopwatch.getElapsedMillis());
                    }
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
