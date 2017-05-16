package fi.haagahelia.course.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.domain.AreaRepository;
import fi.haagahelia.course.domain.Gig;
import fi.haagahelia.course.domain.GigRepository;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;
import fi.haagahelia.course.domain.ArtistRepository;
import fi.haagahelia.course.domain.VenueRepository;

@Controller
public class GigController {
	@Autowired
	private GigRepository grepository;
	
	@Autowired
	private ArtistRepository arepository;
	
	@Autowired
	private VenueRepository vrepository;
	
	@Autowired 
	private AreaRepository rrepository;
	
	@Autowired
	private UserRepository urepository;
	

	//Login
	 @RequestMapping(value="/login")
		public String login() {
			return "login";
		}    
	 	 
	//Show all gigs
	@RequestMapping(value = "/allgigs")
	public String gigList(Model model){
		model.addAttribute("gigs", grepository.findAll());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails loggedInUser = (UserDetails) auth.getPrincipal();
        User user = urepository.findByUsername(loggedInUser.getUsername());
        
        model.addAttribute("user", urepository.findByUsername(user.getUsername()));
        return "allgigs";
	}
	
	 // RESTful service to get all gigs
    @RequestMapping(value="/gigs", method = RequestMethod.GET)
    public @ResponseBody List<Gig> gigListRest() {	
        return (List<Gig>) grepository.findAll();
    }    
    
	 // RESTful service to get gig title by id
    @RequestMapping(value="{/gig/{id}}", method = RequestMethod.GET)
    public @ResponseBody Gig findGigRest(@PathVariable("id") Long gigId) {	
    	return grepository.findOne(gigId);
    } 
    
	//Show booked gigs
	@RequestMapping(value="/bookedgigs")
		public String bookingList(Model model){
		model.addAttribute("users", urepository.findAll());
			return "bookedgigs";
	}
	
	//Book gigs
    @RequestMapping(value = "booking/{gid}", method = RequestMethod.GET)
    public String bookGig(@PathVariable("gid") Long gigId, Model model){
        
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails loggedinUser = (UserDetails) auth.getPrincipal();
        System.out.println(loggedinUser.getUsername());
        
        Gig gig=grepository.findOne(gigId);
        User user=urepository.findByUsername(loggedinUser.getUsername());
        
		if (user != null) {
			if (!user.hasGig(gig)) {
				user.getGigs().add(gig);
			}
			urepository.save(user);
			return "redirect:/bookedgigs";
		}
		return "redirect:/allgigs";
    
    }
    
  /*Cancel booking: both
    @RequestMapping(value = "/cancel/{gid}", method = RequestMethod.DELETE)
    public String cancelBooking(@PathVariable("id") Long gigId) {
       /* Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails loggedInUser = (UserDetails) auth.getPrincipal();
        User user = urepository.findByUsername(loggedInUser.getUsername());
        if(user.getRole().equals("ADMIN")){
            grepository.delete(gigId);
        }
        return "redirect:../allgigs";
    }
*/
        
    //Delete gigs: admin only
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBooking(@PathVariable("id") Long gigId) {
       /* Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails loggedInUser = (UserDetails) auth.getPrincipal();
        User user = urepository.findByUsername(loggedInUser.getUsername());
        if(user.getRole().equals("ADMIN")){*/
            grepository.delete(gigId);
        //}
        return "redirect:../allgigs";
    }
    
    //Restfull service to delete gigs
    @RequestMapping(value="/delete/{id}", method=RequestMethod.DELETE)
    public @ResponseBody void delete(@PathVariable("id") Long gigId){
    	grepository.delete(gigId);
    }	

	//Add gigs: admin only
	 @RequestMapping(value = "/add")
	    public String addGig(Model model){
	    	model.addAttribute("gig", new Gig());
	    	model.addAttribute("artists", arepository.findAll());
	    	model.addAttribute("venues", vrepository.findAll());
	    	model.addAttribute("areas", rrepository.findAll());
	        return "addgig";
	    }     
	    
	 //Save gigs: admin only
	    @RequestMapping(value = "save", method = RequestMethod.POST)
	    public String save(Gig gig){
	        grepository.save(gig);
	        return "redirect:allgigs";
	    }    

	 //Edit gigs: admin only
	    @RequestMapping(value = "/edit/{id}")
	    public String editStudent(@PathVariable("id") Long gigId, Model model){
	    	model.addAttribute("gig", grepository.findOne(gigId));
	    	model.addAttribute("artists", arepository.findAll());
	    	model.addAttribute("venues", vrepository.findAll());
	    	model.addAttribute("areas", rrepository.findAll());
	        return "editgig";
	    }	   
	
}

