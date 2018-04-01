package fr.dauphine.rentproject2018.web;

import fr.dauphine.rentproject2018.domain.Cart;
import fr.dauphine.rentproject2018.domain.Category;
import fr.dauphine.rentproject2018.domain.Product;
import fr.dauphine.rentproject2018.domain.RentalPoint;
import fr.dauphine.rentproject2018.service.CategoryService;
import fr.dauphine.rentproject2018.service.ProductService;
import fr.dauphine.rentproject2018.service.RentalPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("product")
@Scope("session")
public class ProductController {

    private final CategoryService categoryService;

    private final RentalPointService rentalPointService;

    private final ProductService productService;

    private final Cart cart;

    @Autowired
    public ProductController(CategoryService categoryService, RentalPointService rentalPointService, ProductService productService, Cart cart) {
        this.categoryService = categoryService;
        this.rentalPointService = rentalPointService;
        this.productService = productService;
        this.cart = cart;
    }

    @RequestMapping("/{productID}")
    public String previewProduct(@PathVariable(name = "productID") int productID, Model model) {
        Product current = productService.findOne(productID);

        model.addAttribute("product", current);
        model.addAttribute("isInCart", cart.getBookingWrappers().stream().anyMatch(bookingWrapper -> bookingWrapper.getProduct().getId() == productID));

        return "product/preview";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@PageableDefault(size = 5) Pageable pageable,
                       @RequestParam(value = "categoryIds", required = false) Collection<Integer> categoryIds,
                       @RequestParam(value = "rentalPointIds", required = false) Collection<Integer> rentalPointIds, Model model) {
        categoryIds = Optional.ofNullable(categoryIds).orElse(categoryService.findAll().stream().map(Category::getId).collect(Collectors.toList()));
        rentalPointIds = Optional.ofNullable(rentalPointIds).orElse(rentalPointService.findAll().stream().map(RentalPoint::getId).collect(Collectors.toList()));

        Page products = productService.findAllByCategoriesAndRentalPoints(pageable, categoryIds, rentalPointIds);

        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("rentalPoints", rentalPointService.findAll());
        model.addAttribute("products", products);

        model.addAttribute("totalPages", products.getTotalPages() == 0 ? 0 : products.getTotalPages() - 1);
        model.addAttribute("current", pageable.getPageNumber());
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());

        return "product/list";
    }

    @RequestMapping(value = "{productID}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable int productID, Model model) {
        model.addAttribute("product", productService.findOne(productID));

        return "product/edit";
    }
}
