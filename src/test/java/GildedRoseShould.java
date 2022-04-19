import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseShould {

        /*
    Sellin es igual al numero de dias que hay para vender el item.
    Quality indica el valor del item
    Al final del día disminuyen ambos At the end of each day our system lowers both values for every item
    Cuando la fecha de caducidad ha pasado, quality se degrada por 2
    Quality nunca es negativo y nunca es mayor que 50
    "Aged Brie" aumenta en quality cuanto mas antiguo es
    "Sulfuras" item legendario, nunca se debe vender o disminuira su quality.
    "Backstage passes" aumenta quality cuando Sellin se acerca
    Quality x2 cuando quedan 10 o menos días, x3 cuando quedan 5 o menos, cae a 0
    después del concierto.

    "Conjured" se degrada en "quality" dos veces mas rápido que el resto
    Quality de sulfuras es 80 por ser legendario, nunca se altera su valor.
     */

    GildedRose gildedRose;

    @Test
    public void should_return_the_name() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals("foo", gildedRose.items[0].name);
    }

    @Test
    public void quality_value_lows_when_update(){
        Item[] items = new Item[] { new Item("foo", 2, 1) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(0, gildedRose.items[0].quality);
    }

    @Test
    public void sellin_value_lows_when_update(){
        Item[] items = new Item[] { new Item("foo", 2, 1) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(1, gildedRose.items[0].sellIn);
    }

    @Test
    public void quality_should_degrade_twice_as_fast_if_sell_by_date_passed(){
        Item[] items = new Item[] { new Item("foo", -1, 2) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(0, gildedRose.items[0].quality);
    }

    @Test
    public void quality_should_never_be_negative(){
        Item[] items = new Item[] { new Item("foo", -1, 1) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(0, gildedRose.items[0].quality);
    }

    @Test
    public void quality_of_legendary_items_should_never_decrease_or_increase(){
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 35) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(35, gildedRose.items[0].quality);
    }

    @Test
    public void conjured_items_should_decrease_twice_fast(){
        Item[] items = new Item[] { new Item("Conjured item", 6, 24) };
        gildedRose = new GildedRose(items);
        gildedRose.updateQuality();
        assertEquals(22, gildedRose.items[0].quality);
    }

}
