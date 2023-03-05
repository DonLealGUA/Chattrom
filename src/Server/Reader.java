package Server;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Reader {


    public static boolean readIfUserExist(String newUsername){
        try {
            File myObj = new File("files/Users.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(myObj)));
            while (true) {
                String line = r.readLine();
                if (line == null) { break; }
                List<Object> res = List.of(line.split(" "));
                String username = (String) res.get(0);
                if (newUsername.equals(username)){
                    return true;
                }
            }
            return false;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static ArrayList readChat(){
        try {
            ArrayList<String> chat = new ArrayList<String>();
            File myObj = new File("files/chat.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                chat.add(myReader.nextLine());
            }
            System.out.println(chat);
            return chat;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList readServerLogg(){
        try {
            ArrayList<String> chat = new ArrayList<String>();
            File myObj = new File("files/server.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                chat.add(myReader.nextLine());
            }
            System.out.println(chat);
            return chat;


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList readPrivateChat(String User, String isFriendWith){
        try {
            ArrayList<String> chat = new ArrayList<String>();
            File myObj = new File("files/privateMesseges/\" +User+ \"/\" +isFriendWith+ \".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                chat.add(myReader.nextLine());
            }
            return chat;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getUnsentMessages(String username) throws IOException {
        List<String> unsentMessages = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("files/unSentMessages.txt"));
        PrintWriter writer = new PrintWriter(new FileWriter("files/unSentMessages_temp.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ", 2);
            if (parts.length == 2 && parts[0].equals(username)) {
                unsentMessages.add(parts[1]);
            } else {
                writer.println(line);
            }
        }
        reader.close();
        writer.close();
        boolean deleteSuccess = new File("files/unSentMessages.txt").delete();
        boolean renameSuccess = new File("files/unSentMessages_temp.txt").renameTo(new File("files/unSentMessages.txt"));
        if (!deleteSuccess || !renameSuccess) {
            throw new IOException("Failed to update unsent messages file.");
        }
        return unsentMessages;
    }


    public static HashMap readUsers(){
        try {
            File myObj = new File("files/Users.txt");
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(myObj)));
            HashMap<String, ImageIcon> chat = new HashMap<>();
            while (true) {
                String line = r.readLine();
                if (line == null) { break; }
                List<Object> res = List.of(line.split(" "));
                String username = (String) res.get(0);
                ImageIcon image = new ImageIcon((String) res.get(1));
                chat.put(username,image);
            }
            return chat;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static ArrayList<List<String>> readFriends() throws IOException {
        String fnam = "files/Friends.txt";
        BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(fnam)));
        ArrayList<List<String>> testCaseData = new ArrayList<>();
        ArrayList<String> test = new ArrayList<>();

        while (true) {
            String line = r.readLine();
            if (line == null) { break; }
            assert line.length() == 11; // indatakoll, om man kör med assertions på
            List<String> items = new ArrayList<String>(Arrays.asList(line.split("\n")));
            test.add(items.get(0).toString());
        }
        for(int i = 0; i < test.size(); i++){
            List<String> items = new ArrayList<String>(Arrays.asList(test.get(i).split(" ")));
            testCaseData.add(items);
        }
        System.out.println(testCaseData);
        return testCaseData;
    }
}
