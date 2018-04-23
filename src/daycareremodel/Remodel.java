package daycareremodel;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author Sophie Mester
 *
 * Daycare Remodeling Problem
 * Problem credit: Mark Floryan
 *
 * Solution is a greedy algorithm.
 *
 * In this problem, each room falls into one of three categories:
 * rooms that will increase in size, rooms that will decrease in capacity, and rooms that will remain constant in capacity.
 *
 * Rooms that increase in capacity are remodeled first, in ascending order of starting rooms capacity (room that started smallest first).
 * Note that this means that of the rooms that increase in capacity, the room requiring the smallest trailer will be remodeled first.
 * Next rooms that remain constant in capacity will be remodeled. The starting and ending capacity of the room does not matter if the capacity is not changing.
 * Finally, rooms that decrease in capacity are remodeled, in decending order of ending room capacity (room that will become largest first).
 *
 * Solution is described as "greedy" because remodeling the rooms that provide increased daycare capacity
 * and require the smallest trailer first leads to an overall optimal solution.
 *
 */
public class Remodel {

    public static void main(String[] args) {
        String line;
        String fileName = args[0];

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(fileReader);

            while((line = reader.readLine()) != null) {
                int numRooms = Integer.valueOf(line);

                ArrayList<Pair<Integer, Integer>> roomSizes = new ArrayList<>();
                ArrayList<Pair<Integer, Integer>> sameSize = new ArrayList<>();
                ArrayList<Pair<Integer, Integer>> decreaseSize = new ArrayList<>();

                for (int i = 0; i < numRooms; i++) {
                    line = reader.readLine();

                    String[] splitLine = line.split(" ");
                    int beforeSize = Integer.valueOf(splitLine[0]);
                    int afterSize = Integer.valueOf(splitLine[1]);

                    Pair<Integer, Integer> pair = new Pair<>(beforeSize, afterSize);
                    int change = afterSize - beforeSize;

                    if (change < 0) {
                        decreaseSize.add(pair);
                    } else if (change > 0) {
                        roomSizes.add(pair);
                    } else {
                        sameSize.add(pair);
                    }
                }

                //sort rooms that increase in size by starting room size, ascending
                roomSizes.sort(Comparator.comparing(Pair::getKey));

                //sort rooms that decrease in size by ending room size, descending
                decreaseSize.sort(Comparator.comparing(Pair::getValue, Comparator.reverseOrder()));

                //add rooms that remain constant in size after the increasing rooms in the roomSizes list (no sort necessary)
                roomSizes.addAll(sameSize);

                //add rooms that decrease in size to the end of the roomSizes list
                roomSizes.addAll(decreaseSize);

                //calculate the minimum required trailer size using the sorted list containing all rooms
                findRequiredTrailerSize(roomSizes);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File " + fileName + " not found");
        } catch (IOException ex) {
            System.out.println("Error reading from file " + fileName);
        }
    }

    /**
     * Iterate through the sorted list of rooms, updating the minimum required trailer size if the
     * capacity required to renovate each room surpasses the current trailer size.
     *
     * @param roomSizes ArrayList of pairs containing starting and ending capacity for each room in optimal sorted order
     */
    private static void findRequiredTrailerSize(ArrayList<Pair<Integer, Integer>> roomSizes) {
        int tempTrailerSize = 0;
        int trailerSize = 0;
        int openRemodeledSpots = 0;

        for (Pair<Integer, Integer> room : roomSizes) {
            tempTrailerSize = room.getKey() - openRemodeledSpots;
            trailerSize = tempTrailerSize > trailerSize ? tempTrailerSize : trailerSize;
            openRemodeledSpots += (room.getValue() - room.getKey());
        }

        System.out.println(trailerSize);
    }
}
