package com.java.redis.repository;


import com.java.redis.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Repository
public class ProductDao {

    public static final String HASH_KEY = "user-logs";

    @Autowired
    private RedisTemplate template;

    public Product save(Product product) {
        Random rd = new Random();
        Integer idRandom = rd.nextInt(Integer.MAX_VALUE);
        product.setId(idRandom);
        product.setTimeCreate(new Date());
        template.opsForHash().put(HASH_KEY, product.getId(), product);
        return product;
    }

    public List<Product> findAll() {
        List<Product> products = template.opsForHash().values(HASH_KEY);
        if (products.size() > 0)
            return products
                    .stream()
                    .sorted((p1, p2) -> p1.getTimeCreate().compareTo(p2.getTimeCreate()))
                    .collect(Collectors.toList());
        else
            return products;
    }

    public Product findProductById(int id) {
        return (Product) template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteProduct(int id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "product removed !!";
    }
}
