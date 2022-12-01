package mojDDV;

import static mojDDV.TaxStrategyCreator.createTaxStrategy;

public class ReceiptItem {
    Integer itemPrice;
    String type;
    Double taxReturnForItem;

    public ReceiptItem(Integer itemPrice, String type) {
        this.itemPrice = itemPrice;
        this.type = type;
        this.taxReturnForItem = createTaxStrategy(type).calculateTax(itemPrice);
    }

    public static ReceiptItem generateReceipt(String receiptWithType){
        String [] parts = receiptWithType.split("\\s+");
        Integer itemPrice = Integer.parseInt(parts[0]);
        String theType = parts[1];
        return new ReceiptItem(itemPrice, theType);
    }
}
