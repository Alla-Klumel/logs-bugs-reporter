package telran.logs.bugs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.logs.bugs.jpa.entities.Programmer;

public interface ProgrammerRepo extends JpaRepository<Programmer, Long> {

}
