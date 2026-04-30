package org.uade.algorithm.stack.mios;

import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;
import org.uade.util.PriorityQueueADTUtil;

// Dadas 2 colas con prioridad, calcular la interseccion (INTERSECCION) por prioridad
public class IntersectionExtra {
    public static void main(String[] args) {
        // Sean:
        // pq1: [(12|10), (41|7), (32|4), (22|3)]
        // pq2: [(70|10), (14|3)]
        // Interseccion: [(70|10), (12|10), (22|3), (14|3)]

        PriorityQueueADT pq1 = new DynamicPriorityQueueADT();
        pq1.add(12, 10);
        pq1.add(22, 3);
        pq1.add(32, 4);
        pq1.add(41, 7);

        PriorityQueueADT pq2 = new DynamicPriorityQueueADT();
        pq2.add(70, 10);
        pq2.add(14, 3);

        PriorityQueueADT result = calcIntersection(pq1, pq2);
        System.out.println("Result:");
        PriorityQueueADTUtil.print(result);

    }

    public static PriorityQueueADT calcIntersection(PriorityQueueADT q1, PriorityQueueADT q2) {
        PriorityQueueADT pq1 = PriorityQueueADTUtil.copy(q1);
        PriorityQueueADT pq2 = PriorityQueueADTUtil.copy(q2);
        PriorityQueueADT result = new DynamicPriorityQueueADT();

        while (!pq1.isEmpty()) {
            int currentPq1Prio = pq1.getPriority();

            PriorityQueueADT tempPq2 = PriorityQueueADTUtil.copy(pq2);
            while (!tempPq2.isEmpty()) {
                int currentPq2Prio = tempPq2.getPriority();
                if (currentPq1Prio == currentPq2Prio) {
                    result.add(pq1.getElement(), currentPq1Prio);
                    result.add(tempPq2.getElement(), currentPq2Prio);
                }
                tempPq2.remove();
            }
            pq1.remove();
        }

        return result;
    }

}
