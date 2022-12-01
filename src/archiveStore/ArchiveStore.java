package archiveStore;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArchiveStore {
    private List<Archive> archives;
    public PrintWriter printWriter;
    public static PrintWriter exceptionPrintWriter;

    public ArchiveStore() {
        archives = new ArrayList<>();
        printWriter = new PrintWriter(System.out);
    }

    void archiveItem(Archive item, LocalDate date) {
        archives.add(item);
        printWriter.format("Item with id %d archived at date %s \n", item.id, date.toString());
    }

    void openItem(int id, LocalDate date) throws NonExistingItemException {

//        List<Integer> collect = archives.stream()
//                .mapToInt(archives -> archives.id)
//                .boxed()
//                .collect(Collectors.toList());
//
//        boolean containsId = false;
//        for (Integer idToCheck: collect){
//            if (idToCheck==id) {
//                containsId = true;
//                break;
//            }
//        }


        Archive itemToOpen = archives.stream().filter(archive -> archive.id.equals(id)).findAny().orElseThrow(() -> new NonExistingItemException(id));


        if (itemToOpen.getArchiveType().equals("LockedArchive")) {
            LockedArchive lockedArchive = (LockedArchive) itemToOpen;
            if (lockedArchive.dateToOpen.isAfter(date)) {
                printWriter.format("Item %d cannot be opened before %s \n", lockedArchive.id, lockedArchive.dateToOpen.toString());
            } else {
                printWriter.format("Item %d opened at %s \n", lockedArchive.id, date);
            }
        } else {
            SpecialArchive specialArchive = (SpecialArchive) itemToOpen;
            if (specialArchive.timesOpened < specialArchive.maxOpen) {
                printWriter.format("Item %d opened at %s \n", specialArchive.id, date.toString());
                ++specialArchive.timesOpened;
            } else {
                printWriter.format("Item %d cannot be opened more than %d times \n", specialArchive.id, specialArchive.maxOpen);
            }
        }
    }

    public boolean getLog(){
        printWriter.close();
        return true;
    }
}
