package Com.Example.Demo.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Com.Example.Demo.Entity.FileData;

@Repository
public interface FileRepository extends JpaRepository<FileData, Integer> {

}
