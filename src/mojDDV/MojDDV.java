package mojDDV;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MojDDV {
    List<Receipt> receipts;

    public MojDDV() {
        this.receipts = new ArrayList<>();
    }

    public void readRecords(InputStream in) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        this.receipts =bufferedReader.lines()
                .map(line -> {
                    try {
                        return Receipt.createReceiptFromLine(line);
                    } catch (AmountNotAllowedException e) {
                        System.out.println(e.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public void printTaxReturns(OutputStream out) {
        PrintWriter printWriter = new PrintWriter(out);
        this.receipts.forEach(printWriter::println);
        printWriter.flush();
    }
}
