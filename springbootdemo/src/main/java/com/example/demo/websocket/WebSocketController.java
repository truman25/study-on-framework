package com.example.demo.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/websocketdemo")
public class WebSocketController {

    @RequestMapping("/{nickname}")
    String home(Model model, @PathVariable String nickname) {
    	model.addAttribute("nick", nickname);
        return "webSocketDemo";
    }
}
