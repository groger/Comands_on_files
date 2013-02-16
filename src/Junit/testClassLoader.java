package junit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Modele.MyClassLoader;

/**
 * test Junit de la classe MyClassLoader
 * @author GR03
 *
 */
public class testClassLoader {

	@Test
	public void testLoadClassString() {
		MyClassLoader mcl = new MyClassLoader("C:\\Traitement");
		Class <?> c1 = mcl.loadClass("Traitement2");
		assertEquals(c1.getName(),"C:\\Traitement\\Traitement2.class");	
	}


	@Test
	public void testFindFiles() {
		ArrayList<String> files = new ArrayList<String>();
		MyClassLoader mcl = new MyClassLoader("C:\\Traitement");
		files = mcl.findFiles();
		assertTrue(!files.isEmpty());
		assertTrue(files.get(0).equals("Taille_fichier"));
		assertTrue(files.get(1).equals("Traitement2"));
		assertTrue(files.get(2).equals("Traitement3"));
	}

}
