package fr.dauphine.rentproject2018.repository;

import fr.dauphine.rentproject2018.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
