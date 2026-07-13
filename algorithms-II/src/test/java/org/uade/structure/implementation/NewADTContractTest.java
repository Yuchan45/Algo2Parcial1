package org.uade.structure.implementation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.uade.exception.ElementNotFoundADTException;
import org.uade.exception.EmptyADTException;
import org.uade.structure.definition.BinaryTreeADT;
import org.uade.structure.definition.GraphADT;
import org.uade.structure.definition.MultipleDictionaryADT;
import org.uade.structure.definition.SetADT;
import org.uade.structure.definition.SimpleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicBinaryTreeADT;
import org.uade.structure.implementation.dynamic.DynamicGraphADT;
import org.uade.structure.implementation.dynamic.DynamicMultipleDictionaryADT;
import org.uade.structure.implementation.dynamic.DynamicSetADT;
import org.uade.structure.implementation.dynamic.DynamicSimpleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticBinaryTreeADT;
import org.uade.structure.implementation.fixed.StaticGraphADT;
import org.uade.structure.implementation.fixed.StaticMultipleDictionaryADT;
import org.uade.structure.implementation.fixed.StaticSetADT;
import org.uade.structure.implementation.fixed.StaticSimpleDictionaryADT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class NewADTContractTest {

    static Stream<Arguments> setImplementations() {
        return Stream.of(
                arguments("static", (Supplier<SetADT>) StaticSetADT::new),
                arguments("dynamic", (Supplier<SetADT>) DynamicSetADT::new)
        );
    }

    static Stream<Arguments> simpleDictionaryImplementations() {
        return Stream.of(
                arguments("static", (Supplier<SimpleDictionaryADT>) StaticSimpleDictionaryADT::new),
                arguments("dynamic", (Supplier<SimpleDictionaryADT>) DynamicSimpleDictionaryADT::new)
        );
    }

    static Stream<Arguments> multipleDictionaryImplementations() {
        return Stream.of(
                arguments("static", (Supplier<MultipleDictionaryADT>) StaticMultipleDictionaryADT::new),
                arguments("dynamic", (Supplier<MultipleDictionaryADT>) DynamicMultipleDictionaryADT::new)
        );
    }

    static Stream<Arguments> binaryTreeImplementations() {
        return Stream.of(
                arguments("static", (Supplier<BinaryTreeADT>) StaticBinaryTreeADT::new),
                arguments("dynamic", (Supplier<BinaryTreeADT>) DynamicBinaryTreeADT::new)
        );
    }

    static Stream<Arguments> graphImplementations() {
        return Stream.of(
                arguments("static", (Supplier<GraphADT>) StaticGraphADT::new),
                arguments("dynamic", (Supplier<GraphADT>) DynamicGraphADT::new)
        );
    }

    @ParameterizedTest(name = "{0} set")
    @MethodSource("setImplementations")
    void setDoesNotAcceptDuplicates(String name, Supplier<SetADT> factory) {
        SetADT set = factory.get();

        set.add(10);
        set.add(10);
        set.remove(10);

        assertTrue(set.isEmpty());
        assertFalse(set.exist(10));
    }

    @ParameterizedTest(name = "{0} set")
    @MethodSource("setImplementations")
    void setHandlesExistChooseAndRemove(String name, Supplier<SetADT> factory) {
        SetADT set = factory.get();

        assertThrows(EmptyADTException.class, set::choose);
        assertThrows(EmptyADTException.class, () -> set.remove(1));

        set.add(1);
        set.add(2);
        set.add(3);
        set.remove(99);

        assertTrue(set.exist(1));
        assertTrue(set.exist(2));
        assertTrue(set.exist(3));
        assertSetContainsExactly(set, 1, 2, 3);
    }

    @ParameterizedTest(name = "{0} simple dictionary")
    @MethodSource("simpleDictionaryImplementations")
    void simpleDictionaryAddsOverridesAndRemoves(String name, Supplier<SimpleDictionaryADT> factory) {
        SimpleDictionaryADT dictionary = factory.get();

        assertThrows(EmptyADTException.class, () -> dictionary.get(1));

        dictionary.add(1, 10);
        dictionary.add(2, 20);
        dictionary.add(1, 100);

        assertEquals(100, dictionary.get(1));
        assertEquals(20, dictionary.get(2));
        assertSetContainsExactly(dictionary.getKeys(), 1, 2);

        dictionary.remove(99);
        assertEquals(100, dictionary.get(1));

        dictionary.remove(1);
        assertThrows(ElementNotFoundADTException.class, () -> dictionary.get(1));
        assertEquals(20, dictionary.get(2));
        assertSetContainsExactly(dictionary.getKeys(), 2);
    }

    @ParameterizedTest(name = "{0} multiple dictionary")
    @MethodSource("multipleDictionaryImplementations")
    void multipleDictionaryAllowsManyValuesPerKey(String name, Supplier<MultipleDictionaryADT> factory) {
        MultipleDictionaryADT dictionary = factory.get();

        assertThrows(EmptyADTException.class, () -> dictionary.get(1));

        dictionary.add(1, 10);
        dictionary.add(1, 20);
        dictionary.add(1, 20);
        dictionary.add(2, 30);

        assertSameMultiset(new int[]{10, 20, 20}, dictionary.get(1));
        assertArrayEquals(new int[]{30}, dictionary.get(2));
        assertSetContainsExactly(dictionary.getKeys(), 1, 2);

        dictionary.remove(1, 20);
        assertSameMultiset(new int[]{10, 20}, dictionary.get(1));

        dictionary.remove(1);
        assertThrows(ElementNotFoundADTException.class, () -> dictionary.get(1));
        assertEquals(30, dictionary.get(2)[0]);
        assertSetContainsExactly(dictionary.getKeys(), 2);
    }

    @ParameterizedTest(name = "{0} binary tree")
    @MethodSource("binaryTreeImplementations")
    void binaryTreeKeepsBinarySearchTreeShape(String name, Supplier<BinaryTreeADT> factory) {
        BinaryTreeADT tree = factory.get();

        assertThrows(EmptyADTException.class, tree::getRoot);

        addAll(tree, 5, 3, 7, 2, 4, 6, 8);

        assertEquals(5, tree.getRoot());
        assertEquals(3, tree.getLeft().getRoot());
        assertEquals(7, tree.getRight().getRoot());
        assertEquals(2, tree.getLeft().getLeft().getRoot());
        assertEquals(4, tree.getLeft().getRight().getRoot());
        assertEquals(6, tree.getRight().getLeft().getRoot());
        assertEquals(8, tree.getRight().getRight().getRoot());
    }

    @ParameterizedTest(name = "{0} binary tree")
    @MethodSource("binaryTreeImplementations")
    void binaryTreeRemovesLeafOneChildAndTwoChildrenNodes(String name, Supplier<BinaryTreeADT> factory) {
        BinaryTreeADT tree = factory.get();
        addAll(tree, 5, 3, 7, 2, 4, 6, 8);

        tree.remove(2);
        assertTrue(tree.getLeft().getLeft().isEmpty());

        tree.remove(3);
        assertEquals(4, tree.getLeft().getRoot());

        tree.remove(5);
        assertEquals(6, tree.getRoot());
        assertEquals(4, tree.getLeft().getRoot());
        assertEquals(7, tree.getRight().getRoot());
        assertTrue(tree.getRight().getLeft().isEmpty());
        assertEquals(8, tree.getRight().getRight().getRoot());

        assertThrows(ElementNotFoundADTException.class, () -> tree.remove(99));
    }

    @ParameterizedTest(name = "{0} binary tree")
    @MethodSource("binaryTreeImplementations")
    void binaryTreeDoesNotDuplicateValues(String name, Supplier<BinaryTreeADT> factory) {
        BinaryTreeADT tree = factory.get();

        tree.add(5);
        tree.add(5);
        tree.remove(5);

        assertTrue(tree.isEmpty());
    }

    @ParameterizedTest(name = "{0} graph")
    @MethodSource("graphImplementations")
    void graphAddsVerticesEdgesAndWeights(String name, Supplier<GraphADT> factory) {
        GraphADT graph = factory.get();

        assertTrue(graph.isEmpty());
        assertThrows(EmptyADTException.class, () -> graph.existsEdge(1, 2));
        assertThrows(EmptyADTException.class, () -> graph.edgeWeight(1, 2));

        graph.addVertx(1);
        graph.addVertx(1);
        graph.addEdge(1, 2, 12);

        assertFalse(graph.isEmpty());
        assertSetContainsExactly(graph.getVertxs(), 1, 2);
        assertTrue(graph.existsEdge(1, 2));
        assertTrue(graph.existsEdge(2, 1));
        assertEquals(12, graph.edgeWeight(1, 2));
        assertEquals(12, graph.edgeWeight(2, 1));

        graph.addEdge(1, 2, 21);
        assertEquals(21, graph.edgeWeight(1, 2));
        assertEquals(21, graph.edgeWeight(2, 1));
    }

    @ParameterizedTest(name = "{0} graph")
    @MethodSource("graphImplementations")
    void graphRemovesEdgesAndVertices(String name, Supplier<GraphADT> factory) {
        GraphADT graph = factory.get();

        graph.addEdge(1, 2, 12);
        graph.addEdge(2, 3, 23);
        graph.removeEdge(1, 2);

        assertFalse(graph.existsEdge(1, 2));
        assertFalse(graph.existsEdge(2, 1));
        assertThrows(ElementNotFoundADTException.class, () -> graph.edgeWeight(1, 2));
        assertTrue(graph.existsEdge(2, 3));

        graph.removeVertx(2);
        assertSetContainsExactly(graph.getVertxs(), 1, 3);
        assertFalse(graph.existsEdge(3, 2));
        assertFalse(graph.existsEdge(1, 2));
    }

    private static void addAll(BinaryTreeADT tree, int... values) {
        for (int value : values) {
            tree.add(value);
        }
    }

    private static void assertSetContainsExactly(SetADT actual, int... values) {
        Set<Integer> expected = Arrays.stream(values).boxed().collect(Collectors.toSet());
        Set<Integer> seen = new HashSet<>();

        while (!actual.isEmpty()) {
            int value = actual.choose();
            assertTrue(expected.contains(value), "Unexpected value in set: " + value);
            assertTrue(seen.add(value), "Duplicated value in set: " + value);
            actual.remove(value);
        }

        assertEquals(expected, seen);
    }

    private static void assertSameMultiset(int[] expected, int[] actual) {
        assertEquals(toMultiset(expected), toMultiset(actual));
    }

    private static Map<Integer, Integer> toMultiset(int[] values) {
        Map<Integer, Integer> result = new HashMap<>();
        for (int value : values) {
            result.put(value, result.getOrDefault(value, 0) + 1);
        }

        return result;
    }
}
