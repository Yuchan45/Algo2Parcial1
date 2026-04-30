package org.uade.algorithm.queue.mio;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.util.QueueADTUtil;

// Determinar si el final de la Cola C1 coincide o no con la Cola C2.
public class NormalExercise4d {
    public static void main(String[] args) {
        QueueADT queue = new DynamicQueueADT();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);
        queue.add(6);

        System.out.println("Queue1: ");
        QueueADTUtil.print(queue);

        int lastElement = getQueueLastElement(queue);
        System.out.println("Queue1 Last Element: " + lastElement);

        QueueADT queue2 = new DynamicQueueADT();
        queue2.add(4);

        System.out.println("Queue2: ");
        QueueADTUtil.print(queue2);

        int lastElement2 = getQueueLastElement(queue2);
        System.out.println("Queue2 Last Element: " + lastElement2);

        if (lastElement == lastElement2) {
            System.out.println("El elemento final de ambas colas coincide");
        } else {
            System.out.println("El elemento final de ambas colas NO coincide");
        }

    }

    public static int getQueueLastElement(QueueADT queue) {
        if (queue.isEmpty()) {
            throw new EmptyADTException();
        }
        int size = getSize(queue);
        QueueADT cq = QueueADTUtil.copy(queue);

        int i = 0;
        int lastValue = cq.getElement();
        while (!cq.isEmpty()) {
            if (i == size-1) {
                lastValue = cq.getElement();
            }
            cq.remove();
            i++;
        }

        return lastValue;
    }

    public static int getSize(QueueADT queue) {
        QueueADT cq = QueueADTUtil.copy(queue);

        int i = 0;
        while (!cq.isEmpty()) {
            cq.remove();
            i++;
        }
        return i;
    }
}
