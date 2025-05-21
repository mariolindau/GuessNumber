import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *  Klass mis on mõeldud edetabeli faili sisuga majandamiseks
 */
public class Content implements Comparable<Content> {
    private final String name;          // Mängija nimi
    private final int steps;            // Sammude arv
    private final String timestamp;     // Mõngu lõpetamise aeg kujul AAAA-KK-PP HH:MM:SS
    private final long durationMillis;  // Mängu kestvus millisekundites

    /**
     * Objekti loomise konstruktor
     * @param name  Mängija nimi
     * @param steps Sammude arv
     */
    public Content(String name, int steps, long durationMillis, String timestampParam) {
        this.name = name;
        this.steps = steps;
        this.durationMillis = durationMillis;

        if (timestampParam == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            this.timestamp = LocalDateTime.now().format(formatter);
        } else {
            this.timestamp = timestampParam;
        }
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

    public String getTimestamp() {
        return timestamp;
    }

    public long getDurationMillis() {
        return durationMillis;
    }

    /**
     * Sorteerimine sammude järgi kahanevalt
     * @param o objekt mida võrrelda.
     * @return täisarv
     */
    @Override
    public int compareTo(Content o) {
        int stepCompare = Integer.compare(this.steps, o.steps);
        if (stepCompare != 0) {
            return stepCompare;
        }
        return this.timestamp.compareTo(o.timestamp); // Varasem aeg eespool
    }

    /**
     * Vormindatud edetabel konsooli näitamiseks
     * @return vormindatud rida
     */
    public String formattedData() {
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter estonianFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

        String formattedtimestamp = LocalDateTime.parse(timestamp, inputFormat).format(estonianFormat);
        String displayName = name.length() > 15 ? name.substring(0, 15) : String.format("%-15s", name);
        String n = String.format("%-15s", displayName);
        String s = String.format("%3d", steps);
        String t = String.format("%-6d", durationMillis); // Kestvus millisek
        return formattedtimestamp + " " + n + s + " " + t + " ms";
    }
}