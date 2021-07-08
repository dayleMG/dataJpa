package datajpa.datajpa.repository;

import datajpa.datajpa.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface TeamRepo extends JpaRepository<Team, Long> {

}
