package org.uade.algorithm.stack.mios;

import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.util.StackADTUtil;

//  Cree, inicialice y cargue la pila, ordene los elementos.
public class AddExercise017 {
    public static void main(String[] args) {
        StackADT stack = new DynamicStackADT();
        stack.add(1);
        stack.add(4);
        stack.add(2);
        stack.add(7);
        stack.add(10);

        StackADT sorted = sortStack(stack);
        StackADTUtil.print(sorted);
    }

    public static StackADT sortStack(StackADT stack) {
        StackADT aux = new DynamicStackADT();

        while (!stack.isEmpty()) {
            int temp = stack.getElement();
            stack.remove();

            while (!aux.isEmpty() && aux.getElement() < temp) {
                stack.add(aux.getElement());
                aux.remove();
            }

            aux.add(temp);
        }

        return aux;
    } 
}
