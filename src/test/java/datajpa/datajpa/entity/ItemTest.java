package datajpa.datajpa.entity;

import datajpa.datajpa.repository.ItemRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemTest {

    @Autowired
    ItemRepo itemRepo;

    @Test
    public void save() {
        Item item = new Item("A");
        itemRepo.save(item);

    }



}