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
        return Integer.parseInt(scanner.nextLine());
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
        System.out.print("Sisesta nimber: ");
        return Integer.parseInt(scanner.nextLine());
    }

    /**
     * Küsib mängija nime mängu lõpus
     * @return sisestatud nimi
     */
    public String askName() {
        System.out.print("Sissta nime: ");
        return scanner.nextLine();
    }

    /**
     *
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
