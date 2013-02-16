package Modele;

import Vue.Observer;
import Vue.ScreenObserver;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Classe définissant le fichier ou le répertoire choisi
 * 
 * @author GR03
 */

public class Objet {

	private File f;
	Observer observer;
	Commande cmd;

	public void setObserver(Observer co) {
		observer = co;
	}

	public Observer getObserver() {
		return this.observer;
	}

	public void setCommand(Commande c) {
		this.cmd = c;
	}

	public void setFile(File file) {
		this.f = file;
	}

	public File getFile() {
		return f;
	}

	/**
	 * applique le traitement sur le fichier ou le dossier
	 * 
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public void applyCommand() {
		if (f == null) {
			System.out.println("pas de fichier ou de répertoire sélectionné");
		} else {
			cmd.traitement();
			// observer.traited();
		}
	}

}
