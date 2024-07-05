package com.daos.EstacionAR.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.daos.EstacionAR.Entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface IUsuarioParaRecargaRepository extends JpaRepository<User, Integer> {
	
	@Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.saldo = u.saldo + :monto WHERE u.id = :usuarioId")
    void actualizarSaldo(Integer usuarioId, Double monto);

}
