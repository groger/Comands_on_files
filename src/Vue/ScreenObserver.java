package Vue;

import Modele.Objet;
import java.io.File;

/**
 * Mise en place du patron observateur : mise 0  jour de la vue considéré comme
 * "observateur" du modèle "observable"
 * 
 * @author GR03
 */
public class ScreenObserver implements Observer {
	Screen screen;
	Objet obj;

	public ScreenObserver(Objet o, Screen s) {
		this.obj = o;
		this.screen = s;
	}

	/**
	 * affiche la taille du fichier dans la vue
	 * 
	 * @param s
	 */
	@Override
	public void traited1(String s) {
		// screen.getTextField1().setText(obj.applyCommand());
		screen.getTextField1().setText(s);
	}

	/**
	 * affiche le résultat du traitement2
	 * 
	 * @param s
	 */

	@Override
	public void traited2(String s) {
		// screen.getTextField2().setText(obj.applyCommand());
		screen.getTextField2().setText("traited2");
	}

	/**
	 * affiche le résultat du traitement3
	 * 
	 * @param s
	 */
	@Override
	public void traited3(String s) {
		// screen.getTextField3().setText(obj.applyCommand());
		screen.getTextField3().setText("traited3");
	}

	public void selectedFile() {
		screen.getTextArea1().setText("");
		if (this.obj.getFile().isDirectory()) {
			File[] fichiers = this.obj.getFile().listFiles();
			String newligne = System.getProperty("line.separator");
			for (File f : fichiers) {
				screen.getTextArea1().setText(screen.getTextArea1().getText() + newligne + "-"+ f.getName());
			}
		} else {
			screen.getTextArea1().setText(this.obj.getFile().getName());
		}
	}

}
