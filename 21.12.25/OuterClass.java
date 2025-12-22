public class OuterClass {
    private int randomValue;
    private Word word;

    public OuterClass() {
        this.randomValue = (int) (Math.random() * 100);
        this.word = Word.SAND;
        System.out.println("OuterClass constructor called. Random value: " + randomValue + ". Word: " + word.getText());
    }

    public void secrets() {
        InnerClass1 uno = new InnerClass1();
        InnerClass2 dos = new InnerClass2();
        uno.setSecretMessage(this.word.getText());
        dos.setSecretNumber(this.randomValue);
        System.out.println(uno.getSecretMessage() + String.valueOf(dos.getSecretNumber()));
    }

    public enum Word {
        // Enum constants representing available words.
        SAND("Sand"),
        ROCK("Rock"),
        WATER("Water"),
        WIND("Wind");

        // Human-readable text associated with each enum constant.
        private final String text;

        // Enum constructor: called once for each constant at class load time.
        Word(String text) { this.text = text; }

        // Accessor: returns the human-readable text for this constant.
        public String getText() { return text; }

        // Returns the next enum constant in declaration order.
        // Uses ordinal() to compute the index and wraps around with modulo,
        // so calling next() on the last constant returns the first.
        public Word next() {
            Word[] vals = values();
            return vals[(this.ordinal() + 1) % vals.length];
        }
    }

    // Advances the OuterClass.word to the next enum value (wraps around).
    public void nextWord() { this.word = this.word.next(); }
    // Explicitly set the current word.
    public void setWord(Word w) { this.word = w; }
    // Retrieve the current word enum.
    public Word getWord() { return this.word; }

    public class InnerClass1 {
        private String secretMessage = "";

        public InnerClass1() {
            System.out.println("InnerClass1 constructor called.");
        }

        public void setSecretMessage(String message) {
            this.secretMessage = message;
            System.out.println("InnerClass1 secretMessage set.");
        }

        public String getSecretMessage() {
            System.out.println("InnerClass1 secretMessage returned.");
            return this.secretMessage;
        }
    }

    public class InnerClass2 {
        private int secretNumber = 0;

        public InnerClass2() {
            System.out.println("InnerClass2 constructor called.");
        }

        public void setSecretNumber(int number) {
            this.secretNumber = number;
            System.out.println("InnerClass2 secretNumber set.");
        }

        public int getSecretNumber() {
            System.out.println("InnerClass2 secretNumber returned.");
            return this.secretNumber;
        }
    }
}