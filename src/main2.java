import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


import Controleur.Control2;
import Modele.Commande;
import Modele.MyClassLoader;
import Modele.Objet;
import Vue.Screen;
import Vue.ScreenObserver;

/**
 * Main correspondant � la partie2 de l'exercice.
 * L'utilisateur n'a plus besoin de cliquer une nouvelle fois 
 * sur les boutons pour mettre � jour les r�sultats 
 * lors d'un changement de fichier ou de dossiers.
 * 
 * @author GR03
 */
public class main2 {

	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {
		Scanner sc = new Scanner(System.in);
		boolean found = false;

		while (found == false) {
			System.out.println("Veuillez saisir un chemin :");
			String str = sc.nextLine();
			System.out.println("Vous avez saisi : " + str);
			File f = new File(str);
			if (f.exists() && f.isDirectory()) {
				found = true;
				// d�fini le repertoire o� sont stock� les dossiers
				String path = str; 
				// Ici on d�finit un classloader qui va s'occuper de loader les classes
				MyClassLoader mcl = new MyClassLoader(path); 
				// Liste des classes qui sont dans le r�pertoire
				ArrayList<Class<?>> traitements = new ArrayList<Class<?>>();
				 // Liste des noms des classes qui sont dans le r�pertoire
				ArrayList<String> nomsTraitement = new ArrayList<String>();
				// r�cupere la liste des noms des classes
				nomsTraitement = mcl.findFiles(); 
				for (String s : nomsTraitement) {
					// pour chaque classe trouv� � partir du nom, on charge dynamiquement la classe
					Class<?> t = mcl.loadClass(s); 
					traitements.add(t);
				}
				if (nomsTraitement.size() > 3) {
					// par choix, on d�cide qu'on ne peux pas avoir plus de trois traitements � la fois.
					System.out.println("Vous ne pouvez pas mettre plus de 3 traitements � la fois");
					return;
				} else {
					Objet obj = new Objet();
					Screen screen = new Screen(nomsTraitement);
					screen.setVisible(true);
					ScreenObserver observer = new ScreenObserver(obj, screen);
					obj.setObserver(observer);
					Control2 control = new Control2(screen, obj, traitements);
				}
			} else {
				System.out.println("mauvais chemin");
			}
		}
	}
}
