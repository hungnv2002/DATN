package DrSport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  DrSport.entities.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
