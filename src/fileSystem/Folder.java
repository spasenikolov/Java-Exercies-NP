package fileSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Folder implements IFile {
    String name;
    long size;

    List<IFile> iFiles;

    public Folder(String name) {
        this.name = name;
        this.size = 0;
        iFiles = new ArrayList<>();
    }

    @Override
    public String getFileName() {
        return this.name;
    }

    @Override
    public long getFileSize() {
        return this.size;
    }

    @Override
    public String getFileInfo(String iFileName, long iFileSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Folder name %10s Folder size: %10s \n", this.name, this.size));

        this.iFiles.forEach(iFile -> stringBuilder.append("\t").append(iFile.getFileInfo(iFile.getFileName(), iFile.getFileSize())));
        return stringBuilder.toString();
    }

    @Override
    public void sortBySize() {
        this.iFiles = this.iFiles.stream().sorted(Comparator.comparing(IFile::getFileSize)).collect(Collectors.toList());
        iFiles.forEach(IFile::sortBySize);
    }

    @Override
    //TODO
    public long findLargestFile() {
      return this.iFiles.stream().mapToLong(IFile::getFileSize).max().getAsLong();
    }

    public void addFile(IFile file) {
        if (this.iFiles.stream().anyMatch(iFile -> iFile.getFileName().equals(file.getFileName())))
            throw new FileNameExistsException(file.getFileName(),this.name);
        this.iFiles.add(file);
        this.size += file.getFileSize();
    }

    public String getIFileType() {
        return "Folder";
    }
}
