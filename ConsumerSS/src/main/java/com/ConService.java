package com;

import model.Cons;

import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document;


	
	
	@Path("/Cons") 
	public class ConService {

		Cons itemObj = new Cons(); 
		@GET
		@Path("/") 
		@Produces(MediaType.TEXT_HTML) 
		public String readItems() 
		 { 
			return itemObj.readItems(); 
		 }

		@POST
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String insertItem(@FormParam("name") String name, 
		 @FormParam("address") String address, 
		 @FormParam("phone") String phone, 
		 @FormParam("email") String email, 
		 @FormParam("description") String description)
		{ 
		 String output = itemObj.insertItem(name, address, phone, email,description); 
		return output; 
		}
		
		@PUT
		@Path("/") 
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String updateItem(String itemData) 
		{ 
		//Convert the input string to a JSON object 
		 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
		//Read the values from the JSON object
		 String cid = itemObject.get("cid").getAsString(); 
		 String name = itemObject.get("name").getAsString(); 
		 String address = itemObject.get("address").getAsString(); 
		 String phone = itemObject.get("phone").getAsString(); 
		 String email = itemObject.get("email").getAsString(); 
		 String description = itemObject.get("description").getAsString();
		 String output = itemObj.updateItem(cid, name, address, phone, email,description); 
		return output; 
		}
		
		@DELETE
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		public String deleteItem(String itemData) 
		{ 
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
		 
		//Read the value from the element 
		 String cid = doc.select("cid").text(); 
		 String output = itemObj.deleteItem(cid); 
		return output; 
		}
		
		
		
		
}
