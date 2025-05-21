/**
 *  Mõõdab taustaks mängu aega.
 */
public class Stopwatch {
    private long startTime;
    private long elapsedTime;
    private boolean running;

    /**
     * käivitab stopperi
     */
    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    /**
     * Peatab stopperi
     */
    public void stop() {
        if (running) {
            elapsedTime = System.currentTimeMillis() - startTime;
            running = false;
        }
    }

    /**
     * kajastab vormindatud mänguaja
     * @return vormindatud aeg
     */
    public String getElapsedTime() {
        long totalMillis = elapsedTime;
        if (running) {
            totalMillis = System.currentTimeMillis() - startTime;
        }
        long seconds = totalMillis / 1000;
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    /**
     * Tagastab mänguaja millisekundites
     * @return mängu aeg
     */
    public long getElapsedMillis() {
        long totalMillis = elapsedTime;
        if (running) {
            totalMillis = System.currentTimeMillis() - startTime;
        }
        return totalMillis;
    }
}

