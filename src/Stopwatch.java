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
     * Peatab stopperi ja summeerib aja
     */
    public void stop() {
        if (running) {
            elapsedTime += System.currentTimeMillis() - startTime;
            running = false;
        }
    }

    /**
     * Tagastab mänguaja millisekundites
     * @return mängu aeg millisekundites
     */
    public long getElapsedMillis() {
      if (running) {
          return elapsedTime + (System.currentTimeMillis() - startTime);
      } else {
          return elapsedTime;
      }
    }

    /**
     * kajastab vormindatud mänguaega kujul hh:mm:ss
     * @return vormindatud aeg stringis
     */
    public String getElapsedTimeFormatted() {
        long totalMillis = getElapsedMillis();
        long totalSeconds = totalMillis / 1000;
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Lähtestab stopperi (nullib)
     */
    public void reset() {
        startTime = 0;
        elapsedTime = 0;
        running = false;
    }
}

