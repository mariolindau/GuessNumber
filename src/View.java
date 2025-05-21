import java.util.List;
import java.util.Scanner;

/**
 * Kõik mida kasutaja näeb konsoolis
 */
public class View {
    private final Scanner scanner = new Scanner(System.in);

    /**
     *  Mängu menüü näitamine
     */
    public void showMenu() {
        System.out.println("1. Mängima");
        System.out.println("2. Edetabel");
        System.out.println("3. Välju");
        System.out.print("Tee Valik: ");
    }

    /**
     *  Tagastab kasutaja sisestuse
     * @return kasutaja sisestus
     */
    public int getMenuChoice() {
        while (true) {
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Vigane sisestus! Palun sisesta arv 1 kuni 3: ");
            }
        }
    }

    /**
     * Väljastab etteantud teate konsooli
     * @param message teade mida väljastada
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Küsib kasutajalt numbrit
     * @return Kasutaja sisestatud number
     */
    public int askGuess() {
        int number;
        while (true) {
            System.out.print("Sisesta number: ");
            String input = scanner.nextLine();
            try {
                number = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Viga! Palun sisesta reaalne number.");
            }
        }
        return number;
    }

    /**
     * Küsib mängija nime mängu lõpus
     * @return sisestatud nimi
     */
    public String askName() {
        System.out.print("Sissta nimi: ");
        return scanner.nextLine();
    }

    /**
     * Näitab konsooli edetabelit
     * @param scores Faili sisu
     */
    public void showScoreBoard(List<Content> scores) {
        System.out.println("EDETABEL");
        System.out.println("--------");
        for (Content c : scores) {
            System.out.println(c.formattedData());
        }
        System.out.println();
    }
}