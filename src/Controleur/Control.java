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
 * partie contrôleur du mvc : prend en charge la gestion des évènements pour
 * mettre à jour la vue ou le modèle et les synchroniser
 * 
 * @author GR03
 */
public class Control {
	Screen screen;
	Objet obj;
	JFileChooser m_chooser;
	ScreenObserver so;
	ArrayList<Class<?>> commandes;

	public Control(Screen s, Objet o, ArrayList<Class<?>> c) {
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

	/**
	 * gestion de l'évènement du traitement1
	 */
	class Traitement1Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Class<?> t = commandes.get(0);
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
			System.out.println("traitement1");
			obj.setCommand(com);
			obj.applyCommand();
		}
	}

	/**
	 * gestion de l'évènement du traitement2
	 */
	class Traitement2Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Class<?> t = commandes.get(1);
			Commande com2 = null;
			try {
				com2 = (Commande) t.newInstance();
				com2.setObjet(obj);
			} catch (InstantiationException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IllegalAccessException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			System.out.println("traitement2");

			obj.setCommand(com2);
			obj.applyCommand();
		}
	}

	/**
	 * gestion de l'évènement du traitement3
	 */
	class Traitement3Listener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Class<?> t = commandes.get(2);
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
			System.out.println("traitement3");

			obj.setCommand(com);
			obj.applyCommand();
		}
	}

	/**
	 * gère la sélection d'un fichier ou d'un dossier avec un JFileChooser
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
					System.out.println("Opening: " + obj.getFile().getName()
							+ ".");
				} else {
					System.out.println("Open command cancelled by user.");
				}
				// log.setCaretPosition(log.getDocument().getLength());
			}
		}
	}

}