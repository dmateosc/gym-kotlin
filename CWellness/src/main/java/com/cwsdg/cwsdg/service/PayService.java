package com.cwsdg.cwsdg.service;


import com.cwsdg.cwsdg.controller.model.PayUserDTO;
import com.cwsdg.cwsdg.controller.model.UserDTO;
import com.cwsdg.cwsdg.controller.model.enums.PayMode;
import com.cwsdg.cwsdg.repository.PayRepository;
import com.cwsdg.cwsdg.repository.UserRepository;
import com.cwsdg.cwsdg.repository.entity.Pay;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PayService {

    private UserRepository userRepository;
    private PayRepository payRepository;
    public void registerPay(PayUserDTO payUserDTO){

        Optional<Long> userId = Optional.ofNullable(userRepository.findByDni(payUserDTO.getUserDTO().getDni()).get().getUserId());

        userId.ifPresent(aLong -> {
            String type = payUserDTO.getPayDTO().getPayment().getType();
            Date initDate = payUserDTO.getPayDTO().getInitPayment();
            Date enDate = new Date();
            if("MONTHLY".equalsIgnoreCase(type)){
                Calendar c = Calendar.getInstance();
                c.setTime(initDate);
                c.add(Calendar.MONTH, 3);

                enDate.setTime(c.getTime().getTime());
            }

            Pay pay = new Pay();
            pay.setInitDate(payUserDTO.getPayDTO().getInitPayment());
            pay.setEndDate(enDate);

            payRepository.save(pay);


        });




    }


}
