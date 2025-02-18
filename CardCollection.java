import java.util.*;

class Card {
    private String symbol;
    private int value;

    public Card(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return "Symbol: " + symbol + ", Value: " + value;
    }
}

public class CardCollection {
    public static void main(String[] args) {
        Collection<Card> cards = new ArrayList<>();
        Scanner sc = new Scanner(System.in);  //Selim Jahangir 22bcs13878
        int choice;

        do {
            System.out.println("\n1. Add Card\n2. Display All Cards\n3. Search by Symbol\n4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Card Symbol: ");
                    String symbol = sc.nextLine();
                    System.out.print("Enter Card Value: ");
                    int value = sc.nextInt();
                    cards.add(new Card(symbol, value));
                    System.out.println("Card added successfully!");
                    break;

                case 2:
                    System.out.println("\nAll Stored Cards:");
                    for (Card card : cards) {
                        System.out.println(card);
                    }
                    break;

                case 3:
                    System.out.print("Enter Symbol to Search: ");
                    String searchSymbol = sc.nextLine();
                    boolean found = false;
                    System.out.println("Cards with Symbol '" + searchSymbol + "':");
                    for (Card card : cards) {
                        if (card.getSymbol().equalsIgnoreCase(searchSymbol)) {
                            System.out.println(card);
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("No cards found with the given symbol.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice, try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
