package jayray.net.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Output {
    private double[] values;


    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    private double[][] multivalues;

    public double[][] getMultivalues() {
        return multivalues;
    }

    public void setMultivalues(double[][] multivalues) {
        this.multivalues = multivalues;
    }

    private double[][][] manyvalues;

    public double[][][] getManyvalues() {
        return manyvalues;
    }

    public void setManyvalues(double[][][] manyvalues) {
        this.manyvalues = manyvalues;
    }
}