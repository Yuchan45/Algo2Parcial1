package org.uade.algorithm.queue.mio;

import org.uade.structure.definition.QueueADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicQueueADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.util.QueueADTUtil;

// Determinar si la Cola C1 es la inversa de la Cola C2. 
// Dos Colas serán inversas, si tienen los mismos elementos pero en orden inverso.
public class NormalExercise4f {
    public static void main(String[] args) {
        QueueADT queue1 = new DynamicQueueADT();
        queue1.add(1);
        queue1.add(2);
        queue1.add(3);
        queue1.add(4);
        queue1.add(5);

        System.out.println("Queue1: ");
        QueueADTUtil.print(queue1);

        QueueADT queue2 = new DynamicQueueADT();
        queue2.add(5);
        queue2.add(4);
        queue2.add(3);
        queue2.add(2);
        queue2.add(1);
        

        System.out.println("Queue2: ");
        QueueADTUtil.print(queue2);

        System.out.println("Resultado: ");
        boolean isInversed = areInversed(queue1, queue2);
        if (isInversed) {
            System.out.println("Ambas colas estan invertidas");
        } else {
            System.out.println("Ambas colas NO estan invertidas");
        }

    }


    private static boolean areInversed(QueueADT queue1, QueueADT queue2) {
        QueueADT q1 = QueueADTUtil.copy(queue1);
        QueueADT q2 = QueueADTUtil.copy(queue2);
        StackADT stackAux = new DynamicStackADT();

        // Armamos la Pila con los elementos de q2 (quedarian "invertidos" porque la pila se extrae al reves de la cola).
        while (!q2.isEmpty()) {
            stackAux.add(q2.getElement());
            q2.remove();
        }

        while (!q1.isEmpty() && !stackAux.isEmpty()) {
            if (q1.getElement() != stackAux.getElement()) {
                return false;
            }
            q1.remove();
            stackAux.remove();
        }

        while (!q1.isEmpty()) {
            // Si entro aca es porque le quedaron elementos, osea que q1 y q2 tienen diferente tamaño -> no puede estar invertida
            return false;
        }

        while (!q2.isEmpty()) {
            // Si entro aca es porque le quedaron elementos, osea que q1 y q2 tienen diferente tamaño -> no puede estar invertida
            return false;
        }

        return true;
    }

}
