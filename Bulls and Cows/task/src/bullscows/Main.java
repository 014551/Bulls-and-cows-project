package bullscows;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.*;

public class Main {
    public static boolean bullCowGuess(String result, String guess){
        int bulls = 0;
        int cows = 0;
        boolean guessed = false;
        for (int i = 0; i < result.length(); i++) {
            if (guess.charAt(i) == result.charAt(i)) {
                bulls++;
            } else if (result.contains(String.valueOf(guess.charAt(i)))) {
                cows++;

            }

        }
        if (cows == 0 && bulls == 0) {
            System.out.println("Grade: None.");

        } else if (bulls == result.length()) {
            System.out.printf("Grade: %d bull(s).", bulls);
            System.out.println("Congratulations! You guessed the secret code.");
            guessed = true;

        } else if (bulls == 0) {
            System.out.printf("Grade: %d cow(s).%n", cows);

        } else if (cows == 0 ) {
            System.out.printf("Grade: %d bull(s).", bulls);

        } else {
            System.out.printf("Grade: %d bull(s) and %d cow(s).%n", bulls, cows);
        }
        return guessed;


    }
    public static String generateRandomNum(int numberLength) {
        StringBuilder result = new StringBuilder();
        do{
            long pseudoRandomNumber = System.nanoTime();
            String randomNum = String.valueOf(pseudoRandomNumber);
            for(int i = 0; i < randomNum.length(); i++) {
                char currentDigit = randomNum.charAt(i);
                if(!result.toString().contains(String.valueOf(currentDigit))){
                    result.append(currentDigit);
                }
                if(result.length() == numberLength) {
                    break;

                }
            }

        } while(result.length() != numberLength);

        return result.toString();


    }
    public static String generateRandomNumWithRandomClass(int numberLength) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        do{
            int generatedNum = random.nextInt(10);
            if(!result.toString().contains(String.valueOf(generatedNum))){
                result.append(generatedNum);
            }
        } while(result.length() != numberLength);
        return result.toString();
    }

    public static String generateRandomNumAlph(int numberLength, int rangeAlph) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        do{
            int generatedNum = random.nextInt(10);
            if(rangeAlph > 11) {
                char generateChar = (char) ('a' + random.nextInt(rangeAlph - 11));
                if(!result.toString().contains(String.valueOf(generatedNum)) && !result.toString().contains(String.valueOf(generateChar))){
                    result.append(generatedNum);
                    result.append(generateChar);
                }
            } else {
                if (!result.toString().contains(String.valueOf(generatedNum))) {
                    result.append(generatedNum);
                }
            }

        } while(result.length() != numberLength);
        return result.toString();
    }

    public static String inputProcessor(){
        Scanner scanner = new Scanner(System.in);
        int input;
        int input2;

        try {
            System.out.println("Input the length of the secret code:");
            input = scanner.nextInt();

        } catch(InputMismatchException e) {
            throw new IllegalArgumentException("Error: isn't a valid number.");
        }

        try {
            System.out.println("Input the number of possible symbols in the code:");
            input2 = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new IllegalArgumentException("Error: isn't a valid number.");
        }




        String result = "";
        String stars = "";
        char alphTill;
        if (input2 < 11 && input > input2) {
           throw new IllegalArgumentException("Error: it's not possible to generate a code with a length of " + input + " with " + input2 + " unique symbols.");
        }
        if (input2 > 36) {
            throw new IllegalArgumentException("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
        }
        if(input == 0) {
            throw new IllegalArgumentException("Error: the length of the input should be more than 0.");
        }

        for(int i = 0; i < input; i ++) {
            stars += "*";
        }
        if(input <= 36) {
            result = generateRandomNumAlph(input, input2);
            if(input2 < 11) {
                System.out.println("The secret is prepared: " +  stars + " (0-9)");
            } else {
                alphTill = (char) ('a' + input2 - 11);
                System.out.println("The secret is prepared: " +  stars + " (0-9, a-" + alphTill + ")");
            }

            System.out.println("Okay, let's start a game!");
        } else {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits. %n", input);
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String result = "";



        boolean guessed = false;

        try {
            result = inputProcessor();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;

        }

        int counter = 1;
        String guess;

        do{
            System.out.println("Turn " + counter++);

                guess = scanner.next();
                if(guess.length() != result.length()) {
                    System.out.println("Please, enter valid input guess.");
                }
                guessed = bullCowGuess(result, guess);



        } while (!guessed);
    }
}
