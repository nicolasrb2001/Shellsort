import java.io.PrintStream;
import java.util.ArrayList;

public class Shellsort {
    private static final int[] gap_size = new int[]{1, 3, 7, 15, 31, 63, 127, 255, 511};

    public Shellsort() {
    }

    public static void validation(ArrayList<Double> array) {
        if (array.size() == 1) {
            System.out.println(String.format("%.2f%n", array.get(0)).replace(",", "."));
            System.exit(0);
        }

        if (array.size() > 1000 || array.size() == 0) {
            System.out.println("invalid array size");
            System.exit(-1);
        }

    }

    public static int starting_index(ArrayList<Double> array) {
        int index = 0;

        for(int i = 0; i < 9; ++i) {
            if (gap_size[i] >= array.size()) {
                index = i;
                break;
            }

            if (i == 8) {
                index = 8;
            }
        }

        return index;
    }

    public static void print_array(ArrayList<ArrayList<Double>> sub_arrays, ArrayList<Double> array) {
        int t = 0;

        for(int y = 0; y < sub_arrays.size(); ++y) {
            for(int x = 0; x < (sub_arrays.get(y)).size(); ++x) {
                PrintStream var10000;
                String var10001;
                if (y == 0 & x == 0) {
                    var10000 = System.out;
                    var10001 = String.format("%.2f", (sub_arrays.get(0)).get(0));
                    var10000.print("[" + var10001.replace(",", ".") + ",");
                } else if (y == sub_arrays.size() - 1 & x == (sub_arrays.get(y)).size() - 1) {
                    var10000 = System.out;
                    var10001 = String.format("%.2f", (sub_arrays.get(y)).get(x));
                    var10000.println(" " + var10001.replace(",", ".") + "]");
                } else {
                    var10000 = System.out;
                    var10001 = String.format("%.2f", (sub_arrays.get(y)).get(x));
                    var10000.print(" " + var10001.replace(",", ".") + ",");
                }

                array.set(t, (sub_arrays.get(y)).get(x));
                ++t;
            }
        }

    }

    public static boolean split_arrays(boolean state, ArrayList<ArrayList<Double>> sub_arrays, int current_index, ArrayList<Double> array, int index) {
        state = false;
        sub_arrays.add(new ArrayList());

        while(current_index + gap_size[index] < array.size()) {
            current_index += gap_size[index];
            (sub_arrays.get(sub_arrays.size() - 1)).add(array.get(current_index));
        }

        if ((sub_arrays.get(sub_arrays.size() - 1)).size() == 0) {
            (sub_arrays.get(sub_arrays.size() - 1)).add(array.get(current_index));
            state = true;
        }

        sub_arrays.set(sub_arrays.size() - 1, insertion_sort(sub_arrays.get(sub_arrays.size() - 1)));
        return state;
    }

    public static void shell_sort(ArrayList<Double> array) {
        validation(array);
        int index = starting_index(array);

        do {
            ArrayList<ArrayList<Double>> sub_arrays = new ArrayList();
            --index;
            int current_index = -gap_size[index];
            int number = 0;
            boolean state = false;
            int total = 0;

            while(true) {
                state = split_arrays(state, sub_arrays, current_index, array, index);
                total += (sub_arrays.get(sub_arrays.size() - 1)).size();
                ++number;
                if (total == array.size()) {
                    print_array(sub_arrays, array);
                    break;
                }

                if (!state) {
                    current_index = sub_arrays.size() - gap_size[index];
                } else {
                    ++current_index;
                }
            }
        } while(gap_size[index] != 1);

    }

    public static ArrayList<Double> insertion_sort(ArrayList<Double> doubles) {
        if (doubles.size() == 1) {
            return doubles;
        } else {
            for(int i = 1; i < doubles.size(); ++i) {
                for(int x = i - 1; x > -1; --x) {
                    if (!(doubles.get(i) < doubles.get(x) & x != 0)) {
                        if (doubles.get(i) <= doubles.get(x)) {
                            doubles.add(x, doubles.get(i));
                            doubles.remove(i + 1);
                        } else if (doubles.get(i) <= doubles.get(x + 1)) {
                            doubles.add(x + 1, doubles.get(i));
                            doubles.remove(i + 1);
                        }
                    }
                }
            }

            return doubles;
        }
    }

    public static void main(String[] args) {
        ArrayList<Double> testList = new ArrayList();
        testList.add(3.00);
        testList.add(6.55);
        testList.add(-12.2);
        testList.add(1.73);
        testList.add(140.98);
        testList.add(-4.18);
        testList.add(52.87);
        testList.add(99.14);
        testList.add(73.202);
        testList.add(-23.6);
        System.out.println(testList);
        shell_sort(testList);
    }
}
