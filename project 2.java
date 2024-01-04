import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class LinkShortener {
    private Map<String, String> urlMap;
    private Random random;

    public LinkShortener() {
        urlMap = new HashMap<>();
        random = new Random();
    }

    public String shortenURL(String longURL) {
        String shortURL = generateShortURL();

        // Check for collision
        while (urlMap.containsKey(shortURL)) {
            shortURL = generateShortURL();
        }

        urlMap.put(shortURL, longURL);
        return shortURL;
    }

    public String expandURL(String shortURL) {
        if (urlMap.containsKey(shortURL)) {
            return urlMap.get(shortURL);
        } else {
            return "Short URL not found.";
        }
    }

    private String generateShortURL() {
        // Generate a random short URL using a basic hash function
        StringBuilder sb = new StringBuilder();
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        int length = 6;

        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter the long URL to shorten:");
                    String longURL = scanner.nextLine();
                    String shortURL = linkShortener.shortenURL(longURL);
                    System.out.println("Shortened URL: " + shortURL);
                    break;
                case 2:
                    System.out.println("Enter the short URL to expand:");
                    String inputURL = scanner.nextLine();
                    String expandedURL = linkShortener.expandURL(inputURL);
                    System.out.println("Expanded URL: " + expandedURL);
                    break;
                case 3:
                    scanner.close();
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
