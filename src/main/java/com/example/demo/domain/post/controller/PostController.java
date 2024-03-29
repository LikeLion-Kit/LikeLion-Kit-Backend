package com.example.demo.domain.post.controller;


import com.example.demo.domain.auth.domain.UserPrincipal;
import com.example.demo.domain.post.domain.page.PageSort;
import com.example.demo.domain.post.domain.request.PostRequest;
import com.example.demo.domain.post.domain.response.PostInfoResponse;
import com.example.demo.domain.post.domain.response.PostPageResponse;
import com.example.demo.domain.post.service.PostService;
import com.example.demo.domain.user.domain.vo.Track;
import com.example.demo.global.base.exception.ErrorCode;
import com.example.demo.global.base.exception.ServiceException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping("/save")
    public ResponseEntity<PostInfoResponse> postSave(
            @AuthenticationPrincipal UserPrincipal user,
            @ModelAttribute @Valid PostRequest postRequest) throws IOException {
        CheckAuthentication(user);

            return ResponseEntity.ok(postService.postSave(postRequest, user.getId()));
    }

    @PostMapping("/update/{postId}")
    public ResponseEntity<PostInfoResponse> postUpdate(@AuthenticationPrincipal UserPrincipal user,
                                                       @ModelAttribute @Valid PostRequest postRequest,
                                                       @PathVariable Long postId) throws IOException {
        CheckAuthentication(user);
        return ResponseEntity.ok(postService.postUpdate(postRequest,user.getName(),postId));
    }
    @GetMapping("/search/{postId}")
    public ResponseEntity<PostInfoResponse> postSearch(@AuthenticationPrincipal UserPrincipal user,
                                                       @PathVariable Long postId) throws IOException {
        CheckAuthentication(user);
        return ResponseEntity.ok(postService.findById(postId,user.getName()));
    }

    @PatchMapping("/delete/{postId}")
    public ResponseEntity postDelete(@AuthenticationPrincipal UserPrincipal user,@PathVariable Long postId) {
        CheckAuthentication(user);
        postService.postRemove(postId, user.getUsername());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<PostPageResponse> findPageList(
                                                         @RequestParam(defaultValue = "1", required = false) int page,
                                                         @RequestParam(required = true) Track track,
                                                         @RequestParam(defaultValue = "DESC", required = false ) PageSort pageSort) {

        return ResponseEntity.ok(postService.findPageList(page,track,pageSort));
    }

    private void CheckAuthentication(UserPrincipal user) {
        if(user == null){
            throw new ServiceException(ErrorCode.NEED_AUTHORIZED);
        }
    }

}
