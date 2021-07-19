package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello"; //resources에서 html 파일에 반환
    }
    //2. HTML로 데이터 전송해서 가공하도록
    @GetMapping("hello-mvc") //해당 주소로 들어오면
    public String helloMvc(@RequestParam(name = "name") String name, Model model) { //param에 get방식으로 name으로 들어온 값을
        model.addAttribute("name", name); // model에 key값이 name인 아이랑 mapping
        return "hello-template"; //static 폴더 밑에서 hello-template html 파일 찾아서 model 전달달
   }


    //3. API (문자 전송)
    //http://localhost:8080/hello-string?name=aaa!!!
    //aaa!!!
    @GetMapping("hello-string")
    @ResponseBody //http에서 body부에 return 데이터를 직접 넣어주겠다.
    public String helloString (@RequestParam("name") String name){
        return "hello" +name;
    }


    //3-1 API (데이터 전송)
    //http://localhost:8080/hello-api?name=aaa!!!
    // return {"name":"aaa!!!"} (json방식으로 return)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // ctrl + shift + enter 자동완성 마무리
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        // getter, setter - property 접근방식
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    //alt + insert : generate => getter, setter

}