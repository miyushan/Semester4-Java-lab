import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Stack;


/**
 *
 * This program helps to find the shortest path to the city from a jungle.
 *
 */

/**
 *
 * @author 2018/E/102
 *
 */

public class  JungleRun_2018_E_102_L7 {

    private Node start, end;    //Node typed variables
    Node[][] arr;
    Stack<Node> obj = new Stack<>();

    //properties of Graph
    public class Node {
        int position;
        Node previous;
        boolean isPath = false;
        boolean isVisited = false;
        char sign = ' ';

        Node(int position) {
            this.position = position;
        }
    }


    /**
     * This is the constructor of main class
     * @param n     rows or columns of the matrix
     * @param way   way to get out fro te jungle
     */
    JungleRun_2018_E_102_L7(int n, char[][] way) {

        arr = new Node[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                int position = i * n + j;
                arr[i][j] = new Node(position);
                arr[i][j].sign = way[i][j];

                if (arr[i][j].sign == 'S') {
                    this.start = arr[i][j];
                }
                if (arr[i][j].sign == 'E') {
                    this.end = arr[i][j];
                }
                if (arr[i][j].sign == 'P') {
                    arr[i][j].isPath = true;
                }

            }

        }
    }


    /**
     * This method is used to find the minimum number of moves
     *
     * @return
     */
    public int minMoves() {

        Node firstNode = start;

        while (true) {

            int totalMoves;
            firstNode.isVisited = true;
            if (firstNode == end) {
                totalMoves = 0;
                while (firstNode.previous != null) {
                    if (firstNode.sign != 'S' && firstNode.sign != 'E') {
                        firstNode.sign = ' ';
                    }
                    firstNode = firstNode.previous;
                    totalMoves++;
                }
                return totalMoves;
            }

            stack(firstNode, arr.length);
            if (!obj.empty()) {
                firstNode = obj.pop();
            } else {
                return -1;
            }

        }

    }


    /**
     *
     * This method is used to add nodes to the stack
     *
     * @param firstNode     appropriate node
     * @param n             rows or columns of the matrix
     */
    public void stack(Node firstNode, int n) {

        int firstRow = firstNode.position / n;
        int firstCol = firstNode.position % n;
        int[][] temp = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

        for (int[] i : temp) {

            int tempCol = firstCol + i[1];
            int tempRow = firstRow + i[0];
            if (tempRow >= 0 && tempRow < n && tempCol >= 0 && tempCol < n && !arr[tempRow][tempCol].isVisited
                    && (arr[tempRow][tempCol].sign == 'E' || arr[tempRow][tempCol].sign == 'P'))
            {
                arr[firstRow + i[0]][firstCol + i[1]].previous = arr[firstRow][firstCol];
                obj.push(arr[firstRow + i[0]][firstCol + i[1]]);
            }
        }

    }



    /**
     * Main method
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        System.out.print("This program is used to find the minimum number of moves from S to E\n");
        System.out.print("\nInput array size: ");
        //get the input array size
        BufferedReader readN = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(readN.readLine());

        //two dimensional array to store input points
        char arr[][] = new char[n][n];
        int arrSize;
        String point;

        System.out.println("\nEnter your points as an array\nS: Start point\nE: End point\nP: path\nT: Tree");
        System.out.println("Note: Dont leave space between inputs");
        //get the input points
        for (int i = 0; i < n; i++) {

            do {
                point = readN.readLine();
                arrSize = point.length();
            } while (arrSize != n);

            for (int j = 0; j < n; j++) {
                arr[i][j] = point.charAt(j);
            }
        }

        JungleRun_2018_E_102_L7 obj = new JungleRun_2018_E_102_L7(n, arr);
        int minMoves = obj.minMoves();
        System.out.println("\nMinimum number of moves: " + minMoves);

    }

}
