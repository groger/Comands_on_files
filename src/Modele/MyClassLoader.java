package Modele;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

/**
 * MyClassLoader hérite de ClassLoader. Permet de charger des fichiers .class
 * présent dans un dossier défini en paramètres
 * 
 * @author GR03
 * 
 */
public class MyClassLoader extends ClassLoader {

	private String directory;

	public MyClassLoader(String dir) {
		this.directory = dir;
	}

	/**
	 * charge la classe dont le nom est donné en paramètres
	 */
	@Override
	public synchronized Class loadClass(String name) {
		Class c = findLoadedClass(name);
		if (c != null)
			return c;
		try {
			c = findSystemClass(name);
			return c;
		} catch (ClassNotFoundException e) {
			ByteBuffer data = getDataClass(name);
			return defineClass(name, data.array(), 0, data.array().length);
		}
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		ByteBuffer b = getDataClass(name);
		if (b == null) {
			throw new ClassNotFoundException();
		} else {
			return defineClass(name, b.array(), 0, b.array().length);
		}
	}

	private final ByteBuffer getDataClass(String name) {
		ByteBuffer b = null;
		try {
			FileInputStream fis = new FileInputStream(directory + "\\"+ name.replace('.', '\\') + ".class");
			FileChannel fc = fis.getChannel();
			// sets the size of the byte buffer to the size of the file to be
			// read
			b = ByteBuffer.wrap(new byte[fis.available()]);
			fc.read(b);
			return b;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * recupère la liste des noms des fichiers (.class) présents dans le
	 * répertoire
	 * 
	 * @return la liste des noms des fichiers(.class) présents dans le
	 *         répertoire
	 */
	public ArrayList<String> findFiles() {
		File repertoire = new File(this.directory);

		File[] files = repertoire.listFiles();
		if (files.length == 0) {
			System.out.println("vide");
		} else {
			System.out.println("nombre de .class : " + files.length);
		}
		ArrayList<String> nomsFile = new ArrayList<String>();
		for (File f : files) {
			String nomFile = f.getName().replace(".class", "");
			nomsFile.add(nomFile);
		}
		return nomsFile;
	}

}
