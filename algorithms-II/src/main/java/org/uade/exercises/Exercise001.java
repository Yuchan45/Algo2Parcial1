package org.uade.exercises;

import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.fixed.StaticBinaryTreeADT;
import org.uade.structure.implementation.fixed.StaticGraphADT;
import org.uade.structure.implementation.fixed.StaticSetADT;

/**
 * Exercise001
 * Dado un arbol y un grafo, se busca obtener la lista de elementos comunes al comparar
 * los nodos del arbol con los vertices del grafo. Se deben ignorar los
 * vertices aislados
 * 
 * Hay que hacer un metodo que reciba un grafo y un tree, y retorna una unica 
 * estructura que evite duplicados -> Determine que el TDA correspondiente seria un SET.
 */
public class Exercise001 {
    public static void main(String[] args) {
        BinaryTreeADT tree = new StaticBinaryTreeADT();
        tree.add(5);
        tree.add(3);
        tree.add(6);
        tree.add(2);
        tree.add(4);


        GraphADT graph = new StaticGraphADT();
        graph.addVertx(2);
        graph.addVertx(3);
        graph.addVertx(4);
        graph.addVertx(5); 
        graph.addEdge(2, 3, 20);
        graph.addEdge(3, 4, 30);


        // Dado estos datos, deberia obtener de resultado un set con
        // [2, 3, 4] y NO 5 pues este esta aislado (por mas que se encuentre en el arbol tmb)
        SetADT result = new StaticSetADT();
        getSharedElementsExcludingIsolated(tree, graph, result); //  O(n*m)
        
        // Muestro el contenido del resultado SIN PERDERLO ya que esta funcion utiliza un copy por dentro
        printSet(result, "Result Set: "); // O(y) porque itera todos los elems del set.

        // Complejidad algoritmica general: O(n * n) + O(y). 
        // Me quedo con la peor, O(n * m)

        // boolean isIsolated = false;
        // int checkval = 4;
        // isIsolated = isIsolated(graph, checkval);
        // if (isIsolated) {
        //     System.out.println("Is isolated " + checkval);
        // } else {
        //     System.out.println("Not isolated " + checkval);
        // }
    }

    /**
     * Recibe un grafo y un tree.
     * Se busca obtener la lista de elementos comunes al comparar los nodos del arbol 
     * con los vertices del grafo. Se deben ignorar los vertices aislados.
     * Retorna un Set (recibido por parametro) con los elementos compartidos entre las estructuras.
     * 
     * Complejidad:
     * Se recorre el arbol (NO Equilibrado) para comparar cada nodo por lo que el peor caso es O(n)
     * Por cada nodo, se iteran lso elems del grafo y se verifica si esta aislado "isIsolated" O(m)
     * Por lo que diria que es O(n*m)
     */
    public static void getSharedElementsExcludingIsolated(BinaryTreeADT tree, GraphADT graph, SetADT result) {
        if (tree.isEmpty()) {
            return;
        }

        SetADT verts = graph.getVertxs();
        int currentTreeValue = tree.getRoot();
        if (verts.exist(currentTreeValue) && !isIsolated(graph, currentTreeValue)) {
            // Si el nodo del arbol se encuentra en las keys del grafo y NO esta aislado -> result
            result.add(currentTreeValue);
        }
        getSharedElementsExcludingIsolated(tree.getLeft(), graph, result);
        getSharedElementsExcludingIsolated(tree.getRight(), graph, result);

    }

    /**
     * Dados un grafo y un vertice a analizar
     * Determina si el vertice se encuentra aislado o no
     * Devuelve:
     *  true si esta aislado
     *  false si NO esta aislado
     * 
     * O(N) pues se iteran todos los N nodos del grafo para comparar con el vertice del arg.
     */
    public static boolean isIsolated(GraphADT graph, int checkVert) {
        boolean isIsolated = true;
        SetADT verts = graph.getVertxs();
        if (!verts.exist(checkVert)) {
            return false;
        }

        while (!verts.isEmpty()) {
            int vert = verts.choose();
            verts.remove(vert);
            if (graph.existsEdge(vert, checkVert) || graph.existsEdge(checkVert, vert)) {
                isIsolated = false;
            }
        }

        return isIsolated;
    }

    public static SetADT getNewSet(SetADT baseSet) {
        if (baseSet instanceof StaticSetADT) {
            return new StaticSetADT();
        }
        return new DynamicSetADT();
    }

    public static SetADT copySet(SetADT set) {
        SetADT copy = getNewSet(set);
        SetADT aux = getNewSet(set);

        while (!set.isEmpty()) {
            int value = set.choose();
            set.remove(value);
            copy.add(value);
            aux.add(value);
        }

        while (!aux.isEmpty()) {
            int value = aux.choose();
            aux.remove(value);
            set.add(value);
        }

        return copy;
    }

    public static void printSet(SetADT baseSet, String title) {
        SetADT set = copySet(baseSet);

        System.out.println(title);
        while (!set.isEmpty()) {
            int value = set.choose();
            set.remove(value);
            System.out.print(value + " ");
        }
        System.out.println("");
    }


}
