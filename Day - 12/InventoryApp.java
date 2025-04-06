import java.util.Scanner;

class Item {
    String itemName;
    int itemId;
    int quantity;
    double price;
    Item next;

    public Item(String itemName, int itemId, int quantity, double price) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.price = price;
    }
}

class Inventory {
    private Item head;

    public void addAtBeginning(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    public void addAtEnd(String itemName, int itemId, int quantity, double price) {
        Item newItem = new Item(itemName, itemId, quantity, price);
        if (head == null) {
            head = newItem;
        } else {
            Item temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newItem;
        }
    }

    public void addAtPosition(int pos, String itemName, int itemId, int quantity, double price) {
        if (pos <= 0) {
            System.out.println("Invalid position.");
            return;
        }
        if (pos == 1) {
            addAtBeginning(itemName, itemId, quantity, price);
            return;
        }
        Item newItem = new Item(itemName, itemId, quantity, price);
        Item temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Position out of bounds.");
        } else {
            newItem.next = temp.next;
            temp.next = newItem;
        }
    }

    public void removeById(int itemId) {
        if (head == null) return;
        if (head.itemId == itemId) {
            head = head.next;
            return;
        }
        Item temp = head;
        while (temp.next != null && temp.next.itemId != itemId) {
            temp = temp.next;
        }
        if (temp.next != null) {
            temp.next = temp.next.next;
        } else {
            System.out.println("Item not found.");
        }
    }

    public void updateQuantity(int itemId, int newQty) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQty;
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    public void searchById(int itemId) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                displayItem(temp);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found.");
    }

    public void searchByName(String name) {
        Item temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(name)) {
                displayItem(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) {
            System.out.println("No item with the name found.");
        }
    }

    public void displayInventory() {
        Item temp = head;
        if (temp == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        while (temp != null) {
            displayItem(temp);
            temp = temp.next;
        }
    }

    public void displayTotalValue() {
        double total = 0;
        Item temp = head;
        while (temp != null) {
            total += temp.price * temp.quantity;
            temp = temp.next;
        }
        System.out.println("Total Inventory Value: ₹" + total);
    }

    public void sortInventory(String by, boolean ascending) {
        head = mergeSort(head, by, ascending);
    }

    private Item mergeSort(Item head, String by, boolean asc) {
        if (head == null || head.next == null) return head;
        Item mid = getMid(head);
        Item right = mid.next;
        mid.next = null;
        Item leftSorted = mergeSort(head, by, asc);
        Item rightSorted = mergeSort(right, by, asc);
        return merge(leftSorted, rightSorted, by, asc);
    }

    private Item getMid(Item head) {
        Item slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Item merge(Item l1, Item l2, String by, boolean asc) {
        Item dummy = new Item("", 0, 0, 0);
        Item tail = dummy;

        while (l1 != null && l2 != null) {
            boolean compare;
            if (by.equalsIgnoreCase("name")) {
                compare = asc ? l1.itemName.compareToIgnoreCase(l2.itemName) <= 0
                              : l1.itemName.compareToIgnoreCase(l2.itemName) > 0;
            } else {
                compare = asc ? l1.price <= l2.price : l1.price > l2.price;
            }

            if (compare) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    private void displayItem(Item item) {
        System.out.println("Name: " + item.itemName + ", ID: " + item.itemId + ", Qty: " + item.quantity + ", Price: ₹" + item.price);
    }
}

public class InventoryApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();
        int choice;

        do {
            System.out.println("\nInventory Management Menu");
            System.out.println("1. Add Item at Beginning");
            System.out.println("2. Add Item at End");
            System.out.println("3. Add Item at Position");
            System.out.println("4. Remove Item by ID");
            System.out.println("5. Update Item Quantity");
            System.out.println("6. Search by ID");
            System.out.println("7. Search by Name");
            System.out.println("8. Display All Items");
            System.out.println("9. Display Total Inventory Value");
            System.out.println("10. Sort Inventory");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            int id, qty, pos;
            String name;
            double price;

            switch (choice) {
                case 1:
                case 2:
                case 3:
                    System.out.print("Item Name: ");
                    name = sc.nextLine();
                    System.out.print("Item ID: ");
                    id = sc.nextInt();
                    System.out.print("Quantity: ");
                    qty = sc.nextInt();
                    System.out.print("Price: ");
                    price = sc.nextDouble();
                    if (choice == 1)
                        inventory.addAtBeginning(name, id, qty, price);
                    else if (choice == 2)
                        inventory.addAtEnd(name, id, qty, price);
                    else {
                        System.out.print("Position: ");
                        pos = sc.nextInt();
                        inventory.addAtPosition(pos, name, id, qty, price);
                    }
                    break;
                case 4:
                    System.out.print("Enter Item ID to remove: ");
                    id = sc.nextInt();
                    inventory.removeById(id);
                    break;
                case 5:
                    System.out.print("Enter Item ID: ");
                    id = sc.nextInt();
                    System.out.print("Enter new Quantity: ");
                    qty = sc.nextInt();
                    inventory.updateQuantity(id, qty);
                    break;
                case 6:
                    System.out.print("Enter Item ID: ");
                    id = sc.nextInt();
                    inventory.searchById(id);
                    break;
                case 7:
                    System.out.print("Enter Item Name: ");
                    name = sc.nextLine();
                    inventory.searchByName(name);
                    break;
                case 8:
                    inventory.displayInventory();
                    break;
                case 9:
                    inventory.displayTotalValue();
                    break;
                case 10:
                    System.out.print("Sort by (name/price): ");
                    String by = sc.nextLine();
                    System.out.print("Ascending? (true/false): ");
                    boolean asc = sc.nextBoolean();
                    inventory.sortInventory(by, asc);
                    break;
                case 0:
                    System.out.println("Exiting Inventory System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
