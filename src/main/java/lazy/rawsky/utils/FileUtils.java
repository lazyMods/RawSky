package lazy.rawsky.utils;

import lazy.rawsky.Ref;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
}
