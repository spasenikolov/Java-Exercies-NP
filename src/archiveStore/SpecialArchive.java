package archiveStore;

public class SpecialArchive extends Archive{
    Integer maxOpen;
    Integer timesOpened;

    public SpecialArchive(Integer id, Integer maxOpen) {
        super(id);
        this.maxOpen = maxOpen;
        timesOpened = 0;
    }
    public String getArchiveType(){
        return "SpecialArchive";
    }
}
