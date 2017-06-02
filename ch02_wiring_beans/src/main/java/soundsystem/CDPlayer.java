package soundsystem;

/**
 * Created by zk on 17-6-2.
 */
public class CDPlayer implements CompactDisc {

    private CompactDisc compactDisc;

    public CDPlayer(CompactDisc compactDisc) {
        this.compactDisc = compactDisc;
    }

    public void play() {
        System.out.println("cdPlayer playing...");
    }

}
