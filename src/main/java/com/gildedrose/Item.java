package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public static Item createItem(String name, int sellIn, int quality) {

        switch (name) {
            case "Aged Brie":
                return new AgedBrie(sellIn,quality);
            case "Backstage passes to a TAFKAL80ETC concert":
                return new BackStagePass(sellIn,quality);
            case "Sulfuras, Hand of Ragnaros":
                return new Sulfuras(sellIn,quality);
            case "Conjured":
                return new Conjured(sellIn,quality);
            default:
                return new Item(name, sellIn, quality);
        }

    }

    void doUpdateQuality() {
        if (quality > 0) {
            quality--;
        }
        sellIn--;

        if (sellIn < 0) {
            if (quality > 0) {
                quality--;
            }
        }
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
