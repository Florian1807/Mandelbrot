import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel; //Importieren



public class JPanelMandelbrot extends JFrame { // 
	

	static int imageBreite = 459;
	static int imageHoehe = 405;// H�he und Breite definieren
	int frameBreite = imageBreite + 30, frameHoehe = imageHoehe + 50;
	Leinwand malPanel = new Leinwand(imageBreite, imageHoehe);//Erstellen der Zeichenfl�che
	JPanel contentPanel;

	public JPanelMandelbrot() { // Konstruktor
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(frameBreite, frameHoehe);
		setLocationRelativeTo(null);
		setTitle("Mandelbrotmenge mit JPanel");//Titel des Fensters
		contentPanel = new JPanel();
		setContentPane(contentPanel);
		setVisible(true);//Fenster anzeigen lassen
		malPanel.setPreferredSize(new Dimension(imageBreite, imageHoehe));//Malpanel wird definiert
		contentPanel.add(malPanel);
	}
}

class Leinwand extends JPanel {
	public Leinwand(int imageBreite, int imageHoehe) {//Gr��e der Zeichenfl�che
		setBackground(Color.white);//Hintergrundfarbe
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		long zeit = System.currentTimeMillis(); 
		Mandelbrot.zeichneMandelbrotmenge(g, JPanelMandelbrot.imageBreite, JPanelMandelbrot.imageHoehe);//Ausgeben das Mandelbrotmenge �ber die Klasse Mandelbrot
		System.out.println("ben�tigte Zeit = " + (System.currentTimeMillis() - zeit) + " ms");//Konsolenausgbabe
	}
}

class Mandelbrot { // statische Klasse
	public static int iterZahl(final double cx, final double cy, int maxIt) {
		int zaehler = 0; //Z�hler deklarieren und initialisieren
		double zx = 0.0, zy = 0.0, tmp;
		do {
			tmp = zx * zx - zy * zy + cx;
			zy = 2 * zx * zy + cy;//Mandelbrotformel
			zx = tmp;
			zaehler++;//Z�hler erh�hen->n�chste Runde
		} while (zx * zx + zy * zy <= 4.0 && zaehler < maxIt);
		if (zaehler == maxIt)//wenn Z�hler beim Maximalwert(Rand) ist wird die Methode mit return beendet

		return zaehler;
		return maxIt;//R�ckgabe
	}

	public static void zeichneMandelbrotmenge(Graphics g, int imageBreite, int imageHoehe) {//Array farbFeld erstellen und mit Farben bef�llen
		final Color[] farbFeld = { 
				Color.YELLOW, 
				Color.RED, 
				Color.MAGENTA, 
				Color.CYAN, 
				Color.LIGHT_GRAY,
				Color.GREEN, 
				Color.ORANGE,
				Color.GRAY, 
				Color.BLUE, 
				Color.DARK_GRAY,
				Color.PINK//Farben in Array 
				};
		int pixFarbe=0;//Pixelfarbe schwarz
		double xa = -2.02, xe = 0.7, ya = -1.2, ye = 1.2;
		final double dx = (xe - xa) / (imageBreite - 1), dy = (ye - ya) / (imageHoehe - 1);
		double cx, cy;
		int maxIt = 20;
		g.setColor(Color.BLACK);
		for (int sp = 0; sp < imageBreite; sp++) {
			cx = xa + sp * dx; // von links nach rechts
			for (int ze = 0; ze < imageHoehe; ze++) {
				cy = ye - ze * dy; // von oben nach unten
				if (iterZahl(cx, cy, maxIt) == maxIt) {// Wenn das Pixel an dr Stelle (sp|ze) in der Mandelrotmenge liegt...
					g.drawLine(sp, ze, sp, ze);
				zeichnePixel(sp, ze, pixFarbe);//...wird das Pixel mit der Pixelfarbe aus dem Array eingef�rbteingef�rbt
			}else{
				 zeichnePixel(sp, ze, farbFeld[zaehler % farbFeld.length]);
				 
			}
		}
		
		}
	}
}

