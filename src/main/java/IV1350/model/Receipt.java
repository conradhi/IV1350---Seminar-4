package IV1350.model;

import java.time.LocalDateTime;

/**
 *  Represents a receipt of the finished {@Sale}.
 */
public class Receipt {
    private Sale sale;

    /**
     * Creates a new receipt that contains all the information of the {@Sale}
     *
     * @param sale The given {@Sale} that will be printed
     */
    public Receipt(Sale sale){
        this.sale = sale;
    }

    /**
     * Creates a string that kind of looks like a receipt.
     * 
     * @return String that represents the receipt in the print out. 
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        addNewLine(builder);
        appendLine(builder, "----------RECEIPT----------");
        addNewLine(builder);
        addLocalDate(builder);
        addNewLine(builder);
        appendLine(builder, "Varor: ");
        addNewLine(builder);
        appendLine(builder, sale.toString());
        appendLine(builder, "------------END------------");
        return builder.toString();
    }

    private void addNewLine(StringBuilder builder){
        builder.append("\n");
    }

    private void appendLine(StringBuilder builder, String line){
        builder.append(line);
        addNewLine(builder);
    }

    private void addLocalDate(StringBuilder builder){
        LocalDateTime saleTime = LocalDateTime.now();
        appendLine(builder, "Tid av köp " + saleTime.toLocalDate().toString());
    }

}