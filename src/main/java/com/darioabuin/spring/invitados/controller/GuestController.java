package com.darioabuin.spring.invitados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.darioabuin.spring.invitados.entities.Guest;
import com.darioabuin.spring.invitados.repository.GuestRepository;

@Controller
@ControllerAdvice
public class GuestController {
	@Autowired 
	GuestRepository guestRepository;
	
	@GetMapping("/index")
	public String redirectToIndex() {
		return "forward:index.html";
	}
	
	@GetMapping("/newGuest")
	public String newGuest(@ModelAttribute("guest") Guest guest) {
		guestRepository.save(guest);
		return "redirect:/queryGuests";
	}
	
	@GetMapping("/queryGuests")
	public String queryGuests(Model model) {
		List<Guest> guestList = (List<Guest>) guestRepository.findAll();
		model.addAttribute("guestList", guestList);
		model.addAttribute("msg", "Guest List");
		return "guestList.html";
	}
	
	@GetMapping("/deleteGuest/{guestId}")
	public String deleteGuest(@PathVariable Integer guestId) {
		guestRepository.deleteById(guestId);
		return "redirect:/queryGuests";
	}
	
	@GetMapping("/updateGuest")
	public String updateGuest(@RequestParam("guestId") Integer guestId, @RequestParam("newGuestName") String guestName) {
		try {
			Guest guest = guestRepository.getById(guestId);
			guest.setGuestName(guestName);
			guestRepository.save(guest);
		} catch(Exception e) {
			System.out.println("Couldn't update guest");
		}
		return "redirect:/queryGuests";
	}

}
