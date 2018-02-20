package com.shoppingCart;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

public class ShoppingCartTest extends TestCase {
    
    private static final int FIVE_QUANTITY = 5;
    private static final int EIGHT_QUANTITY = 8;
    private static final int THREE_QUANTITY = 3;
    private static final int TWO_QUANTITY = 2;
    private static final int FOUR_QUANTITY = 4;
    private static Map <ItemType, Double> ITEM_PRICE;
    double DOVE_UNIT_PRICE = 39.99;
    double AXE_UNIT_PRICE = 99.99;
    private static final double TAX_AMOUNT = 12.5;
    

    @Override
    protected void setUp() throws Exception {
        ITEM_PRICE = new HashMap<ItemType, Double>();
        ITEM_PRICE.put(ItemType.DOVE, DOVE_UNIT_PRICE);
        ITEM_PRICE.put(ItemType.AXE, AXE_UNIT_PRICE);
    }

    public void testShoppingCartWithFIVEDoveSoap() {
        ShoppingCart shoppingCart = shoppingCartWith(ItemType.DOVE, FIVE_QUANTITY);
        assertEquals(199.95, shoppingCart.computeBill());
        assertEquals(FIVE_QUANTITY, shoppingCart.noOfItems());
        assertEquals(DOVE_UNIT_PRICE, shoppingCart.getItemPrice(ItemType.DOVE));
    }
    
    public void testShoppingCartFirstFiveThenEightDoveSoap() {
        ShoppingCart shoppingCart =new ShoppingCart(ITEM_PRICE);
        shoppingCart.add(ItemType.DOVE, FIVE_QUANTITY);
        shoppingCart.add(ItemType.DOVE, THREE_QUANTITY);
        assertEquals(EIGHT_QUANTITY, shoppingCart.noOfItems());
        assertEquals(DOVE_UNIT_PRICE, shoppingCart.getItemPrice(ItemType.DOVE));
        assertEquals(319.92, shoppingCart.computeBill());
    }
    
    public void testShoppingCartFirstTwoDoveThenTwoAxe() {
        ShoppingCart shoppingCart =new ShoppingCart(ITEM_PRICE);
        TaxCalculator taxCalculator = new TaxCalculator(TAX_AMOUNT);
        shoppingCart.add(ItemType.DOVE, TWO_QUANTITY);
        shoppingCart.add(ItemType.AXE, TWO_QUANTITY);
        assertEquals(FOUR_QUANTITY, shoppingCart.noOfItems());
        assertEquals(TWO_QUANTITY, shoppingCart.noOfItemsByType(ItemType.DOVE));
        assertEquals(TWO_QUANTITY, shoppingCart.noOfItemsByType(ItemType.AXE));
        assertEquals(35.0,taxCalculator.calculateTax(shoppingCart));
        assertEquals(314.96, shoppingCart.computeBill() + taxCalculator.calculateTax(shoppingCart));
    }

    private ShoppingCart shoppingCartWith(ItemType itemType, int quantity) {
        return new ShoppingCart(ITEM_PRICE).add(itemType, quantity);
    }
}
