package parsing;

import java.io.File;
import java.io.IOException;

import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Parser extends SAXBuilder {

    Document document;
   
    public Parser(String fichier) throws JDOMException, IOException {
        document = this.build(new File(fichier));
    }
   
    public Image image() {
        Image image = new Image(0, 0);
        Element element = document.getRootElement();
        if (!element.getName().equals("svg"))
            throw new Error("Le fichier svg ne commence pas par une balise 'svg'");
        else {
            try {
                image.setLargeur(element.getAttribute("width").getDoubleValue());
                image.setHauteur(element.getAttribute("height").getDoubleValue());
            } catch (DataConversionException e) {
                e.printStackTrace();
            }
        }
        parcours(image, document.getRootElement());
        return image;
    }
   
    private void parcours(Superposable superposable, Element element) {
       
        Image image = null;
        Calque calque = null;
       
        try {
            image = (Image)superposable;
        } catch (ClassCastException e) {}
       
        try {
            calque = (Calque)superposable;
        } catch (ClassCastException e) {}
       
        for (Element e : element.getChildren()) {
            Superposable suivant = null;
            Transformation transform = Transformation.matrix(1, 0, 0, 1, 0, 0);
            Style style = null;
           
            switch (e.getName()) {
            case "g" :
                if (image != null) {
                    if (e.getAttribute("transform") != null)
                        transform = new Transformation(e.getAttribute("transform").getValue());
                    if (e.getAttribute("style") != null)
                        style = new Style(e.getAttribute("style").getValue());
                    suivant = new Calque(transform, style);
                    image.add(suivant);
                } else if (calque != null) {
                    if (e.getAttribute("transform") != null)
                        transform = new Transformation(e.getAttribute("transform").getValue());
                    if (e.getAttribute("style") != null)
                        style = new Style(e.getAttribute("style").getValue());
                    suivant = new Calque(transform, style);
                    calque.add(suivant);
                }
                break;
            case "path" :
                if (image != null) {
                    if (e.getAttribute("transform") != null)
                        transform = new Transformation(e.getAttribute("transform").getValue());
                    if (e.getAttribute("style") != null)
                        style = new Style(e.getAttribute("style").getValue());
                    suivant = new Forme(e.getAttribute("d").getValue(),transform, style);
                    image.add(suivant);
                } else if (calque != null) {
                    if (e.getAttribute("transform") != null)
                        transform = new Transformation(e.getAttribute("transform").getValue());
                    if (e.getAttribute("style") != null)
                        style = new Style(e.getAttribute("style").getValue());
                    suivant = new Forme(e.getAttribute("d").getValue(), transform, style);
                    calque.add(suivant);
                }
                break;
            }
            parcours(suivant, e);
        }
       
       
    }
}