package com.example.techub.techubStore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.techub.techubStore.model.ClientTechub;

public interface ClientTechubRepository extends JpaRepository<ClientTechub, Integer > {

    @Query(value = " select * from client c where c.clientName like '%:clientName%' ", nativeQuery = true)
    List<ClientTechub> encontrarPorNome( @Param("clientName") String clientName );

    /*@Query(" delete from Cliente c where c.nome =:nome ")
    @Modifying
    void deleteByNome(String nome);

    boolean existsByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
    ClientTechub findClienteFetchPedidos( @Param("id") Integer id );*/


}