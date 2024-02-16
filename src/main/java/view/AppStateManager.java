package view;

import java.awt.Color;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AppStateManager {
    private static final String FILE_PATH = "app_state.txt";

    // Save room state including color and card layout
    public static void saveAppState(int roomNumber, Color color, String cardLayoutState) {
        Map<Integer, RoomState> roomStates = loadAppState();
        if (roomStates == null) {
            roomStates = new HashMap<>();
        }
        roomStates.put(roomNumber, new RoomState(color, cardLayoutState));

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(roomStates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static RoomState loadAppState(int roomNumber) {
        Map<Integer, RoomState> roomStates = loadAppState();
        if (roomStates != null && roomStates.containsKey(roomNumber)) {
            return roomStates.get(roomNumber);
        }
        return null;
    }

    public static Map<Integer, RoomState> loadAppState() {
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Map<Integer, RoomState>) ois.readObject();
        } catch (FileNotFoundException e) {
            // File not found, return an empty map
            return new HashMap<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // Handle other exceptions appropriately
        }
        return new HashMap<>();
    }

    public static class RoomState implements Serializable {
        private Color color;
        private String cardLayoutState;

        public RoomState(Color color, String cardLayoutState) {
            this.color = color;
            this.cardLayoutState = cardLayoutState;
        }

        public Color getColor() {
            return color;
        }

        public String getCardLayoutState() {
            return cardLayoutState;
        }
    }
}
