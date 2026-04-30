package org.uade.algorithm.queue.mio;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.util.QueueADTUtil;

// Invertir el contenido de una Cola (NO pueden usarse Pilas auxiliares)
public class NormalExercise4c {
    public static void main(String[] args) {
        QueueADT queue = new DynamicQueueADT();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println("Queue: ");
        QueueADTUtil.print(queue);

        invertQueue(queue);
        System.out.println("Result Queue: ");
        QueueADTUtil.print(queue);
    }

    public static QueueADT invertQueue(QueueADT queue) {
        if (queue.isEmpty()) {
            return queue;
        }

        int firstElement = queue.getElement();
        queue.remove();

        invertQueue(queue);
        queue.add(firstElement);

        return queue;
    }
}
