package year2021.day16;

import utilities.BaseManipulator;

public class PacketMaker {
    public static Packet createPacket(String binaryString,int startIndex) {

        int index = startIndex + 3;
        int ID = (int) BaseManipulator.convertBaseToDecimal(binaryString.substring(index, index + 3),2);
        if (ID == 4) {
            return new LiteralPacket(binaryString,startIndex);
        } else {
            return new OperatorPacket(binaryString,startIndex);
        }

    }

}
