package controller;

import domain.Wishlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import service.WishlistService;

import java.util.List;

@Controller
public class WishlistController {
    private final WishlistService wishlistService;

    @Autowired
    public WishlistController(WishlistService wishlistService){
        this.wishlistService = wishlistService;
    }

    // 위시리스트 목록을 보여주는 메소드
    @GetMapping("/wishlist")
    public String list(Model model){
        List<Wishlist> wishlists = wishlistService.listWishlist();
        model.addAttribute("wishlists", wishlists);
        return "wishlist/list";
    }

    // 위시리스트 등록 폼을 보여주는 메소드
    @GetMapping("/wishlist/upload")
    public String uploadForm(Model model){
        model.addAttribute("wishlistForm", new WishlistForm());
        return "wishlist/uploadForm";
    }

    // 위시리스트를 등록하는 메소드
    @PostMapping("/wishlist/upload")
    public String upload(@ModelAttribute WishlistForm wishlistForm){
        Wishlist wishlist = new Wishlist();
        wishlist.setTitle(wishlistForm.getTitle());
        wishlist.setAuthor(wishlistForm.getPublisher());
        wishlist.setPublisher(wishlistForm.getPublisher());
        wishlist.setGenre(wishlistForm.getGenre());;
        wishlistService.upload(wishlist);
        return "redirect:/wishlist";
    }

    // 위시리스트를 삭제하는 메소드
    @GetMapping("/wishlist/delete/{id}")
    public String delete(@PathVariable Long id){
        wishlistService.delete(id);
        return "redirect:/wishlist";
    }

    // 위시리스트 삭제 확인 화면을 보여주는 메소드 (옵션)
    @GetMapping("/wishlist/delete/confirm/{id}")
    public String deleteConfirm(@PathVariable Long id, Model model){
        model.addAttribute("wishlistId", id);
        return "wishlist/deleteConfirm";
    }
}