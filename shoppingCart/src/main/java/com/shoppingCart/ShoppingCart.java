package com.shoppingCart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
    
    private final int scale = 2;
    
    private class Items {
        private ItemType itemType;
        private Integer quantity;
        
        private Items(ItemType itemType, Integer quantity) {
            this.setItemType(itemType);
            this.setQuantity(quantity);
        }

        public ItemType getItemType() {
            return itemType;
        }

        public void setItemType(ItemType itemType) {
            this.itemType = itemType;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    
    private Map<ItemType, Double> itemPriceDict;
    private List<Items> items;

    public ShoppingCart(Map<ItemType, Double> itemPriceDict) {
        this.itemPriceDict = itemPriceDict;
        items = new LinkedList<Items>();
    }

    public double computeBill() {
        double total = 0.00;
        Iterator<Items> itemIterator = this.items.iterator();
        while (itemIterator.hasNext()) {
            Items item = itemIterator.next();
            total += itemPriceDict.get(item.getItemType()) * item.getQuantity();
        }
        return new BigDecimal(total).setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    public ShoppingCart add(ItemType itemType, int quantity) {
        this.items.add(new Items(itemType, quantity));
        return this;
    }

    public int noOfItems() {
        int total = 0;
        Iterator<Items> itemIterator = this.items.iterator();
        while (itemIterator.hasNext()) {
            Items item = itemIterator.next();
            total += item.getQuantity();
        }
        return total;
    }

    public double getItemPrice(ItemType itemType) {
        return this.itemPriceDict.get(itemType);
    }

    public int noOfItemsByType(ItemType itemType) {
        int quantity = 0;
        Iterator<Items> itemIterator = this.items.iterator();
        while (itemIterator.hasNext()) {
            Items item = itemIterator.next();
            if(item.getItemType().equals(itemType))
                quantity += item.getQuantity();
        }
        return quantity;
    }

}
