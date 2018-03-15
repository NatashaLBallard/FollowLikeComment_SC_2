package com.followlikecomment_sc_2.demo;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    MessageRepository messageRepository;


    @Autowired
    UserRepository userRepository;

    @Autowired
    FollowListRepository followListRepository;

    @Autowired
    LikesRepository likesRepository;

//    @Autowired
//    CommentRepository commentRepository;

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



    //***************** Start of USER POSTS MESSAGE *****************

    @GetMapping("/add")
    public String addMyMessageForm(Model model){
        model.addAttribute("message",new Message());
        return"/add";
    }

    @PostMapping("/add")
    public String processMessage(@Valid @ModelAttribute("message") Message message,BindingResult result, Authentication auth)
    {
        System.out.println(result);
        if (result.hasErrors()) {
            return "add";
        }
        else {
            return "redirect:/list";
        }
    }

    @PostMapping("/process")
    public String saveMyMessage(@Valid @ModelAttribute("message") Message message,BindingResult result, Authentication auth)
    {
        if (result.hasErrors()) {
            return "add";
        }
        User currentUser = userRepository.findUserByUsername(auth.getName());
        currentUser.getMyMessages().toString();
        message.setUser(currentUser);
        message.setSavedUsername(auth.getName());
        messageRepository.save(message);
        return "redirect:/";
    }

    //***************** End of USER POSTS MESSAGE *****************

    //***************** Start of USER POSTS COMMENT *****************

//    @RequestMapping("/comment/{id}")
//    public String addComment(@PathVariable("id")long id,Model model){
//        model.addAttribute("message",messageRepository.findOne(id));
//        return "addcomment";
//    }
//
//
//    @GetMapping("/addcomment")
//    public String addMyCommentForm(Model model){
//        model.addAttribute("comment",new Comment());
//        return "addcomment";
//    }
//
//    @PostMapping("/addcomment")
//    public String processComment(@Valid @ModelAttribute("comment") Comment comment,BindingResult result, Authentication auth)
//    {
//        System.out.println(result);
//        if (result.hasErrors()) {
//            return "addcomment";
//        }
//        else {
//            return "redirect:/showitemdetails";
//        }
//    }
//
//    @PostMapping("/processcomment")
//    public String saveMyComment(@Valid @ModelAttribute("comment") Comment comment,Long id, BindingResult result, Authentication auth)
//    {
//        if (result.hasErrors()) {
//            return "add";
//        }
//
//
//        Message commentformessage = messageRepository.findMessageById(id);
//        commentformessage.getCommentsformessage().toString();
//        String comdescript = comment.getCommentDescription();
//        comment.setCommentDescription(comdescript);
//
////        Message commentformessage = messageRepository.findMessageById(id);
////        commentformessage.getCommentsformessage().toString();
////        messageComment.setMessageComment(commentformessage);
////        messageComment.setCommentDescription();
//        commentRepository.save(comment);
//
//
////        User currentUser = userRepository.findUserByUsername(auth.getName());
////        currentUser.getMyMessages().toString();
////        comment.setUsers(currentUser);
////        comment.setSavedUsername(auth.getName());
////        messageRepository.save(message);
//        return "redirect:/";
//    }

    //***************** End of USER POSTS COMMENT *****************



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


    @RequestMapping("/follow")
    public String listOfFollowings(Model model){
        model.addAttribute("users",userRepository.findAllByFollowContainingIgnoreCase("Yes"));
//        model.addAttribute("aFollow",userRepository.findAllByFollowContainingIgnoreCase("Yes"));
        System.out.println();
//        return "redirect:/viewallusers";
        return "followinglist";
    }

    @RequestMapping("/unfollow")
    public String listOfUnfollowings(Model model){
        model.addAttribute("users",userRepository.findAllByFollowContainingIgnoreCase("No"));
        return "redirect:/viewallusers";
    }


    @RequestMapping("/follow/{id}")
    public String followUser(@PathVariable("id") long id,Model model){
        model.addAttribute("user",userRepository.findOne(id));

        User user =userRepository.findOne(id);

        user.setFollow("Yes");

        model.addAttribute("aFollow", userRepository.findOne(id));
        userRepository.save(user);




//       model.addAttribute("aFollow", followListRepository.findByTheFollowerId(theFollowerId));

//        user.setFollow("Yes");
//
//        model.addAttribute("aFollow", userRepository.findOne(id));
//        userRepository.save(user);


        return "redirect:/viewallusers";
    }
    @RequestMapping("/unfollow/{id}")
    public String unfollowUser(@PathVariable("id") long id,Model model){
        model.addAttribute("user",userRepository.findOne(id));

        User user =userRepository.findOne(id);

        user.setFollow("No");

        model.addAttribute("aFollow", userRepository.findOne(id));
        userRepository.save(user);
        return "redirect:/viewallusers";
    }

//
//    @GetMapping("/listitems")
//    public @ResponseBody String listMessages(Authentication auth)
//    {
//        return userRepository.findUserByUsername(auth.getName()).getMyMessages().toString();
//    }








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







    @RequestMapping("/streamlist")
    public String streamOfMessages(Model model){

        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
        model.addAttribute("messages",messageRepository.findAll(sort));
        return"streamlist";
    }


    @RequestMapping("/likethispost/{id}")
    public String likeThisPost(Model model, @PathVariable("id") String theMessageLike, Authentication auth){
        Message likeMessage = messageRepository.findOne(new Long(theMessageLike));
        likeMessage.addToLikeCount();

//        likeMessage.addToUsersWhoLikedMessage(auth.getName());
//        String usersWhoLikedMessage = "";
//        System.out.println(usersWhoLikedMessage);

        messageRepository.save(likeMessage);

//        User currentUser = userRepository.findUserByUsername(auth.getName());
//        currentUser.getMyMessages().toString();
//        message.setUsers(currentUser);
//        message.setSavedUsername(auth.getName());
//        messageRepository.save(message);


//        Message userWhoLikesMessage = messageRepository.findUserBySavedUsername(savedUsername);
//        userWhoLikesMessage.getMyLikes().toString();
//        likeMessage.setSavedUsernameWhoLiked(auth.getName());
//        likesRepository.save(userWhoLikesMessage);
        return "redirect:/streamlist";
    }

    @RequestMapping("/unlikethispost/{id}")
    public String unlikeThisPost(Model model, @PathVariable("id") String theMessageLike){
        Message likeMessage = messageRepository.findOne(new Long(theMessageLike));
        likeMessage.minusFromLikeCount();
        messageRepository.save(likeMessage);
        return "redirect:/streamlist";
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
