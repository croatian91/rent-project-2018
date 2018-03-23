package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.Cart;
import fr.dauphine.rentproject2018.domain.Product;
import fr.dauphine.rentproject2018.service.CategoryService;
import fr.dauphine.rentproject2018.service.ProductService;
import fr.dauphine.rentproject2018.service.RentalPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("product")
@Scope("session")
public class ProductController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RentalPointService rentalPointService;

    @Autowired
    private ProductService productService;

    @Autowired
    private Cart cart;

    @RequestMapping("/{productID}")
    public String previewProduct(@PathVariable(name = "productID") int productID, Model model) {
        Product current = productService.findOne(productID);

        model.addAttribute("product", current);
        model.addAttribute("products", cart.getProducts());

        System.out.println(cart.getProducts().size());

        return "product/preview";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@PageableDefault(size = 5) Pageable pageable, Model model) {
        Page products = productService.findAll(pageable);

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("rentalPoints", rentalPointService.findAll());
        model.addAttribute("products", products);

        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("current", pageable.getPageNumber());
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());

        return "product/list";
    }

    @RequestMapping(value = "{productID}//edit", method = RequestMethod.GET)
    public String edit(@PathVariable int productID, Model model) {
        model.addAttribute("product", productService.findOne(productID));

        return "product/edit";
    }
}
