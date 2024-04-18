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
                String letterForQueue1 = generateText("abc", 1);
                String letterForQueue2 = generateText("abc", 1);
                String letterForQueue3 = generateText("abc", 1);
                try {
                    queue1.put(letterForQueue1);
                    queue2.put(letterForQueue2);
                    queue3.put(letterForQueue3);
                    System.out.println("добавлена " + i + " буква");
                } catch (InterruptedException e) {
                    return;
                }
            }
        }).start();
        new Thread(() -> {
            int countA = 0;
            for (int i = 0; i < 100_000; i++) {
                try {
                    if (queue1.take().equals("a")) {
                        countA++;
                    }
                    if (queue2.take().equals("a")) {
                        countA++;
                    }
                    if (queue3.take().equals("a")) {
                        countA++;
                    }

                } catch (InterruptedException e) {
                    return;
                }
                System.out.println("букв a " + countA);
            }

        }).start();
        new Thread(() -> {
            int countB = 0;
            for (int i = 0; i < 100_000; i++) {
                try {
                    if (queue1.take().equals("b")) {
                        countB++;
                    }
                    if (queue2.take().equals("b")) {
                        countB++;
                    }
                    if (queue3.take().equals("b")) {
                        countB++;
                    }

                } catch (InterruptedException e) {
                    return;
                }
                System.out.println("букв b " + countB);
            }

        }).start();
        new Thread(() -> {
            int countC = 0;
            for (int i = 0; i < 100_000; i++) {
                try {
                    if (queue1.take().equals("c")) {
                        countC++;
                    }
                    if (queue2.take().equals("c")) {
                        countC++;
                    }
                    if (queue3.take().equals("c")) {
                        countC++;
                    }

                } catch (InterruptedException e) {
                    return;
                }
                System.out.println("букв с " + countC);
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