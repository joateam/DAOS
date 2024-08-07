package com.daos.EstacionAR.Service;

import java.util.List;
import java.util.Optional;

import com.daos.EstacionAR.Entity.Recarga;

public interface IRecargaService {
	public List<Recarga> getALL();
	public List<Recarga> getByDni(Integer dni);
	public List<Recarga> getByPatente(String patente);
	public List<Recarga> getByNroComercio(Long nroComercio);
	public Recarga getById(Long id);
	public void Recargar(Recarga recarga);
	public void actualizarSaldo(Recarga recarga);
}
