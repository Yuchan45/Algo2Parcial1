package org.uade.algorithm.queue.mio;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.util.QueueADTUtil;
import org.uade.util.StackADTUtil;

public class Test {
    public static void main(String[] args) {
        // stack: [1, 2, 3, 4, 5]
        // Si hago getElement, me deberia devolver 5
        StackADT stack = new DynamicStackADT();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);

        // queue: [1, 2, 3, 4, 5]
        // Si hago getElement, me deberia devolver 1
        QueueADT queue = new DynamicQueueADT();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println("Stack: " + stack.getElement());
        System.out.println("Queue: " + queue.getElement());

        System.out.println("Stack Print:");
        StackADTUtil.print(stack);
        
        System.out.println("Queue Print:");
        QueueADTUtil.print(queue);
    }
}
