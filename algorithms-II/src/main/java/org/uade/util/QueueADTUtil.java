package org.uade.util;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;

public class QueueADTUtil {

    // Factory
    public static QueueADT getNewQueue(QueueADT queue) {
        if (queue instanceof DynamicQueueADT) {
            return new DynamicQueueADT();
        }
        return new StaticQueueADT();
    }

    // Copy
    public static QueueADT copy(QueueADT queue) {
        QueueADT copy = getNewQueue(queue);
        QueueADT aux = getNewQueue(queue);

        while(!queue.isEmpty()) {
            copy.add(queue.getElement());
            aux.add(queue.getElement());
            queue.remove();
        }

        while (!aux.isEmpty()) {
            queue.add(aux.getElement());
            aux.remove();
        }

        return copy;
    }

    // Print
    public static void print(QueueADT queue) {
        QueueADT cqueue = copy(queue);

        while (!cqueue.isEmpty()) {
            System.out.println(cqueue.getElement());
            cqueue.remove();
        }

    }

}
