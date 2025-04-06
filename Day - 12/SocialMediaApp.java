import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friendIds;
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }
}

class SocialMedia {
    private User head;

    public SocialMedia() {
        this.head = null;
    }

    public void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newUser;
        }
    }

    public void addFriendConnection(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        if (user1 != null && user2 != null) {
            if (!user1.friendIds.contains(userId2)) {
                user1.friendIds.add(userId2);
            }
            if (!user2.friendIds.contains(userId1)) {
                user2.friendIds.add(userId1);
            }
            System.out.println("Friendship established between " + user1.name + " and " + user2.name);
        }
    }

    public void removeFriendConnection(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        if (user1 != null && user2 != null) {
            user1.friendIds.remove(Integer.valueOf(userId2));
            user2.friendIds.remove(Integer.valueOf(userId1));
            System.out.println("Friendship removed between " + user1.name + " and " + user2.name);
        }
    }

    public void findMutualFriends(int userId1, int userId2) {
        User user1 = findUserById(userId1);
        User user2 = findUserById(userId2);

        if (user1 != null && user2 != null) {
            HashSet<Integer> mutualFriends = new HashSet<>(user1.friendIds);
            mutualFriends.retainAll(user2.friendIds);
            System.out.println("Mutual friends between " + user1.name + " and " + user2.name + ": " + mutualFriends);
        }
    }

    public void displayAllFriends(int userId) {
        User user = findUserById(userId);

        if (user != null) {
            System.out.println(user.name + "'s Friends:");
            for (Integer friendId : user.friendIds) {
                User friend = findUserById(friendId);
                if (friend != null) {
                    System.out.println(" - " + friend.name);
                }
            }
        }
    }

    public User findUserById(int userId) {
        User current = head;
        while (current != null) {
            if (current.userId == userId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public User findUserByName(String name) {
        User current = head;
        while (current != null) {
            if (current.name.equalsIgnoreCase(name)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public int countFriends(int userId) {
        User user = findUserById(userId);
        if (user != null) {
            return user.friendIds.size();
        }
        return 0;
    }

    public void displayAllUsers() {
        User current = head;
        while (current != null) {
            System.out.println("User ID: " + current.userId + ", Name: " + current.name + ", Age: " + current.age);
            current = current.next;
        }
    }
}

public class SocialMediaApp {
    public static void main(String[] args) {
        SocialMedia socialMedia = new SocialMedia();

        socialMedia.addUser(1, "Alice", 25);
        socialMedia.addUser(2, "Bob", 30);
        socialMedia.addUser(3, "Charlie", 28);
        socialMedia.addUser(4, "David", 35);

        socialMedia.addFriendConnection(1, 2);
        socialMedia.addFriendConnection(1, 3);
        socialMedia.addFriendConnection(2, 3);

        socialMedia.displayAllFriends(1);

        socialMedia.findMutualFriends(1, 2);

        socialMedia.removeFriendConnection(1, 2);
        socialMedia.displayAllFriends(1);

        System.out.println("Number of friends of Alice: " + socialMedia.countFriends(1));

        System.out.println("Search for user by Name (Charlie): " + socialMedia.findUserByName("Charlie").name);
        System.out.println("Search for user by ID (2): " + socialMedia.findUserById(2).name);

        socialMedia.displayAllUsers();
    }
}
