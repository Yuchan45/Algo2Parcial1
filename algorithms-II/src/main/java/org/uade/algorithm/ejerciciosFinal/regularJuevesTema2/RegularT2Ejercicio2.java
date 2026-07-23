package org.uade.algorithm.ejerciciosFinal.regularJuevesTema2;

import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.definition.QueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.fixed.StaticPriorityQueueADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;

/**
 * RegularT2Ejercicio2
 * Pasar dos colas con prioridad a otra cola con los valores que tengan el mismo valor
 * y la misma prioridad.
 */
public class RegularT2Ejercicio2 {
    public static void main(String[] args) {
        // Init
        PriorityQueueADT pq1 = new StaticPriorityQueueADT();
        pq1.add(10, 1);
        pq1.add(20, 2);
        pq1.add(30, 3);
        pq1.add(40, 4);
        pq1.add(50, 5);

        PriorityQueueADT pq2 = new StaticPriorityQueueADT();
        pq2.add(11, 1); // No se deberia pasar
        pq2.add(20, 1); // No se deberia pasar
        pq2.add(30, 3);
        pq2.add(40, 4);
        pq2.add(50, 6); // No se deberia pasar

        printPQueue(pq1, "PQueue 1:");
        printPQueue(pq2, "PQueue 2:");

        QueueADT result = getSameValueAndPrio(pq1, pq2);
        printQueue(result, "Result: ");
        
    }

    public static QueueADT getSameValueAndPrio(PriorityQueueADT pqueue1, PriorityQueueADT pqueue2) {
        PriorityQueueADT pq1 = copyPQueue(pqueue1);
        PriorityQueueADT pq2 = copyPQueue(pqueue2);
        QueueADT resultQueue = new StaticQueueADT();

        while (!pq1.isEmpty()) {
            int valuePQ1 = pq1.getElement();
            int prioPQ1 = pq1.getPriority();

            // Recordar hacer una copia antes, porque hay que recuperarla antes de cada iteracion
            PriorityQueueADT copyPQ2 = copyPQueue(pq2);
            while (!copyPQ2.isEmpty()) {
                int valuePQ2 = copyPQ2.getElement();
                int prioPQ2 = copyPQ2.getPriority();
                if (valuePQ1 == valuePQ2 && prioPQ1 == prioPQ2) {
                    resultQueue.add(valuePQ1); // value de PQ1 o PQ2 es lo mismo
                }
                copyPQ2.remove();
            }

            pq1.remove();
        }

        return resultQueue;
    }

    public static QueueADT getNewQueue(QueueADT queue) {
        if (queue instanceof StaticQueueADT) {
            return new StaticQueueADT();
        }
        return new DynamicQueueADT();
    }

    public static QueueADT copyQueue(QueueADT queue) {
        QueueADT copy = getNewQueue(queue);
        QueueADT aux = getNewQueue(queue);

        while (!queue.isEmpty()) {
            int e = queue.getElement();
            copy.add(e);
            aux.add(e);
            queue.remove();
        }

        while (!aux.isEmpty()) {
            int e = aux.getElement();
            queue.add(e);
            aux.remove();
        }

        return copy;
    }

    public static void printQueue(QueueADT queue, String title) {
        QueueADT q = copyQueue(queue);

        System.out.println(title);
        while (!q.isEmpty()) {
            int e = q.getElement();
            System.out.print(e + ", ");
            q.remove();
        }
        System.out.println("\n");
    }

    public static PriorityQueueADT getNewPriorityQueue(PriorityQueueADT pqueue) {
        if (pqueue instanceof StaticPriorityQueueADT) {
            return new StaticPriorityQueueADT();
        }
        return new DynamicPriorityQueueADT();
    }

    public static PriorityQueueADT copyPQueue(PriorityQueueADT pqueue) {
        PriorityQueueADT copy = getNewPriorityQueue(pqueue);
        PriorityQueueADT aux = getNewPriorityQueue(pqueue);

        while (!pqueue.isEmpty()) {
            int value = pqueue.getElement();
            int priority = pqueue.getPriority();
            copy.add(value, priority);
            aux.add(value, priority);
            pqueue.remove();
        }

        while (!aux.isEmpty()) {
            int value = aux.getElement();
            int priority = aux.getPriority();
            pqueue.add(value, priority);
            aux.remove();
        }
        return copy;
    }

    public static void printPQueue(PriorityQueueADT priorityQueue, String title) {
        PriorityQueueADT pqueue = copyPQueue(priorityQueue);

        System.out.println(title);
        while (!pqueue.isEmpty()) {
            int value = pqueue.getElement();
            int priority = pqueue.getPriority();
            System.out.println("Value: " + value + " | Prio: " + priority);
            pqueue.remove();
        }

        System.out.println("\n");
    }
}
