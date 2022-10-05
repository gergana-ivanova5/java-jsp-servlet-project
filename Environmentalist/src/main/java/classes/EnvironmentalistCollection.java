package classes;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.xml.sax.SAXException;

import xmlclasses.XMLWorker;

@XmlRootElement(name="environmentalists")
@XmlAccessorType(XmlAccessType.FIELD)
public class EnvironmentalistCollection {
	
	private static EnvironmentalistCollection instance = null;
	
	@XmlElement(name="environmentalist")
	private static HashSet<Environmentalist> collection = new HashSet<Environmentalist>();
	
	private static String xmlFile = "D:\\Уни\\IT_WorkSpace\\Environmentalist\\src\\main\\webapp\\xml\\allEnvirons.xml";
	private static String xsdFile = "D:\\Уни\\IT_WorkSpace\\Environmentalist\\src\\main\\webapp\\xml\\environSchema1.xsd";
	
	private EnvironmentalistCollection(){}
	
	public static EnvironmentalistCollection getInstance() {
		if(instance==null) {
			XMLWorker worker = new XMLWorker();
			
			try {
				instance = worker.readFromXML(xmlFile, xsdFile);
			} catch (FileNotFoundException | UnsupportedEncodingException | JAXBException e) {
			
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public boolean addEnvironmentalist(Environmentalist e) {
		return collection.add(e);
	}
	
	public Environmentalist getEnvironmentalist(String username) {
		for(Environmentalist e: collection) {
			if(e.getUsername().equals(username)) {
				return e;
			}
		}
		return null;
	}
	
	public void update() {
		XMLWorker worker = new XMLWorker();
		try {
			worker.writeToXML(xmlFile, this);
		} catch (JAXBException e) {
		
			e.printStackTrace();
		}
	}
	
	public HashSet<Environmentalist> getList() {
		return collection;
	}
}
