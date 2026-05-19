package org.uade.exercises;

public class ExerciseSet {
    public static void main(String[] args) {
        
        SetADT set1 = new SetADT();
        SetADT set2 = new SetADT();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(1);
        set2.add(2);
        set2.add(3);


        private static SetADT union(SetADT set1, SetADT set2) {
            SetADT resultSet = new SetADT();

            for (int i = 0; i < set1.size(); i++) {
                resultSet.add(set1.get(i));
            }

            for (int i = 0; i < set2.size(); i++) {
                if (!resultSet.contains(set2.get(i))) {
                    resultSet.add(set2.get(i));
                }
            }

            return resultSet;
        }
    }
}
