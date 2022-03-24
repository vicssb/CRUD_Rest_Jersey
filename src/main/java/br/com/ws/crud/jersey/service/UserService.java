package br.com.ws.crud.jersey.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;
 
import br.com.ws.crud.jersey.dao.UserDao;
import br.com.ws.crud.jersey.model.User;

public class UserService {

	@Context UriInfo uriInfo;
    
    @GET
    @Path("/users")
    @Produces(MediaType.TEXT_XML)
    public List<User> listar() throws SQLException {
        UserDao dao = new UserDao();
        List<User> users = dao.list();
        return users;
    }
     
    @POST
    @Consumes(MediaType.TEXT_XML)
    @Produces(MediaType.TEXT_XML)
    public Response criar(JAXBElement<User>  dada) { 
 
    	User user = dada.getValue();
        UserDao dao = new UserDao();
             
        try {
            int idNewRec = dao.criar(user);         
            String newRec = uriInfo.getPath() + "/" + idNewRec;
            URI uri = new URI(newRec) ;                
            return Response.created(uri).build();               
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }       
        return Response.serverError().build();
    } 
     
    @PUT
    @Path("/{cod}")
    @Consumes(MediaType.TEXT_XML)
    @Produces(MediaType.TEXT_XML)
    public Response alterar(@PathParam("cod") int cod, 
                                 JAXBElement<User>  dada) { 
 
    	User user = dada.getValue();
    	user.setId(cod);
        UserDao dao = new UserDao();          
        try {           
        	User newUser = dao.edit(user);     
            return Response.ok(newUser).build();                
        } catch (SQLException e) {
            e.printStackTrace();
        }       
        return Response.serverError().build();
    } 
     
    @GET
    @Path("/{cod}")
    @Produces(MediaType.TEXT_XML)
    public Response noticia(@PathParam("cod") int cod) { 
 
    	UserDao dao = new UserDao();      
        try {           
        	User user = dao.read(cod);      
            return Response.ok(user).build();                
        } catch (SQLException e) {
            e.printStackTrace();
        }       
        return Response.serverError().build();
    } 
     
    @DELETE
    @Path("/{cod}")
    public Response apagar(@PathParam("cod") int cod) { 
 
    	UserDao dao = new UserDao();  
        try {           
            dao.deletar(cod);
            return Response.noContent().build();                
        } catch (SQLException e) {
            e.printStackTrace();
        }       
        return Response.serverError().build();
    }
}
