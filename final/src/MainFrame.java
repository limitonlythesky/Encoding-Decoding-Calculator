import javax.swing.*;

public class MainFrame extends JFrame {
    private MainMenu mainMenu;
    private SFHEnPage sfhEnPage;
    private SFHDecPage sfhDecPage;
    private HamEnPage hamEnPage;
    private HamDecPage hamDecPage;
    private EntropyPage entropyPage;
    ArrayOfTrees arrayOfTrees = new ArrayOfTrees();
    ArrayOfHamming arrayOfHamming = new ArrayOfHamming();

    public MainFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Encoding / Decoding Calculator");
        setSize(600, 600);
        setLayout(null);

        mainMenu = new MainMenu(this);
        mainMenu.setVisible(true);
        add(mainMenu);

        sfhEnPage = new SFHEnPage(this);
        sfhEnPage.setVisible(false);
        add(sfhEnPage);

        sfhDecPage = new SFHDecPage(this);
        sfhDecPage.setVisible(false);
        add(sfhDecPage);

        hamEnPage = new HamEnPage(this);
        hamEnPage.setVisible(false);
        add(hamEnPage);

        hamDecPage = new HamDecPage(this);
        hamDecPage.setVisible(false);
        add(hamDecPage);

        entropyPage = new EntropyPage(this);
        entropyPage.setVisible(false);
        add(entropyPage);
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

    public SFHEnPage getSfhEnPage() {
        return sfhEnPage;
    }

    public SFHDecPage getSfhDecPage() {
        return sfhDecPage;
    }

    public HamEnPage getHamEnPage() {
        return hamEnPage;
    }

    public HamDecPage getHamDecPage() {
        return hamDecPage;
    }

    public EntropyPage getEntropyPage() {
        return entropyPage;
    }
}
