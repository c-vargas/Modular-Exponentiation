/*
 * Written By: Christian Vargas
 */

import java.util.*;

public class modularExponentiation {
    public static void main(String[] args) {
        int[] array = getInput();
        int base = array[0];
        int exponent = array[1];
        int mod = array[2];
        int answer = modularExp(base, exponent, mod);
        System.out.println("Final solution: " + answer);
    }

    public static int[] getInput() {
        Scanner in = new Scanner(System.in);
        int[] array = new int[3];
        System.out.println("Enter the base number: ");
        array[0] = in.nextInt();
        System.out.println("Enter the exponent: ");
        array[1] = in.nextInt();
        System.out.println("Enter the mod: ");
        array[2] = in.nextInt();
        return array;
    }

    public static int modularExp(int base, int exponent, int mod) {
        int answer, counter = 0;
        int currentExp = 1;
        int currentAnswer;

        Map<Integer, Integer> previousAnswer = new HashMap<>();
        System.out.println();
        System.out.println(base + "^" + exponent + " mod " + mod + " is calculated as follows:");

        while (currentExp < exponent) {
            if (counter == 0) {
                currentAnswer = (int)(Math.pow((double)base,(double)currentExp) % mod);
                System.out.println(base + "^" + currentExp + " mod " + mod);
            } else {
                currentAnswer = (int)Math.pow((double)previousAnswer.get(currentExp/2), 2.0) % mod;
                System.out.println(base + "^" + currentExp + " mod " + mod + " = " + previousAnswer.get(currentExp/2) + "^2 mod " + mod + " = " + currentAnswer);
            }

            previousAnswer.put(currentExp, currentAnswer);
            counter++;
            currentExp = currentExp * 2;
        }
        if (currentExp > exponent) {
            currentExp = currentExp/2;
        }

        ArrayList<Integer> desiredExponents = getDesiredExponents(exponent - currentExp);
        answer = previousAnswer.get(desiredExponents.get(0));

        for (int i = 0; i < desiredExponents.size() - 1; i++) {
            System.out.println();
            System.out.print(answer + " * " +  previousAnswer.get(desiredExponents.get(i+1)) + " mod " + mod);
            answer = (answer * previousAnswer.get(desiredExponents.get(i+1))%mod);
            System.out.print(" = " + answer);
        }
        System.out.println();
        System.out.println();
        System.out.println("Answer: " + answer + " * " + previousAnswer.get(currentExp) + " mod " + mod);
        return answer * previousAnswer.get(currentExp) % mod;
    }

    public static ArrayList<Integer> getDesiredExponents(int exp) {
        ArrayList<Integer> exponents = new ArrayList<>();
        int sum = 0;

        while (sum+2 <= exp) {
            sum += 2;
            exponents.add(2);
        }
        if (sum % exp != 0) {
            sum += 1;
            exponents.add(1);
        }
        return exponents;
    }
}