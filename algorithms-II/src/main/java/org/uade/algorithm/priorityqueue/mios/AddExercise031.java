package org.uade.algorithm.priorityqueue.mios;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;
import org.uade.structure.implementation.fixed.StaticPriorityQueueADT;

// Eliminar de una cola con prioridad un elemento con una prioridad especifica.
public class AddExercise031 {
    public static void main(String[] args) {
        PriorityQueueADT pqueue = new DynamicPriorityQueueADT();
        pqueue.add(1, 10);
        pqueue.add(7, 5);
        pqueue.add(2, 8);
        pqueue.add(17, 5);

        System.out.println("Priority Queue (Value | Priority): ");
        print(pqueue);

        System.out.println("Result queue (Value | Priority): ");
        PriorityQueueADT result = removeElementsByPriority(pqueue, 5);
        print(result);

    }

    // Remove elements by priority
    public static PriorityQueueADT removeElementsByPriority(PriorityQueueADT pqueue, int prioToDelete) {
        if (pqueue.isEmpty()) {
            throw new EmptyADTException();
        }
        PriorityQueueADT cpq = copy(pqueue);
        PriorityQueueADT result = getNewPriorityQueue(pqueue);

        while (!cpq.isEmpty()) {
            int currentPrio = cpq.getPriority();
            if (currentPrio != prioToDelete) {
                result.add(cpq.getElement(), cpq.getPriority());
            }
            cpq.remove();
        }
        return result;
    }

    // Factory
    public static PriorityQueueADT getNewPriorityQueue(PriorityQueueADT pQueue) {
        if (pQueue instanceof DynamicPriorityQueueADT) {
            return new DynamicPriorityQueueADT();
        }
        return new StaticPriorityQueueADT();
    }

    // Copy
    public static PriorityQueueADT copy(PriorityQueueADT pQueue) {
        PriorityQueueADT cpq = getNewPriorityQueue(pQueue);
        PriorityQueueADT aux = getNewPriorityQueue(pQueue);

        while (!pQueue.isEmpty()) {
            cpq.add(pQueue.getElement(), pQueue.getPriority());
            aux.add(pQueue.getElement(), pQueue.getPriority());
            pQueue.remove();
        }

        // Realimentamos la pQueue original
        while (!aux.isEmpty()) {
            pQueue.add(aux.getElement(), aux.getPriority());
            aux.remove();
        }

        return cpq;
    }

    // Print
    public static void print(PriorityQueueADT pqueue) {
        PriorityQueueADT cpq = copy(pqueue);

        while (!cpq.isEmpty()) {
            System.out.print("(" + cpq.getElement() + "|" + cpq.getPriority() + "), ");
            cpq.remove();
        }
        System.out.println("");

    }

}
