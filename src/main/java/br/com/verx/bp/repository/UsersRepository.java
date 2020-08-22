package br.com.verx.bp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.verx.bp.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
