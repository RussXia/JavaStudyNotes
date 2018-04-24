package com.xzy.test;

import lombok.Data;

import java.io.Serializable;

@Data
public class BusiOADTO implements Serializable {

    private static final long serialVersionUID = 1159712018304699650L;

    private String key;

    private Long business_id;

    private Long contract_id;

    private String user_email;

    private String company_name;

    private Integer contract_type;

    private String contract_name;

    private String parent_serial_number;

    private String party_b;

    private String amount;

    private String currency;

    private String start_date;

    private String end_date;

    private String beneficiary;

    private String opening_bank_num;

    private String opening_bank;

    private String bank_add;

    private String swift_code;

    private String email_add;
}
