package org.uade.util;

import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.implementation.dynamic.DynamicGraphADT;
import org.uade.structure.implementation.fixed.StaticGraphADT;

public class GraphUtil {

    public static GraphADT getNewGraph(GraphADT graph) {
        if (graph instanceof StaticGraphADT) {
            return new StaticGraphADT();
        } else {
            return new DynamicGraphADT();
        }
    }

    public static void print(GraphADT graph) {
        SetADT vertices = SetADTUtil.copy(graph.getVertxs());

        while (!vertices.isEmpty()) {
            int from = vertices.choose();
            SetADT destinations = SetADTUtil.copy(graph.getVertxs());

            System.out.print(from + " -> [");
            boolean first = true;
            while (!destinations.isEmpty()) {
                int to = destinations.choose();
                if (graph.existsEdge(from, to)) {
                    if (!first) {
                        System.out.print(", ");
                    }
                    System.out.print(to + "(" + graph.edgeWeight(from, to) + ")");
                    first = false;
                }
                destinations.remove(to);
            }
            System.out.println("]");

            vertices.remove(from);
        }
    }
}
