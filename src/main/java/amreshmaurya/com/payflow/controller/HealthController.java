package amreshmaurya.com.payflow.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/health")
public class HealthController {
    
    @GetMapping("/check")
   public String checkHealth() {
        return "Application is running!";
    }
}
