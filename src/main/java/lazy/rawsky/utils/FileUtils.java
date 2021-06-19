package lazy.rawsky.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lazy.rawsky.Ref;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    public static boolean isEmpty(File file) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            if (br.readLine() == null) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void checkOrCreatePath(Path path){
        if(!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static File createOrGetFile(String path){
        try {
            Path path_ = Paths.get(path);
            if(Files.exists(path_)) return path_.toFile();
            Files.createFile(Paths.get(path));
            return path_.toFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T gsonRead(Gson gson, Type type, File file, T fallback){
        try {
            return gson.fromJson(new FileReader(file), type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fallback;
    }

    public static void gsonWrite(Gson gson, File file, Object content){
        try {
            FileWriter writer = new FileWriter(file);
            gson.toJson(content, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
