package com.cwsdg.cwsdg.controller;


import com.cwsdg.cwsdg.controller.model.PayDTO;
import com.cwsdg.cwsdg.controller.model.PayUserDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/gym/pay")
public class PayController {



    @PostMapping
    public void registerPay(@RequestBody PayUserDTO payUserDTO){
        log.debug(payUserDTO);
    }

}
