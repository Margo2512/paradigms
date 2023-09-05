package search;
public class BinarySearchMax {
    public static void main(String[] args) {
        //Pred: for i=1..n: ards[i] != null
        int key = BinarySearchMax(args);
        // for i=1..n: ards[i] != null && key = BinarySearchMax(args)
        if (key < 0) {
            // for i=1..n: ards[i] != null && key = BinarySearchMax(args) && key < 0
            key = args.length - 1;
            // for i=1..n: ards[i] != null && key = BinarySearchMax(args) && key < 0 && key = args.length -
        }
        // for i=1..n: ards[i] != null && key = BinarySearchMax(args)
        System.out.println(args[key%args.length]);
        //Post: println(args[key%args.length])
    }

    //Pred: for i=1..n: args[i] != null, args[i] < args[i+1] && left_part < right_part
    public static int BinarySearchMax(String[] args) {
        int left_part = 0;
        // for i=1..n: args[i] != null, args[i] < args[i+1] && left_part < right_part && left_part = 0
        int right_part = args.length - 1;
        // for i=1..n: args[i] != null, args[i] < args[i+1] && left_part = 0 && right_part = args.length - 1
        while(left_part < right_part) {
            // for i=1..n: args[i] != null, args[i] < args[i+1] && left_part < right_part && left_part = 0 && right_part = args.length - 1 && left_part < right_part
            if(Integer.parseInt(args[(left_part + right_part)/2]) > Integer.parseInt(args[right_part]))
                // for i=1..n: args[i] != null, args[i] < args[i+1] && left_part < right_part && left_part = 0 && right_part = args.length - 1 && left_part < right_part && args[(left_part + right_part)/2]) > Integer.parseInt(args[right_part])
                left_part = (left_part + right_part)/2 + 1;
                // for i=1..n: args[i] != null, args[i] < args[i+1] && left_part < right_part && left_part = 0 && right_part = args.length - 1 && left_part < right_part && args[(left_part + right_part)/2]) > Integer.parseInt(args[right_part]) && left_part = (left_part + right_part)/2 + 1
            else
                // for i=1..n: args[i] != null, args[i] < args[i+1] && left_part < right_part && left_part = 0 && right_part = args.length - 1 && left_part < right_part && args[(left_part + right_part)/2]) <= Integer.parseInt(args[right_part])
                right_part = (left_part + right_part)/2;
            // for i=1..n: args[i] != null, args[i] < args[i+1] && left_part < right_part && left_part = 0 && right_part = args.length - 1 && left_part < right_part && args[(left_part + right_part)/2]) <= Integer.parseInt(args[right_part]) && right_part = (left_part + right_part)/2
        }
        //left_part >= right_part && for i=1..n: args[i] != null, args[i] < args[i+1] && left_part = (left_part + right_part)/2 + 1 || right_part = (left_part + right_part)/2
        return Integer.parseInt(args[left_part% args.length]) > Integer.parseInt(args[(left_part + 1) % args.length]) ? left_part : left_part-1;
        //Post: args[left_part% args.length] > args[(left_part + 1) % args.length] ? left_part : left_part-1
    }
}

