package ekotermo.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class HelloController {

    @RequestMapping('/hello')
    public String firstPage() {
        return "Hello World";
    }
}
