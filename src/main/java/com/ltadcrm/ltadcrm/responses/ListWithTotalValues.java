package com.ltadcrm.ltadcrm.responses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data   
@AllArgsConstructor
public class ListWithTotalValues<T> {
    private List<T> items;
    private Double total;
}
