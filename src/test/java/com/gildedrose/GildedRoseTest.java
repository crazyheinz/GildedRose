package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void testFrameworkWorks() {
        Item[] items = new Item[] {Item.createItem("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    //Once the sell by date has passed, Quality degrades twice as fast
    void whenSellInHasPassedQualityShouldDegradedTwiceAsFast() {
        Item[] items = new Item[] {Item.createItem("foo", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(8, app.items[0].quality);
    }

    @Test
    //The Quality of an item is never negative
    void theQualityOfanItemIsNeverNegative() {
        Item[] items = new Item[] {Item.createItem("foo", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void agedBrieIncreasesInQuality() {
        Item[] items = new Item[] { new AgedBrie( 50, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(44, app.items[0].quality);
    }

    @Test
    void QualityCanNeverBeMoreThen50ExceptSulfuras() {
        Item[] items = new Item[] {Item.createItem("Aged Brie", 50, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void SulfurasNeverHasToBeSoldOrDecreasesInQuality() {
        Item[] items = new Item[] {Item.createItem("Sulfuras, Hand of Ragnaros", 50, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
        assertEquals(50, app.items[0].sellIn);
    }

    //"Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
    @Test
    void BackstagePassesIncreaseInQualityAsSellInValueApproaches() {
        Item[] items = new Item[] {Item.createItem("Backstage passes to a TAFKAL80ETC concert", 10, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        app.updateQuality();
        assertEquals(20, app.items[0].quality);
        assertEquals(5, app.items[0].sellIn);
    }

    @Test
    void BackstagePassesGoToZeroQualityAfterConcert() {
        Item[] items = new Item[] {Item.createItem("Backstage passes to a TAFKAL80ETC concert", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }



}
