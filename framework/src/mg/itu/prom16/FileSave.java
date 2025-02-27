package mg.itu.prom16;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import jakarta.servlet.http.Part;

public class FileSave {

    public static String saveFile(Part file) throws Exception {
        if (file != null) {
            String directoryPath = System.getProperty("user.dir") + "\\save";  // Utilise le r\u00E9pertoire de travail actuel
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();  // Cr\u00E9e le r\u00E9pertoire si n\u00E9cessaire
            }

            // Set read, write, and execute permissions for Windows
            setDirectoryPermissions(directory.toPath());

            // Get the file name and construct the file path
            String fileName = Paths.get(file.getSubmittedFileName()).getFileName().toString();
            Path filePath = Paths.get(directoryPath, fileName);
            
            // Try writing the file using Files.write to avoid file locks
            try (var inputStream = file.getInputStream()) {
                Files.copy(inputStream, filePath); // Write file to path
            } catch (IOException e) {
                throw new IOException("Failed to write file: " + filePath.toString(), e);
            }
            
            return filePath.toString(); // Return the full path
        }
        return null;
    }

    private static void setDirectoryPermissions(Path directoryPath) {
        File directory = directoryPath.toFile();
        
        // Set permissions explicitly for Windows
        directory.setWritable(true, false); // writable by all users
        directory.setReadable(true, false); // readable by all users
        directory.setExecutable(true, false); // executable by all users
    }
}
