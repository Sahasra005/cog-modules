class MessagePrinter extends Thread {
    private String message;

    public MessagePrinter(String message) {
        this.message = message;
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(message + " - " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted.");
            }
        }
    }
}

public class ThreadDemo {
    public static void main(String[] args) {
        MessagePrinter t1 = new MessagePrinter("Thread One");
        MessagePrinter t2 = new MessagePrinter("Thread Two");

        t1.start();
        t2.start();
    }
}
