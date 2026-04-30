package org.uade.algorithm.priorityqueue.mios;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;
import org.uade.util.LinkedListADTUtil;
import org.uade.util.PriorityQueueADTUtil;

// Dada una cola con prioridad, implementa un método que extraiga los k elementos
// con mayor prioridad y los devuelva en una lista.
public class AddExercise035 {
    public static void main(String[] args) {
        PriorityQueueADT pqueue = new DynamicPriorityQueueADT();
        pqueue.add(1, 10);
        pqueue.add(7, 5);
        pqueue.add(2, 8);
        pqueue.add(17, 5);
        pqueue.add(15, 10);

        int k = 3;

        System.out.println("Priority Queue (Value | Priority)  - with K=" + k);
        PriorityQueueADTUtil.print(pqueue);

        System.out.println("Result List: ");
        LinkedListADT result = removeKelements(pqueue, k);
        LinkedListADTUtil.print(result);
    }

    public static LinkedListADT removeKelements(PriorityQueueADT pqueue, int k) {
        PriorityQueueADT cpq = PriorityQueueADTUtil.copy(pqueue);
        LinkedListADT result = new DynamicLinkedListADT();

        int i = 0;
        while (!cpq.isEmpty()) {
            if (i < 3) {
                result.add(cpq.getElement());
            }
            cpq.remove();
            i++;
        }

        return result;        
    }

}
