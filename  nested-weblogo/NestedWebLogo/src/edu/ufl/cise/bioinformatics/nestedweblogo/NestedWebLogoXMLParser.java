package edu.ufl.cise.bioinformatics.nestedweblogo;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import edu.ufl.cise.bioinformatics.nestedweblogo.datastructure.WeblogoDataStructure;

public class NestedWebLogoXMLParser {

	public void test() {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("book.xml"));

			// normalize text representation
			doc.getDocumentElement().normalize();
			System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

			NodeList listOfPersons = doc.getElementsByTagName("person");
			int totalPersons = listOfPersons.getLength();
			System.out.println("Total no of people : " + totalPersons);

			for (int s = 0; s < listOfPersons.getLength(); s++) {

				Node firstPersonNode = listOfPersons.item(s);
				if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {

					Element firstPersonElement = (Element) firstPersonNode;

					// -------
					NodeList firstNameList = firstPersonElement.getElementsByTagName("first");
					Element firstNameElement = (Element) firstNameList.item(0);

					NodeList textFNList = firstNameElement.getChildNodes();
					System.out.println("First Name : " + ((Node) textFNList.item(0)).getNodeValue().trim());

					// -------
					NodeList lastNameList = firstPersonElement.getElementsByTagName("last");
					Element lastNameElement = (Element) lastNameList.item(0);

					NodeList textLNList = lastNameElement.getChildNodes();
					System.out.println("Last Name : " + ((Node) textLNList.item(0)).getNodeValue().trim());

					// ----
					NodeList ageList = firstPersonElement.getElementsByTagName("age");
					Element ageElement = (Element) ageList.item(0);

					NodeList textAgeList = ageElement.getChildNodes();
					System.out.println("Age : " + ((Node) textAgeList.item(0)).getNodeValue().trim());

					// ------

				}// end of if clause

			}// end of for loop with s var

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	/*public WeblogoDataStructure getWebLogo(){
		
	}*/
	
	public void parseWebLogoXML() {
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("weblogo.xml"));

			// normalize text representation
			doc.getDocumentElement().normalize();
			System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

			NodeList listOfNestedLogo = doc.getChildNodes();
			int totalPersons = listOfNestedLogo.getLength();
			System.out.println("Total nested logo : " + totalPersons);

			WeblogoDataStructure webLogoInstance = WeblogoDataStructure.getInstance();
			
			for (int s = 0; s < listOfNestedLogo.getLength(); s++) {
				Node webLogo = listOfNestedLogo.item(s);
				System.out.println("Node name: "+webLogo.getNodeName());
			
				if(webLogo.getNodeName().equals("weblogo")){
					webLogoInstance = parseWebLogo(webLogo);
				}
			}
			
			System.out.println("webLogoInstance: "+webLogoInstance);
			printWebLogo(webLogoInstance);

		} catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} catch (SAXException e) {
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
	
	public void printWebLogo(WeblogoDataStructure webLogo){
		if(webLogo == null){
			return;
		}
		System.out.println("Start: "+webLogo.getStartPosition());
		System.out.println("End  : "+webLogo.getEndPosition());
		Map<String, NestedWebLogoDataStructure> map = webLogo.getNestedWeblogoMap();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			printNestedWebLogo(map.get(key));
		}
	}
	
	public void printNestedWebLogo(NestedWebLogoDataStructure nestedWebLogo){
		System.out.println("Nested web logo : wild card : "+nestedWebLogo.getWildCardPattern());
		printWebLogo(nestedWebLogo.getSourceWebLogo());
		printWebLogo(nestedWebLogo.getTargetWebLogo());
	}
	
	public WeblogoDataStructure parseWebLogo(Node webLogo){
		System.out.println("________________________WEB LOGO____________________________");
		NodeList webLogoChildNodes = webLogo.getChildNodes();
		
		WeblogoDataStructure webLogoInstance = WeblogoDataStructure.getInstance();
		
		for(int i=0 ; i<webLogoChildNodes.getLength() ; i++){
			if(webLogoChildNodes.item(i).getNodeName().equals("start")){
				if(webLogoChildNodes.item(i).getTextContent() != null || webLogoChildNodes.item(i).getTextContent().trim().length() != 0){
					webLogoInstance.setStartPosition(Integer.parseInt(webLogoChildNodes.item(i).getTextContent().trim()));
				}
				System.out.println("start : "+webLogoChildNodes.item(i).getTextContent());
			}
			
			if(webLogoChildNodes.item(i).getNodeName().equals("end")){
				if(webLogoChildNodes.item(i).getTextContent() != null || webLogoChildNodes.item(i).getTextContent().trim().length() != 0){
					webLogoInstance.setEndPosition(Integer.parseInt(webLogoChildNodes.item(i).getTextContent().trim()));
				}
				System.out.println("end : "+webLogoChildNodes.item(i).getTextContent());
			}
			
			if(webLogoChildNodes.item(i).getNodeName().equals("NestedWebLogo")){
//				System.out.println("Nested web logo");
				webLogoInstance.addEntryToNestedWebLogoMap(parseNestedWebLogo(webLogoChildNodes.item(i)));
			}
		}		
		System.out.println("____________________END WEB LOGO____________________________");
		return webLogoInstance;
	}
	
	public NestedWebLogoDataStructure parseNestedWebLogo(Node nestedWebLogoNode){
		System.out.println("________________________NESTED WEB LOGO____________________________");
		NodeList nestedWebLogoChildNodes = nestedWebLogoNode.getChildNodes();
		
		NestedWebLogoDataStructure nestedWebLogInstance = new NestedWebLogoDataStructure();
		
		for(int i=0 ; i<nestedWebLogoChildNodes.getLength() ; i++){
			if(nestedWebLogoChildNodes.item(i).getNodeName().equals("weblogo") && nestedWebLogoChildNodes.item(i).getAttributes().getNamedItem("type").getChildNodes().item(0).getTextContent().equals("source")){
				WeblogoDataStructure sourceWebLogo = parseWebLogo(nestedWebLogoChildNodes.item(i));
				nestedWebLogInstance.setSourceWebLogo(sourceWebLogo);
			}

			if(nestedWebLogoChildNodes.item(i).getNodeName().equals("weblogo") && nestedWebLogoChildNodes.item(i).getAttributes().getNamedItem("type").getChildNodes().item(0).getTextContent().equals("target")){
				WeblogoDataStructure targetWebLogo = parseWebLogo(nestedWebLogoChildNodes.item(i));
				nestedWebLogInstance.setTargetWebLogo(targetWebLogo);
			}
			
			if(nestedWebLogoChildNodes.item(i).getNodeName().equals("wildcard")){
				System.out.println("wildcard : "+nestedWebLogoChildNodes.item(i).getTextContent());
				nestedWebLogInstance.setWildCardPattern(nestedWebLogoChildNodes.item(i).getTextContent().trim());
			}
		}		
		System.out.println("____________________END NESTED WEB LOGO____________________________");
		return nestedWebLogInstance;
	}

	public static void main(String args[]) {
		NestedWebLogoXMLParser parser = new NestedWebLogoXMLParser();
		parser.parseWebLogoXML();
	}
}
