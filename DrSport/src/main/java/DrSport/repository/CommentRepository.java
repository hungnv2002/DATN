package DrSport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  DrSport.entities.Comment;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
