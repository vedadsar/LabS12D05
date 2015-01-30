package ba.bitcamp.vedadz.klix.xmlBase;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLConnection {
	private DocumentBuilder docReader;
	private Document xmldoc;
	
	public XMLConnection() throws ParserConfigurationException{
		docReader =  DocumentBuilderFactory.newInstance().newDocumentBuilder();
	}
	
	public void setDocument(String tableName) throws SAXException, IOException, ParserConfigurationException{
		String path = "./XML/" +tableName +".xml";		
		 xmldoc = docReader.parse(new File(path));
	}
	
	public NodeList query(String tableName, String query) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException{
		setDocument(tableName);
		query = "//item";
		XPath xPath = XPathFactory.newInstance().newXPath();
		NodeList table = (NodeList) xPath.compile(query).evaluate(xmldoc, XPathConstants.NODESET);
		return table;
	}
}
