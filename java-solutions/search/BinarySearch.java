package search;
public class BinarySearch {

    public static void main(String[] args) {
        //Pred: for i=1..n: ards[i] != null
        int sum = 0;
        // sum = 0
        for (int i = 1; i < args.length - 1; i++) {
            // i < args.length - 1
            sum += Integer.parseInt(args[i]);
            // i < args.length - 1 && sum += args[i]
        }
        // i >= args.length - 1
        int key = Integer.parseInt(args[0]);
        // true && key = args[0];
        int right_part = args.length - 1;
        //true && key = args[0] && right_part = args.length - 1
        int left_part = -1;
        //true && key = args[0] && right_part = args.length - 1 && left_part = -1
        int middle = (left_part + right_part) / 2;
        //true && key = args[0] && right_part = args.length - 1 && left_part = -1 && middle = (left_part + right_part) / 2
        if (sum % 2 == 0) {
            //true && key = args[0] && right_part = args.length - 1 && left_part = -1 && middle = (left_part + right_part) / 2 && sum % 2 == 0
            System.out.println(recursive_search(args, key, left_part, middle, right_part));
            //true && key = args[0] && right_part = args.length - 1 && left_part = -1 && middle = (left_part + right_part) / 2 && sum % 2 == 0 && recursive_search(args, key, left_part, middle, right_part)
        }
        else {
            //true && key = args[0] && right_part = args.length - 1 && left_part = -1 && middle = (left_part + right_part) / 2 && sum % 2 != 0
            System.out.println(iterative_search(args, key, left_part, right_part));
            //true && key = args[0] && right_part = args.length - 1 && left_part = -1 && middle = (left_part + right_part) / 2 && sum % 2 != 0 && iterative_search(args, key, left_part, right_part)
        }
        //Post: println(recursive_search(args, key, left_part, middle, right_part)
        //println(iterative_search(args, key, left_part, right_part)
    }

    //Pred: for i=1..n: args[i] != null, args[i] < args[i+1] && left_part < right_part && key - целое
    //Post: R = key (если есть), 0 (если нет)
    public static int iterative_search(String[] args, int key, int left_part, int right_part) {
        // Pred: args[right_part] <= key
        while (left_part < right_part - 1) {
            // args[right_part] <= key && left_part < right_part - 1
            int middle = (left_part + right_part) / 2;
            //Pred: args[right_part] <= key && left_part < right_part - 1 && middle = (left_part + right_part) / 2
            if (Integer.parseInt(args[middle + 1]) > key) {
                //Pred1: args[right_part] <= key && left_part < right_part - 1 && middle = (left_part + right_part) / 2 && args[middle + 1] > key
                left_part = middle;
                //Post1:args[right_part] <= key && left_part < right_part - 1 && middle = (left_part + right_part) / 2 && args[middle + 1] > key && left_part = middle
            } else {
                //Pred2: args[right_part] <= key && left_part < right_part - 1 && middle = (left_part + right_part) / 2 && args[middle + 1] <= key
                right_part = middle;
                //Pred2: args[right_part] <= key && left_part < right_part - 1 && middle = (left_part + right_part) / 2 && args[middle + 1] <= key && right_part = middle
            }
            //args[right_part] <= key
        }
        //Post: args[right_part] <= key && left_part >= right_part - 1
        return right_part;
    }

    //Pred: for i=1..n: args[i] != null, args[i] < args[i+1] && left_part < right_part && key - целое
    //Post: R = key (если есть), 0 (если нет)
    public static int recursive_search(String[] args, int key, int left_part, int middle, int right_part) {
        //Pred: -1 <= left_part <= middle <= right_part <= n
        while (left_part < right_part - 1) {
            // -1 <= left_part <= middle <= right_part <= n && left_part < right_part - 1
            if (Integer.parseInt(args[middle + 1]) > key) {
                // -1 <= left_part <= middle <= right_part <= n && left_part < right_part - 1 && args[middle + 1] > key
                left_part = middle;
                // -1 <= left_part <= middle <= right_part <= n && left_part < right_part - 1 && args[middle + 1] > key && left_part = middle
                return recursive_search(args, key, middle, (left_part + right_part) / 2, right_part);
            } else {
                // -1 <= left_part <= middle <= right_part <= n && left_part < right_part - 1 && args[middle + 1] <= key
                right_part = middle;
                // -1 <= left_part <= middle <= right_part <= n && left_part < right_part - 1 && args[middle + 1] <= key && right_part = middle
                return recursive_search(args, key, left_part, (left_part + right_part) / 2, middle);
            }
            // -1 <= left_part <= middle <= right_part <= n
        }
        //Post: -1 <= left_part <= middle <= right_part <= n && left_part >= right_part - 1
        return right_part;
    }
}
