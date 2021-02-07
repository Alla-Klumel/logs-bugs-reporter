package telran.logs.bugs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.logs.bugs.jpa.entities.Bug;

public interface BugRepo extends JpaRepository<Bug, Long> {

}
