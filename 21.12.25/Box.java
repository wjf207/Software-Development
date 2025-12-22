/**
 * Simple container used as the shared monitor between threads in the demo.
 * IMPORTANT: the wait()/notify() calls are performed on the Box instance itself,
 * so producers and consumers must synchronize on the same Box object.
 */
public class Box {

    // actual field to hold the contents
    private String contents = "";

    public Box() {
        // default empty contents
        this.contents = "";
    }

    // Mutator - note: in the demo we call this inside a synchronized(box) block, so
    // additional synchronization here is unnecessary. In general, you might make
    // these methods synchronized if you expect concurrent unsynchronized access.
    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void printContents() {
        System.out.println("Box contains: " + contents);
    }
}