package simbirsoft;

import java.util.LinkedList;
import java.util.Queue;

public class PizzaQueue {
    private final Queue<Pizza> myQueueOfPreparation = new LinkedList<Pizza>();
    private final Queue<Pizza> myQueueOfIssue = new LinkedList<Pizza>();

    public void add(Pizza pizza) {
        final int sleepTime = 10000;
        myQueueOfPreparation.add(pizza);
        Runnable myRunnable = () -> {
            try {
                Thread.sleep(sleepTime);
            } catch (Exception e) {
                System.out.println(e.toString());
            }
            ;
            myQueueOfIssue.add(myQueueOfPreparation.poll());
        };
        Thread th = new Thread(myRunnable);
        th.start();
    }
}
