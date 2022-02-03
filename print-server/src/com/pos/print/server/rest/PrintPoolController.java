package com.pos.print.server.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pos.print.server.entity.PrintDataEntity;
import com.pos.print.server.servelt.PoolConfigLoadServlet;
import com.pos.util.jersey.json.XJsonUtil;

@Path("/Pool")
public class PrintPoolController {
	private Logger log = LoggerFactory.getLogger(PrintPoolController.class);

	@GET
	@Path("Path")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response print() {
		try {

			return Response.status(201)
					.entity(new ObjectMapper().writeValueAsString(PoolConfigLoadServlet.REAL_PATH_POOL)).build();

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			log.error("Error: ", e);
			return Response.status(500).build();
		}

	}

	@POST
	@Path("Print")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response print(PrintDataEntity data) {
		try {

			// PrintDataEntity dataLoad = new ObjectMapper().readValue(new File(new
			// StringBuilder(PoolServlet.REAL_PATH_POOL).append("/").append(fileName).toString()),
			// PrintDataEntity.class);
			if (data.getDevice() == null || data.getDevice().trim().length() == 0 || data.getComanda() == null
					|| data.getComanda().trim().length() == 0) {
				return Response.status(590).build();
			} else {
				StringBuilder fileName = new StringBuilder(data.getDevice()).append("-")
						.append(System.currentTimeMillis()).append(".printer");
				new XJsonUtil().saveToFile(new StringBuilder(PoolConfigLoadServlet.REAL_PATH_POOL)
						.append(PoolConfigLoadServlet.FILE_SEPARATOR).append(fileName).toString(), data);
			}

		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			log.error("Error: ", e);
			return Response.status(406).build();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			log.error("Error: ", e);
			return Response.status(406).build();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			log.error("Error: ", e);
			return Response.status(500).build();
		}
		return Response.status(201).build();
	}
}
