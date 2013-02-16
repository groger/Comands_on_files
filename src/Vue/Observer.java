/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

/**
 * interface permettant d'implanter soit une vue console (uniquement pour
 * afficher les tests de la partie 1 dans la console), ou l'interface graphique
 * 
 * @author GR03
 */
public interface Observer {
	public void traited1(String s);

	public void traited2(String s);

	public void traited3(String s);

	public void selectedFile();
}
