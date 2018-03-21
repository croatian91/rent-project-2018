package fr.dauphine.rentproject2018.repository;

import fr.dauphine.rentproject2018.domain.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    Product findById(int id);
}
