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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SalvaCarica {
    private Document document;
    private static SalvaCarica istanza;
    
    private final File fileXML;
    private final TransformerFactory transformerFactory;
    
    private final String FILE_PATH = "data\\eventi.xml";
    private final String EVENTI = "Eventi";
    private final String PARTITE_DI_CALCIO = "partite_di_calcio";
    private final String ALTRA_CATEGORIA = "altra_categoria";
    private final String EVENTO = "evento";
    private final String CAMPI_FISSI = "campi_fissi";
    private final String CAMPI_VARIABILI = "campi_variabili";
    private final String CAMPO = "campo";
    private final String TIPO = "tipo";
    private final String ERRORE = "Errore per versione 1: trovato evento istanza di !PartitaDiCalcio";
    
    public static SalvaCarica getIstanza() { return istanza == null ? new SalvaCarica() : istanza; }
    
    private SalvaCarica() {
        fileXML = new File(FILE_PATH);
        transformerFactory = TransformerFactory.newInstance();
    }
    
    /**
     * 
     * @return bacheca
     */
    public ArrayList<Evento> eventiFromXML() {
        ArrayList<Evento> eventi = new ArrayList<>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(fileXML);
            document.getDocumentElement().normalize();
            
            NodeList nodeL = document.getDocumentElement().getChildNodes();
            for(int cont = 0; cont < nodeL.getLength(); cont++) {
                String nomeCategoria = nodeL.item(cont).getNodeName();
                NodeList eventiL = nodeL.item(cont).getChildNodes();
                if(nomeCategoria.equals(PARTITE_DI_CALCIO)) {
                    // Ciclo gli eventi in PARTITE_DI_CALCIO
                    for(int i = 0; i < eventiL.getLength(); i++) {
                        PartitaDiCalcio pdc = new PartitaDiCalcio();
                        NodeList tipiDiCampiL = eventiL.item(i).getChildNodes();
                        NodeList campiFissiL = tipiDiCampiL.item(0).getChildNodes();
                        NodeList campiVariabiliL = tipiDiCampiL.item(1).getChildNodes();
                        for(int j = 0; j < campiFissiL.getLength(); j++) {
                            // Ci assicuriamo che sia un Element per poter fare il cast
                            if(campiFissiL.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element campoNode = (Element) campiFissiL.item(j);
                                funzionePerCampiFromXML(pdc, campoNode);
                            }
                        }
                        for(int j = 0; j < campiVariabiliL.getLength(); j++) {
                            // Ci assicuriamo che sia un Element per poter fare il cast
                            if(campiVariabiliL.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                Element campoNode = (Element) campiVariabiliL.item(j);
                                funzionePerCampiFromXML(pdc, campoNode);
                            }
                        }
                        eventi.add(pdc);
                    }
                } else {
                    System.err.println("FromXML: non dovrebbero esistere in V1 eventi !partdical");
                }
                return eventi;
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SalvaCarica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(SalvaCarica.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SalvaCarica.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    private void funzionePerCampiFromXML(Evento e, Element campoNode) {
        String tipo = campoNode.getAttribute(TIPO).replaceAll("_", " ");
        String content = campoNode.getTextContent();
        switch (tipo) {
            case (Evento.N_TITOLO):
                e.setValoreDaString(Evento.I_TITOLO, content);
                break;
            case (Evento.N_NUMERO_PARTECIPANTI):
                e.setValoreDaString(Evento.I_NUM_PARTECIPANTI, content);
                break;
            case (Evento.N_TERMINE_ISCR):
                e.setValoreDaString(Evento.I_TERMINE_ISCRIZIONE, content);
                break;
            case (Evento.N_LUOGO):
                e.setValoreDaString(Evento.I_LUOGO, content);
                break;
            case (Evento.N_DATA):
                e.setValoreDaString(Evento.I_DATA, content);
                break;
            case (Evento.N_ORA):
                e.setValoreDaString(Evento.I_ORA, content);
                break;
            case (Evento.N_DURATA):
                e.setValoreDaString(Evento.I_DURATA, content);
                break;
            case (Evento.N_QUOTA_INDIVIDUALE):
                e.setValoreDaString(Evento.I_QUOTA_INDIVIDUALE, content);
                break;
            case (Evento.N_COMPRESO_QUOTA):
                e.setValoreDaString(Evento.I_COMPRESO_QUOTA, content);
                break;
            case (Evento.N_DATA_CONCLUSIVA):
                e.setValoreDaString(Evento.I_DATA_CONCLUSIVA, content);
                break;
            case (Evento.N_ORA_CONCLUSIVA):
                e.setValoreDaString(Evento.I_ORA_CONCLUSIVA, content);
                break;
            case (Evento.N_NOTE):
                e.setValoreDaString(Evento.I_NOTE, content);
                break;
            case (PartitaDiCalcio.N_GENERE):
                e.setValoreDaString(PartitaDiCalcio.I_GENERE, content);
                break;
            case (PartitaDiCalcio.N_FASCIA_ETA):
                e.setValoreDaString(PartitaDiCalcio.I_FASCIA_ETA, content);
            default:
                System.err.println("Errore funzione per campi from XML: campo in "
                        + "partita di calcio con tipo");
                break;
        }
    }
    
    /**
     * Aggiornato a v1: prende in ingresso bacheca e ne salva gli eventi
     * @param bacheca
     */
    public void eventiToXML(Bacheca bacheca) {
        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SalvaCarica.class.getName()).log(Level.SEVERE, null, ex);
        }
        document = documentBuilder.newDocument();
        
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
                
                Element campiFissi = document.createElement(CAMPI_FISSI);
                
                Campo[] cf = e.getCampi();
                funzionePerCampiToXML(cf, campiFissi, document);
                
                Element campiVariabili = document.createElement(CAMPI_VARIABILI);
                
                Campo[] ca = e.getCampiExtra();
                funzionePerCampiToXML(ca, campiVariabili, document);
                
                eTemp.appendChild(campiFissi);
                eTemp.appendChild(campiVariabili);
                categoriaPartitaDiCalcio.appendChild(eTemp);
                
            } else {
                System.err.println(ERRORE);
            }
            
        }

        salvaXML(document);
        
        System.out.println("Done creating XML File");
        
    }
    
    private void salvaXML(Document document) {
        try {
            Transformer transformer = transformerFactory.newTransformer();

            //transform the DOM Object to an XML File
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(fileXML);

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
    
    private void funzionePerCampiToXML(Campo[] campi, Element campiFoV, Document document) {
        for (Campo c : campi) {
            if(!(c.getValore() == null)) {
                Element campoTemp = document.createElement(CAMPO);
                campoTemp.setAttribute(TIPO, c.getNome().replaceAll(" ", "_"));
                campoTemp.appendChild(document.createTextNode(c.getValore().toString()));
                campiFoV.appendChild(campoTemp);
            }
        }
    }
    
}
