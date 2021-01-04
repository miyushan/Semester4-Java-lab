import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 *
 * @author  2018/E/102
 *
 */

/**
 *
 * This programme is used to find the maximum number of bottles can fill with the inputs ( Number of bottles & capacity of each bottles) by using Greedy approach
 *
 */
public class MaximumBottles_2018e102 {


    /**
     *
     * This method is used to sort bottle capacities in ascending order by using Optimized Bubble Sort
     *
     * @param arr   unsorted capacity array
     */
    static void optimizedBubbleSort(int[] arr) {
        int sum = arr.length;
        boolean isSwapped;
        int newLimit = 0, temp = 0;
        do {

            isSwapped = false;
            for (int i = 1; i < sum; i++) {

                if (arr[i - 1] < arr[i]) {
                    //swap operation
                    temp = arr[i - 1];
                    arr[i - 1] = arr[i];
                    arr[i] = temp;

                    isSwapped = true;
                    newLimit = i;

                }
            }
            sum = newLimit;
        } while (isSwapped);
    }


    /**
     *
     * This method is used to find the maximum bottles can fill from given input bottles
     *
     * @param reqBottles    capacity to fill
     * @param capacity      sorted capacity array (descending order)
     * @return
     */
    static int greedyApproach(int reqBottles, int capacity[]) {

        //not possible
        if (reqBottles <= 0) {
            return 0;
        }

        for (int i = capacity.length - 1; i >= 0; i--) {
            //compair sum of sorted bottle sizes and maximum capacity
            if (reqBottles >= capacity[i]) {
                return 1 + greedyApproach(reqBottles - capacity[i], Arrays.copyOf(capacity, capacity.length - 1));
            }
        }
        return 0;
    }


    /**
     * Main method
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.println("This programme finds the maximum numbers of bottles fill from input number of bottles.");
        System.out.println("Enter total number of bottles and capacity of the container.\nNote: Use one space to separate them.");

        //Get number of bottles as an input
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String bottle  = reader.readLine();
        String[] arrBottle = bottle.split(" ");

        //Number of required bottles
        int reqBottles[] = new int[2];
        for (int i = 0; i < 2; i++) {
            reqBottles[i] = Integer.parseInt(arrBottle[i]);
        }

        //bottle capacity array
        int capacity[] = new int[reqBottles[0]];
        int inputSize;
        String nextInput;

        //print bottle sizes and store as an input
        do {
            System.out.println("\nEnter capacities of above bottles.\nNote: Use one space to separate them.");
            nextInput = reader.readLine();
            inputSize = nextInput.split(" ").length;
        } while (inputSize != reqBottles[0]);

        //convert String array to an int array
        String[] size = nextInput.split(" ");
        for (int i = 0; i < reqBottles[0]; i++) {
            capacity[i] = Integer.parseInt(size[i]);
        }

        //to sort the input array
        optimizedBubbleSort(capacity);
        System.out.println("\nThe maximum number of bottles can fill: ");
        System.out.println(greedyApproach(reqBottles[1], capacity));

    }

}
