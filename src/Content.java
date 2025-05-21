/**
 *  Klass mis on mõeldud edetabeli faili sisuga majandamiseks
 */
public class Content implements Comparable<Content> {
    private final String name;    // Mängija nimi
    private final int steps;      // Sammude arv

    /**
     * Objekti loomise konstruktor
     * @param name  Mängija nimi
     * @param steps Sammude arv
     */
    public Content(String name, int steps) {
        this.name = name;
        this.steps = steps;
    }

    /**
     * Tagastab mängija nime failist
     * @return mängijanimi
     */
    public String getName() {
        return name;
    }

    /**
     * Tagastab sammude arvu
     * @return sammude arv
     */
    public int getSteps() {
        return steps;
    }

    /**
     * Sorteerimine sammude järgi kahanevalt
     * @param o objekt mida võrrelda.
     * @return täisarv
     */
    @Override
    public int compareTo(Content o) {
       // return Integer.compare(o.steps, steps); // Kahanevalt
        return Integer.compare(steps, o.steps); // Kasvavalt (vaikimisi) .sort() .reversed()
    }

    /**
     * Vormindatud edetabel konsooli näitamiseks
     * @return vormindatud rida
     */
    public String formattedData() {
        String displayName = name.length() > 15 ? name.substring(0, 15) : String.format("%-15s", name);
        String n = String.format("%-15s", displayName);
        String s = String.format("%3d", steps);
        return n + s;
    }
}
