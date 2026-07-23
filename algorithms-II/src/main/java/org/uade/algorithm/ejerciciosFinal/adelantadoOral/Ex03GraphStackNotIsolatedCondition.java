package org.uade.algorithm.ejerciciosFinal.adelantadoOral;

import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.StackADT;
import org.uade.structure.implementation.dynamic.DynamicStackADT;
import org.uade.structure.implementation.fixed.StaticGraphADT;
import org.uade.structure.implementation.fixed.StaticStackADT;

/**
 * GraphStackNotIsolatedConditionEx03
 * Sea un grafo y un stack, quiero obtener un stack con los elementos que estan en ambas estructuras siempre y cuando el elemento NO este aislado
 */
public class Ex03GraphStackNotIsolatedCondition {
    public static void main(String[] args) {
        GraphADT graph = new StaticGraphADT();
        graph.addVertx(1);
        graph.addVertx(2);
        graph.addVertx(3);
        graph.addVertx(4); // Este vertice 4 esta aislado
        graph.addEdge(1, 2, 10);
        graph.addEdge(1, 3, 10);
        graph.addEdge(2, 3, 10);
        printGraph(graph, "Grafo base:");

        StackADT stack = new StaticStackADT();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4); // Este NO deberia estar en el resultado.
        printStack(stack, "Stack:");

        StackADT result = getUnionDespiteIsolated(graph, stack);
        printStack(result, "Result Stack:");
    }

    public static StackADT getUnionDespiteIsolated(GraphADT graph, StackADT baseStack) {
        StackADT result = getNewStack(baseStack);
        SetADT verts = graph.getVertxs();
        StackADT stack = copyStack(baseStack);

        // Iterar el stack
        // Si el elemento del stack esta en el grafo y NO esta aislado, agregarlo al resultado
        while (!stack.isEmpty()) {
            int value = stack.getElement();
            boolean isIsolated = isIsolated(value, graph);
            if (verts.exist(value) && !isIsolated) {
                result.add(value);
            }
            stack.remove();
        }

        return result;
    }

    public static boolean isIsolated(int value, GraphADT graph) {
        SetADT verts = graph.getVertxs();

        while (!verts.isEmpty()) {
            int vert = verts.choose();
            if (graph.existsEdge(value, vert) || graph.existsEdge(vert, value)) {
                return false;
            }
            verts.remove(vert);
        }
        return true;
    }


    public static StackADT getNewStack(StackADT stack) {
        if (stack instanceof StaticStackADT) {
            return new StaticStackADT();
        }
        return new DynamicStackADT();
    }

    public static StackADT copyStack(StackADT stack) {
        StackADT copy = getNewStack(stack);
        StackADT aux = getNewStack(stack);

        while (!stack.isEmpty()) {
            int value = stack.getElement();
            aux.add(value);
            stack.remove();
        }
        while (!aux.isEmpty()) {
            int value = aux.getElement();
            copy.add(value);
            stack.add(value);
            aux.remove();
        }

        return copy;
    }

    public static void printStack(StackADT stackBase, String title) {
        System.out.println(title);
        StackADT stack = copyStack(stackBase);

        while (!stack.isEmpty()) {
            int value = stack.getElement();
            System.out.print(value + ", ");
            stack.remove();
        }
        System.out.println("");

    }


    public static void printGraph(GraphADT graph, String title) {
        System.out.println(title);
        SetADT fromVerts = graph.getVertxs();

        while (!fromVerts.isEmpty()) {
            int fromVert = fromVerts.choose();
            fromVerts.remove(fromVert);
            System.out.print("fromVert: " + fromVert + " -> [");

            SetADT destinationVerts = graph.getVertxs();
            while (!destinationVerts.isEmpty()) {
                int toVert = destinationVerts.choose();
                destinationVerts.remove(toVert);
                if (graph.existsEdge(fromVert, toVert)) {
                    System.out.print(toVert + "(" + graph.edgeWeight(fromVert, toVert) + "), ");
                }
            }
            System.out.print("] \n");
        }
        System.out.println("");
    }
}
