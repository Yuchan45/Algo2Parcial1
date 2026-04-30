package org.uade.util;

import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;
import org.uade.structure.implementation.fixed.StaticPriorityQueueADT;

public class PriorityQueueADTUtil {
    // Factory
    public static PriorityQueueADT getNewQueue(PriorityQueueADT queue) {
        if (queue instanceof DynamicPriorityQueueADT) {
            return new DynamicPriorityQueueADT();
        }
        return new StaticPriorityQueueADT();
    }

    // Copy
    public static PriorityQueueADT copy(PriorityQueueADT queue) {
        PriorityQueueADT copy = getNewQueue(queue);
        PriorityQueueADT aux = getNewQueue(queue);

        while(!queue.isEmpty()) {
            copy.add(queue.getElement(), queue.getPriority());
            aux.add(queue.getElement(), queue.getPriority());
            queue.remove();
        }

        while (!aux.isEmpty()) {
            queue.add(aux.getElement(), aux.getPriority());
            aux.remove();
        }

        return copy;
    }

    // Print
    public static void print(PriorityQueueADT queue) {
        PriorityQueueADT cqueue = copy(queue);

        while (!cqueue.isEmpty()) {
            System.out.println(cqueue.getElement() + " (Priority: " + cqueue.getPriority() + ")");
            cqueue.remove();
        }

    }
}
