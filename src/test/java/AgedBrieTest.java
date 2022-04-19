import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgedBrieTest {

    GildedRose gildedRose;

    @Test
    public void quality_should_never_be_higher_than_fifty(){
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(50, gildedRose.items[0].quality);
    }

    @Test
    public void should_increase_quality_the_older_it_gets(){
        Item[] items = new Item[] { new Item("Aged Brie", 10, 34) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(35, gildedRose.items[0].quality);
    }
}
