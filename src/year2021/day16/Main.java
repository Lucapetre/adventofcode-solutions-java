package year2021.day16;

import utilities.BaseManipulator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class Main {

    public static void main(String[] args) {

        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get("input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        String binaryLine = BaseManipulator.splitHexToBinary(input.get(0));
        Packet bigPacket = PacketMaker.createPacket(binaryLine,0);
        System.out.println("Part 1:\n" +
                "Sum of version numbers: " + bigPacket.getVersionSum());
        System.out.println("Part 2:\n" +
                "Packet content: " + bigPacket.getValue());

    }

}
