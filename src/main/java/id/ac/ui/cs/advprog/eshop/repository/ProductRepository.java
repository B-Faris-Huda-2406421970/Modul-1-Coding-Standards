package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product){
        productData.add(product);
        return product;
    }

    public Product update(Product updateProduct){
        Product product = findById(updateProduct.getProductId());
        if (product == null) return null;

        product.setProductName(updateProduct.getProductName());
        product.setProductQuantity(updateProduct.getProductQuantity());
        return product;
    }

    public void delete(String productId){
        Iterator<Product> iterator = productData.iterator();
        while (iterator.hasNext()){
            Product product = iterator.next();
            if (product.getProductId().equals(productId)){
                iterator.remove();
                break;
            }
        }
    }

    public Iterator<Product> findAll(){
        return productData.iterator();
    }

    public Product findById(String productId){
        for (Product product : productData){
            if (product.getProductId().equals(productId)){
                return product;
            }
        }

        return null;
    }
}
