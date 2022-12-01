package fileSystem;

public class File implements IFile{
    String name;
    long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getFileName() {
        return  this.name;
    }

    @Override
    public long getFileSize() {
        return this.size;
    }

    @Override
    public String getFileInfo(String iFileName, long iFileSize) {
        return String.format("\tFile name %10s File size: %10s\n",this.name, this.size);
    }

    @Override
    public void sortBySize() {

    }

    @Override
    public long findLargestFile() {
        return this.size;
    }
    public String getIFileType(){
        return "File";
    }
}
