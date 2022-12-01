package fileSystem;

public class FileNameExistsException extends RuntimeException {
    public FileNameExistsException(String fileName, String folderName) {
        super(String.format("There is already a file named %s in the folder %s",fileName,folderName));
    }
}
