package org.calculator.materialprice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class WasherSteelRequest {
    private UUID washer_standard_id;
    private UUID steel_grade_id;
}
