package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import java.util.Iterator;

public interface ProductRepository {
    public Product create(Product product);
    public Product update(Product updateProduct);
    public void delete(String productId);
    public Iterator<Product> findAll();
    public Product findById(String productId);
}
