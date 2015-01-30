package ba.bitcamp.vedadz.klix.shared;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Article {
	private int id;
	private String title;
	private String content;
	private int pubDate;
	private int authorID;
	
	public Article(int id, String title, String content, int pubDate, int authorID) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.pubDate = pubDate;
		this.authorID = authorID;
	}
	
	public Article(Node node){
		NodeList children = node.getChildNodes();
		//Checking if node is instance of element so we cast it and take ID from it ( we can't take attribute directly from node).		
		if(node instanceof Element){
			Element item = (Element) node;
			this.id = Integer.parseInt(item.getAttribute("id"));			//parsing id from item to our id.
		}else
			System.err.println("Not instance of Element (first id)");
		//Loop where we take all children, then depending on its tag name we add them into our class attributes.		
		for(int i=0; i<children.getLength(); i++){
			Node currentNode = children.item(i);
			
			if(currentNode instanceof Element){		//checking if we can cast into element. 				
				Element current = (Element) currentNode;
				
				if(current.getTagName().equals("title"))				//checking if elements tag name is title
					this.title = current.getTextContent();
				if(current.getTagName().equals("autor"))				//... if its author	
					this.authorID = Integer.parseInt(current.getAttribute("id"));
				if(current.getTagName().equals("pubDate"))				//.. if its pubDate
					this.pubDate = Integer.parseInt(current.getTextContent());
				if(current.getTagName().equals("clanak"))				//... if its clanak
					this.content = current.getTextContent();
				
			}
		}
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public int getPubDate() {
		return pubDate;
	}

	public int getAuthorID() {
		return authorID;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content="
				+ content + ", pubDate=" + pubDate + ", authorID=" + authorID
				+ "]";
	}	
}
