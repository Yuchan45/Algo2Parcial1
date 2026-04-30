package org.uade.algorithm.queue.mio;

import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticQueueADT;

// Dada una cola, verifica si es un palíndromo utilizando una pila. Una cola es un
// palíndromo si se lee igual de izquierda a derecha que de derecha a izquierda. Por
// ejemplo, C = [1, 2, 3, 2, 1] es un palíndromo
public class AddExercise023 {
    public static void main(String[] args) {
        QueueADT queue = new DynamicQueueADT();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(2);
        queue.add(1);

        System.out.println("Queue: ");
        print(queue);

        Boolean result = isPalindrome(queue);
        System.out.println("Result: ");
        if (result) {
            System.out.println("Palindrome");
        } else {
            System.out.println("NOT Palindrome");
        }

    }

    // Palindrome
    public static boolean isPalindrome(QueueADT queue) {
        if (queue.isEmpty()) {
            throw new EmptyADTException();
        }
        QueueADT copyQueue = copy(queue);
        StackADT stack = new DynamicStackADT();

        // Copiamos la cola en la pila
        while (!copyQueue.isEmpty()) {
            stack.add(copyQueue.getElement());
            copyQueue.remove();
        }

        // Tenemos queue y stack cargados con los mismos elementos, mismo size.
        // Pero stack y queue se recorren de forma "invertida"
        while (!queue.isEmpty() && !stack.isEmpty()) {

            if (queue.getElement() != stack.getElement()) {
                return false;
            }

            queue.remove();
            stack.remove();
        }

        return true;
    }

    // Factory
    public static QueueADT getNewQueue(QueueADT queue) {
        if (queue instanceof DynamicQueueADT) {
            return new DynamicQueueADT();
        }
        return new StaticQueueADT();
    }

    // Copy
    public static QueueADT copy(QueueADT queue) {
        QueueADT copyQ = getNewQueue(queue);
        QueueADT aux = getNewQueue(queue);

        while (!queue.isEmpty()) {
            copyQ.add(queue.getElement());
            aux.add(queue.getElement());
            queue.remove();
        }

        while (!aux.isEmpty()) {
            queue.add(aux.getElement());
            aux.remove();
        }

        return copyQ;
    }

    // Print
    public static void print(QueueADT queue) {
        QueueADT copyQ = copy(queue);

        while (!copyQ.isEmpty()) {
            System.out.println(copyQ.getElement() + " ");
            copyQ.remove();
        }
        System.out.println("");
    }
}
