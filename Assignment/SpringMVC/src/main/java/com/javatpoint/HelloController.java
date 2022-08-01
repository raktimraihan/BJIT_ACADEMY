package com.javatpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HelloController {
		@RequestMapping("/")
		public String display()
		{
			return "index";
		}
		
		@RequestMapping("/home")
		public String displayHome()
		{
			return "home";
		}
		
		@RequestMapping("/welcome")
		public String displayWelcome()
		{
			return "welcome";
		}
		
		
}