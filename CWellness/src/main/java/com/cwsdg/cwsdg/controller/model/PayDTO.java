package com.cwsdg.cwsdg.controller.model;


import com.cwsdg.cwsdg.controller.model.enums.PayMode;
import lombok.Data;

import java.util.Date;

@Data
public class PayDTO {

    private Date initPayment;
    private Date endDate;
    private PayMode payment;
    private long amount;


}
