package org.uade.algorithm.queue.mio;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.util.QueueADTUtil;

// Invertir el contenido de una Cola (pueden usarse Pilas auxiliares)
public class NormalExercise4b {
    public static void main(String[] args) {
        QueueADT queue = new DynamicQueueADT();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println("Queue: ");
        QueueADTUtil.print(queue);

        QueueADT result = invertQueue(queue);
        System.out.println("Result Queue: ");
        QueueADTUtil.print(result);
    }

    public static QueueADT invertQueue(QueueADT queue) {
        QueueADT cqueue = QueueADTUtil.copy(queue);
        StackADT stack = new DynamicStackADT();
        QueueADT result = new DynamicQueueADT();

        while (!cqueue.isEmpty()) {
            stack.add(cqueue.getElement());
            cqueue.remove();
        }

        while (!stack.isEmpty()) {
            result.add(stack.getElement());
            stack.remove();
        }

        return result;
    }
}
