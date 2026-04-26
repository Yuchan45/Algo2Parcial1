package org.uade.algorithm.queue.basic;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.util.QueueADTUtil;
import org.uade.util.SetADTUtil;

// 20.c - Generar el conjunto de elementos que se repiten en una Cola,
public class BasicQueueExercise20c {

    public static void main(String[] args) {
        QueueADT queue = new StaticQueueADT();

        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(2);
        queue.add(4);
        queue.add(3);
        queue.add(5);
        queue.add(1);

        System.out.println("Cola original:");
        QueueADTUtil.print(queue);

        SetADT repeated = getRepeatedElements(queue);

        System.out.println("\nElementos repetidos:");
        SetADTUtil.print(repeated);
    }

    public static SetADT getRepeatedElements(QueueADT queue) {
        QueueADT tempQueue = new StaticQueueADT();
        SetADT seenElements = new StaticSetADT();
        SetADT repeatedElements = new StaticSetADT();

        while (!queue.isEmpty()) {
            int element = queue.getElement();
            queue.remove();
            tempQueue.add(element);

            if (seenElements.exist(element)) {
                repeatedElements.add(element);
            } else {
                seenElements.add(element);
            }
        }

        // Restore the original queue
        while (!tempQueue.isEmpty()) {
            queue.add(tempQueue.getElement());
            tempQueue.remove();
        }

        return repeatedElements;
    }
}
