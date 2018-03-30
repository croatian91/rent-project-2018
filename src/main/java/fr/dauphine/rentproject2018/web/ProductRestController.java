package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.Product;
import fr.dauphine.rentproject2018.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/product/all")
    @ResponseStatus(HttpStatus.OK)
    public Page List(@PageableDefault(size = 5) Pageable pageable,
                     @RequestParam("categoryIds") Collection<Integer> categoryIds,
                     @RequestParam("rentalPointIds") Collection<Integer> rentalPointIds) {
        return productService.findAllByCategoriesAndRentalPoints(pageable, categoryIds, rentalPointIds);
    }

    @RequestMapping("/product/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @RequestMapping("/product/update")
    @ResponseStatus(HttpStatus.OK)
    public Product update(@ModelAttribute Product product) {
        return productService.update(product);
    }

    @RequestMapping("/product/findOne")
    @ResponseStatus(HttpStatus.OK)
    public Product findOne(@RequestBody Product product) {
        Product p = productService.findOne(product.getId());

        return p == null ? new Product() : product;
    }

    @RequestMapping("/product/{id}/delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        productService.delete(productService.findOne(id));
    }
}
