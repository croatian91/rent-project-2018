package fr.dauphine.rentproject2018.service;

import fr.dauphine.rentproject2018.domain.Category;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface CategoryService {

    Collection<Category> findAll();
}
