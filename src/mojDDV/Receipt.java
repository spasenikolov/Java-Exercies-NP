package mojDDV;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    Integer id;
    List<ReceiptItem> receiptItems;
    Integer sum;

    public Receipt(Integer id, List<ReceiptItem> receiptItems, Integer sum) throws AmountNotAllowedException {
        if (sum > 30000) throw new AmountNotAllowedException(sum);
        this.id = id;
        this.receiptItems = receiptItems;
        this.sum=sum;
    }

    public static Receipt createReceiptFromLine(String line) throws AmountNotAllowedException {
        Integer theSum = 0;
        // 11213 8383 A 2921 B 1292 A 2191 V
        String [] parts = line.split("\\s+");
        Integer theId = Integer.parseInt(parts[0]);
        List<ReceiptItem> receiptItemsList = new ArrayList<>();

        for(int i = 1; i<parts.length-1; i++){
            //i=1 8383 i=2 A
            Integer price = Integer.parseInt(parts[i++]);
            theSum+=price;
            receiptItemsList.add(new ReceiptItem(price, parts[i]));
        }
        return new Receipt(theId, receiptItemsList, theSum);

    }
    public Double getTaxReturn(){
        return 0.15 * this.receiptItems.stream()
                .mapToDouble(receiptItem -> receiptItem.taxReturnForItem)
                .sum();
    }

    @Override
    public String toString() {
        return String.format("%10d\t%10d\t%10.5f",this.id, this.sum, getTaxReturn());
    }
}
