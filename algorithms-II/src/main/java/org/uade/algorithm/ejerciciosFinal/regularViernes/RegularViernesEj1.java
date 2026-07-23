package org.uade.algorithm.ejerciciosFinal.regularViernes;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.PriorityQueueADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicPriorityQueueADT;
import org.uade.structure.implementation.fixed.StaticBinaryTreeADT;
import org.uade.structure.implementation.fixed.StaticGraphADT;
import org.uade.structure.implementation.fixed.StaticPriorityQueueADT;

/**
 * RegularViernesEj1
 * Dadas una cola con prioridad y un grafo, obtener un BinaryTree. Al agregar los
 * elementos al árbol, incluir solamente los VALORES de las tuplas(Valor | Prio) de la cola de prioridad
 * cuya PRIO representen un vértice y utilizar la prioridad como el otro valor.
 * Ejemplo:
 * Grafo con vértices:
  - {1, 3, 5}

  Priority queue (Value | Prio):
  - (10, 1)
  - (20, 2)
  - (30, 3)
  - (40, 4)
  - (50, 5)

  entonces:
  - prioridad 1 está en el grafo -> agregar 10 al árbol
  - prioridad 2 no está -> no agregar nada
  - prioridad 3 está -> agregar 30
  - prioridad 4 no está -> no agregar nada
  - prioridad 5 está -> agregar 50
 */
public class RegularViernesEj1 {
    public static void main(String[] args) {
        // Init
        GraphADT graph = new StaticGraphADT();
        graph.addVertx(1);
        graph.addVertx(3);
        graph.addVertx(5);

        PriorityQueueADT pq1 = new StaticPriorityQueueADT();
        pq1.add(10, 1);
        pq1.add(20, 2);
        pq1.add(30, 3);
        pq1.add(40, 4);
        pq1.add(50, 5);
        // Deberian quedar en el arbol los valores: [10, 30, 50]
        printPQueue(pq1);

        BinaryTreeADT resultTree = getMatches(graph, pq1);
        printTreePreOrder(resultTree);
    }

    public static BinaryTreeADT getMatches(GraphADT graph, PriorityQueueADT pqueue) {
        // Obtenemos los vertices como Set
        // Iteramos la priority queue
        // Agarramos cada prio y vemos si existe en el grafo como vertice
        //      Si existe, agregamos el VALOR al tree resultado
        //      Si NO existe, skip

        BinaryTreeADT resultTree = new StaticBinaryTreeADT();
        SetADT verts = graph.getVertxs();
        PriorityQueueADT pq = copyPQueue(pqueue);

        while (!pq.isEmpty()) {
            int value = pq.getElement();
            int prio = pq.getPriority();
            if (verts.exist(prio)) {
                resultTree.add(value);
            }
            pq.remove();
        }

        return resultTree;
    }

    public static void printTreePreOrder(BinaryTreeADT tree) {
        // PreOrder: N, L, R
        System.out.println("\nPrint tree Pre-Order:");
        iterateTreePreOrderRecursive(tree);
        System.out.println("");
    }
    public static void iterateTreePreOrderRecursive(BinaryTreeADT tree) {
        if (tree.isEmpty()) {
            return;
        }

        System.out.println(tree.getRoot() + " ");
        iterateTreePreOrderRecursive(tree.getLeft());
        iterateTreePreOrderRecursive(tree.getRight());

    }

    public static PriorityQueueADT getNewPQueue(PriorityQueueADT pqueue) {
        if (pqueue instanceof StaticPriorityQueueADT) {
            return new StaticPriorityQueueADT();
        }
        return new DynamicPriorityQueueADT();
    }

    public static PriorityQueueADT copyPQueue(PriorityQueueADT pqueue) {
        PriorityQueueADT copy = getNewPQueue(pqueue);
        PriorityQueueADT aux = getNewPQueue(pqueue);

        while (!pqueue.isEmpty()) {
            int value = pqueue.getElement();
            int prio = pqueue.getPriority();
            copy.add(value, prio);
            aux.add(value, prio);
            pqueue.remove();
        }

        while (!aux.isEmpty()) {
            int value = aux.getElement();
            int prio = aux.getPriority();
            pqueue.add(value, prio);
            aux.remove();
        }

        return copy;
    }

    public static void printPQueue(PriorityQueueADT pqueue) {
        PriorityQueueADT copy = copyPQueue(pqueue);

        while (!copy.isEmpty()) {
            int value = copy.getElement();
            int prio = copy.getPriority();
            System.out.println("Value: " + value + " | Prio: " + prio);
            copy.remove();
        }
    }

}
