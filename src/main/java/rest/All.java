/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.json.Json;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import util.JsonValidator;

/**
 * REST Web Service
 *
 * @author plaul1
 */
@Path("demoall")
public class All {

  @Context
  private UriInfo context;

  /**
   * Creates a new instance of A
   */
  public All() {
  }

  /**
   * Retrieves representation of an instance of rest.All
   * @return an instance of java.lang.String
   */
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getText() {
    return " {\"message\" : \"result for all\"}";
  }
  
  
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public String postSamaritterRequest(String json){
      
      try{
          JsonValidator.validateRequest(json);
      }
      catch(Exception e ){
          return "[false]";
      }
      finally{
          
      }
      return "[true]";
      
  }

  //
    

}
