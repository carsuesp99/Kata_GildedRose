class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE_PASSES = "Backstage passes";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String CONJURED = "Conjured item";

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }



    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            updateItemQuality(items[i]);
        }
    }

    private void updateItemQuality(Item item) {
        int degradeValue = item.name.equals(CONJURED) ? -2: -1;
        boolean doesDegrade = !item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES) && !item.name.equals(SULFURAS);

        if (doesDegrade) {
            adjustQuality(item, degradeValue);
        }
        if (item.name.equals(AGED_BRIE) || item.name.equals(BACKSTAGE_PASSES)) {
            adjustQuality(item, 1);
        }
        if (item.name.equals(BACKSTAGE_PASSES)) {
            if (item.sellIn < 11) {
                if (item.quality < 50) {
                    adjustQuality(item, 1);
                }
            }
            if (item.sellIn < 6) {
                adjustQuality(item, 1);
            }
        }

        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }

        if (item.sellIn < 0) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE_PASSES)) {
                    if (!item.name.equals(SULFURAS)) {
                        adjustQuality(item, degradeValue);
                    }
                } else {
                    item.quality = item.quality - item.quality;
                }
            } else {
                adjustQuality(item, 1);
            }
        }
    }

    private void adjustQuality(Item item, int adjustment) {
        int newQuality = item.quality + adjustment;
        if(newQuality <= 50 && newQuality >= 0){
            item.quality = newQuality;
        }
    }
}
