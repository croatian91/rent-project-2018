package fr.dauphine.rentproject2018.repository;

import fr.dauphine.rentproject2018.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    Product findById(int id);

    Page findAllByCategoryIdInAndRentalPointIdIn(Pageable pageable, Collection<Integer> categoryIds, Collection<Integer> rentalPointIds);
}
