package com.daos.EstacionAR.Service;

import java.util.List;


import com.daos.EstacionAR.Entity.Recarga;

public interface IRecargaService {
	public List<Recarga> getALL();
	public List<Recarga> getByDni(Integer dni);
	public List<Recarga> getByPatente(String patente);
	public List<Recarga> getByNroComercio(Long nroComercio);
	public void Recargar(Recarga recarga);
}
