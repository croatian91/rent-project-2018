package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ProductService {

    Product create(Product product);

    Product update(Product product);

    void delete(Product product);

    Product findOne(int id);

    Page findAll(Pageable pageable);

    Page findAllByCategoriesAndRentalPoints(Pageable pageable, Collection<Integer> categoryIds, Collection<Integer> rentalPointIds);
}
