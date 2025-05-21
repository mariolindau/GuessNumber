import java.io.*;
import java.util.*;

/**
 * Kogu mändu loogika asub siin.
 */
public class Model {
    private final String fileName = "scoreboard.txt";
    private final List<Content> scoreboard = new ArrayList<>(); // Edetabeli faili sisu

    private int pc_number; // Arvuti mõeldud number
    private int steps; // Käikude lugeja
    private boolean game_over; // Kas mäng on läbi

    /**
     * Uue mängu loomine
     */
    public void initGame() {
        int MINIMUM = 1;
        int MAXIMUM = 100;
        pc_number = new Random().nextInt(MAXIMUM - MINIMUM + 1) + MINIMUM;
        game_over = false;
        steps = 0;
    }

    //GETTERS

    /**
     * Arvuti "mõeldud" number
     *
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
        if (guess == 1000) {
            game_over = true;
            steps = 1000; // See oli tagauks
            return "Tagauks avatud!";
        }

        steps++; // Sammude arv kasvab
        if (guess == pc_number) {
            game_over = true;
            return "Sa võitsid " + steps + " sammuga.";
        } else if (guess < pc_number) {
            return "Liiga väike";
        } else {
            return "Liiga suur";
        }
    }

    public int getSteps() {
        return steps;
    }

    /**
     * Salvestab listi sisu (edetabel) uuesti faili (kirjutab üle)
     *
     * @param name mängija nimi
     */
    public void saveScore(String name, long durationMillis) {
        if (steps == 1000) {
            System.out.println("Tagauks. Tulemust ei salvestata!");
            return;
        }

        loadScores(); // lae failist sisu listi
        scoreboard.add(new Content(name, steps, durationMillis, null)); // Lisa uus info, milles on ka aeg
        Collections.sort(scoreboard); // Sorteerib listi (content compareTo() omaga)

        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (Content c : scoreboard) {
                // kirjuta ka aeg
                out.println(c.getTimestamp() + ";" + c.getName() + ";" + c.getSteps() + ";" + c.getDurationMillis());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Laeb edetabeli faili sisu ja lisab sisu listi
     *
     * @return edetabeli list
     */
    public List<Content> loadScores() {
        scoreboard.clear(); // Tühjenda list
        File file = new File(fileName);
        if (!file.exists()) return scoreboard;  // Kui faili ei ole tagastab listi

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) { // Kui failis on järgmine rida
                String[] parts = sc.nextLine().split(";"); // Semikoolon jutumärkides
                if (parts.length == 4) {
                    // uus formaat "timestamp;name;steps;durationMillis
                    String timestamp = parts[0]; // Timestampi ei kasutata otse, info genereerub ise
                    String name = parts[1];
                    int steps = Integer.parseInt(parts[2]);
                    long duration = Long.parseLong(parts[3]);
                    scoreboard.add(new Content(name, steps, duration, timestamp));
                } else if (parts.length == 2) {
                    // vana formaat: name;steps
                    String name = parts[0];
                    int steps = Integer.parseInt(parts[1]);

                    scoreboard.add(new Content(name, steps, 0, null)); // kesvus puudub
                } else {
                    System.out.println("Vigane rida failis: " + String.join(";", parts));
                }
            }
            Collections.sort(scoreboard); // Sorteerib listi ( ilmselt siin üleliigne)
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return scoreboard;
    }
}