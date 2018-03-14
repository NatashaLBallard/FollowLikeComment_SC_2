package com.followlikecomment_sc_2.demo;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import sun.net.www.protocol.http.AuthenticationInfo;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    MessageRepository messageRepository;


    @Autowired
    UserRepository userRepository;


    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String showIndex(Model model){
        model.addAttribute("messages",messageRepository.findAllByFoundContainingIgnoreCase("No"));
        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/add")
    public String addMyMessageForm(Model model){
        model.addAttribute("message",new Message());
        return"/add";
    }



    @PostMapping("/process")
    public String saveMyMessage(@Valid @ModelAttribute("message") Message message,BindingResult result, Authentication auth)
    {
        if (result.hasErrors()) {
            return "add";
        }

        User currentUser = userRepository.findUserByUsername(auth.getName());
        currentUser.getMyMessages().toString();
        message.setUsers(currentUser);
        message.setSavedUsername(auth.getName());

        messageRepository.save(message);

        return "redirect:/";

    }


    @PostMapping("/add")
    public String processMessage(@Valid @ModelAttribute("message") Message message,BindingResult result, Authentication auth){

        System.out.println(result);
        if (result.hasErrors()) {
            return "add";
        }
        else {
            return "redirect:/list";

        }
    }




    @RequestMapping("showuserlist")
    public String showUserList(Model model, Authentication auth)
    {
        if(auth!=null)
        {
            User thisUser = userRepository.findUserByUsername(auth.getName());
            if(thisUser!=null){
                model.addAttribute("usermessages", thisUser.getMyMessages());
                System.out.println(auth.getName() + "authorities:" +auth.getAuthorities().toString());
                model.addAttribute("currentuser", userRepository.findUserByUsername(auth.getName()));
            }
        }

        else
        {
            if(auth!=null)
                model.addAttribute("inMemory",true);
            else
                model.addAttribute("inMemory",false);
        }

        return "index";
    }




    @GetMapping("/listitems")
    public @ResponseBody String listMessages(Authentication auth)
    {
        return userRepository.findUserByUsername(auth.getName()).getMyMessages().toString();
    }








    @RequestMapping("/list")
    public String listMessages(Model model){
        model.addAttribute("messages",messageRepository.findAllByFoundContainingIgnoreCase("No"));
        return"list";
    }


    @RequestMapping("/listfound")
    public String listOfFoundMessages(Model model){
        model.addAttribute("messages",messageRepository.findAllByFoundContainingIgnoreCase("Yes"));
        return"listfound";
    }

    @RequestMapping("/currentlist")
    public String currentListings( Model model){
        model.addAttribute("messages",messageRepository.findAll());
        return"currentlist";
    }


    @RequestMapping("/adminlist/{id}")
    public String allListings(@PathVariable("id") long id,Model model){
        model.addAttribute("messages",messageRepository.findAll());
        model.addAttribute("users",userRepository.findOne(id));
        return"adminlist";
    }


    @RequestMapping("/detail/{id}")
    public String showDetail(@PathVariable("id")long id,Model model, Authentication auth){
        model.addAttribute("message",messageRepository.findOne(id));

        User currentUser = userRepository.findUserByUsername(auth.getName());
        User savedUsername = userRepository.findUserByUsername(auth.getName());
        return "showitemdetails";
    }



    @RequestMapping("/update/{id}")
    public String updateDetail(@PathVariable("id")long id,Model model){
        model.addAttribute("message",messageRepository.findOne(id));
        return "add";
    }




    @RequestMapping("/found/{id}")
    public String foundMessage(@PathVariable("id") long id,Model model){
        model.addAttribute("message",messageRepository.findOne(id));

        Message message=messageRepository.findOne(id);

        message.setFound("Yes");

        model.addAttribute("aMessage", messageRepository.findOne(id));
        messageRepository.save(message);
        return "redirect:/list";
    }
    @RequestMapping("/lost/{id}")
    public String lostMessage(@PathVariable("id") long id,Model model){
        model.addAttribute("message",messageRepository.findOne(id));

        Message message=messageRepository.findOne(id);

        message.setFound("No");

        model.addAttribute("aMessage", messageRepository.findOne(id));
        messageRepository.save(message);
        return "redirect:/list";
    }

    //For user registration
    @RequestMapping(value="/register",method=RequestMethod.GET)
    public String showRegistrationPage(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }


    @RequestMapping(value="/register",method= RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("User") User user, BindingResult result, Model model){
        model.addAttribute("user",user);
        if(result.hasErrors()){
            return "registration";
        }else{
            userService.saveUser(user);
            model.addAttribute("message","User Account Successfully Created");
        }
        return "index";
    }



    @GetMapping ("/search")
    public String getSearch(){
        return "search";
    }

    @PostMapping("/search")
    public String showSearchByUsernameResults(HttpServletRequest request, Model model){
        String searchString = request.getParameter("search");
        model.addAttribute("search",searchString);
        model.addAttribute("messages", messageRepository.findAllBySavedUsernameContainingIgnoreCase(searchString));
        return "searchbyusername";
    }




    @RequestMapping("/viewcurrentuseritems")
    public String showMessagesByCurrentUserResults(Model model, Authentication authentication){
        model.addAttribute("messages",messageRepository.findAllBySavedUsernameIs(authentication.getName()));
        return "viewcurrentuseritems";
    }



    @RequestMapping("/viewallusers")
    public String showAllUsers(Model model){
        model.addAttribute("users",userRepository.findAll());
        return "viewallusers";
    }



}
