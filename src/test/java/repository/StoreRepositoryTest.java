package repository;

import model.Product;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StoreRepositoryTest {

    StoreRepository productRepo;
    int uniqueCode;

    @Before
    public void setUp() {
        productRepo = new StoreRepository("testFile");
        try {
            productRepo.readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        uniqueCode = 10;
    }

    @Test
    public void addNewProductTC1() throws IOException {
        int size = productRepo.getAllProducts().size();
        assertTrue(productRepo.getAllProducts().size()==size);
        productRepo.addNewProduct(new Product(++uniqueCode,"milk","lactate",1));
        assertTrue(productRepo.getAllProducts().size()==size+1);
    }

    @Test
    public void addNewProductTC2() throws IOException {
        int size = productRepo.getAllProducts().size();
        assertTrue(productRepo.getAllProducts().size()==size);
        productRepo.addNewProduct(new Product(1,"9","",1));
        assertTrue(productRepo.getAllProducts().size()==size+1);
    }

    @Test
    public void addNewProductTC3() throws IOException {
        int size = productRepo.getAllProducts().size();
        assertTrue(productRepo.getAllProducts().size()==size);
        productRepo.addNewProduct(new Product(Integer.MAX_VALUE,"Z","abc",Integer.MAX_VALUE));
        assertTrue(productRepo.getAllProducts().size()==size+1);
    }

    @Test
    public void addNewProductTC4() throws IOException {
        int size = productRepo.getAllProducts().size();
        assertTrue(productRepo.getAllProducts().size()==size);
        productRepo.addNewProduct(new Product(Integer.MAX_VALUE-1,"milk","lactate",Integer.MAX_VALUE-1));
        assertTrue(productRepo.getAllProducts().size()==size+1);
    }

    @Test
    public void addNewProductTC5() throws IOException {
        int size = productRepo.getAllProducts().size();
        assertTrue(productRepo.getAllProducts().size()==size);
        productRepo.addNewProduct(new Product(-1,"milk","lactate",1));
        assertTrue(productRepo.getAllProducts().size()==size);
    }

    @Test
    public void addNewProductTC6() throws IOException {
        int size = productRepo.getAllProducts().size();
        assertTrue(productRepo.getAllProducts().size()==size);
        productRepo.addNewProduct(new Product(Integer.MAX_VALUE+1,"milk","lactate",1));
        assertTrue(productRepo.getAllProducts().size()==size);
    }

    @Test
    public void addNewProductTC7() throws IOException {
        int size = productRepo.getAllProducts().size();
        assertTrue(productRepo.getAllProducts().size()==size);
        productRepo.addNewProduct(new Product(++uniqueCode,"a@","lactate",1));
        assertTrue(productRepo.getAllProducts().size()==size);
    }

    @Test
    public void addNewProductTC8() throws IOException {
        int size = productRepo.getAllProducts().size();
        assertTrue(productRepo.getAllProducts().size()==size);
        productRepo.addNewProduct(new Product(++uniqueCode,"","lactate",1));
        assertTrue(productRepo.getAllProducts().size()==size);
    }

    @Test
    public void addNewProductTC9() throws IOException {
        int size = productRepo.getAllProducts().size();
        assertTrue(productRepo.getAllProducts().size()==size);
        productRepo.addNewProduct(new Product(++uniqueCode,"milk","lactate",-1));
        assertTrue(productRepo.getAllProducts().size()==size);
    }

    @Test
    public void addNewProductTC10() throws IOException {
        int size = productRepo.getAllProducts().size();
        assertTrue(productRepo.getAllProducts().size()==size);
        productRepo.addNewProduct(new Product(++uniqueCode,"milk","lactate",Integer.MAX_VALUE+1));
        assertTrue(productRepo.getAllProducts().size()==size);
    }

    @Test
    public void addNewProductTC11() throws IOException {
        int size = productRepo.getAllProducts().size();
        assertTrue(productRepo.getAllProducts().size()==size);
        productRepo.addNewProduct(new Product(0,"milk","lactate",1));
        assertTrue(productRepo.getAllProducts().size()==size);
    }

    @Test
    public void addNewProductTC12() throws IOException {
        assertTrue(productRepo.getAllProducts().size()==0);
        productRepo.addNewProduct(new Product(7,"test","test",7));
        productRepo.addNewProduct(new Product(7,"milk","lactate",1));
        assertTrue(productRepo.getAllProducts().size()==1);
        productRepo.clearFile();
    }

    @Test
    public void testGetProductsByCategory1() throws IOException {
        StoreRepository testRepo = new StoreRepository("testFile");
        testRepo.addNewProduct(new Product(101,"dummy","test1",1));
        testRepo.addNewProduct(new Product(102,"dummy","test1",1));
        testRepo.addNewProduct(new Product(103,"dummy","test2",1));
        List<Product> testList = testRepo.getProductsCategory("test1");
        assertEquals(2,testList.size());
        testRepo.clearFile();
    }

    @Test
    public void testGetProductsByCategory2() throws IOException {
        StoreRepository testRepo = new StoreRepository("testFile");
        List<Product> testList = new ArrayList<Product>();
        testList = testRepo.getProductsCategory("test1");
        assertEquals(0,testList.size());
        testRepo.clearFile();
    }

    @Test
    public void testGetProductsByCategoryTC1() throws IOException {
        StoreRepository testRepo = new StoreRepository("testFile");
        testRepo.addNewProduct(new Product(101,"dummy","milk",1));
        List<Product> testList = testRepo.getProductsCategory("milk");
        assertEquals(1,testList.size());
        testRepo.clearFile();
    }

    @Test
    public void testGetProductsByCategoryTC2() throws IOException {
        StoreRepository testRepo = new StoreRepository("testFile");
        List<Product> testList = testRepo.getProductsCategory("test");
        assertEquals(0,testList.size());
        testRepo.clearFile();
    }

    @Test
    public void testGetProductsByCategoryTC3() throws IOException {
        StoreRepository testRepo = new StoreRepository("testFile");
        testRepo.addNewProduct(new Product(101,"dummy","milk",1));
        List<Product> testList = testRepo.getProductsCategory("meat");
        assertEquals(0,testList.size());
        testRepo.clearFile();
    }

    @Test
    public void testGetProductsByCategoryTC4() throws IOException {
        StoreRepository testRepo = new StoreRepository("testFile");
        testRepo.addNewProduct(new Product(101,"dummy","milk",1));
        testRepo.addNewProduct(new Product(102,"dummy","meat",1));
        List<Product> testList = testRepo.getProductsCategory("meat");
        assertEquals(1,testList.size());
        testRepo.clearFile();
    }

    @Test
    public void testStockSituation() throws IOException {
        StoreRepository testRepo = new StoreRepository("testFile");
        testRepo.addNewProduct(new Product(101,"dummy","milk",1));
        testRepo.addNewProduct(new Product(102,"dummy","meat",1));
        List<Product> testList = testRepo.stockSituation();
        assertEquals(2,testList.size());
        testRepo.clearFile();
    }

    @Test
    public void integrationTest() throws IOException{
        addNewProductTC1();
        testGetProductsByCategoryTC1();
        testStockSituation();
    }

    @Test
    public void incrementTestA() throws IOException {
        addNewProductTC1();
    }

    @Test
    public void incrementTestAB() throws IOException {
        addNewProductTC1();
        testGetProductsByCategoryTC1();
    }

    @Test
    public void incrementTestABC() throws IOException {
        addNewProductTC1();
        testGetProductsByCategoryTC1();
        testStockSituation();
    }

    @After
    public void destroy(){
        try {
            productRepo.clearFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}