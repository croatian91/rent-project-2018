package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.Category;
import fr.dauphine.rentproject2018.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Collection<Category> findAll() {
        return categoryRepository.findAll();
    }
}
