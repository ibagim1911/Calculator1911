import java.util.Scanner;
class Calculator {

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Vidite dva chislo: arabski ili rimski = ");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }
    public static String parse(String expression) throws Exception {
        int nomer1;
        int nomer2;
        String oper;
        String result;
        boolean itRim;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("dolzhno bit, dva operanda");
        oper = detectOperation(expression);
        if (oper == null) throw new Exception("nepoderzhivaet matematicheski operatsii");

        if (Rim.itRim(operands[0]) && Rim.itRim(operands[1])) {

            nomer1 = Rim.convertToArabi(operands[0]);
            nomer2 = Rim.convertToArabi(operands[1]);
            itRim = true;
        }

        else if (!Rim.itRim(operands[0]) && !Rim.itRim(operands[1])) {
            nomer1 = Integer.parseInt(operands[0]);
            nomer2 = Integer.parseInt(operands[1]);
            itRim = false;
        }

        else {
            throw new Exception("chislo dolzhni bit: v odnom formate");
        }
        if (nomer1 > 10 || nomer2 > 10) {
            throw new Exception("chislo dolzhni bit: ot 1 do 10");
        }
        int arabi = Calc(nomer1, nomer2, oper);
        if (itRim) {

            if (arabi<= 0) {
                throw new Exception(" chisli dolzhni bit: bolshe nolya");
            }

            result = Rim.convertToRim(arabi);
        } else {

            result = String.valueOf(arabi);
        }

        return result;
    }
    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }
    static int Calc(int a, int b, String oper) {
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }
}
class Rim {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};
    public static boolean itRim(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }
    public static int convertToArabi(String rim) {
        for (int i = 0; i < romanArray.length; i++) {
            if (rim.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }
    public static String convertToRim(int arabi) {
        return romanArray[arabi];
    }
}
