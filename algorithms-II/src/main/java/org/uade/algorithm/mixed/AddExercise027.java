package org.uade.algorithm.mixed;

import java.util.Queue;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.util.QueueADTUtil;
import org.uade.util.StackADTUtil;

// Dada una Pila y una Cola, implementa un método que determine si contienen
// exactamente los mismos elementos, aunque en diferente orden.
public class AddExercise027 {
    public static void main(String[] args) {
        // Si las 2 estructuras tienen la misma cantidad de elementos y ademas
        // los elementos de una estructura, estan en la otra tambien
        // Entonces contienen los mismo elementos (aunque puedan estar en diferente orden)
        StackADT stack = new DynamicStackADT();
        stack.add(1);
        stack.add(3);
        stack.add(2);

        QueueADT queue = new DynamicQueueADT();
        queue.add(3);
        queue.add(1);
        queue.add(2);

        System.out.println("Stack:");
        StackADTUtil.print(stack);
        int stackSize = getStackSize(stack);
        System.out.println("Size: " + stackSize);

        System.out.println("Queue:");
        QueueADTUtil.print(queue);
        int queueSize = getQueueSize(queue);
        System.out.println("Size: " + queueSize);

        System.out.println("");

        boolean isContained = stackElemsContainedInQueue(stack, queue);
        if (isContained && (stackSize == queueSize)) {
            System.out.println("Contienen los mismos elementos (aunque puedan estar en diferente orden)");
        } else {
            System.out.println("NO contienen los mismos elementos");
        }


    }

    private static boolean stackElemsContainedInQueue(StackADT stack, QueueADT queue) {
        StackADT st = StackADTUtil.copy(stack);
        
        while (!st.isEmpty()) {
            QueueADT tempQ = QueueADTUtil.copy(queue);
            boolean isContained = false;
            while (!tempQ.isEmpty()) {
                if (st.getElement() == tempQ.getElement()) {
                    isContained = true;
                }
                tempQ.remove();
            }

            if (!isContained) {
                // Encontre un valor del stack que NO se encuentra contenido en el queue, entonces ya no va.
                return false;
            }

            st.remove();
        }

        return true;

    }

    private static int getStackSize(StackADT stack) {
        if (stack.isEmpty()) {
            return 0;
        }

        int i = 0;
        while (!stack.isEmpty()) {
            i++;
            stack.remove();
        }
        return i;
    }

    private static int getQueueSize(QueueADT queue) {
        if (queue.isEmpty()) {
            return 0;
        }

        int i = 0;
        while (!queue.isEmpty()) {
            i++;
            queue.remove();
        }
        return i;
    }

}
