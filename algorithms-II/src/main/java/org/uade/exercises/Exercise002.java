package org.uade.exercises;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;
import org.uade.structure.implementation.fixed.StaticStackADT;
import org.uade.utils.QueueADTUtils;
import org.uade.utils.StackADTUtils;

// Dada una pila y una cola, se requiere verificar si la primera contiene 
// todos los elementos de la segunda. 
// No tenga en cuenta el orden de aparicion de los elementos.
public class Exercise002 {
    public static void main(String[] args) {
        // Init
        StackADT stack = new StaticStackADT();
        stack.add(4);
        stack.add(2);
        // stack.add(1);
        stack.add(5);
        stack.add(4);
        stack.add(3);

        System.out.println("Stack:");
        StackADTUtils.print(stack);

        System.out.println("");
        QueueADT queue = new StaticQueueADT();
        // queue.add(1);
        queue.add(2);
        queue.add(3);

        System.out.println("Queue:");
        QueueADTUtils.print(queue);

        System.out.println("");
        System.out.println("2.b) ---------------------");

        boolean stackContainsQueue = stackContainsAllQueueValues(stack, queue);
        if (stackContainsQueue) {
            System.out.println("El Stack contiene todos los elementos de la Queue.");
        } else {
            System.out.println("El Stack NO contiene todos los elementos de la Queue.");
        }
        
        System.out.println("");
        System.out.println("2.c) ---------------------");
        // Printeo la queue sin perder datos (ej 2c)
        System.out.println("Ejercicio 2c. Printeo sin perder la info del la estructura (queue)");
        print(queue);
    }

    // Ejercicio 2, b)
    // Dados una pila y una cola
    // Devuelve:
    // True en caso de que la pila contenga todos los elementos de la cola
    // False caso contrario
    // Complejidad Algoritmica estimada: O(n) + O(n*m) + O(n) = O(n*m)
    private static boolean stackContainsAllQueueValues(StackADT stack, QueueADT queue) {
        if (stack.isEmpty()) {
            return false;
        }
        StackADT cStack = StackADTUtils.copy(stack); // O(n)
        // Aqui meto los elementos del stack original que se encuentran en la cola
        QueueADT existsInBoth = new StaticQueueADT();

        // Recorro la pila.  O(n*m)
        while (!cStack.isEmpty()) {
            int stackValue = cStack.getElement();

            // Recorro la cola
            QueueADT tempQueue = QueueADTUtils.copy(queue);
            while (!tempQueue.isEmpty()) {
                // Si el valor del stack esta en la cola, lo mando al stack de ExistsInBoth
                if (stackValue == tempQueue.getElement()) {
                    existsInBoth.add(stackValue);
                }
                tempQueue.remove();
            }

            cStack.remove();
        }

        // Si el queue "existsInBoth" tiene igual cantidad de elementos que el queue original
        // Entonces quiere decir que todos los elementos del queue estan en la cola (y por eso
        // se movieron a "existsInBoth").
        if (getQueueSize(existsInBoth) == getQueueSize(queue)) { // O(n) + O(n) = O(n)
            return true;
        }
        
        return false;
    }


    private static int getQueueSize(QueueADT queue) {
        QueueADT copyQueue = QueueADTUtils.copy(queue);

        int i = 0;
        while (!copyQueue.isEmpty()) {
            i++;
            copyQueue.remove();
        }
        return i;
    }


    // Ejercicio 2, c)
    // Dado una QueueADT 
    // Muestra por pantalla (print) del contenido de la Queue SIN PERDER la 
    // informacion de la queue original.
    private static void print(QueueADT queue) {
        QueueADT copy = copy(queue);

        while (!copy.isEmpty()) {
            System.out.println(copy.getElement());
            copy.remove();
        }
    }

    // Copy (necesario para el punto 2c )
    private static QueueADT copy(QueueADT queue) {
        QueueADT copy = getNewQueue(queue);
        QueueADT aux = getNewQueue(queue);

        while (!queue.isEmpty()) {
            copy.add(queue.getElement());
            aux.add(queue.getElement());
            queue.remove();
        }

        // Re alimentamos queue IMPORTANTE para evitar la perdida de datos
        while (!aux.isEmpty()) {
            queue.add(aux.getElement());
            aux.remove();
        }

        return copy;
    }

    // Factory (necesario para el punto 2c )
    private static QueueADT getNewQueue(QueueADT queue) {
        if (queue instanceof DynamicQueueADT) {
            return new DynamicQueueADT();
        }
        return new StaticQueueADT();
    }

}
