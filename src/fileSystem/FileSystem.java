package fileSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileSystem {
    String name;
    long size;
    List<IFile> iFiles;

    public FileSystem() {
        this.name = "root";
        size = 0;
        iFiles = new ArrayList<>();
    }
    public void addFile (IFile file){
        this.iFiles.add(file);
        this.size = file.getFileSize();
    }
    public long findLargestFile (){
        return this.iFiles.stream().mapToLong(IFile::findLargestFile).max().getAsLong();
    }
    public void sortBySize(){
        this.iFiles = this.iFiles.stream().sorted(Comparator.comparing(IFile::getFileSize)).collect(Collectors.toList());
        iFiles.forEach(IFile::sortBySize);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Folder name: root Folder size: %10s \n", this.size));
        this.iFiles.forEach(iFile -> stringBuilder.append("\t").append(iFile.getFileInfo(iFile.getFileName(), iFile.getFileSize())));
        return stringBuilder.toString();
    }
}
