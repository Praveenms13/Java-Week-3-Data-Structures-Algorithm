class TextState {
    String text;
    TextState prev;
    TextState next;

    public TextState(String text) {
        this.text = text;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    private TextState currentState;
    private TextState head;
    private TextState tail;
    private int historyLimit;
    private int historySize;

    public TextEditor(int historyLimit) {
        this.historyLimit = historyLimit;
        this.historySize = 0;
        this.currentState = null;
        this.head = null;
        this.tail = null;
    }

    public void typeText(String newText) {
        TextState newState = new TextState(newText);

        if (currentState != null) {
            currentState.next = newState;
            newState.prev = currentState;
            currentState = newState;

            if (historySize == historyLimit) {
                head = head.next; // Remove the oldest state from history
            } else {
                historySize++;
            }
        } else {
            currentState = newState;
            head = newState;
            tail = newState;
            historySize++;
        }
        tail = currentState; // Update the tail to the latest state
    }

    public void undo() {
        if (currentState != null && currentState.prev != null) {
            currentState = currentState.prev;
            System.out.println("Undo: " + currentState.text);
        } else {
            System.out.println("No more actions to undo.");
        }
    }

    public void redo() {
        if (currentState != null && currentState.next != null) {
            currentState = currentState.next;
            System.out.println("Redo: " + currentState.text);
        } else {
            System.out.println("No more actions to redo.");
        }
    }

    public void displayCurrentState() {
        if (currentState != null) {
            System.out.println("Current Text: " + currentState.text);
        } else {
            System.out.println("No text available.");
        }
    }

    public void displayHistory() {
        TextState temp = head;
        System.out.println("History of text states:");
        while (temp != null) {
            System.out.println(temp.text);
            temp = temp.next;
        }
    }
}

public class TextEditorApp {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor(5); // Limit to last 5 actions

        editor.typeText("Hello");
        editor.displayCurrentState();

        editor.typeText("Hello, World!");
        editor.displayCurrentState();

        editor.typeText("Hello, World! How are you?");
        editor.displayCurrentState();

        editor.undo();
        editor.undo();
        editor.redo();

        editor.typeText("New state after redo");
        editor.displayCurrentState();

        editor.undo();
        editor.undo();
        editor.undo();

        editor.displayHistory();
    }
}
