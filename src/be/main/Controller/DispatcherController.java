package be.main.Controller;

import java.io.IOException;
import java.util.ArrayList;
//import java.io.File;
//import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

//import org.springframework.http.converter.json.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import be.main.Models.PexelResponse;
import be.main.Models.Photo;
import be.main.Models.Post;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.core.JsonGenerationException;
//import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
public class DispatcherController {
	
	 public static final String USER_NAME = "";
	 public static final String PASSWORD = "";
	 //user name and password not required for pexel's API
	 
	 static final String TARGET_URL = "http://api.pexels.com/v1/search?query=";
	
	
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index(){
	
		return "view";
	}

	@RequestMapping(value = "/testvm", method = RequestMethod.GET)
	//@RequestMapping(value = "/testvm/{username}", method = RequestMethod.GET)//should use this for pathVariable
	public String totoavelo(HttpServletRequest request,@RequestParam Map<String,String> reqPar /*, @PathVariable("name") String name*/) {
		
		String keyword = reqPar.getOrDefault("keyword", "image");
		String title = reqPar.getOrDefault("postTitle", "");
		String text = reqPar.getOrDefault("postText", ":::empty:::");
		
		request.setAttribute("keyword: ", "<h1>Hi "+keyword+"!!</h1>");
			
		String imageURLs =  searchImagesOnPexel(keyword);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = imageURLs;
		

		//JSON from String to Object
		try {
			PexelResponse obj = mapper.readValue(jsonInString, PexelResponse.class);
			//System.out.println("photos:");
			//System.out.println(((java.util.LinkedHashMap)obj.getPhotos().get(0)).get("url"));
			//System.out.println(  ( (java.util.LinkedHashMap)    ( (java.util.LinkedHashMap)obj.getPhotos().get(0) ).get("src") ).get("medium") );
	
		
			Collection<Photo> urls = new ArrayList<Photo>();
			
			for (int i = 0; i < obj.getPhotos().size(); i++) {
				Photo p = new Photo(((java.util.LinkedHashMap) ( (java.util.LinkedHashMap)obj.getPhotos().get(i) ).get("src") ).get("small")+"");
				//System.out.println(p.getUrl());
				urls.add(p);
				
			}
			
			
			
			request.setAttribute("urls",urls);
			
			
			
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
		//request.setAttribute("urls", "<p>"+imageURLs +"</p>");
		return "test";
	}
	
	@RequestMapping(value = "/modetview", method = RequestMethod.GET)
	public ModelAndView mev(HttpServletRequest request,@RequestParam Map<String,String> reqPar /*, @PathVariable("name") String name*/) {
		Post model = new Post();
		
		String title = reqPar.getOrDefault("postTitle", "Default Title");
		String text = reqPar.getOrDefault("postText", ":::empty:::");
		String author = reqPar.getOrDefault("author", "anonymous");
		String imageUrl = reqPar.getOrDefault("chosen_image", "#");
		
		model.setTitle(title);
		model.setAuthor(author);
		model.setContent(text);
		model.setImageUrl(imageUrl);
		model.setDt_redac("16/12/2015");
		//here, model could be saved to the DB
		
		
/*		System.out.println("imageUrl:");
		System.out.println(imageUrl);
		request.setAttribute("imageUrl: ", imageUrl);*/

		return new ModelAndView("mev", "m", model);
	}
	
	
	private String searchImagesOnPexel(String keyword){
 
        // HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        
        // AUTHENTICATION
        /*
       		String auth = USER_NAME + ":" + PASSWORD;
        		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        		String authHeader = "Basic " + new String(encodedAuth);
        */
        
        String authHeader = "563492ad6f9170000100000164f5440f7a154c744d1657abb4449aec";
        
        headers.set("Authorization", authHeader);
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        /*
     		reminder: 
         		a user agent is software (a software agent) that is acting on behalf of a user. Such as the browser.
         		In particular, the Hypertext Transfer Protocol (HTTP) identifies the client software originating the request, 
         		using a user-agent header, even when the client is not operated by a user.
         */
        
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        
        // REQUEST TO RETURN JSON FORMAT
        headers.setContentType(MediaType.APPLICATION_JSON);
        
 
        // HttpEntity<String>: TO GET RESULT AS STRING.
        HttpEntity<String> entity = new HttpEntity<String>(headers);
 
        // RESTTEMPLATE
        RestTemplate restTemplate = new RestTemplate();
 
        // SEND REQUEST WITH GET METHOD, AND HEADERS.
        String result = null;
        try {
        			ResponseEntity<String> response = restTemplate.exchange(TARGET_URL+""+keyword, //
        			HttpMethod.GET, entity, String.class);
        			result = response.getBody();
        }
        catch(Exception e) {
        	
        			System.out.println(e);
        }
 
        return result;
    }
}
