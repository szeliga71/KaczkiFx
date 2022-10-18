package Game.Elements;

public class Time implements Runnable{

        int sec;
        public boolean rozrusznik = true;

        public int sekunda;

        public void setRozrusznik(boolean rozrusznik) {
            this.rozrusznik = rozrusznik;
        }

        public void setTime(int sekunda) {
            this.sekunda = sekunda;
        }

        public int getTime() {
            return sekunda;
        }

        public void run() {
            sec = 0;
            while (rozrusznik) {
                sec++;
                try {
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setTime(sec);

            }
        }

    }

