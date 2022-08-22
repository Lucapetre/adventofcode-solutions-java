package year2021.day16;

import utilities.BaseManipulator;

import java.util.ArrayList;
import java.util.List;

public class OperatorPacket extends Packet {

    List<Packet> subpackets;

    public OperatorPacket(String binaryString, int index) {
        super(binaryString, index);
        this.subpackets = new ArrayList<>();
        index += 6;
        if(binaryString.charAt(index) == '0') {
            index++;
            long bitLength = BaseManipulator.convertBaseToDecimal(binaryString.substring(index,index + 15),2);
            index += 15;
            while(bitLength > 0) {
                Packet newPacket = PacketMaker.createPacket(binaryString,index);
                subpackets.add(newPacket);
                bitLength -= newPacket.getLength();
                index = newPacket.getEndIndex();
            }
            this.endIndex = index;
        } else {
            index++;
            long numberOfPackets = BaseManipulator.convertBaseToDecimal(binaryString.substring(index,index + 11),2);
            index += 11;
            while(numberOfPackets > 0) {
                Packet newPacket = PacketMaker.createPacket(binaryString,index);
                subpackets.add(newPacket);
                numberOfPackets --;
                index = newPacket.getEndIndex();
            }
            this.endIndex = index;
        }
    }

    @Override
    public int getVersionSum() {
        return this.version + this.subpackets.stream().mapToInt(Packet::getVersionSum).sum();
    }

    @Override
    public long getValue() {
        return switch (this.ID) {
            case 0 -> subpackets.stream().mapToLong(Packet::getValue).sum();
            case 1 -> subpackets.stream().mapToLong(Packet::getValue).reduce(1, (a, b) -> a * b);
            case 2 -> subpackets.stream().mapToLong(Packet::getValue).reduce(Long.MAX_VALUE, Math::min);
            case 3 -> subpackets.stream().mapToLong(Packet::getValue).reduce(0, Math::max);
            case 5 -> subpackets.get(0).getValue() > subpackets.get(1).getValue() ? 1 : 0;
            case 6 -> subpackets.get(0).getValue() < subpackets.get(1).getValue() ? 1 : 0;
            case 7 -> subpackets.get(0).getValue() == subpackets.get(1).getValue() ? 1 : 0;
            default -> 0;
        };
    }
}
