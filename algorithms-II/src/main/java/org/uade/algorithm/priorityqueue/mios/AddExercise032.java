package org.uade.algorithm.priorityqueue.mios;

import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;
import org.uade.util.PriorityQueueADTUtil;

// Dada una cola con prioridad, implementa un método que distribuya los elementos en
// varias colas separadas, una por cada nivel de prioridad.
public class AddExercise032 {
    public static void main(String[] args) {
        PriorityQueueADT pqueue = new DynamicPriorityQueueADT();
        pqueue.add(1, 10);
        pqueue.add(7, 5);
        pqueue.add(2, 8);
        pqueue.add(17, 5);

        System.out.println("Priority Queue (Value | Priority): ");
        PriorityQueueADTUtil.print(pqueue);

        // System.out.println("Result queue (Value | Priority): ");
        // PriorityQueueADT result = 
        // PriorityQueueADTUtil.print(result);

    }

}
