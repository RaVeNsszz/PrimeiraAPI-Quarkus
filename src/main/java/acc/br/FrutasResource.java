package acc.br;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.jboss.logging.Logger;


import acc.br.model.Fruta;

@Path("/frutas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FrutasResource {
	
	private static final Logger LOG = Logger.getLogger(FrutasResource.class);


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruta> list() {
    	LOG.info("Mostrando as Lista de frutas");
        return Fruta.listAll();
    }
    
    @POST
    @Transactional
    @Consumes("application/json")
    public Response novaFruta(Fruta fruta) {
    	LOG.info("Recebendo fruta: " + fruta.nome + ", Quantidade: " + fruta.qtd);
    	fruta.persist();
        return Response.status(Response.Status.CREATED).entity(fruta).build();
    }
    
    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deletarFruta(@PathParam("id") Long id) {
        Fruta fruta = Fruta.findById(id);
        
        if (fruta == null) {
        	LOG.info("Fruta nao encontrada");
            // Se a fruta não for encontrada, retorna 404 Not Found
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("Fruta não encontrada com ID " + id)
                           .build();
        }
    	LOG.info("Fruta no id: "+id+" deletada");

        fruta.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
