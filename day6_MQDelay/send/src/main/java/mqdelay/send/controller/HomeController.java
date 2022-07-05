package mqdelay.send.controller;
import mqdelay.send.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private SendService sendService;
    //初始化并发送消息
    @RequestMapping("/send")
    public String send() throws Exception {
        //得到订单编号:
        for(int i=1;i<=9;i++) sendService.SendMs(i);
        for(int i=11;i<=13;i++) sendService.SendMs(i);
        return "success";
    }
}
