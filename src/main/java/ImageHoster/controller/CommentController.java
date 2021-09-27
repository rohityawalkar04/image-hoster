package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/image/{id}/{title}/comments", method = RequestMethod.POST)
    public String create(@PathVariable("id") Integer id, @PathVariable("title") String title,  @RequestParam("comment") String text, Comment comment, HttpSession session){
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImageById(id);
        comment.setUser(user);
        comment.setImage(image);
        comment.setCreatedDate(LocalDate.now());
        comment.setText(text);
        commentService.create(comment);
        return "redirect:/images/" + id + "/" + title;
    }
}
