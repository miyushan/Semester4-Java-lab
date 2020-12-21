/**
 *
 * @author 2018/E/102
 *
 */

/**
 *
 * This programme is used to store some details of a city to database records and arrange them in different particular orders.
 *
 */

public class CityDatabase {

    //root node of the tree
    private static node root;

    public static class node {

        node left;           //left node of a tree
        node right;          //right node of a tree
        String cityName;     //name of a city
        Double latitude;     //latitude of the city
        Double longitude;    //longitude of the city

        /**
         *
         * node: use to initialize the variables and get inputs
         *
         * @param city      name of the city
         * @param x         latitude of the city
         * @param y         longitude of the city
         */
        public node(String city, Double x, Double y) {

            cityName = city;
            left = null;
            right = null;
            latitude = x;
            longitude = y;
        }
    }


    ////////////////insertion////////////////////
    /**
     *
     * This method is used to call the node typed insert method and print inserting nodes
     *
     * @param city      name of the city which want to insert
     * @param x         latitude of the city
     * @param y         longitude of the city
     */
    public static void insert(String city, double x, double y)
    {
        root = insert(root, city, x, y);
        System.out.println(city + " is inserted to the database");    //show a message when inserting a new element
    }



    /**
     *
     * This method is used to insert a given data into the appropriate node (Insertion)
     *
     * @param root      root node of the main tree or a sub tree
     * @param city      name of the city which want to insert
     * @param x         latitude of the city
     * @param y         longitude of the city
     * @return          name of the city that wanted to insert
     */
    public static node insert(node root, String city, Double x, Double y) {

        if (root == null) {
            root = new node(city, x, y);    //root node of a considering tree
        }
        else {

            //compare the root node and child node and choose the right path of insertion
            if (city.compareTo(root.cityName) < 0) {
                root.left = insert(root.left, city, x, y);
            }
            else if (city.compareTo(root.cityName) > 0) {
                root.right = insert(root.right, city, x, y);
            }

        }
        return root;
    }



    ////////////////deletion////////////////////
    /**
     *
     * This method is used to call the node typed delete method and print the deleted node
     *
     * @param city       name of the city which want to delete
     */
    public void delete(String city) {
        root = delete(root, city);
        System.out.println("\n" + city + " is deleted from the database");
    }



    /**
     *
     * This method is used to delete a node from the tree (Deletion)
     *
     * @param root      root node of the main tree or a sub tree
     * @param city      name of the city which want to delete
     * @return          name of the city that wanted to delete
     */
    public node delete(node root, String city) {

        if (root == null) {
            return null;    //check the tree include nodes or not
        }
        else {

            //compare the root node and child node and choose the right path of deletion
            if (city.compareTo(root.cityName) < 0) {
                root.left = delete(root.left, city);
            }
            else if (city.compareTo(root.cityName) > 0) {
                root.right = delete(root.right, city);
            }

            //found the element need to delete
            else {

                //check no left child nodes
                if (root.left == null) {
                    return root.right;
                }
                //check no right child nodes
                else if (root.right == null) {
                    return root.left;
                }
                //check is there two child nodes
                root.cityName = minimum(root.right);
                root.right = delete(root.right, root.cityName);
            }
        }
        return root;
    }


    /**
     *
     * This method is used to find the minimum valued node of a tree
     *
     * @param root      root node of the main tree or a sub tree
     * @return          minimum node of a tree
     */
    String minimum(node root) {

        String min = root.cityName;
        while (root.left != null) {
            min = root.left.cityName;
            root = root.left;
        }
        return min;
    }




    ////////////////search////////////////////
    /**
     *
     * This method is used to call the node typed search method
     *
     * @param city      name of the city which want to search
     */
    public void search(String city) {
        search(root, city);
    }


    /**
     *
     * This method is used to search a node from the tree (Search)
     *
     * @param root      root node of the main tree or a sub tree
     * @param city      name of the city which want to search
     * @return
     */
    public node search(node root, String city) {

        if (root == null) {
            System.out.println(city + " city is not inserted in the database");   //Searched city is not in the database
            return root;   //end the inside recursion
        }

        //searched city has found
        if (city.compareTo(root.cityName) == 0) {
            System.out.println(city + " city's coordinates are: " + root.latitude+ " , " + root.longitude); //show a message when found the searched element
        }

        //compare the root node and child node and choose the right path to search
        else if (city.compareTo(root.cityName) > 0) {
            return search(root.right, city);
        }
        else {
            return search(root.left, city);
        }

        return root;
    }



    ////////////////distance////////////////////
    /**
     *
     * This method is used to print the considering distance and call node typed distance method
     *
     * @param dis       input distance
     */
    public void distance(double dis) {
        System.out.println(dis);
        distance(root, dis);
    }


    /**
     *
     * This method is used to print all cities within a given distance of a specified point
     *
     * @param root      root node or root node of a sub tree
     * @param dis       input distance
     */
    void distance(node root, double dis) {
        //check input distance null value or not
        if (root != null) {
            distance(root.left, dis);
            //calculate the distance with reference (0,0)
            double val = Math.sqrt( root.latitude * root.latitude + root.longitude * root.longitude );
            if (val <= dis) {
                //print the details which are related to the statement
                System.out.println(root.cityName + " Distance of a specified point\t: " + val);
            }
            //recursive operation to check the statements for other nodes.
            distance(root.right, dis);
        } else
            return;
    }



    ////////////////change the order////////////////////
    /**
     *
     * This method is used to Print details in ascending order
     *
     * @param root      root node of the tree
     */
    void ascending(node root) {

        if (root != null) {
            ascending(root.left);
            System.out.println(root.cityName + "\t\t: " + root.latitude + "," + root.longitude);
            ascending(root.right);
        } else
            return;
    }



    /**
     *
     * This method is used to Print details in descending order
     *
     * @param root      root node of the tree
     */
    void descending(node root) {

        if (root != null) {
            descending(root.right);
            System.out.println(root.cityName + "\t\t: " + root.latitude + "," + root.longitude);
            descending(root.left);
        } else
            return;
    }



    ////////////////print////////////////////
    /**
     *
     * This method is used to print the details after a function
     *
     * @param functionName      the function called before print details
     */
    void printData(String functionName) {

        System.out.println("\n\tDatabase details after " + functionName);
        if(functionName == "descending operation"){
            descending(root);
        }
        else {
            ascending(root);
        }
    }



    ////////////////main method////////////////////
    /**
     *
     * This main methode is used to give input hard cordedly, to call all other functions and get the required solutions
     *
     * @param args
     */
    public static void main(String[] args) {

        System.out.println();
        //insertion
        CityDatabase.insert("Colombo", 6.927079, 79.861244);
        CityDatabase.insert("Chicago", 41.881832, -87.623177);
        CityDatabase.insert("Sydney", -33.865143, 151.209900);
        CityDatabase.insert("Dubai", 20.638273, -2.643787);
        CityDatabase.insert("Paris", -15.402167, 98.755374);
        CityDatabase.insert("Kandy", 18.234865, -11.435766);


        CityDatabase obj = new CityDatabase();

        //print the details after insertion
        obj.printData("insertion");

        //deletion
        obj.delete("Sydney");
        //print the details after deletion
        obj.printData("deletion");

        //search various cities
        System.out.println();
        obj.search("Paris");
        obj.search("Bangkok");
        //print the details after search operation
        obj.printData("search operation");

        //convert to descending order (print)
        obj.printData("descending operation");

        //distance operation
        System.out.print("\nAll cities within a given distance of a specified point: ");
        obj.distance(95);
        //print the details after distance operation
        obj.printData("distance operation");

    }

}
