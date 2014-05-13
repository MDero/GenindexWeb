package kernel;

/**
 * This class manages the different fields of an invoice.
 */
public class Invoice {

    private int idOrder;

    private double price;
    /**
     * It is the number of analysis of the order.
     */
    private int numberAnalysis;

    public Invoice(double price, int numberAnalysis) {
        this.price = price;
        this.numberAnalysis = numberAnalysis;
    }

    protected Invoice(double price, int numberA, int id) {
        // Bouml preserved body begin 00021A45
        this.idOrder = id;
        this.price = price;
        this.numberAnalysis = numberA;
        // Bouml preserved body end 00021A45
    }

    /**
     * This function permits to print the informations of this invoice.
     */
    public void printInvoice(Database d) {
        // Bouml preserved body begin 00043382
        System.out.print("Invoice for the order n ?");
        System.out.println(idOrder);
        System.out.print("Number of analysis : ");
        System.out.println(numberAnalysis);

        System.out.println("Details of analysis : ");
        Orders o = d.getOrders(this.idOrder);
        for (int i = 0; i < o.getSamples().size(); i++) {
            if (o.getSamples().get(i).getAnalyses() != null) {
                System.out.print(o.getSamples().get(i).getAnalysis().getTypeAnalysis().getType());
                System.out.print(" : ");
                System.out.print(o.getSamples().get(i).getAnalysis().getTypeAnalysis().getPriceFor(o.getSamples().get(i).getType()));
                System.out.println(" ? ");
            }
        }

        System.out.print("Total price : ");
        System.out.print(price);
        System.out.println(" ? ");
        // Bouml preserved body end 00043382
    }

    public double getPrice() {
        // Bouml preserved body begin 00043482
        return this.price;
        // Bouml preserved body end 00043482
    }

    public int getIdOrder() {
        return idOrder;
    }

    public int getNumberAnalysis() {
        return numberAnalysis;
    }

}
