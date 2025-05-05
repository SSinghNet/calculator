public class Main {
    public static void main(String[] args) {
        try {
            Calculator.calculate("3 * 4 - 9^2 + 6");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}