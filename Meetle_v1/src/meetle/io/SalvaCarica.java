package meetle.io;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import meetle.eventi.*;
import meetle.eventi.campi.Campo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SalvaCarica {
    
    private final File fileXML;
    private final TransformerFactory transformerFactory;
    
    private final String FILE_PATH = "data\\eventi.xml";
    private final String EVENTI = "Eventi";
    private final String PARTITE_DI_CALCIO = "partite_di_calcio";
    private final String ALTRA_CATEGORIA = "altra_categoria";
    private final String EVENTO = "evento";
    private final String CAMPI = "campi";
    private final String CAMPI_FISSI = "campi_fissi";
    private final String CAMPI_VARIABILI = "campi_variabili";
    private final String ERRORE = "Errore per versione 1: trovato evento istanza di !PartitaDiCalcio";
    
    public SalvaCarica() {
        fileXML = new File(FILE_PATH);
        transformerFactory = TransformerFactory.newInstance();
        
    }
    
    /**
     * Aggiornato a v1: prende in ingresso bacheca e ne salva gli eventi
     * @param bacheca
     * @throws ParserConfigurationException
     * @throws TransformerConfigurationException
     * @throws TransformerException 
     */
    public void eventiToXML(Bacheca bacheca) throws ParserConfigurationException, 
        TransformerConfigurationException, TransformerException {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        
        // <Eventi>
        Element root = document.createElement(EVENTI);
        document.appendChild(root);
        
        // <Partite di calcio>
        Element categoriaPartitaDiCalcio = document.createElement(PARTITE_DI_CALCIO);
        root.appendChild(categoriaPartitaDiCalcio);
        
        // <Altra categoria>
//        Element categoriaAltraCategoria = document.createElement(ALTRA_CATEGORIA);
//        root.appendChild(categoriaAltraCategoria);        
        
        ArrayList<Evento> eventi = bacheca.getEventi();
        
        for(int i = 0; i < eventi.size(); i++) {
            Evento e = eventi.get(i);
            if(e instanceof PartitaDiCalcio) {
                // <Evento>
                Element eTemp = document.createElement(EVENTO);
                Element campi = document.createElement(CAMPI);
                
                Element campiFissi = document.createElement(CAMPI_FISSI);
                
                Campo[] cf = e.getCampi();
                funzionePerCampi(cf, campiFissi, document);
                
                Element campiVariabili = document.createElement(CAMPI_VARIABILI);
                
                Campo[] ca = e.getCampiExtra();
                funzionePerCampi(ca, campiVariabili, document);
                
                campi.appendChild(campiFissi);
                campi.appendChild(campiVariabili);
                eTemp.appendChild(campi);
                categoriaPartitaDiCalcio.appendChild(eTemp);
                
            } else {
                System.err.println(ERRORE);
            }
            
        }

        mamma(document);
        
        System.out.println("Done creating XML File");
        
    }
    
    private void mamma(Document document) {
        try {
            // create the xml file
            //transform the DOM Object to an XML File
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(FILE_PATH));

            // If you use
            // StreamResult result = new StreamResult(System.out);
            // the output will be pushed to the standard output ...
            // You can use that for debugging 

            transformer.transform(domSource, streamResult);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SalvaCarica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(SalvaCarica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void funzionePerCampi(Campo[] campi, Element campiFoV, Document document) {
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
