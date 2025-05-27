public class TypeCastingExample {
    public static void main(String[] args) {
        double doubleVal = 45.67;
        int intVal = (int) doubleVal;

        int intNum = 100;
        double doubleNum = intNum;

        System.out.println("Original double: " + doubleVal);
        System.out.println("Double cast to int: " + intVal);
        System.out.println("Original int: " + intNum);
        System.out.println("Int cast to double: " + doubleNum);
    }
}
