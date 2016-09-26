package jayray.net.data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;


public class Demo {
    public static void main(String[] args) throws Exception {
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
        System.out.println(writer.toString());


        String text = "<input><multivalues><item>1.0</item><item>2.0</item><item>3.0</item></multivalues><multivalues><item>4.0</item><item>5.0</item><item>6.0</item></multivalues><values>1.0</values><values>2.0</values><values>3.0</values></input>";

        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.marshal(input, System.out);


        JAXBContext context2 = JAXBContext.newInstance(Input.class);
        Unmarshaller unmarshallerB = context.createUnmarshaller();
        Input input2 = (Input)unmarshallerB.unmarshal(new ByteArrayInputStream(text.getBytes()));

        System.out.println(input2.getValues().length);
    }
}
