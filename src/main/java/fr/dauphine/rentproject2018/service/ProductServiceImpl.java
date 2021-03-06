package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.Product;
import fr.dauphine.rentproject2018.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        Product current = productRepository.findById(product.getId());

        if (current != null) {
            current.setLabel(product.getLabel());
            current.setDailyPrice(product.getDailyPrice());
            current.setDailyForfeit(product.getDailyForfeit());
            current.setDescription(product.getDescription());
            current.setDefaults(product.getDefaults());

            productRepository.save(current);
        }

        return current;
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product findOne(int id) {
        return productRepository.findById(id);
    }

    @Override
    public Page findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page findAllByCategoriesAndRentalPoints(Pageable pageable, Collection<Integer> categoryIds, Collection<Integer> rentalPointIds) {
        return productRepository.findAllByCategoryIdInAndRentalPointIdIn(pageable, categoryIds, rentalPointIds);
    }
}
