package org.uade.utils;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;

public class QueueADTUtils {

    // Factory
    private static QueueADT getNewQueue(QueueADT queue) {
        if (queue instanceof DynamicQueueADT) {
            return new DynamicQueueADT();
        }
        return new StaticQueueADT();
    }
    
    // Copy
    public static QueueADT copy(QueueADT queue) {
        QueueADT copy = getNewQueue(queue);
        QueueADT aux = getNewQueue(queue);

        while (!queue.isEmpty()) {
            copy.add(queue.getElement());
            aux.add(queue.getElement());
            queue.remove();
        }

        // Re alimentamos queue
        while (!aux.isEmpty()) {
            queue.add(aux.getElement());
            aux.remove();
        }

        return copy;
    }

    // Print
    public static void print(QueueADT queue) {
        QueueADT copy = copy(queue);

        while (!copy.isEmpty()) {
            System.out.println(copy.getElement());
            copy.remove();
        }
    }

}
