package Controleur;

import Modele.Commande;
import Modele.Objet;
import Vue.Screen;
import Vue.ScreenObserver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.swing.JFileChooser;

/**
 * partie contr�leur du mvc : prend en charge la gestion des �v�nements pour
 * mettre � jour la vue ou le mod�le et les synchroniser
 * Sur ce Control2, le client n'a plus besoin de r�appuyer sur le bouton pour 
 * mettre � jour automatiquement les r�sultats des traitements
 * lors d'un changement d'un r�pertoire ou un fichier diff�rent.
 * 
 * @author GR03
 */
public class Control2 {
	Screen screen;
	Objet obj;
	JFileChooser m_chooser;
	ScreenObserver so;
	ArrayList<Class<?>> commandes;
	boolean b1 = false;
	boolean b2 = false;
	boolean b3 = false;

	public Control2(Screen s, Objet o, ArrayList<Class<?>> c) {
		this.screen = s;
		this.obj = o;
		this.commandes = c;
		m_chooser = new JFileChooser();
		m_chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		// add listener to the view
		screen.addTraitement1Listener(new Traitement1Listener());
		screen.addTraitement2Listener(new Traitement2Listener());
		screen.addTraitement3Listener(new Traitement3Listener());
		screen.addSelectListener(new SelectListener());

		so = new ScreenObserver(obj, s);
	}
	
	public void apply(int n){
		Class<?> t = commandes.get(n);
		Commande com = null;
		try {
			com = (Commande) t.newInstance();
			com.setObjet(obj);
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		obj.setCommand(com);
		obj.applyCommand();
		
	}

	/**
	 * gestion de l'�v�nement du traitement1
	 */
	class Traitement1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("traitement1");
			apply(0);
			b1 = true;
		}
	}

	/**
	 * gestion de l'�v�nement du traitement2
	 */
	class Traitement2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("traitement2");
			apply(1);
			b2=true;
		}
	}

	/**
	 * gestion de l'�v�nement du traitement3
	 */
	class Traitement3Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.out.println("traitement3");
			apply(2);
			b3=true;
		}
	}

	/**
	 * g�re la s�lection d'un fichier ou d'un dossier avec un JFileChooser
	 */
	class SelectListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Handle open button action.
			if (e.getSource() == screen.getSelectButton()) {
				int returnVal = m_chooser.showOpenDialog(screen
						.getSelectButton());

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					obj.setFile(m_chooser.getSelectedFile());
					obj.setObserver(so);
					obj.getObserver().selectedFile();
					// This is where a real application would open the file.
					System.out.println("Opening: " + obj.getFile().getName()+ ".");
					if(b1==true){
						apply(0);
					}
					if(b2==true){
						apply(1);
					}
					if(b3==true){
						apply(2);
					}
				} else {
					System.out.println("Open command cancelled by user.");
				}
				// log.setCaretPosition(log.getDocument().getLength());
			}
		}
	}

}