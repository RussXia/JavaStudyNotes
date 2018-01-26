package com.xzy.hibernate.validator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @author RuzzZZ
 * @since 26/01/2018 2:29 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entity {

    @NotNull(message = "年龄不能为空!")
    @Range(min = 0L, max = 100L, message = "年龄不能超过100")
    private Integer age;

    @NotBlank(message = "姓名不能为空!")
    private String name;

    @Length(min = 0, max = 4)
    public String getNameAndAge() {
        return name + age;
    }
}