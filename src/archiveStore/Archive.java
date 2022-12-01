package archiveStore;

import java.time.LocalDate;

public abstract class Archive {
    Integer id;
    LocalDate dateArchived;

    public Archive(Integer id) {
        this.id = id;
        this.dateArchived = LocalDate.now();
    }
    public abstract String getArchiveType();
}
