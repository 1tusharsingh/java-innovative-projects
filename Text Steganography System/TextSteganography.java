import java.io.*;
import java.util.Scanner;

public class TextSteganography {

    // Encode Message
    public static void encodeMessage(String coverFile,
                                     String outputFile,
                                     String secretMessage)
            throws IOException {

        BufferedReader br =
                new BufferedReader(new FileReader(coverFile));

        BufferedWriter bw =
                new BufferedWriter(new FileWriter(outputFile));

        String line;

        // Copy original file content
        while ((line = br.readLine()) != null) {

            bw.write(line);
            bw.newLine();
        }

        // Add hidden message
        bw.write("###SECRET###" + secretMessage);
        bw.newLine();

        br.close();
        bw.close();

        System.out.println("Message Hidden Successfully!");
    }

    // Decode Message
    public static void decodeMessage(String encodedFile)
            throws IOException {

        BufferedReader br =
                new BufferedReader(new FileReader(encodedFile));

        String line;
        boolean found = false;

        while ((line = br.readLine()) != null) {

            if (line.startsWith("###SECRET###")) {

                String secret =
                        line.substring(12);

                System.out.println("Hidden Message: " + secret);

                found = true;
            }
        }

        if (!found) {
            System.out.println("No Hidden Message Found!");
        }

        br.close();
    }

    // Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            System.out.print("Enter Cover File Name: ");
            String coverFile = sc.nextLine();

            System.out.print("Enter Output File Name: ");
            String outputFile = sc.nextLine();

            System.out.print("Enter Secret Message: ");
            String message = sc.nextLine();

            encodeMessage(coverFile, outputFile, message);

            System.out.println("\nRetrieving Hidden Message...\n");

            decodeMessage(outputFile);

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}