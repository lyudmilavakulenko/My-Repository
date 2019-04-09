public class Lesson1_Strings {
    // главный метод
    public static void main(String[] args) {
        System.out.println(factorial(1));

        }
        // факториал рекурсии
    public static long factorial (long n) {
        if (n<=1)
            return 1;
        else {
            return n*(factorial(n-1));
        }
    }
    }

