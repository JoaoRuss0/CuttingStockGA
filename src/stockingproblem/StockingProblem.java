package stockingproblem;

import algorithms.Problem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StockingProblem implements Problem<StockingProblemIndividual> {
    private int materialHeight;
    private int materialWidth;
    private double NumColsPer;
    private double MaxSizePer;
    private ArrayList<Item> items;

    public StockingProblem(int materialHeight, ArrayList<Item> items) {
        this.materialHeight = materialHeight;
        this.items = items;
        this.materialWidth = 0;

        for (Item item : items)
        {
            this.materialWidth += item.getColumns();
        }
    }

    @Override
    public StockingProblemIndividual getNewIndividual()
    {
        return new StockingProblemIndividual(this, items.size() - 1);
    }

    public void setNumColsPer(double numColsPer) {
        this.NumColsPer = numColsPer;
    }

    public void setMaxSizePer(double maxSizePer) {
        this.MaxSizePer = maxSizePer;
    }

    public int getMaterialHeight() {
        return materialHeight;
    }

    public int getMaterialWidth() {
        return materialWidth;
    }

    public double getNumColsPer() {
        return NumColsPer;
    }

    public double getMaxSizePer() {
        return MaxSizePer;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Raw material height: ")
                .append(materialHeight);
        sb.append("\nNumber of items: ").append(items.size());

        sb.append("\n\nItems: \n");
        for (Item item : items) {
            sb.append("\nPiece ").append(item.getId()).append("-").append(item.getRepresentation())
                    .append(item);
        }
        return sb.toString();
    }

    public static StockingProblem buildProblem(File file) throws IOException {

        java.util.Scanner f = new java.util.Scanner(file);
        int materialHeight = f.nextInt();
        int numberOfItems = f.nextInt();
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < numberOfItems; i++) {
            int itemLines = f.nextInt();
            int itemColumns = f.nextInt();
            if (itemLines > materialHeight) {
                throw new RuntimeException("Invalid item, cannot be fitted to specified material");
            }
            int[][] matrix = new int[itemLines][itemColumns];
            for (int j = 0; j < itemLines; j++) {
                for (int k = 0; k < itemColumns; k++) {
                    matrix[j][k] = f.nextInt();
                }
            }
            items.add(new Item(i, matrix));
        }
        return new StockingProblem(materialHeight, items);
    }
}
