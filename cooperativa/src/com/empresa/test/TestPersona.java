package com.empresa.test;

import java.util.ArrayList;
import java.util.List;

import com.empresa.dao.PersonaDao;
import com.empresa.entities.Beneficiario;
import com.empresa.entities.Cliente;
import com.empresa.entities.Direccion;
import com.empresa.entities.Persona;
import com.empresa.entities.Referencia;
import com.empresa.entities.Telefono;

public class TestPersona 
{
	public static void main(String[] args) 
	{
		Persona persona = new Persona();//cliente
		persona.setNombres("miguel");
		persona.setApellidos("Escobar");
		persona.setNumeroDocumento("ñ2-hgj133d34hd4");
		
		List<Cliente> cliList = new ArrayList<Cliente>();
		Cliente cli = new Cliente();
		cli.setEsMiembro("1");
		cli.setPersona(persona);
		cliList.add(cli);
		
		List<Telefono> telList = new ArrayList<Telefono>();
		Telefono telCli = new Telefono();
		telCli.setTelefono("12354");
		telCli.setPersona(persona);
		telList.add(telCli);
		telCli = new Telefono();
		telCli.setTelefono("135436");
		telCli.setPersona(persona);
		telList.add(telCli);
		
		List<Direccion> dirList = new ArrayList<Direccion>();
		Direccion dic = new Direccion();
		dic.setDireccion("Direccion uno");
		dic.setPersona(persona);
		dirList.add(dic);
		
		persona.setDireccions(dirList);
		persona.setTelefonos(telList);
		persona.setClientes(cliList);
		
		//guardando persona
		PersonaDao perDao = new PersonaDao();
		Persona perRest = perDao.insertPersona(persona);
		System.out.println("id persona insertada :"+ perRest.getIdPersona());

		//beneficiarios 
		 List<Beneficiario> benList=new ArrayList<Beneficiario>(); 
		 Beneficiario ben = new Beneficiario();
		 Persona perBen= new Persona();
		 perBen.setNombres("Ben Nombre 1");
		 perBen.setApellidos("Benn Ape 1");
		 perBen.setNumeroDocumento("123-854697");
		 
		 Persona respuestaperBen =perDao.insertPersona( perBen);
		 ben.setPersona(respuestaperBen);
		 ben.setCliente(persona.getClientes().get(0));
		 ben.setParentesco("padre");		 
		 ben.setEdad(19);
		 ben.setPorcentaje(100);
		

		 benList.add(ben);

		 cli.setBeneficiarios(benList);
		 String respuestaBen=perDao.insertBeneficiario(benList);
		 System.out.println("Respuesta Beneficiario"+respuestaBen);
		
		//refencias
		List<Referencia> refList = new ArrayList<Referencia>();
		Referencia ref = new Referencia();
		Persona perRef = new Persona();
		perRef.setNombres("Ref Nombre 1");
		perRef.setApellidos("Ref Ape 1");
		perRef.setNumeroDocumento("9876543210");
		Persona respuestaPerRef = perDao.insertPersona(perRef);
		ref.setPersona(respuestaPerRef);
		ref.setCliente(persona.getClientes().get(0));
		refList.add(ref);
		cli.setReferencias(refList);
		String respuestaRef = perDao.insertReferencias(refList);
		System.out.println("Respuesta referencia: " + respuestaRef);
		
		
		
		
		
		
		
		
		
	} 
}