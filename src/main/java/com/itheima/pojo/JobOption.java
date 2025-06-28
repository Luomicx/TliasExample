/**
 * @author Wiretender
 * @version 1.0
 */
package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class JobOption {
    private List jobList;
    private List dataList;
}
