package org.uade.algorithm.priorityqueue.mios;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;
import org.uade.util.PriorityQueueADTUtil;

// Dada una cola con prioridad, implementa un método que elimine los elementos
// duplicados que tienen la misma prioridad, dejando solo el primero que apareció.
public class AddExercise033 {
    public static void main(String[] args) {
        PriorityQueueADT pqueue = new DynamicPriorityQueueADT();
        pqueue.add(1, 10);
        pqueue.add(7, 5);
        pqueue.add(2, 8);
        pqueue.add(17, 5);
        pqueue.add(15, 10);

        System.out.println("Priority Queue (Value | Priority): ");
        PriorityQueueADTUtil.print(pqueue);

        System.out.println("Result queue (Value | Priority): ");
        PriorityQueueADT result = removeDuplicatesByPrio(pqueue, 5);
        PriorityQueueADTUtil.print(result);

    }


    public static PriorityQueueADT removeDuplicatesByPrio(PriorityQueueADT pqueue, int prioToDelete) {
        if (pqueue.isEmpty()) {
            throw new EmptyADTException();
        }
        PriorityQueueADT cpq = PriorityQueueADTUtil.copy(pqueue);
        PriorityQueueADT result = new DynamicPriorityQueueADT();

        while (!cpq.isEmpty()) {
            PriorityQueueADT tempResult = PriorityQueueADTUtil.copy(result);
            Boolean exists = false;
            while (!tempResult.isEmpty()) {
                int currentPrio = tempResult.getPriority();
                if (currentPrio == cpq.getPriority()) {
                    exists = true;
                }
                tempResult.remove();
            }

            if (!exists) {
                result.add(cpq.getElement(), cpq.getPriority());
            }
            cpq.remove();
        }
        return result;
    }


}
