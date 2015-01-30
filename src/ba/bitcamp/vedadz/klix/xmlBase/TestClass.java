package ba.bitcamp.vedadz.klix.xmlBase;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ba.bitcamp.vedadz.klix.shared.Article;

public class TestClass {
	
	public static boolean testXMLConnection(String tableName)
			throws ParserConfigurationException, XPathExpressionException,
			SAXException, IOException {
		
		XMLConnection xc = new XMLConnection();					//
		if (xc.query(tableName, "path") instanceof NodeList) {
			NodeList table = xc.query(tableName, "path");
			Node firstNode = table.item(0);
			Element firstItem = (Element) firstNode;
			int id = Integer.parseInt(firstItem.getAttribute("id"));

			if (id != 1)
				return false;
			NodeList childrenElements = firstNode.getChildNodes();
			for (int i = 0; i < childrenElements.getLength(); i++) {
				Node current = childrenElements.item(i);
			
				if (current instanceof Element) {
					
					Element ce = (Element) current;
					// Check for author
					if (ce.getTagName().equals("autor")) {
						int authorID = Integer.parseInt(ce.getAttribute("id"));
						if (authorID != 1)
							return false;
					}
					// Check for pubDate
					if (ce.getTagName().equals("pubDate")) {
						int pubDate = Integer.parseInt(ce.getTextContent());
						if (pubDate != 0)
							return false;
					}
				}
			}

		}

		return true;
	}
	
	private static boolean testArticleConstructor(Document xmldoc) throws ParserConfigurationException, SAXException, IOException{
		
		DocumentBuilder docRead = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		//Document xmldoc = docRead.parse(new File("./XML/articles.xml"));
		Node testItem = xmldoc.getElementsByTagName("item").item(0);
		Article testArticle =  new Article(testItem);
		
		if(testArticle.getId() != 1)
			return false;
		if(testArticle.getAuthorID() != 1)
			return false;
		if(testArticle.getPubDate() != 0)
			return false;	
		
		return true;
	}
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		DocumentBuilder docRead = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document xmldoc = docRead.parse(new File("./XML/articles.xml"));
		//System.out.println(testArticleConstructor(xmldoc));
		System.out.println(testXMLConnection("articles"));
		
		
	}
}
