package com.hotdog.petcam.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hotdog.petcam.security.Auth;
import com.hotdog.petcam.security.AuthUser;
import com.hotdog.petcam.service.BlogService;
import com.hotdog.petcam.service.PostService;
import com.hotdog.petcam.vo.PostVo;
import com.hotdog.petcam.vo.UserVo;

@Controller
@RequestMapping("/diary")
public class PostController {

		@Autowired
		private PostService postService;
		
		@Autowired
		private BlogService blogService;
		
		@Auth
		@RequestMapping("/{nickname}/postlist")
		public String diary(@PathVariable String nickname, Model model){
			
			Map<String, Object> map = blogService.index(nickname);
			model.addAttribute("map", map);
			
			System.out.println(map);
			return "blog/diary";
		}
		
		/*@Auth
		@RequestMapping(value = "/{id}/admin/write", method = RequestMethod.POST)
		public String insert(@AuthUser Jusers authUser, @ModelAttribute Post post) {
			String id = authUser.getId();
			postService.insert(post);
			return "redirect:/" + id;
		}
		*/
		@Auth
		@RequestMapping("/{nickname}/write")
		public String main(@PathVariable String nickname, @ModelAttribute PostVo postVo, Model model, @AuthUser UserVo authUser) {
			
			return "blog/write";
		}
		
		@Auth
		@RequestMapping(value="/{nicknaem}/insert",  method = RequestMethod.POST)
		public String insert(@ModelAttribute PostVo postVo, @AuthUser UserVo authUser){
			String nickname = authUser.getNickname();
			postService.insert(postVo);
			return "redirect:/blog/" + nickname;
		}
		
		/*@ResponseBody
		@RequestMapping("{nicknaem}/api/list")
		public JSONResult list(@RequestParam(value="p", required=true, defaultValue="1")Integer page){
			List<PostVo> list = postService.getList(page);
			return JSONResult.success(list);
		}
		*/
		
	
}
