package telran.logs.bugs.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import telran.logs.bugs.jpa.entities.Artifact;

public interface ArtifactRepo extends JpaRepository<Artifact, String> {

}
