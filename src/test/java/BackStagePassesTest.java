
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BackStagePassesTest {

    GildedRose gildedRose;

    @Test
    public void quality_should_increase_when_update(){
        Item[] items = new Item[] { new Item("Backstage passes", 14, 35) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(36, gildedRose.items[0].quality);
    }

    @Test
    public void quality_should_increase_twice_when_sellin_is_equal_or_lower_than_10(){
        Item[] items = new Item[] { new Item("Backstage passes", 10, 35) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(37, gildedRose.items[0].quality);
    }

    @Test
    public void quality_should_increase_by_three_when_sellin_is_equal_or_lower_than_5(){
        Item[] items = new Item[] { new Item("Backstage passes", 4, 35) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(38, gildedRose.items[0].quality);
    }

    @Test
    public void quality_should_be_zero_when_sellin_is_zero_or_lower(){
        Item[] items = new Item[] { new Item("Backstage passes", -2, 35) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(0, gildedRose.items[0].quality);
    }

    @Test
    public void quality_should_not_be_higher_than_fifty(){
        Item[] items = new Item[] { new Item("Backstage passes", 4, 48) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(50, gildedRose.items[0].quality);
    }

}
