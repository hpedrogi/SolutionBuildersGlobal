package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.HotelDAO;
import br.com.fiap.model.Hotel;

@Path("/hotels")
@Produces(MediaType.APPLICATION_JSON)
public class HotelEndpoint {
	
	private HotelDAO dao = new HotelDAO();
	
	@GET
	public Response index() {
		try {
			List<Hotel> list = dao.getAll();
			return Response
					.status(Response.Status.OK)
					.entity(list)
					.build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Hotel hotel) {
		if (hotel == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.save(hotel);
			return Response
					.status(Response.Status.CREATED)
					.entity(hotel)
					.build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("{id}")
	public Response show(@PathParam("id") Long id) {
		Hotel hotel = dao.findById(id);
		if (hotel == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(Response.Status.OK).entity(hotel).build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, Hotel hotel) {
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (hotel == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if(dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		hotel.setId(id);
		try {
			dao.update(hotel);
			return Response.status(Response.Status.OK).entity(hotel).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		dao.deleteById(id);
		return Response.status(200).entity(id).build();
	}
	
	
	
	
	
	
	
}