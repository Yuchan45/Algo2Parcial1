package org.uade.algorithm.queue.mio;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;

// Cree e inicialice la cola DADA. Pase el primer elemento de la cola DADA a su última
// posición, dejando los restantes elementos en el orden original.
public class AddExercice021 {
    public static void main(String[] args) {

        // Queue: [1, 2, 3, 4, 5]
        // Expected result: [2, 3, 4, 5, 1]
        QueueADT queue = new DynamicQueueADT();
        queue.add(1); // 1er elemento
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5); // Ultimo elemento de la cola

        System.out.println("Queue: ");
        print(queue);

        QueueADT result = firstToLast(queue);
        System.out.println("Result: ");
        print(result);

        
    }

    // First to Last function
    public static QueueADT firstToLast(QueueADT queue) {
        if (queue.isEmpty()) {
            return getNewQueue(queue);
        }

        QueueADT copyQueue = copy(queue);
        int firstValue = copyQueue.getElement();
        copyQueue.remove();
        QueueADT result = getNewQueue(queue);

        while (!copyQueue.isEmpty()) {
            result.add(copyQueue.getElement());
            copyQueue.remove();
        }
        
        // Agregamos el primer value al final de la cola
        result.add(firstValue);
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
        QueueADT copy = getNewQueue(queue);
        QueueADT aux = getNewQueue(queue);

        while (!queue.isEmpty()) {
            copy.add(queue.getElement());
            aux.add(queue.getElement());
            queue.remove();
        }

        // Dejamos queue como estaba
        while (!aux.isEmpty()) {
            queue.add(aux.getElement());
            aux.remove();
        }

        return copy;
    }

    // Print
    public static void print(QueueADT queue) {
        QueueADT copyQueue = copy(queue);

        while(!copyQueue.isEmpty()) {
            System.out.print(copyQueue.getElement() + " ");
            copyQueue.remove();
        }
        System.out.println("");
    }
}
