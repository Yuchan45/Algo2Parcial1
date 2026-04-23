package org.uade.algorithm.priorityqueue.basic;


import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.fixed.StaticMultipleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticPriorityQueueADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.util.MultipleDictionaryADTUtil;
import org.uade.util.PriorityQueueADTUtil;

// Escribir un metodo externo que permita generar un Diccionario Múltiple que permita, para cada valor presente en la ColaPrioridad C recuperar todas las prioridades que tiene asociadas en C.
public class BasicPriorityQueueExercise21 {

    public static void main(String[] args) {
        PriorityQueueADT queue = new StaticPriorityQueueADT();

        queue.add(5, 2);
        queue.add(3, 1);
        queue.add(5, 4);
        queue.add(2, 3);
        queue.add(3, 5);

        System.out.println("Cola con prioridad original:");
        PriorityQueueADTUtil.print(queue);

        MultipleDictionaryADT dictionary = generateDictionary(queue);

        System.out.println("\nDiccionario:");
        MultipleDictionaryADTUtil.print(dictionary);
    }

    public static MultipleDictionaryADT generateDictionary(PriorityQueueADT queue) {
        MultipleDictionaryADT dictionary = new StaticMultipleDictionaryADT();
        PriorityQueueADT tempQueue = new StaticPriorityQueueADT();
        SetADT processedValues = new StaticSetADT();

        while (!queue.isEmpty()) {
            int priority = queue.getPriority();
            int value = queue.getElement();
            queue.remove();
            tempQueue.add(value, priority);

            if (!processedValues.exist(value)) {
                processedValues.add(value);
            }
            dictionary.add(value, priority);
        }

        while (!tempQueue.isEmpty()) {
            int priority = tempQueue.getPriority();
            int value = tempQueue.getElement();
            tempQueue.remove();
            queue.add(value, priority);
        }

        return dictionary;
    }
}
