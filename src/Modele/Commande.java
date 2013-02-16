package Modele;

/**
 * interface Commande impl�ment� par les diff�rents traitements appliqu�s au
 * fichier, mise en place du patron commande
 * 
 * @author GR03
 */
public interface Commande {

	/**
	 * effectue le traitement
	 */
	public void traitement();

	public void setCommand();

	public void setObjet(Objet obj);
}
