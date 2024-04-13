import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static BlockingQueue<String> queue1 = new ArrayBlockingQueue<>(100);
    public static BlockingQueue<String> queue2 = new ArrayBlockingQueue<>(100);
    public static BlockingQueue<String> queue3 = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                try {
                    queue1.put(generateText("abc", 1));
                    queue2.put(generateText("abc", 1));
                    queue3.put(generateText("abc", 1));
                } catch (InterruptedException e) {
                    return;
                }
            }

        }).start();

        new Thread(() -> {
            int countA1 = 0;
            int countA2 = 0;
            int countA3 = 0;
            for (int j = 0; j < 100; j++) {
                try {
                    if (queue1.take().equals("a")) {
                        countA1++;
                    }
                    if (queue2.take().equals("a")) {
                        countA2++;
                    }
                    if (queue3.take().equals("a")) {
                        countA3++;
                    }

                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();

        new Thread(() -> {
            int countB1 = 0;
            int countB2 = 0;
            int countB3 = 0;
            for (int j = 0; j < 100; j++) {
                try {
                    if (queue1.take().equals("b")) {
                        countB1++;
                    }
                    if (queue2.take().equals("b")) {
                        countB2++;
                    }
                    if (queue3.take().equals("b")) {
                        countB3++;
                    }

                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();
        new Thread(() -> {
            int countC1 = 0;
            int countC2 = 0;
            int countC3 = 0;
            for (int j = 0; j < 100; j++) {
                try {
                    if (queue1.take().equals("c")) {
                        countC1++;
                    }
                    if (queue2.take().equals("c")) {
                        countC2++;
                    }
                    if (queue3.take().equals("c")) {
                        countC3++;
                    }

                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();
    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}