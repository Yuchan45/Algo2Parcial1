package org.uade.exercises;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.fixed.StaticLinkedListADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;
import org.uade.structure.implementation.fixed.StaticStackADT;

// 
public class Exercise001 {
    public static void main(String[] args) {
        QueueADT queue = new StaticQueueADT();
        queue.add(1);
        queue.add(2);

        StackADT stack = new StaticStackADT();
        stack.add(1);
        stack.add(2);

        LinkedListADT list = new StaticLinkedListADT();
        list.add(1);
        list.add(2);

        
    }
}
