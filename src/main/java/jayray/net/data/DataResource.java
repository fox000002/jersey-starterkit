package jayray.net.data;

import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;

@Path("data")
public class DataResource {

    private static final Logger logger = Logger.getLogger(DataResource.class);

    @Path("/test")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Output test() {
        Output out = new Output();

        out.setValues(new double[] {
            1, 2, 3
        });

        return out;
    }

    @Path("/test2")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public Output test2() {
        Output out = new Output();

        out.setValues(new double[] {
                1, 2, 3
        });

        out.setMultivalues(new double[][]{
                {1, 2, 3},
                {4, 5, 6}
        });

        return out;
    }

    @Path("/run")
    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes("application/x-www-form-urlencoded")
    public Output run(@FormParam("input") String inputString) throws Exception {
        logger.debug("run : " + inputString);
        logger.info(inputString);

        JAXBContext context = JAXBContext.newInstance(Input.class);
        Unmarshaller unmarshallerB = context.createUnmarshaller();
        Input input = (Input) unmarshallerB.unmarshal(new ByteArrayInputStream(inputString.getBytes()));

        Output out = new Output();
        out.setValues(input.getValues().clone());
        out.setMultivalues(input.getMultivalues());
        out.setManyvalues(input.getManyvalues());
        return out;
    }


    @Path("/text")
    @GET
    @Produces("text/plain")
    public String text() throws Exception {
        Input input = new Input();

        input.setValues(new double[] {
                1, 2, 3
        });

        input.setMultivalues(new double[][]{
                {1, 2, 3},
                {4, 5, 6}
        });

        input.setManyvalues(new double[][][] {
                {
                        {1, 2, 3},
                        {4, 5, 6}
                },
                {
                        {7, 8, 9},
                        {10, 11, 12}
                }
        });

        JAXBContext context = JAXBContext.newInstance(Input.class);
        JAXBElement<Input> jaxbElement = new JAXBElement(new QName("Input"), Input.class, input);
        JAXBSource source = new JAXBSource(context, jaxbElement);
        StringWriter writer = new StringWriter();
        Marshaller m = context.createMarshaller();
        m.marshal(jaxbElement, writer);
        return writer.toString();
    }
}