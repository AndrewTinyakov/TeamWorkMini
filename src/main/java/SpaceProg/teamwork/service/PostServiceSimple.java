package SpaceProg.teamwork.service;

import SpaceProg.teamwork.dao.PostDao;
import SpaceProg.teamwork.exeption.notFoundExeption.UserNotFoundException;
import SpaceProg.teamwork.model.Post;
import SpaceProg.teamwork.model.User;
import SpaceProg.teamwork.model.page.PostPage;
import SpaceProg.teamwork.payload.request.createRequest.CreatePostRequest;
import SpaceProg.teamwork.payload.request.upgrateRequest.UpdatePostRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class PostServiceSimple implements PostService {
    private final PostDao postDao;
    private final UserService userService;

    public PostServiceSimple(PostDao postDao, UserService userService) {
        this.postDao = postDao;
        this.userService = userService;
    }

    @Override
    public Post findById(Long id) {
        return postDao.findById(id).orElse(null);
    }

    @Override
    public Post createPost(CreatePostRequest newPost) {
        Post post;

        post = new Post(newPost, userService.getCurrentUser());

        savePost(post);
        return post;
    }

    public void savePost(Post post) {
        postDao.save(post);
    }

    @Override
    public Post updatePost(UpdatePostRequest newPost, Long id) {
        Post post = this.findById(id);
        post.setHeader(newPost.getHeader());
        post.setText(newPost.getText());
        post.setSendingTime(newPost.getSendingTime());

        post.setFixed(true);
        post.setSendingTime(new Date());
        return post;
    }

    @Override
    public void deleteById(Long id) throws Exception {
        Post post = findById(id);
        if (post.getUser().equals(userService.getCurrentUser())) {
            postDao.deleteById(id);
        } else {
            throw new Exception("У вас нет доступа к ресурсу");
        }
    }


    public PostPage findFeedPosts(Pageable pageable) {

        //get current user posts
        User currentUser = userService.getCurrentUser();
        Set<Post> currentUserPosts = currentUser.getPosts();

        //get colleagues posts
        Set<User> colleaguesSet1 = currentUser.getColleaguesFirst();
        List<User> colleaguesList1 = new ArrayList<>(colleaguesSet1);
        Set<Post> colleaguesPosts1 = new HashSet<>();
        for (int i = 0; i < colleaguesSet1.size(); i++) {
            colleaguesPosts1.addAll(colleaguesList1.get(i).getPosts());
        }

        Set<User> colleaguesSet2 = currentUser.getColleaguesSecond();
        List<User> colleaguesList2 = new ArrayList<>(colleaguesSet2);
        Set<Post> colleaguesPosts2 = new HashSet<>();
        for (int i = 0; i < colleaguesSet2.size(); i++) {
            colleaguesPosts2.addAll(colleaguesList2.get(i).getPosts());
        }

        //feed

        List<Post> feed = new ArrayList<>();
        feed.addAll(currentUserPosts);
        feed.addAll(colleaguesPosts1);
        feed.addAll(colleaguesPosts2);

        feed.removeIf(post -> post.getSendingTime()
                .before(new Date(new Date().getTime() - (24 * 3600000))));

        Page<Post> page = new PageImpl<>(feed);

        return new PostPage(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

    @Override
    public PostPage findUsersPost(Long id, Pageable pageable) throws UserNotFoundException {
//        User user = userService.findById(id);
//
//        Set<Post> postSet = user.getPosts();
//        List<Post> postList = new ArrayList<>(postSet);
//        Page<Post> page = new PageImpl<>(postList);
        Page<Post> page = postDao.findUserPostsPage(pageable, id);
        return new PostPage(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }

}
