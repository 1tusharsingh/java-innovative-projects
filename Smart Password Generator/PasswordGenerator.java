import java.util.Scanner;

public class PasswordGenerator {

    
    public static String generatePassword(String name, String pan, String dob) {

        
        String part1 = name.substring(0, 3).toUpperCase();

        
        String part2 = pan.substring(pan.length() - 4);

        
        String[] dateParts = dob.split("/");
        String year = dateParts[2];

        
        String symbols = "@#";

        return part1 + part2 + symbols + year;
    }

    
    public static String checkStrength(String password) {

        if (password.length() >= 12 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[0-9].*") &&
                password.matches(".*[@#$%^&+=!].*")) {

            return "Strong";
        }
        else if (password.length() >= 8) {
            return "Medium";
        }
        else {
            return "Weak";
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== Smart Password Generator =====");

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter PAN Number: ");
        String pan = sc.nextLine();

        System.out.print("Enter Date of Birth (DD/MM/YYYY): ");
        String dob = sc.nextLine();

        String password = generatePassword(name, pan, dob);

        System.out.println("\nGenerated Password: " + password);

        String strength = checkStrength(password);

        System.out.println("Password Strength: " + strength);

        sc.close();
    }
}