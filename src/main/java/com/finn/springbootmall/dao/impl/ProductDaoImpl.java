package com.finn.springbootmall.dao.impl;

import com.finn.springbootmall.constant.ProductCategory;
import com.finn.springbootmall.dao.ProductDao;
import com.finn.springbootmall.dao.ProductQueryParams;
import com.finn.springbootmall.dto.ProductRequest;
import com.finn.springbootmall.model.Product;
import com.finn.springbootmall.rowmapper.ProductRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Component
public class ProductDaoImpl implements ProductDao {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Integer countProduct(ProductQueryParams params) {
        String sql = "SELECT count(*) FROM product WHERE 1=1 ";

        Map<String, Object> map = new HashMap<>();
        // 查詢條件
        sql = addFilteringSql(sql,map,params);

        Integer total = jdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public List<Product> getProducts(ProductQueryParams params) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description," +
                " created_date, last_modified_date FROM product WHERE  1 = 1";

        Map<String, Object> map = new HashMap<>();
        // 查詢條件
        sql = addFilteringSql(sql,map,params);

        //排序
        sql = sql + " ORDER BY " + params.getOrderBy() + " " + params.getSort();

        //分頁
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", +params.getLimit());
        map.put("offset", +params.getOffset());


        List<Product> productList = jdbcTemplate.query(sql, map, new ProductRowmapper());
        return productList;

    }

    @Override
    public Product getProductById(Integer productId) {
        String sql = "SELECT product_id, product_name, category, image_url, price, stock, description," +
                " created_date, last_modified_date FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        List<Product> productList = jdbcTemplate.query(sql, map, new ProductRowmapper());

        if (productList.size() > 0) {
            return productList.get(0);
        } else {

            return null;
        }
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        String sql = "INSERT INTO product(product_name, category, image_url, price, stock, " +
                "description, created_date, last_modified_date)" +
                "VALUES (:productName,:category, :imageUrl,:price,:stock,:description," +
                ":createdDate, :lastModifiedDate)";

        Map<String, Object> map = new HashMap<>();
        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int productId = keyHolder.getKey().intValue();


        return productId;
    }

    @Override
    public void updateProduct(Integer productId, ProductRequest productRequest) {
        String sql = "UPDATE product SET product_name = :productName, category = :category, image_url = :imageUrl, price = :price, stock = :stock, " +
                "description = :description, last_modified_date = :lastModifiedDate " +
                "WHERE product_id = :productId";


        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        map.put("productName", productRequest.getProductName());
        map.put("category", productRequest.getCategory().toString());
        map.put("imageUrl", productRequest.getImageUrl());
        map.put("price", productRequest.getPrice());
        map.put("stock", productRequest.getStock());
        map.put("description", productRequest.getDescription());

        map.put("lastModifiedDate", new Date());

        jdbcTemplate.update(sql, map);

    }

    @Override
    public void updateStock(Integer productId, Integer stock) {
        String sql = "UPDATE product SET stock = :stock, last_modified_date = :lastModifiedDate " +
                "WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        map.put("stock", stock);
        map.put("lastModifiedDate", new Date() );

        jdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteProductById(Integer productId) {
        String sql = "DELETE FROM product WHERE product_id = :productId";

        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);

        jdbcTemplate.update(sql, map);
    }

    private String addFilteringSql(String sql ,Map<String,Object>map, ProductQueryParams params){

        if (params.getCategory() != null) {
            sql = sql + " AND category= :category";
            map.put("category", params.getCategory().name());
        }

        if (params.getSearch() != null) {
            sql = sql + " AND product_name LIKE :search";
            map.put("search", "%" + params.getSearch() + "%");
        }
        return sql;
    }
}
