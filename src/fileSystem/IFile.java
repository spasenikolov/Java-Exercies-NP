package fileSystem;

public interface IFile {
    public String getFileName();
    public long getFileSize();
    String getFileInfo(String iFileName, long iFileSize);
    public void sortBySize();
    public long findLargestFile();
    public String getIFileType();

}
