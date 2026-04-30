package org.uade.exercises;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.fixed.StaticLinkedListADT;
import org.uade.structure.implementation.fixed.StaticPriorityQueueADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;
import org.uade.structure.implementation.fixed.StaticStackADT;
import org.uade.util.LinkedListADTUtil;
import org.uade.util.PriorityQueueADTUtil;
import org.uade.util.StackADTUtil;
import org.uade.util.QueueADTUtil;

// 
public class Exercise001 {
    public static void main(String[] args) {
        QueueADT queue = new StaticQueueADT();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        QueueADTUtil.print(queue);

        System.out.println("----");

        StackADT stack = new StaticStackADT();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        StackADTUtil.print(stack);

        System.out.println("----");

        LinkedListADT list = new StaticLinkedListADT();
        list.add(1);
        list.add(2);
        list.add(3);

        LinkedListADTUtil.print(list);

        System.out.println("----");

        PriorityQueueADT pqueue = new StaticPriorityQueueADT();
        pqueue.add(11, 10);
        pqueue.add(23, 6);
        pqueue.add(63, 8);
        pqueue.add(24, 1);
        PriorityQueueADTUtil.print(pqueue);


    }
}
