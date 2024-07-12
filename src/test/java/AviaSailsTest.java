import org.aviaSails.AviaSails;
import org.aviaSails.Ticket;
import org.aviaSails.TicketTimeComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AviaSailsTest {
    AviaSails aviaSails = new AviaSails();
    TicketTimeComparator ticketTimeComparator = new TicketTimeComparator();


    Ticket ticket1 = new Ticket("Сызрань", "Караганда", 10000, 11_00, 15_00);
    Ticket ticket2 = new Ticket("Бухарест", "Куала-Лумпур", 100000, 06_00, 23_00);
    Ticket ticket3 = new Ticket("Кудыкина-Гора", "Вникуда", 100, 01_00, 02_00);
    Ticket ticket4 = new Ticket("Берёзовский", "Екатеринбург", 10000, 13_00, 14_00);
    Ticket ticket5 = new Ticket("Берёзовский", "Екатеринбург", 3000, 13_00, 14_00);

    @Test
    public void shouldAddAndFindTickets() {
        aviaSails.add(ticket1);
        aviaSails.add(ticket2);
        aviaSails.add(ticket3);
        aviaSails.add(ticket4);

        Ticket[] expected = {ticket1, ticket2, ticket3, ticket4};
        Ticket[] actual = aviaSails.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test

    public void ticketNotFound() {
        aviaSails.add(ticket1);
        aviaSails.add(ticket2);
        aviaSails.add(ticket3);
        aviaSails.add(ticket4);

        Ticket[] expected = {};
        Ticket[] actual = aviaSails.search("Не аул", "Аул");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ticketFound() {
        aviaSails.add(ticket1);
        aviaSails.add(ticket2);
        aviaSails.add(ticket3);
        aviaSails.add(ticket4);

        Ticket[] expected = {ticket4};
        Ticket[] actual = aviaSails.search("Берёзовский", "Екатеринбург");

        Assertions.assertArrayEquals(expected, actual);
    }


    @Test
    public void ticketPriceCompareToPriceLower() {
        assertEquals(-1, ticket1.compareTo(ticket2));
    }

    @Test
    public void ticketPriceCompareToPriceHigher() {
        assertEquals(1, ticket1.compareTo(ticket3));
    }

    @Test
    public void ticketPriceCompareToPriceEquals() {
        assertEquals(0, ticket1.compareTo(ticket4));
    }

    @Test
    public void ticketTimeComparator() {
        aviaSails.add(ticket1);
        aviaSails.add(ticket2);
        aviaSails.add(ticket3);
        aviaSails.add(ticket4);
        aviaSails.add(ticket5);

        Ticket[] tickets = aviaSails.findAll();
        Arrays.sort(tickets, ticketTimeComparator);

        Ticket[] expected = {ticket3, ticket4, ticket5, ticket1, ticket2};
        Ticket[] actual = tickets;

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test

    public void searchAndSortTest() {
        Ticket ticket1 = new Ticket("A", "B", 10000, 11_00, 15_00);
        Ticket ticket2 = new Ticket("A", "B", 100000, 06_00, 23_00);
        Ticket ticket3 = new Ticket("Кудыкина-Гора", "Вникуда", 100, 01_00, 02_00);
        TicketTimeComparator timeComparator = new TicketTimeComparator();
        aviaSails.add(ticket1);
        aviaSails.add(ticket2);
        aviaSails.add(ticket3);


        Ticket[] expected = {ticket1, ticket2};
        Ticket[] actual = aviaSails.searchAndSortBy("A", "B", timeComparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test

    public void shouldReturnPrice() {
        Ticket ticket3 = new Ticket("Кудыкина-Гора", "Вникуда", 100, 01_00, 02_00);

        int expected = 100;
        int actual = ticket3.getPrice();

        assertEquals(expected, actual);
    }

    @Test
    public void testTicketEqualsAndHashCode() {
        // Создаём 2 билета с одинаковыми атрибутами
        Ticket ticket1 = new Ticket("A", "B", 100, 10, 20);
        Ticket ticket2 = new Ticket("A", "B", 100, 10, 20);

        // Сравниваем 2 билета
        assertTrue(ticket1.equals(ticket2));


        assertEquals(ticket1.hashCode(), ticket2.hashCode());
    }

    @Test
    public void testEqualsNullCheck() {
        Ticket ticket = new Ticket("A", "B", 100, 10, 20);
        assertFalse(ticket.equals(null));
    }


}

