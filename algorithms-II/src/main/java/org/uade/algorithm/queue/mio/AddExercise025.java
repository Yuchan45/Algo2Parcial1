package org.uade.algorithm.queue.mio;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.util.QueueADTUtil;

// Eliminar de una cola un elemento específico con recursión
public class AddExercise025 {
    public static void main(String[] args) {
        QueueADT queue = new DynamicQueueADT();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(2);
        queue.add(1);

        System.out.println("Queue: ");
        QueueADTUtil.print(queue);

        deleteRecursively(queue, 3);
        System.out.println("Result: ");
        QueueADTUtil.print(queue);
    }

    public static void deleteRecursively(QueueADT queue, int valueToDelete) {
        if (queue.isEmpty()) {
            return;
        }

        int value = queue.getElement();
        queue.remove();

        deleteRecursively(queue, valueToDelete);

        if (value != valueToDelete) {
            queue.add(value);
        }

    }
}
