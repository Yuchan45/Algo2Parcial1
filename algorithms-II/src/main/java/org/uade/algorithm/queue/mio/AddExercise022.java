package org.uade.algorithm.queue.mio;

import org.uade.exception.GenericADTException;
import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;

// Dado dos colas, escribi un programa que las intercale. Por ejemplo, si tenes la cola
// A = [1, 3, 5] y la cola B = [2, 4, 6, 7, 8], 
// el resultado debería ser una nueva cola C = [1, 2, 3, 4, 5, 6, 7, 8].


public class AddExercise022 {
    public static void main(String[] args) {
        // Init
        QueueADT queue1 = new DynamicQueueADT();
        queue1.add(1);
        queue1.add(3);
        queue1.add(5);
        
        // Queue: [2, 4, 6, 7, 8]
        // 
        QueueADT queue2 = new DynamicQueueADT();
        queue2.add(2);
        queue2.add(4);
        queue2.add(6);
        queue2.add(7);
        queue2.add(8);
        
        System.out.println("Queue 1: ");
        print(queue1);
        System.out.println("Queue 2: ");
        print(queue2);

        // Merge
        QueueADT result = merge(queue1, queue2);
        System.out.println("Result: ");
        print(result);
    }

    // Merge funcion
    public static QueueADT merge(QueueADT queue1, QueueADT queue2) {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            throw new GenericADTException("Merge: Must receive at least 2 not empty queues.");
        }
        QueueADT cq1 = copy(queue1);
        QueueADT cq2 = copy(queue2);
        QueueADT result = getNewQueue(queue1);

        while (!cq1.isEmpty() && !cq2.isEmpty()) {
            result.add(cq1.getElement());
            cq1.remove();
            result.add(cq2.getElement());
            cq2.remove();
        }

        while (!cq1.isEmpty()) {
            result.add(cq1.getElement());
            cq1.remove();
        }

        while (!cq2.isEmpty()) {
            result.add(cq2.getElement());
            cq2.remove();
        }

        return result;
    }


    // Factory
    public static QueueADT getNewQueue(QueueADT queue) {
        if (queue instanceof DynamicQueueADT) {
            return new DynamicQueueADT();
        }
        return new StaticQueueADT();
    }

    // Copy
    public static QueueADT copy(QueueADT queue) {
        QueueADT aux = getNewQueue(queue);
        QueueADT copyQueue = getNewQueue(queue);

        while (!queue.isEmpty()) {
            aux.add(queue.getElement());
            copyQueue.add(queue.getElement());
            queue.remove();
        }

        // Re armamos queue
        while (!aux.isEmpty()) {
            queue.add(aux.getElement());
            aux.remove();
        }

        return copyQueue;
    }

    // Print
    public static void print(QueueADT queue) {
        QueueADT copyQueue = copy(queue);

        while (!copyQueue.isEmpty()) {
            System.out.print(copyQueue.getElement() + " ");
            copyQueue.remove();
        }
        System.out.println("");
    }
}
