package hr.fer.tel.ilj.dz3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class XMLValidator {

	public static void main(String[] args) {
		
		if(args.length!=2) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Unesite put do xml datoteke: ");
			String xml = sc.next();
			System.out.println("Unesite put do scheme: ");
			String xsd = sc.next();
			boolean isValid = validateXMLSchema(xsd, xml);
			if(isValid) {
				System.out.println("Datoteka je napravljena po schemi!");
			} else {
				System.out.println("Datoteka nije napravljena po schemi!");
			}
		} else {
			String xml = args[0];
			String xsd = args[1];
			boolean isValid = validateXMLSchema(xsd, xml);
			if(isValid) {
				System.out.println("Datoteka je napravljena po schemi!");
			} else {
				System.out.println("Datoteka nije napravljena po schemi!");
			}
		}
	}
	
    public static boolean validateXMLSchema(String xsdPath, String xmlPath){
        
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(xmlPath)));
        } catch (IOException | SAXException e) {
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
}

