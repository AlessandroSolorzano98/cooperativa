package com.empresa.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.empresa.dao.PersonaDao;
import com.empresa.entities.Beneficiario;
import com.empresa.entities.Cliente;
import com.empresa.entities.Direccion;
import com.empresa.entities.Persona;
import com.empresa.entities.PersonaGenerica;
import com.empresa.entities.Referencia;
import com.empresa.entities.Telefono;

@ManagedBean
@ViewScoped
public class RegistroMb {
	private Persona persona;
	private List<Cliente> cliList;
	private Cliente cliente;
	private List<Direccion> listDirCliente;
	private List<Telefono> telLitCliente;
	private List<PersonaGenerica> referencia;
	private List<PersonaGenerica> beneficiario;
	private List<Referencia> refListRefCliente;
	private List<Beneficiario> refListBenCliente;
	private PersonaDao perDao;
	
	@PostConstruct
	public void init() {
	  persona = new Persona();
	  cliList = new ArrayList<Cliente>();
	  cliente = new Cliente();
	  listDirCliente = new ArrayList<Direccion>() ;
	  telLitCliente = new ArrayList<Telefono>();
	  referencia = new ArrayList<PersonaGenerica>();
	  beneficiario = new ArrayList<PersonaGenerica>();
	  refListRefCliente = new ArrayList<Referencia>();
	  refListBenCliente = new ArrayList<Beneficiario>();
	  perDao = new PersonaDao();
	}
	
	public void insertCliente() {
		cliente.setPersona(persona);
		cliList.add(cliente);
		persona.setClientes(cliList);
		for(Direccion dir:listDirCliente) {
			dir.setPersona(persona);
		}
		persona.setDireccions(listDirCliente);
		for(Telefono tel:telLitCliente) {
			tel.setPersona(persona);
		}
		
		persona.setTelefonos(telLitCliente);
		for(PersonaGenerica per: beneficiario) {
			Beneficiario ben = new Beneficiario();
			ben.setCliente(cliente);
			ben.setEdad(per.getEdad());
			ben.setParentesco(per.getParentesco());
			Persona perBen = new Persona();
			perBen.setNombres(per.getNombres());
			perBen.setApellidos(per.getApellidos());
			perBen.setNumeroDocumento(per.getNumeroDocumento());
			Persona perRespuesta = perDao.insertPersona(perBen);
			ben.setPersona(perRespuesta);
			ben.setPorcentaje(per.getPorcentaje());
			refListBenCliente.add(ben);
		}
		
		persona.setBeneficiarios(refListBenCliente);
		for(PersonaGenerica perGen: referencia) {
			Referencia ref = new Referencia();
			ref.setCliente(cliente);
			Persona perBen = new Persona();
			perBen.setNombres(perGen.getNombres());
			perBen.setApellidos(perGen.getApellidos());
			perBen.setNumeroDocumento(perGen.getNumeroDocumento());
			Persona personaGeRespuesta = perDao.insertPersona(perBen);
			ref.setPersona(personaGeRespuesta);
			ref.setTiempoconocerse(perGen.getTiempoconocerse());
			refListRefCliente.add(ref);
		}
		
		persona.setReferencias(refListRefCliente);
		Persona clienteGuardado = perDao.insertPersona(persona);
		FacesMessage msg = new FacesMessage("persona guardada: "+clienteGuardado.getIdPersona());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public void addDireccion() {
		listDirCliente.add(new Direccion());
	}
	
	public void addTelefonoCliente() {
		telLitCliente.add(new Telefono());
	}
	
	public void addReferencia() {
		referencia.add(new PersonaGenerica());
	}
	
	public void addBeneficiario() {
		beneficiario.add(new PersonaGenerica());
	}
	
	
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public List<Cliente> getCliLit() {
		return cliList;
	}
	public void setCliLit(List<Cliente> cliLit) {
		this.cliList = cliLit;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<Direccion> getListDirCliente() {
		return listDirCliente;
	}
	public void setListDirCliente(List<Direccion> listDirCliente) {
		this.listDirCliente = listDirCliente;
	}
	public List<Telefono> getTelLitCliente() {
		return telLitCliente;
	}
	public void setTelLitCliente(List<Telefono> telLitCliente) {
		this.telLitCliente = telLitCliente;
	}
	public List<Referencia> getRefListRefCliente() {
		return refListRefCliente;
	}
	public void setRefListRefCliente(List<Referencia> refListRefCliente) {
		this.refListRefCliente = refListRefCliente;
	}
	public List<Beneficiario> getRefListBenCliente() {
		return refListBenCliente;
	}
	public void setRefListBenCliente(List<Beneficiario> refListBenCliente) {
		this.refListBenCliente = refListBenCliente;
	}

	public List<PersonaGenerica> getReferencia() {
		return referencia;
	}

	public void setReferencia(List<PersonaGenerica> referencia) {
		this.referencia = referencia;
	}

	public List<PersonaGenerica> getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(List<PersonaGenerica> beneficiario) {
		this.beneficiario = beneficiario;
	}
	
	
	
}