package year2021.day16;

import utilities.BaseManipulator;

public class LiteralPacket extends Packet {

    private final long value;

    public LiteralPacket(String binaryString, int index) {
        super(binaryString, index);
        index += 6;
        StringBuilder valueString = new StringBuilder();
        while(true) {
            valueString.append(binaryString, index + 1, index + 5);
            if(binaryString.charAt(index) == '0') {
                break;
            }
            index += 5;
        }
        this.endIndex = index + 5;
        this.value = BaseManipulator.convertBaseToDecimal(valueString.toString(),2);
    }

    @Override
    public int getVersionSum() {
        return this.version;
    }

    @Override
    public long getValue() {
        return this.value;
    }
}
