package com.products.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    // Butun productlari getirmek ucun servis
    @GetMapping("/products")
    public List<Product> list()
    {
        return service.listAll();
    }

    // tek productu getirmek ucun servis
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id)
    {
        try{
            Product product = service.get(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        }catch(NoSuchElementException e){
            return  new ResponseEntity<Product>(HttpStatus.NOT_FOUND);

        }
    }


    //product elave etmek ucun servis
    @PostMapping("/products")
    public void add(@RequestBody  Product product)
    {
        service.save(product);
    }


    // product update etmek ucun servis
    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@RequestBody  Product product,@PathVariable Integer id)
    {
        try{
            Product existProduct = service.get(id);
            service.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // productu silmek ucun servis
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id)
    {
        try{
            Product existProduct = service.get(id);
            service.delete(id);
            return new ResponseEntity<>("Product ugurla silindi",HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>("Bele product tapilamdi",HttpStatus.NOT_FOUND);
        }

    }


}
