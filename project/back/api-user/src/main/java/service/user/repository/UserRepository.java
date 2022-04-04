package service.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.user.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer> {


}
