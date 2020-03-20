
public class ZipCodeRange {

    private long lowerValue;
    private long upperValue;

    public ZipCodeRange(long lowerValue, long upperValue) {
        this.lowerValue = lowerValue;
        this.upperValue = upperValue;
    }

    public long getLowerValue() {
        return lowerValue;
    }

    public long getUpperValue() {
        return upperValue;
    }

    @Override
    public String toString() {
        return "[" + lowerValue + ", " + upperValue + "]";
    }


}
