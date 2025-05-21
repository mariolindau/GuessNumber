import java.io.*;
import java.util.*;

/**
 *  Kogu mändu loogika asub siin.
 */
public class Model {
    private final int MINIMUM = 1; // Konstant
    private final int MAXIMUM = 100; // Konstant
    private final String fileName = "scoreboard.txt";
    private final List<Content> scoreboard = new ArrayList<>(); // Edetabeli faili sisu

    private int pc_number; // Arvuti mõeldud number
    private int steps; // Käikude lugeja
    private boolean game_over; // Kas mäng on läbi

    /**
     * Uue mängu loomine
     */
    public void initGame() {
        pc_number = new Random().nextInt(MAXIMUM - MINIMUM + 1) + MINIMUM;
        game_over = false;
        steps = 0;
    }

    //GETTERS

    /**
     * Arvuti "mõeldud" number
     * @return juhuslik number vahemikus 1-100
     */
    public int getPc_number() {
        return pc_number;
    }

    /**
     * Kas mäng on läbi
     *
     * @return true on läbi, false ei ole mäng läbi
     */
    public boolean isGame_over() {
        return game_over;
    }

    /**
     * Kontrollib kasutaja sisestust ja tagastab sobiva teksti
     *
     * @param guess number mida kontrollida
     * @return tekst mida näidatakse kasutajale
     */
    public String checkGuess(int guess) {
        steps++; // Sammude arv kasvab
        if (guess == pc_number) {
            game_over = true;
            return "Sa võidsid " + steps + " sammuga.";
        } else if (guess < pc_number) {
            return "Liiga väike";
        } else {
            return "Liiga suur";
        }
    }

    /**
     * Salvestab listi sisu (edetabel) uuesti faili (kirjutab üle)
     *
     * @param name mängija nimi
     */
    public void saveScore(String name) {
        loadScores(); // lae failist sisu listi
        scoreboard.add(new Content(name, steps)); // Lisa nimi ja sammude arv listi
        Collections.sort(scoreboard); // Sorteerib listi (content compareTo() omaga)
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (Content c : scoreboard) {
                out.println(c.getName() + ";" + c.getSteps()); // Semikoolon jutumärkides!
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Laeb edetabeli faili sisu ja lisab sisu listi
     * @return edetabeli list
     */
    public List<Content> loadScores() {
        scoreboard.clear(); // Tühjenda list
        File file = new File(fileName);
        if (!file.exists()) return scoreboard;  // Kui faili ei ole tagastab listi

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) { // Kui failis on järgmine rida
                String[] parts = sc.nextLine().split(";"); // Semikoolon jutumärkides
                if (parts.length == 2) {
                    String name = parts[0];
                    int steps = Integer.parseInt(parts[1]);
                    scoreboard.add(new Content(name, steps));
                }
            }
            Collections.sort(scoreboard); // Sorteerib listi ( ilmselt siin üleliigne)
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scoreboard;
    }
}