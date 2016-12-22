
public class Vign_Node implements Comparable<Vign_Node>{

    private String variant;
    private float quality;
    private int shift;

    public String getVariant() {
        return this.variant;
    }
    public float getQuality() {
        return this.quality;
    }
    public int getShift() {
        return this.shift;
    }
    public void setVariant(String s) {
        this.variant = s;
    }
    public void setQuality(float q) {
        this.quality = q;
    }
    public void setShift(int sh) {
        this.shift = sh;
    }
    @Override
    public int compareTo(Vign_Node variantNode) {
        if (variantNode.getQuality() < this.quality) return -1;
        if (variantNode.getQuality() > this.quality) return 1;
        return 0;
    }
}
