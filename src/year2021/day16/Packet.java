package year2021.day16;

import utilities.BaseManipulator;

abstract class Packet {

    protected final int version;
    protected final int ID;

    protected final int startIndex;
    protected int endIndex;

    protected Packet(String binaryString, int index) {
        this.startIndex = index;
        this.version = (int) BaseManipulator.convertBaseToDecimal(binaryString.substring(index,index + 3),2);
        this.ID = (int) BaseManipulator.convertBaseToDecimal(binaryString.substring(index + 3, index + 6), 2);
    }

    public abstract int getVersionSum();

    public abstract long getValue();

    public int getEndIndex() {
        return this.endIndex;
    }

    public int getLength() {
        return this.endIndex - this.startIndex;
    }
}
