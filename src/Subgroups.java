import java.util.HashSet;
import java.util.Scanner;

public class Subgroups {
    public static void main(String[] args) {
        HashSet<Integer> possibleElements = new HashSet<>();
        // Get the input of the rest class
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the rest class of the group: ");
        int restClass = scanner.nextInt();
        scanner.close();
        // Fill the set with the possible elements of the group. That means that they are between 1 and the rest class and that they are coprime to the rest class. That means that the gcd of any element in the rest class and n is 1
        for (int i = 1; i < restClass; i++) {
            if (gcd(i, restClass) == 1) {
                possibleElements.add(i);
            }
        }
        System.out.println(possibleElements);
        HashSet<HashSet<Integer>> result = getSubgroups(possibleElements, restClass);
        System.out.println(result);
    }

    public static int gcd(int i, int restClass) {
        if (restClass == 0) {
            return i;
        }
        return gcd(restClass, i % restClass);
    }

    public static HashSet<HashSet<Integer>> getSubgroups(HashSet<Integer> possibleElements, int restClass) {
        HashSet<HashSet<Integer>> result = new HashSet<>();
        HashSet<HashSet<Integer>> toTest = new HashSet<>();
        // Create all permutations
        for (int i = 0; i <= possibleElements.size(); i++) {
            toTest.addAll(getPermutations(i, possibleElements));
        }
        // Test if they are a subgroup
        for (HashSet<Integer> i : toTest) {
            boolean isSubgroup = true;
            for (int j : i) {
                for (int k : i) {
                    if (!i.contains((j * k) % restClass)) {
                        isSubgroup = false;
                        break;
                    }
                }
            }
            if (isSubgroup) {
                result.add(i);
            }
        }

        return result;
    }

    // Create all sized permutations of a set
    public static HashSet<HashSet<Integer>> getPermutations(int size, HashSet<Integer> possibleElements) {
        HashSet<HashSet<Integer>> result = new HashSet<>();
        if (size == 1) {
            for (int i : possibleElements) {
                HashSet<Integer> temp = new HashSet<>();
                temp.add(i);
                result.add(temp);
            }
        } else {
            for (int i : possibleElements) {
                HashSet<Integer> temp = new HashSet<>();
                temp.add(i);
                HashSet<Integer> temp2 = new HashSet<>(possibleElements);
                temp2.remove(i);
                HashSet<HashSet<Integer>> temp3 = getPermutations(size - 1, temp2);
                for (HashSet<Integer> j : temp3) {
                    HashSet<Integer> temp4 = new HashSet<>(temp);
                    temp4.addAll(j);
                    result.add(temp4);
                }
            }
        }
        return result;
    }
}
