package br.com.fiap.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.fiap.dao.HotelDAO;
import br.com.fiap.model.Hotel;

@Named
@RequestScoped
public class HotelBean {
	
	private Hotel hotel = new Hotel();

	public void save() {
		new HotelDAO().save(this.hotel);
		this.hotel = new Hotel();
		FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage("Hotel cadastrado com sucesso"));
	}
	
	public List<Hotel> getHotels(){
		return new HotelDAO().getAll();
	}
	
	public void executar() {
		System.out.println("acionando o bean");
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	


}