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
        boolean isExpired = item.sellIn < 1;
        int degradeValue = determineDegradeValue(item, isExpired);
        boolean doesDegrade = !item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES) && !item.name.equals(SULFURAS);

        if (doesDegrade) {
            adjustQuality(item, degradeValue);
        }
        if (item.name.equals(AGED_BRIE)) {
            adjustQuality(item, 1);
        }
        if (item.name.equals(BACKSTAGE_PASSES)) {
            updateBackstageQuality(item, isExpired);
        }
        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }
        if (isExpired) {
            if (item.name.equals(AGED_BRIE)) {
                adjustQuality(item, 1);
            }
        }
    }

    private void updateBackstageQuality(Item item, boolean isExpired) {
        adjustQuality(item, 1);
        if (item.sellIn < 11) {
            adjustQuality(item, 1);
        }
        if (item.sellIn < 6) {
            adjustQuality(item, 1);
        }
        if (isExpired) {
            item.quality = item.quality - item.quality;
        }
    }

    private int determineDegradeValue(Item item, boolean isExpired){
        int initialDegradeValue = item.name.equals(CONJURED) ? -2: -1;
        return isExpired ? initialDegradeValue * 2 : initialDegradeValue;
    }

    private void adjustQuality(Item item, int adjustment) {
        int newQuality = item.quality + adjustment;
        if(newQuality <= 50 && newQuality >= 0){
            item.quality = newQuality;
        }
    }
}
