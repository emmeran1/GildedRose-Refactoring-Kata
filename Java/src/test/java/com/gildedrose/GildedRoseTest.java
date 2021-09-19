package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
	/*
    @Test
    void foo() {
    	//This test is expected to fail
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("fixme", app.items[0].name);
    }
    */
    @Test
    void sulfuras1() {
    	//Expected always 80 quality
        Item[] items = new Item[] { new Item(ItemName.SULFURAS, 1, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
        app.updateQuality();
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
    }
   
    @Test
    void brie1() {
        Item[] items = new Item[] { new Item(ItemName.AGED_BRIE, 2, 48) };
        GildedRose app = new GildedRose(items);
        //One day passed so +1 expected
        app.updateQuality();
        assertEquals(49, app.items[0].quality);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        //3 days passed, +3 expected, but this is >50, so actually 50 expected
        assertEquals(50, app.items[0].quality);
    }
    
    @Test
    void backstage1() {
        Item[] items = new Item[] { new Item(ItemName.BACKSTAGE_PASS, 6, 5) };
        GildedRose app = new GildedRose(items);
        //6 days left, so +2 expected
        app.updateQuality();
        assertEquals(7, app.items[0].quality);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        //3 days between 0-5, so 3*3=+9 expected
        assertEquals(16, app.items[0].quality);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        //After sell in date, 0 expected
        assertEquals(0, app.items[0].quality);
    }
    
    @Test
    void default1() {
        Item[] items = new Item[] { new Item("Random Item", 2, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        //2 days passed, so -2 expected
        assertEquals(18, app.items[0].quality);
        app.updateQuality();
        app.updateQuality();
        //1 day on sell indate, 1 day after sell in date, so -3 exptected
        assertEquals(15, app.items[0].quality);
    }
 	
    @Test
    void default2() {
        Item[] items = new Item[] { new Item("Random Item", 5, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        //2 days passed, but quality was 0, so 0 exptected
        assertEquals(0, app.items[0].quality);
    }
    
    @Test
    void default3() {
    	//One regular day, one day after sellInDate, so expected -3 for quality
        Item[] items = new Item[] { new Item("Random Item", 0, 5) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
    }
    
    @Test
    void conjured1() {
        Item[] items = new Item[] { new ConjuredItem("Random Item", 2, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        //Conjured and 2 days, so -2*2=-4 expected
        assertEquals(36, app.items[0].quality);
        app.updateQuality();
        app.updateQuality();
        //Conjured, 1 day on sell date, 1 day after so -6 exptected
        assertEquals(30, app.items[0].quality);
    }
}
