package com.redcatdev86.openpdfdemo.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonBean implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

}
