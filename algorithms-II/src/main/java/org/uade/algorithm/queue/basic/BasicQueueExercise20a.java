package org.uade.algorithm.queue.basic;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.util.QueueADTUtil;

// 20.a - Eliminar de una Cola C las repeticiones de elementos, dejando un representante de cada uno de los elementos presentes originalmente. Se deberá respetar el orden original de los elementos, y en el caso de los repetidos se conservará el primero que haya ingresado en C.
public class BasicQueueExercise20a {

    public static void main(String[] args) {
        QueueADT queue = new StaticQueueADT();

        queue.add(1);
        queue.add(2);
        queue.add(2);
        queue.add(3);
        queue.add(1);
        queue.add(4);
        queue.add(3);

        System.out.println("Cola original:");
        QueueADTUtil.print(queue);

        removeDuplicates(queue);

        System.out.println("\nCola despues de remover duplicados:");
        QueueADTUtil.print(queue);
    }

    public static void removeDuplicates(QueueADT queue) {
        QueueADT tempQueue = new StaticQueueADT();
        SetADT seenElements = new StaticSetADT();

        while (!queue.isEmpty()) {
            int element = queue.getElement();
            queue.remove();

            if (!seenElements.exist(element)) {
                seenElements.add(element);
                tempQueue.add(element);
            }
        }

        while (!tempQueue.isEmpty()) {
            queue.add(tempQueue.getElement());
            tempQueue.remove();
        }
    }
}
