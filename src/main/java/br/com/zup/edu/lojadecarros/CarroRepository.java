package br.com.zup.edu.lojadecarros;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Long> {

    boolean existsByChassi(String chassi);
}
