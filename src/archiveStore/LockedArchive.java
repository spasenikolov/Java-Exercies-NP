package archiveStore;

import java.time.LocalDate;

public class LockedArchive extends Archive{
    LocalDate dateToOpen;


    public LockedArchive(Integer id, LocalDate dateToOpen) {
        super(id);
        this.dateToOpen = dateToOpen;
    }

    @Override
    public String getArchiveType() {
        return "LockedArchive";
    }
    public LocalDate getDateToOpen() {
        return dateToOpen;
    }

}
