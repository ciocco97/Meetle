package meetle.io;


import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import meetle.eventi.*;
import meetle.eventi.campi.Campo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SalvaCarica {
    
    public void toXML(Bacheca bacheca) throws ParserConfigurationException, 
            TransformerConfigurationException, TransformerException {
        String filePath = "data\\eventi.xml";
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        
        // <Eventi>
        Element root = document.createElement("Eventi");
        document.appendChild(root);
        
        // <Partite di calcio>
        Element categoriaPartitaDiCalcio = document.createElement("PartiteDiCalcio");
        root.appendChild(categoriaPartitaDiCalcio);
        
        // <Altra categoria>
//        Element categoriaAltraCategoria = document.createElement("Altra Categoria");
//        root.appendChild(categoriaAltraCategoria);        
        
        ArrayList<Evento> eventi = bacheca.getEventi();
        
        for(int i = 0; i < eventi.size(); i++) {
            Evento e = eventi.get(i);
            if(e instanceof PartitaDiCalcio) {
                // <Evento>
                Element eTemp = document.createElement("evento");
                Element campi = document.createElement("campi");
                
                Element campiFissi = document.createElement("campi_fissi");
                
                Campo[] cf = e.getCampi();
                funzionePerCampi(cf, campiFissi, document);
                
                Element campiVariabili = document.createElement("campi_variabili");
                
                Campo[] ca = e.getCampiExtra();
                funzionePerCampi(ca, campiVariabili, document);
                
                campi.appendChild(campiFissi);
                campi.appendChild(campiVariabili);
                eTemp.appendChild(campi);
                categoriaPartitaDiCalcio.appendChild(eTemp);
                
            } else {
                System.err.println("Errore per versione 1: trovato evento istanza di !PartitaDiCalcio");
            }
            
 
        }
        
        // create the xml file
        //transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(filePath));

        // If you use
        // StreamResult result = new StreamResult(System.out);
        // the output will be pushed to the standard output ...
        // You can use that for debugging 

        transformer.transform(domSource, streamResult);

        System.out.println("Done creating XML File");
        
    }
    
    
    
    void funzionePerCampi(Campo[] campi, Element campiFoV, Document document) {
        for (Campo c : campi) {
            if(!(c.getValore() == null)) {
                Element campoTemp = document.createElement("ampo");
                campoTemp.setAttribute("tipo", c.getNome().replaceAll(" ", "_"));
                campoTemp.setAttribute("facoltativo", "" + c.isFacoltativo());
                campoTemp.appendChild(document.createTextNode(c.getValore().toString()));
                campiFoV.appendChild(campoTemp);
            }
        }
    }
    
}
