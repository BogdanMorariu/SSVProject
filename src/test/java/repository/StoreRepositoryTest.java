package repository;

import model.Product;
import org.junit.Before;
import org.junit.Test;
import sun.plugin.perf.PluginRollup;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StoreRepositoryTest {

    StoreRepository productRepo;

    @Before
    public void setUp() throws Exception {
        productRepo = new StoreRepository();
    }

    @Test
    public void getAllProducts() {
    }

    @Test
    public void readFile() {
    }

    @Test
    public void addNewProduct() throws IOException {
        assertTrue(productRepo.getAllProducts().size()==0);
        productRepo.addNewProduct(new Product(77,"Test","Lactat",7));
        assertTrue(productRepo.getAllProducts().size()==1);
    }

    @Test
    public void getProductsCategory() {
    }

    @Test
    public void stockSituationProduct() {
    }

    @Test
    public void stockSituation() {
    }
}