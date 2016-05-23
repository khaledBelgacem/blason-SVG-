package execution;

import java.io.File;

import heraldique.generique.BlasonXml;
public class Heraldique {

	public static void main(String[] args) {
		BlasonXml bl ; 	
		//programme principale 
		String [] listeFichiers = listerRepertoire("samplesXML/") ; 
		for(String fb : listeFichiers)
			bl = new BlasonXml(fb) ;
	}
	public static String[] listerRepertoire(String repertoire){
		File sampleDir=new File(repertoire);
		String[] listefichiers ; 
		int i;
		listefichiers=sampleDir.list();
		return listefichiers;
	}
}
