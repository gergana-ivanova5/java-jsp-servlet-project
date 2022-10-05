package xmlclasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import classes.EnvironmentalistCollection;

public class XMLWorker {
	
	public void writeToXML(String xmlFile, EnvironmentalistCollection collection) throws JAXBException {
		
		JAXBContext context = JAXBContext.newInstance(EnvironmentalistCollection.class);
		
		Marshaller m = context.createMarshaller();
		
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		m.marshal(collection, new File(xmlFile));
	}
	
	public EnvironmentalistCollection readFromXML(String xmlFile, String xsdFile) throws JAXBException, FileNotFoundException, UnsupportedEncodingException {
		
		JAXBContext context = JAXBContext.newInstance(EnvironmentalistCollection.class);
		
		Unmarshaller u = context.createUnmarshaller();
		
		// dobaveno za shemata
		
		SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = null;
		
		try {
			schema = sf.newSchema(new File(xsdFile));
		} catch (SAXException e) {
			
			e.printStackTrace();
		}
		u.setSchema(schema);
				
		// dobaveno za shemata
		
		InputStream is = new FileInputStream(xmlFile);
		Reader r = new InputStreamReader(is, "UTF-8");
		
		EnvironmentalistCollection collection = (EnvironmentalistCollection) u.unmarshal(r);
		
		return collection;
	}
}
