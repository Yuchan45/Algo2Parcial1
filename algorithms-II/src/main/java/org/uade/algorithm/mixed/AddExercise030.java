package org.uade.algorithm.mixed;

import org.uade.structure.definition.LinkedListADT;
import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicLinkedListADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.util.LinkedListADTUtil;
import org.uade.util.QueueADTUtil;
import org.uade.util.StackADTUtil;

// Dada una PilaTDA y una ColaTDA, implementa un método que genere dos nuevas
// estructuras:
//      ○ Una PilaTDA con los elementos pares.
//      ○ Una ColaTDA con los elementos impares.

public class AddExercise030 {
    public static void main(String[] args) {
        StackADT stack = new DynamicStackADT();
        stack.add(1);
        stack.add(3);
        stack.add(2);

        QueueADT queue = new DynamicQueueADT();
        queue.add(3);
        queue.add(1);
        queue.add(2);

        LinkedListADT mergedList = mergeInList(stack, queue);
        System.out.println("Merged list: ");
        LinkedListADTUtil.print(mergedList);

        StackADT evenStack = new DynamicStackADT();
        QueueADT oddQueue = new DynamicQueueADT();

        splitInOddEven(mergedList, evenStack, oddQueue);
        System.out.println("");
        System.out.println("Even Stack:");
        StackADTUtil.print(evenStack);

        System.out.println("Odd Queue: ");
        QueueADTUtil.print(oddQueue);

    }

    private static void splitInOddEven(LinkedListADT list, StackADT evenStack, QueueADT oddQueue)  {
        for (int i=0; i<list.size(); i++) {
            if (list.get(i) % 2 == 0) {
                evenStack.add(list.get(i));
            } else {
                oddQueue.add(list.get(i));
            }
        }
    }

    private static LinkedListADT mergeInList(StackADT stack, QueueADT queue) {
        LinkedListADT resultList = new DynamicLinkedListADT();

        while (!stack.isEmpty()) {
            resultList.add(stack.getElement());
            stack.remove();
        }

        while (!queue.isEmpty()) {
            resultList.add(queue.getElement());
            queue.remove();
        }

        return resultList;

    }
}
