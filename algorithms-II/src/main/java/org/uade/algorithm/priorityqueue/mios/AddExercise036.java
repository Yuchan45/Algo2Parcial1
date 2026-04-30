package org.uade.algorithm.priorityqueue.mios;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;
import org.uade.util.PriorityQueueADTUtil;

// Dada una cola con prioridad y un valor de prioridad mínima, 
// devuelve una nueva cola que contenga solo los elementos cuya prioridad sea mayor o igual al valor
// especificado
public class AddExercise036 {
    public static void main(String[] args) {
        PriorityQueueADT pqueue = new DynamicPriorityQueueADT();
        pqueue.add(1, 10);
        pqueue.add(7, 5);
        pqueue.add(2, 8);
        pqueue.add(17, 5);
        pqueue.add(15, 10);

        int prio = 10;

        System.out.println("Priority Queue (Value | Priority)  - with prio=" + prio);
        PriorityQueueADTUtil.print(pqueue);

        System.out.println("Result Queue: ");
        PriorityQueueADT result = elementsWithPrioOver(pqueue, prio);
        PriorityQueueADTUtil.print(result);
    }

    public static PriorityQueueADT elementsWithPrioOver(PriorityQueueADT pqueue, int prio) {
        if (pqueue.isEmpty()) {
            throw new EmptyADTException();
        }
        PriorityQueueADT cpq = PriorityQueueADTUtil.copy(pqueue);
        PriorityQueueADT result = new DynamicPriorityQueueADT();

        while (!cpq.isEmpty()) {
            int currentPrio = cpq.getPriority();
            if (currentPrio >= prio) {
                result.add(cpq.getElement(), cpq.getPriority());
            }
            cpq.remove();
        }

        return result;
    }
}
