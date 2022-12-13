package com.obshaga.zapivkom.Controller;

import com.obshaga.zapivkom.Entity.CommentEntity;
import com.obshaga.zapivkom.Entity.Meeting;
import com.obshaga.zapivkom.Entity.UsersEntity;
import com.obshaga.zapivkom.Models.FriendsMeeting;
import com.obshaga.zapivkom.Models.UserProfileInfo;
import com.obshaga.zapivkom.Repo.CommentRepository;
import com.obshaga.zapivkom.Repo.MeetingRepository;
import com.obshaga.zapivkom.Repo.SignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Controller
public class Profile {

    @Value("${upload.path}")
    private String uploadPath;

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SignRepository signRepository;

    @Autowired
    private CommentRepository commentRepository;

    @PreAuthorize("hasAuthority('developers:read')")
    @GetMapping("/profile")
    public String profilePage(@AuthenticationPrincipal User user, Model model){
        Optional<UsersEntity> usersEntity = signRepository.findByUsername(user.getUsername());
        UsersEntity userE = usersEntity.get();
        List<CommentEntity> commentEntityList = commentRepository.findByUsername(userE.getId());
        UserProfileInfo userProfileInfo = new UserProfileInfo(userE.getUsername(), userE.getData(), userE.getRank(),
                (long)(commentEntityList.size()), userE.getFilename());
        if(user!=null) {
            model.addAttribute("user", "pall");
        }else{
            model.addAttribute("user", null);
        }

//        List<FriendsMeeting>list = new ArrayList();
//        for(int i = 0; i < userE.getUsersEntityList().size(); i++){
//            for(int j = 0; j < meetingRepository.findAllUserId().size(); j++){
//                FriendsMeeting friendsMeeting;
//                if(userE.getUsersEntityList().get(i).getId()==meetingRepository.findAllUserId().get(j)){
//                    friendsMeeting = new FriendsMeeting(userE.getUsersEntityList().get(i).getUsername(),
//                            meetingRepository.findAll().get(j).getDate(), meetingRepository.findAll().get(j).getPlace(),
//                            meetingRepository.findAll().get(j).getMessage());
//                    list.add(friendsMeeting);
//                }
//            }
//        }
        model.addAttribute("userProfileInfo", userProfileInfo);
//        model.addAttribute("plan", list);
        model.addAttribute("friends", userE.getUsersEntityList());

        return "profile";
    }

    @PostMapping("/profile")
    public String profilePage(@AuthenticationPrincipal User user, @RequestParam(required = false) String password,
                              @RequestParam(required = false) String usersFriend,
                              @RequestParam(required = false) MultipartFile avatar) throws IOException {
        Optional<UsersEntity> usersEntity = signRepository.findByUsername(user.getUsername());
        UsersEntity userE = usersEntity.get();
        if (avatar != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + avatar.getOriginalFilename();

            avatar.transferTo(new File(uploadDir + "/" + resultFileName));

            userE.setFilename(resultFileName);
        }
        if(password != null) {
            userE.setPassword(passwordEncoder().encode(password));
        }
        if(usersFriend != null) {
            Optional<UsersEntity> friend = signRepository.findByUsername(usersFriend);
            UsersEntity friendE = friend.get();
            System.out.println(usersFriend);
            userE.setUser(friendE);
            userE.addUserToFriend(userE);
            signRepository.save(userE);
        }
        return "redirect:/";
    }
}
